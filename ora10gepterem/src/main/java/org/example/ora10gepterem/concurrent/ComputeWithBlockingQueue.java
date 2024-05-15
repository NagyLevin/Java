package org.example.ora10gepterem.concurrent;

import java.util.concurrent.BlockingQueue;

public class ComputeWithBlockingQueue extends Thread {
    private final double weight;
    private double data;

    public static class Context {
        public final int iterationNum;
        public final BlockingQueue<Double> incomingDataQueue;
        public final BlockingQueue<Double> outgoingDataQueue;

        public Context(int iterationNum, BlockingQueue<Double> incomingDataQueue,
                       BlockingQueue<Double> outgoingDataQueue) {
            this.iterationNum = iterationNum;
            this.incomingDataQueue = incomingDataQueue;
            this.outgoingDataQueue = outgoingDataQueue;
        }
    }

    private final Context context;

    public ComputeWithBlockingQueue(double weight, double data, Context context) {
        this.weight = weight;
        this.data = data;
        this.context = context;
    }

    private void step (double otherData) {
        this.data += this.weight*otherData;
    }

    @Override
    public void run() {
        try {
            for (int iteration = 0; iteration < context.iterationNum; iteration++) {
                double otherData = exchangeData();
                step(otherData);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private double exchangeData() throws InterruptedException {
        context.outgoingDataQueue.add(this.data);
        return context.incomingDataQueue.take();
    }

    public double getData() {
        return data;
    }
}
