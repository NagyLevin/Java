package org.example.ora10gepterem.exceptions;

import java.util.Random;
import java.util.concurrent.Callable;

public class CustomCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Callable interrupted");
        }
        if(new Random().nextDouble() < 0.5) {
            throw new CustomException();
        }
        return 5;
    }
}
