package factory;

import java.util.LinkedList;
import java.util.Random;

public class Factory extends Thread {
    private final LinkedList<Product> products = new LinkedList<>();

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i < 11; i++){
            try {
                Thread.sleep(random.nextInt(1000) + 1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("A gyár legyártotta a " + i + ". elemet");
            synchronized (products) {
                products.add(new Product(i));
                products.notifyAll();
            }
        }
    }

    public Product getProduct(int truckId){
        synchronized (products){
            while (products.size() == 0){
                try{
                    System.out.println("Az " + truckId + ". teherautó várakozik.");
                    products.wait();
                    System.out.println("Felébredt az " + truckId + ". teherautó!");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return products.removeFirst();
        }
    }
}
