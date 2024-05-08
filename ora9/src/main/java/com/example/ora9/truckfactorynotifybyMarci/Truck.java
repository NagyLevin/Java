package com.example.ora9.truckfactorynotifybyMarci;

import java.util.Random;

public class Truck implements Runnable{
    private enum CurrentState {waiting, carrying}
    CurrentState activeState;
    final Factory myFactory;
    private int id;
    public Truck(Factory factory, int id){
        activeState = CurrentState.waiting;
        myFactory = factory;
        this.id = id;
    }

    @Override
    public void run() {
        Random r = new Random();
        Product p = null;
        while (true)
        {
            switch (activeState)
            {
                case waiting -> p =myFactory.getProd(this.id);
                case carrying -> {
                    try {
                        System.out.println("Truck is carrying with id "+ id);
                        Thread.sleep(r.nextInt(500,2000));
                        activeState = CurrentState.waiting;
                        p = null;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(p!=null)
            {
                activeState = CurrentState.carrying;
            }
        }
    }
}
