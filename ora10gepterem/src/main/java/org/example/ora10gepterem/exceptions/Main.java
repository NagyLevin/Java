package org.example.ora10gepterem.exceptions;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService handler = Executors.newCachedThreadPool();

        try {
            Future<?> f = handler.submit(new CustomRunnable());
            //Future<Integer> f = handler.submit(new CustomCallable());
            System.out.println("Progress...");
            f.get(3, TimeUnit.SECONDS);
            System.out.println("Submit Block Finished!");
        } catch (CustomException e) {
            System.out.println("Type: Custom");
        } catch (InterruptedException e) {
            System.out.println("Type: Interrupted");
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            Throwable t = e.getCause();
            System.out.println(t.getMessage());
            System.out.println("Type: Execution");
        } catch (TimeoutException e) {
            System.out.println("Type: Timeout");
        }

        try {
            handler.execute(new CustomRunnable());
            System.out.println("Execute Block Finished!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        handler.shutdown();
    }
}
