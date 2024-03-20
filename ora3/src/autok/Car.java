package autok;
import java.util.*;

/*
public Az osztály bárki számára látható
final Végleges, nem lehet belőle leszármazni. Az öröklődésnél lesz róla szó bővebben.
abstract Az öröklődésnél lesz róla szó, csak leszármazni lehet belőle, példányosítani nem (a tervezés eszköze).
(nincs kulcsszó = package private): A csomagon (package) belül bárki számára látható.
Amik kombinálhatóak:
public + final, public + abstract
Amik nem kombinálhatóak:
final + abstract


 */

public class Car {

    /*
        public Az osztály példányait használó bármely kód számára közvetlenül hozzáférhető.
        private Csak azon osztály objektumai számára elérhetők, melyben meghatározták őket.
        protected A definiáló osztály és leszármazottainak objektumai számára és a csomagon belül látható.
        (nincs kulcsszó): A csomagon (package) belül bárki számára látható.
        final Végleges, azaz konstans érték. Ugyanaz mint a C++ const, de itt a referencia az az érték,  ami nem változtatható, maga az objektum módosítható.
        static Osztályváltozó, egy osztályhoz csak egy tartozik (vagyis az osztály minden példányához is ugyanaz a változó tartozik).
        Amik kombinálhatóak:
        Láthatóság + final + static
        Amik nem kombinálhatóak:
        Láthatósági szintek, egyszerre csak egy láthatósági szint használható (nyilvánvalóan)
     */
    //static private int fuelPrice = 480; // statikus változó = osztályváltozó

    protected String carType; //protecedre allitva azert hogy a buss class/osztaly is lassa
    private double consumption;
    protected static int fuelPrice = 480; // statikus változó = osztályváltozó
    //feluliras miatt protectedre lett allitva

    protected double fuel = 0;
    //feluliras miatt protectedre lett allitva
    private double odometer = 0;       // példa kezdőértékre
    //private double fuel = 0;
    protected int capacity; //proteced azert hogy a taxiban is lehessen rá hivatkozni
    public String licencePlate;


    public Car(String carType, double consumption, int capacity) {
        this.carType = carType;
        this.consumption = consumption;
        this.capacity = capacity;
    }

    //ugyanaz mint a fento car, annyi különbseggel, hogy itt meg lehet hivni egy konstruktort
    //Minden konstruktor első sorában van lehetőségünk egy másik konstruktor tartalmát futtatni, mielőtt az aktuálisan definiált konstruktor törzse végrehajtásra kerül.

    public Car (String carType, double consumption, int capacity, double odometer) {
        this(carType, consumption, capacity);
        this.odometer = odometer;
    }



    /*
    public Car(String carType, double consumption, int capacity, double odometer) {
        this.carType = carType;
        this.consumption = consumption;
        this.odometer = odometer;
        this.capacity = capacity;
    }
    */

    public void refuel(double amount) {
        this.fuel += amount;
    }

    private double fuelNeed(double km) {
        return (km / 100.0) * this.consumption;
    }

    public double cost(double km) {
        return fuelNeed(km) * Car.fuelPrice;
    }

    public double go(double km) {
        if (fuelNeed(km) <= fuel) {
            this.fuel -= fuelNeed(km);
            this.odometer += km;
            System.out.println("Megtettünk " + km + " km utat!");
            return km;
        } else {
            System.out.println("Nincs elég benzin " + km + " km úthoz!");
            return 0;
        }
    }

    public double getFuel() {
        return fuel;
    }

    public double getOdometer() {
        return odometer;
    }

    public double getConsumption() {
        return consumption;
    }


    //lehet alapértékeket felülbrálni beallistaskor, ezert jobb a get set
    public void setConsumption (double newConsumption) {
        if (newConsumption <= 0.0) {
            // Hibaüzenet


        } else {
            this.consumption = newConsumption;
        }
    }

    //Ez a metódus lényegében megadja az adott objektum String reprezentációját. A println a toString segítségével bármilyen objektumot ki tud írni.
    public String toString() {
        return carType + ", benzin: " + getFuel() + ", km: " + getOdometer();
    }

    static public int fuelCost(int liter) {
        return Car.fuelPrice * liter;
    }






}