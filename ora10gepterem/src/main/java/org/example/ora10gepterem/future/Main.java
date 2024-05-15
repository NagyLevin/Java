package future;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String args[]) {
        ArrayList<Integer> a = new ArrayList<>();
        Random r = new Random();
        int size = 100000000;
        a.ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            a.add(r.nextInt() % 50 + 50);
        }

        System.out.println("Serial summation of the Array");
        long startTime = System.currentTimeMillis();
        Integer sum = 0;
        for(Integer e : a) {
            sum += e;
        }
        System.out.println(System.currentTimeMillis() - startTime + "ms");
        System.out.println("Expected: " + sum/(double)size);


        System.out.println("\nParallel summation of the Array");
        startTime = System.currentTimeMillis();
        sum = Summing.Sum(a);
        System.out.println(System.currentTimeMillis() - startTime + "ms");
        System.out.println("Calculated: " + sum/(double)size);

    }
}
