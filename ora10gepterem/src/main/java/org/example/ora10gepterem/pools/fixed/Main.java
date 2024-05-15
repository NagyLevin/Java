package org.example.ora10gepterem.pools.fixed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void printArray(ArrayList<Integer> arr) {
        for (Integer e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    static ArrayList<Integer> add(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> c = new ArrayList<Integer>(Collections.nCopies(a.size(), 0));
        for(int i = 0; i < a.size(); i++) {
            c.set(i, a.get(i) + b.get(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    public static void main(String args[]) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        Random r = new Random();
        int size = 100;
        a.ensureCapacity(size);
        b.ensureCapacity(size);

        for (int i = 0; i < size; i++) {
            a.add(r.nextInt() % 500 + 500);
            b.add(r.nextInt() % 500 + 500);
        }

        long startTime = System.currentTimeMillis();
        ArrayList<Integer> c1 = add(a, b);
        System.out.println("Két tömb összeadása hagyományos módon (" + (System.currentTimeMillis() - startTime) + " ms)");
        System.out.println("Utolsó elemek ellenőrzése:" + a.get(a.size()-1) + "+" + b.get(b.size()-1) + "=" + c1.get(c1.size()-1));




        //printArray(a);
        //printArray(b);
        System.out.println("\nA rendelkezésre álló CPU-k száma: " + Runtime.getRuntime().availableProcessors());
        startTime = System.currentTimeMillis();
        ArrayList<Integer> c2 = Adder.add(a, b);
        System.out.println("Két tömb összeadása párhuzamosítással (" + (System.currentTimeMillis() - startTime) + " ms)");
        //printArray(c);
        System.out.println("Utolsó elemek ellenőrzése:" + a.get(a.size()-1) + "+" + b.get(b.size()-1) + "=" + c2.get(c2.size()-1));

    }

}
