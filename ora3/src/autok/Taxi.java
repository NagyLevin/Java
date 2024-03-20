package autok;

public class Taxi extends Car{

    protected double balance;
    protected double kmCost;

    //hibat ad mert a szülönek nincs üres konstruktora
    //ekkor meg kell hvni a szulo konstruktorat

    // függvények
    public Taxi(String type, double consumption, int capacity, double kmCost) {
        super(type, consumption, capacity);
        //A super kulcsszóval hivatkozhatunk szülőosztálybeli elemekre (pl. konstruktorra, hiszen az nem öröklődik, vagy olyan függvényre, melyet a leszármazottban felüldefiniáltunk vagy elfedtünk.)

        this.kmCost = kmCost;
        this.balance = 0;
    }
}