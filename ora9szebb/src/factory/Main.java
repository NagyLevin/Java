package factory;

public class Main {
    public static void main(String[] args) {

        Factory factory = new Factory();

        for (int i = 1; i < 11; i++) {
            new Truck(factory, i).start();
        }
        factory.start();
    }
}