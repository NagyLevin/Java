package comperator;

public class Table {
    private Integer numberOfLegs;

    public Table(int numberOfLegs) {
        this.setNumberOfLegs(numberOfLegs);
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
