public class Complex {
    protected double re;    // Valós rész
    protected double im;    // Képzetes rész
    public synchronized void set(double re, double im) { //syncronised, az olyan helyeknel ahol bajt okozhat az, ha tobb szal fer hozza egyszerre
        this.re = re;
        this.im = im;
    }
    public synchronized double[] get() {    //get setneél egyaránt //vonatos szabad a vágány példa //nézni sem szabad, mert nézés is zavarhatja
        double[] result = {re, im};
        return result;
    }
}



