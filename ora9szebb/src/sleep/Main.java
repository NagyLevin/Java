package sleep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        SleepThread sleepThread = new SleepThread();
        sleepThread.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.println("Elkezdődött a futás, megállításhoz gépelje be a \"stop\" szót!");
            while (!bufferedReader.readLine().toLowerCase().trim().equals("stop")){

            }
        } catch (IOException e){
            e.printStackTrace();
        }
        sleepThread.interrupt();
    }
}
