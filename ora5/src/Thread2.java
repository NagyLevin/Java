import java.util.Random;

public class Thread2 extends Thread {
    private Complex complex;

    public Thread2(Complex complex) {
        this.complex = complex;
    }

    @Override
    public void run() {
        System.out.println("A második szál elindult!");
        Random rand = new Random();
        while (true) {
            // Képzetes rész mindig 0
            complex.set(rand.nextInt(100), 0);
        }
    }
}