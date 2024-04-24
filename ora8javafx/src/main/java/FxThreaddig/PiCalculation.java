package FxThreaddig;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Labeled;

import java.util.Random;

class PiCalculation implements EventHandler<ActionEvent> {
  private static final long ITERATIONS = 100000000;
  private static final Random random = new Random();

  private final Labeled resultLabel;

  public PiCalculation(Labeled resultLabel) {
    this.resultLabel = resultLabel;
  }

  private static double calculatePi () {
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
    return 4 * (double)in / ITERATIONS;
  }

  @Override
  public void handle(ActionEvent event) {
    resultLabel.setText("Calculating...");
    double result = calculatePi();
    resultLabel.setText(String.valueOf(result));
  }
}
