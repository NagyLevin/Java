package java.itk.ppke.hu;

// A Bagolynak-nak muszáj a Madár osztályból öröklődnie és az Éjszakai életmód interfészt megvalósítania!
// Valamint muszáj valamelyik módosító szót használnia: final, sealed, non-sealed
public final class Owl extends Bird implements Nocturnal{
    public void fly(){
        System.out.println("I believe I can fly!");
    }
    public void huntingBugs(){
        System.out.println( "The night is dark and full of ...termites");
    }
}
