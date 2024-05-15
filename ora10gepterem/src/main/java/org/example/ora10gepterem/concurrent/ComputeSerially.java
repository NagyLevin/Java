package org.example.ora10gepterem.concurrent;

public class ComputeSerially {
    private double[] data;
    private double[] weight;

    public ComputeSerially(double[] data, double[] weight) {
        this.data = data;
        this.weight = weight;
    }

    private void step () {
        double newData0 = data[1] * weight[0] + data[0];
        double newData1 = data[0] * weight[1] + data[1];
        data[0] = newData0;
        data[1] = newData1;
    }

    public double[] calculate (int iterationNum) {
        for (int iteration = 0; iteration < iterationNum; iteration++) {
            step();
        }
        return data;
    }

}
