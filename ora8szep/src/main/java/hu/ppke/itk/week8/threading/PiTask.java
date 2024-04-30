package hu.ppke.itk.week8.threading;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Labeled;

import java.util.Random;

public class PiTask extends Task<Double> {

    private static final long ITERATIONS = 100000000;
    private static final Random random = new Random();

    private final Labeled resultLabel;

    public PiTask(Labeled resultLabel) {
        this.resultLabel = resultLabel;
    }

    @Override
    protected Double call() throws Exception {
        System.out.println("Task call started isJavaFxThread: " + Platform.isFxApplicationThread());
        double pi = calculatePi();
        System.out.println("Task call ended");
        return pi;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        System.out.println("Succeeded ran isJavaFxThread:" + Platform.isFxApplicationThread());
        resultLabel.setText("PI: " + getValue());
    }


    private double calculatePi() {
        // Monte Carlo szimuláció

        // azon pontok száma, amelyek egy egység sugarú körnegyeden belül esnek
        long in = 0;
        for (long i = 0; i < ITERATIONS; ++i) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * y <= 1) {
                ++in;
            }
        }
        // A körnegyed területe $\pi/4 = in/ITERATIONS$
        return 4 * (double) in / ITERATIONS;
    }
}
