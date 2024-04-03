class SecondThread implements Runnable{


    @Override
    public void run() {
        System.out.println("masik szal:");
        System.out.println(Thread.currentThread().getName());

    }
}
    