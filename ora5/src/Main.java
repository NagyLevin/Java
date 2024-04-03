public class Main {



    public static void main(String[] args) {

        Complex cm = new Complex();
        Thread1 T1 = new Thread1(cm);
        Thread2 T2 = new Thread2(cm);

        new Thread(T1).start();
        new Thread(T2).start();

        //nem szabad origo es tengelyen kivuli pontok

        //staticra sinkronizállni nagyon veszélyes

        while (true){

            //synchronized (cm){ //igy is lehet bevarni egymast, de haigy csinaljuk akkor minden helyen ezt kell
                double[] x = cm.get();
            //}


            if (x[0] != 0 && x[1] != 0) {
                System.out.println("Hibát találtunk: " + x[0] + " " + x[1]);
            }

        }

        //monitor
        //kritikus reszeknel meg lehet jelölni, hogy ahhoz a reszhez egyszerre csak egy szal tud hozzaferni




/*
        System.out.println(Thread.currentThread().getName());
        SecondThread nt = new SecondThread(); //mi fut le
        //nt.run(); //EZ NEM JÓ, igy nem jon letre uj szal
        new Thread(new SecondThread()).start(); //igy mar uj szalon fog futni //ez e tenyleges szal
     /*
        while (true){
            new Thread(new SecondThread()).start();
                }
      */
    /*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
*/

    /*
    jobb megoldas adat erkezesere
        public class BetterExampleForWaiting {
            public static void main(String[] args) {
                // Port emulátor, majd később megnézzük, hogyan is működik.
                // Lényege, hogy időnként kapunk adatokat rajta keresztül,
                // de általában várni kell.
                PortEmulator port = new PortEmulator(0.5f);

                while(true)
                {
                    // A sleep nem blokkolja a processzort, hanem átadja a futás lehetőségét más szálaknak
                    try {
                        Thread.sleep(200); // Ennyi időnként akarjuk megnézni, hogy jött-e adat
                    }catch( InterruptedException e){
                        e.getMessage();
                    }
                    while (!port.isEmpty())
                    {
                        // Ha jött adat, akkor amíg ki nem szedek mindent
                        System.out.println("Adat jött: "+port.get());
                    }
                }
            }
        }
        */

    }

}