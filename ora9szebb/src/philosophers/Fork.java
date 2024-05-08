package philosophers;

public class Fork {
    private final int id;

    private static int num = 0;

    private Thread owner = null;

    {
        id = num++;
    }

    public int getID() {
        return id+1;
    }

    public synchronized boolean acquire(Thread candidate){
        if(owner == null || candidate == owner) {
            owner = candidate;
            return true;
        }
        return false;
    }

    public synchronized void release(Thread candidate) {
        if(owner == null || candidate != owner){
            throw new IllegalMonitorStateException("Request to release Fork with non-owner Philosopher.");
        }
        owner = null;
        this.notifyAll();
    }
}
