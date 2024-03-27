package java.itk.ppke.hu;

// A Sas-nak muszáj a madárból öröklődnie!
// Valamint muszáj valamelyik módosító szót használnia: final, sealed, non-sealed
// Sealed esetén meg kell adni a lehetséges leszármazottakat
public sealed class Eagle extends Bird permits BaldEagle {
    public void fly(){
        System.out.println("Eagles fly alone!");
    }
}
