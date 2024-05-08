package join;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<JoinThread> joinThreads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            JoinThread joinThread = new JoinThread(i);
            joinThreads.add(joinThread);
            joinThread.start();
            System.out.println(i + ". szál elindítva.");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ". szál join előtt.");
            try {
                joinThreads.get(i).join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i + ". szál join után.");
        }
    }
}
