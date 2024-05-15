package org.example.ora10gepterem.concurrent;

import java.util.concurrent.*;

public class Main {

    static int iteration = 10;
    static double weight1 = 0.1;
    static double data1 = -0.2;
    static double weight2 = -0.3;
    static double data2 = 0.2;


    private static void testComputeSerially() {
        System.out.println("\nTesting serial implementation");
        long start = System.currentTimeMillis();

        double[] data = new ComputeSerially(new double[]{data1, data2}, new double[]{weight1, weight2}).calculate(iteration);
        System.out.println("Result1: " + data[0]);
        System.out.println("Result2: " + data[1]);

        long finish = System.currentTimeMillis();
        System.out.println("Finishing serial implementation (" + (finish - start) + " ms)" );
    }

    private static void testComputeWithBlockingQueue() {
        System.out.println("\nTesting BlockingQueue implementation");
        long start = System.currentTimeMillis();

        BlockingQueue<Double> communicator1to2 = new ArrayBlockingQueue<Double>(2);
        BlockingQueue<Double> communicator2to1 = new ArrayBlockingQueue<Double>(2);
        ComputeWithBlockingQueue.Context context1 = new ComputeWithBlockingQueue.Context(iteration, communicator2to1, communicator1to2);
        ComputeWithBlockingQueue.Context context2 = new ComputeWithBlockingQueue.Context(iteration, communicator1to2, communicator2to1);
        ComputeWithBlockingQueue computer1 = new ComputeWithBlockingQueue(weight1, data1, context1);
        ComputeWithBlockingQueue computer2 = new ComputeWithBlockingQueue(weight2, data2, context2);
        computer1.start();
        computer2.start();
        try {
            computer1.join();
            computer2.join();
            System.out.println("Computer1 result: " + computer1.getData());
            System.out.println("Computer2 result: " + computer2.getData());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();
        System.out.println("Finishing BlockingQueue implementation (" + (finish - start) + " ms)" );
    }

    private static void testComputeWithExchanger() {
        System.out.println("\nTesting Exchanger implementation");
        long start = System.currentTimeMillis();

        ComputeWithExchanger.Context context = new ComputeWithExchanger.Context(iteration, new Exchanger<>());
        ComputeWithExchanger computer1 = new ComputeWithExchanger(weight1, data1, context);
        ComputeWithExchanger computer2 = new ComputeWithExchanger(weight2, data2, context);
        computer1.start();
        computer2.start();
        try {
            computer1.join();
            computer2.join();
            System.out.println("Computer1 result: " + computer1.getData());
            System.out.println("Computer2 result: " + computer2.getData());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();
        System.out.println("Finishing Exchanger implementation (" + (finish - start) + " ms)" );
    }

    private static void testExecutorService () {
        System.out.println("\nTesting BlockingQueue implementation with ExecutorService");
        long start = System.currentTimeMillis();

        BlockingQueue<Double> communicator1to2 = new ArrayBlockingQueue<Double>(2);
        BlockingQueue<Double> communicator2to1 = new ArrayBlockingQueue<Double>(2);
        ComputeWithBlockingQueue.Context context1 = new ComputeWithBlockingQueue.Context(iteration, communicator2to1, communicator1to2);
        ComputeWithBlockingQueue.Context context2 = new ComputeWithBlockingQueue.Context(iteration, communicator1to2, communicator2to1);
        ComputeWithBlockingQueue computer1 = new ComputeWithBlockingQueue(weight1, data1, context1);
        ComputeWithBlockingQueue computer2 = new ComputeWithBlockingQueue(weight2, data2, context2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(computer1);
        executorService.execute(computer2);

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        System.out.println("Computer1 result: " + computer1.getData());
        System.out.println("Computer2 result: " + computer2.getData());

        long finish = System.currentTimeMillis();
        System.out.println("Finishing BlockingQueue implementation with ExecutorService (" + (finish - start) + " ms)" );
    }

    public static void main(String[] args) {
        testComputeSerially();
        testComputeWithBlockingQueue();
        testComputeWithExchanger();
        testExecutorService();
    }
}
