package autok;

//abstrakt osztály
//lehet refactor extract menüponttal automatikusan ilyet kesziteni
public abstract class Vehicle {

    protected String type;
    protected double odometer = 0; // példa kezdőértékre
    protected int capacity;

    public Vehicle(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    abstract public double go(double km);


}
