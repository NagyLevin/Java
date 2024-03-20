package autok;

//cntrl klick el oda tudsz ugrani a szulohoz
public class Taxi extends Car{

    protected double balance;
    protected double kmCost;

    //hibat ad mert a szülönek nincs üres konstruktora
    //ekkor meg kell hvni a szulo konstruktorat

    // függvények


    @Override
    public double cost(double km) {
        return super.cost(km) + kmCost * km;
    }

    @Override
    public void refuel(double liter) {
        this.fuel += liter;
        this.balance -= liter * Car.fuelPrice;
    }

    @Override
    public String toString() {
        return super.toString() + ", a fuvarozó pénze: " + this.balance;
    }
    //Ahhoz hogy a gyerek is lássa a szülő osztályban is változtatnunk kell: a fuel és a fuelPrice mezők láthatóságát protectedre kell állítani:


    public double transfer(double km) {
        if (go(km) == km) {
            this.balance += this.cost(km);
            return km;
        } else {
            return 0;
        }
    }

    public double costPerPerson(double km, int num) throws Exception {
        if (num > this.capacity + 1) {
            throw new Exception("Több utas, mint férőhely - ha a rendőr is meglátja, akkor nem lesz Taxi tovább.");
        }
        return cost(km) / num;
    }



    public Taxi(String type, double consumption, int capacity, double kmCost) {
        super(type, consumption, capacity);
        //A super kulcsszóval hivatkozhatunk szülőosztálybeli elemekre (pl. konstruktorra, hiszen az nem öröklődik, vagy olyan függvényre, melyet a leszármazottban felüldefiniáltunk vagy elfedtünk.)

        this.kmCost = kmCost;
        this.balance = 0;
    }
}