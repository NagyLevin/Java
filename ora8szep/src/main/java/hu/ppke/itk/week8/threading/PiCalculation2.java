package hu.ppke.itk.week8.threading;

import javafx.application.Platform;
import javafx.scene.control.Labeled;

import java.util.Random;

/**
 * A PiCalculation osztály egy alternatív megvalósítása.
 *
 * Ez esetben magunknak kell gondoskodnunk arról, hogy a számolás ne a
 * JavaFX Application szálon történjen, pl.: new Thread(piCalculation).start().
 *
 * Az eredmény kiíratása egy új Runnable osztályban van implementálva, amelynek
 * egy objektumával meghívjuk a Platform.runLater() metódust. Ezzel az eredmény
 * kiíratása a JavaFX Application szálon történik.
 */
public class PiCalculation2 implements Runnable {
    private static final long ITERATIONS = 100000000;
    private static final Random random = new Random();

    private final Labeled resultLabel;

    public PiCalculation2(Labeled resultLabel) {
        this.resultLabel = resultLabel;
    }

    @Override
    public void run() {
        double result = calculatePi();
        // A Platform.runLater() egy Runnable objektumot vár, amelynek run()
        // metódusát futtatja a JavaFX Application szálon.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                resultLabel.setText("Pi: " + String.valueOf(result));
            }
        });
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
