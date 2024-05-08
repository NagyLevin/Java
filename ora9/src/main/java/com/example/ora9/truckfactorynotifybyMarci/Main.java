package com.example.ora9.truckfactorynotifybyMarci;

public class Main{

    final static int truckCount = 10;
    public static void main(String[] args)
    {
        Factory factory = new Factory();
        for (int i = 0; i < truckCount; i++)
        {
            Thread t = new Thread(new Truck(factory,i));
            t.start();
        }
        Thread factoryThread = new Thread(factory);
        factoryThread.start();

    }

}
