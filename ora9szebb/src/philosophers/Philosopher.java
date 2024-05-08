package philosophers;

public class Philosopher extends Thread {
    private final int id;
    private final Fork left;
    private final Fork right;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id+1;      // Az azonosítókat 0-4-ről 1-5-re módosítjuk a kiiratásnál
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        try {
            boolean haveBothFork = false;
            do {
                System.out.println(id + ". filozófus megpróbálja felvenni a bal(" + left.getID() + ") villáját");
                synchronized (left) {
                    // megszerzi a bal villa monitorát, addig vár, míg meg nem szerzi
                    while (!left.acquire(this)) {
                        System.out.println(id + ". filozófusnak nem sikerült felvenni a bal(" + left.getID() +
                                ") villáját, várakozik");
                        left.wait();
                    }
                }
                System.out.println(id + ". filozófus felvette a bal(" + left.getID() + ") villáját");
                Thread.sleep(1000);
                // megszerezte a bal villát, megpróbálja megszerezni a jobbat.
                System.out.println(id + ". filozófus megpróbálja felvenni a jobb(" + right.getID() + ") villáját");
                haveBothFork = right.acquire(this);
                if (!haveBothFork) {
                    // Ha nem sikerült megszerezni a jobb villát, akkor vissza kell
                    // lépni, hogy ne lehessen deadlock ezért elengedjük a bal villát is.
                    System.out.println(id + ". filozófusnak nem sikerült felvenni a jobb(" + right.getID() + ") villáját.");
                    Thread.sleep(1000); // várakozás, hogy minden esetben kialakuljon a livelock
                    System.out.println(id + ". filozófus leteszi a bal(" + left.getID() + ") villáját, " +
                            "hátha másnak kell és újtapróbálkozik.");
                    left.release(this);
                }
            } while (!haveBothFork);
            // sikerült megszerezni mindkét villát, ehetünk.
            System.out.println(id + ". filozófus felvette a jobb(" + right.getID() + ") villáját, nekiáll enni");
            Thread.sleep(5000);
            System.out.println(id + ". filozófus megebédelt");
            // evés után el kell engedni a vilákat.
            left.release(this);
            right.release(this);
        } catch (InterruptedException e) {
            System.err.println(id + ". filozófus szálat megszakították, felszabadítja az erőforrásokat.");
            //Ha interrupt volt, akkor nem tudjuk, hogy melyik villák vannak meg.
            //Megpróbáljuk lefoglalni, ha sikerült, akkor valószínűszeg amúgy is a miénk volt, ezért elengedjük.
            if(left.acquire(this)) left.release(this);
            if(right.acquire(this)) right.release(this);
            e.printStackTrace();
        }

    }
}
