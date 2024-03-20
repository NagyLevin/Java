package autok;

public class Bus extends Taxi{
    private static final int ticketPrice = 350;

    public Bus(String busType, double consumption, int capacity, double kmCost) {
        super(busType, consumption, capacity, kmCost);
    }

    @Override
    public double transfer(double km) {
        return transfer(km, 1);
    }

    // túlterhelt metódus
    public double transfer(double km, int num) {
        if (go(km) == km) {
            this.balance += profit(km, num);
            return km;
        }
        else return 0;
    }

    @Override
    public double costPerPerson(double km, int num) {
        return Bus.ticketPrice;
    }

    public double profit(double km, int num) {
        return num * Bus.ticketPrice - cost(km);
    }

    public static double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "A busz típusa: " + carType + ", a vállalat pénze: " + balance;
    }
}