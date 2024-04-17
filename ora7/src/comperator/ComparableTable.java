package comperator;

/**
 * Ez egy Comparable interface-t megvalósító osztály, így
 * összehasonlítható, rendezhető, stb.
 *
 * @author zelgala
 */
public class ComparableTable implements Comparable<ComparableTable> {
    private Integer numberOfLegs;

    public ComparableTable(int numberOfLegs) {
        this.setNumberOfLegs(numberOfLegs);
    }

    @Override
    public int compareTo(ComparableTable mc) {
        if (this.numberOfLegs < mc.getNumberOfLegs())
            return -1;
        else if (this.numberOfLegs == mc.getNumberOfLegs())
            return 0;
        else
            return 1;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    @Override
    public String toString() {
        return numberOfLegs.toString();
    }

}
