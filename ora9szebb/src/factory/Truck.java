package factory;

public class Truck  extends Thread {
    private Factory factory;
    private int id;

    public Truck(Factory factory, int id){
        this.factory = factory;
        this.id = id;
    }

    public void run(){
        System.out.println("Az " + id + ". teherautó a gyárhoz érkezett.");
        Product product = factory.getProduct(id);
        System.out.println("Az " + id + ". teherautóba bepakolták a "+ product.getId() +". terméket.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Az " + id + ". teherautó elszállította a "+ product.getId() +". terméket.");
    }
}
