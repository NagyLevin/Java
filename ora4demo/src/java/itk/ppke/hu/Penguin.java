package java.itk.ppke.hu;

// A pingvin is madár, de nem tud repülni...
// Nem lehet a madár leszármazottja jelen esetben
// ...  de BaldEagle lehet :)
public class Penguin //extends Bird
{
    public void fly(){
        System.out.println( Penguin.class.getSimpleName() + ": What about me?!");
    }
}
