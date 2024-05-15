package org.example.ora10gepterem.future;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Summing implements Callable<Integer> {
    private static ArrayList<Integer> a;
    int from;
    int to;

    Summing (int from_p, int to_p){
        from = from_p;
        to = to_p;
    }

    public static Integer Sum(ArrayList<Integer> a_p){
        a = a_p;
        int cores = Runtime.getRuntime().availableProcessors(); //processorok száma
        ArrayList<Future<Integer>> partialSums = new ArrayList<>();
        partialSums.ensureCapacity(cores);
        ExecutorService threads = Executors.newFixedThreadPool(cores); //megadott mennyisegő fixed mennyiségű corral futnak

        int blocks = a.size() / cores;
        for (int i = 0; i < cores-1; i++) {
            partialSums.add(threads.submit(new Summing(i*blocks, (i+1)*blocks)));
        }
        partialSums.add(threads.submit(new Summing((cores-1)*blocks, a.size())));

        Integer sum = 0;
        for(Future<Integer> e : partialSums){
            try {
                sum += e.get();
            } catch (InterruptedException ex){
                ex.printStackTrace();
            } catch (ExecutionException ex){
                ex.printStackTrace();
            }
        }
        threads.shutdownNow();
        return sum;
    }

    @Override
    public Integer call() {
        Integer sum = 0;
        for (int i = from; i < to; i++) {
            sum += a.get(i);
        }
        return sum;
    }
}
