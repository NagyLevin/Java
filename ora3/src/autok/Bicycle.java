package autok;
public class Bicycle extends Vehicle {

    public Bicycle (String type, int capacity, double odometer) {
        super(type, capacity);
        this.odometer = odometer;
    }

    public Bicycle (String type) {
        this(type, 1, 0);
    }

    @Override
    public double go(double km) {
        this.odometer += km;
        System.out.println("Megtettünk " + km + " km utat.");
        return km;
    }

}