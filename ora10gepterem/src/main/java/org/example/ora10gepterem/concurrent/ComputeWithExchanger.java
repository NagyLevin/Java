package org.example.ora10gepterem.concurrent;

import java.util.concurrent.Exchanger;

public class ComputeWithExchanger extends Thread {
    private final double weight;
    private double data;

    public static class Context {
        public final int iterationNum;
        public final Exchanger<Double> exchanger;

        public Context(int iterationNum, Exchanger<Double> exchanger) {
            this.iterationNum = iterationNum;
            this.exchanger = exchanger;
        }
    }

    private final Context context;

    public ComputeWithExchanger(double weight, double data, Context context) {
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
        return context.exchanger.exchange(this.data);
    }

    public double getData() {
        return data;
    }
}
