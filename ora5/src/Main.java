public class Main {



    public static void main(String[] args) {



        System.out.println(Thread.currentThread().getName());
        SecondThread nt = new SecondThread(); //mi fut le
        //nt.run(); //EZ NEM JÓ, igy nem jon letre uj szal
        new Thread(new SecondThread()).start(); //igy mar uj szalon fog futni //ez e tenyleges szal
     /*
        while (true){
            new Thread(new SecondThread()).start();
                }
      */


    }

}