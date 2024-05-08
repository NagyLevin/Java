package com.example.ora9.truckfactorynotifybyMarci;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Factory implements Runnable{

    private final String [] prodName = {"KinderBueno", "KinderMaxiking", "KinderChocholate"};
    int prodCount;
    final Queue<Product> products;
    Random myRandom;
    public Factory()
    {
        products = new ArrayBlockingQueue<>(100);
        myRandom = new Random();
        prodCount = 0;
    }


    public Product getProd(int id){
        System.out.println("Az " + id + ". teherautó várakozik!");
        synchronized (products){
            while (products.isEmpty())
            {
                try {
                    products.wait();
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("A " + id + ". teherautó felébredt!");
            return products.poll();
        }
    }


    Product makeProd()
    {
        long productionTime = myRandom.nextInt(1000,5000);

        try {
            Thread.sleep(productionTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Product res = new Product(prodCount,prodName[myRandom.nextInt(0,2)]);
        prodCount++;
        return res;

    }

    @Override
    public void run()
    {
        while (true)
        {
            synchronized (products){
                products.add(makeProd());
                System.out.println("A gyár legyártott egy terméket.");
               // System.out.println(Thread.currentThread().getName());
                products.notifyAll();
            }try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        }
    }
}
