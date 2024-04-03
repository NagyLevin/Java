public class SleepExample
{
    /**
     * Az aktuális szál (jelen esetben fő szál) altatása
     * @param count	hányszor altassuk egymás után a szálat
     * @param milli hány ezredmásodpercig altassuk a szálat
     * @throws InterruptedException
     */
    private static void sleepTest(int count, int milli) throws InterruptedException {
        System.out.println(count + " * sleep(" + milli + ")");
        long begin = System.currentTimeMillis();
        for (long i=0; i<count; i++)
        {
            Thread.sleep(milli);
        }
        long end = System.currentTimeMillis();
        System.out.println(count * milli + " ms helyett "+(end-begin)+" ms-ig tartott.");
        System.out.println();
    }
    /**
     * Az aktuális szál (jelen esetben fő szál) altatása
     * @param count	hányszor altassuk egymás után a szálat
     * @param milli hány ezredmásodpercig és
     * @param nano hány nanomásodpercig altassuk a szálat
     * @throws InterruptedException
     */
    private static void sleepTest(int count, int milli, int nano) throws InterruptedException {
        System.out.println(count + " * sleep(" + milli + "," + nano + ")");
        long begin = System.currentTimeMillis();
        for (long i=0; i<count; i++)
        {
            Thread.sleep(milli, nano);
        }
        long end = System.currentTimeMillis();
        System.out.println((count * milli + count * nano / 1e6) + " ms helyett "+(end-begin)+" ms-ig tartott.");
        System.out.println();
    }

    /**
     * Példaprogram a sleep() utasítás használatára.
     *
     * Jól látszik, hogy egy nagyon rövid sleep-hívásnak elég nagy hibája is lehet.
     */
    public static void main(String[] args)
    {
        try {
            sleepTest(1, 1000);
            sleepTest(10, 100);
            sleepTest(100, 10);
            sleepTest(1000, 1);
            sleepTest(10000, 0, 100000);
            sleepTest(10, 100, 500000);
        }catch( InterruptedException e){
            e.getMessage();
        }
    }
}