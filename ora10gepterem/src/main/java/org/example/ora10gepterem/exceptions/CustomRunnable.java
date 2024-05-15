package org.example.ora10gepterem.exceptions;

public class CustomRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Callable interrupted");
        }
        throw new CustomException();
    }
}
