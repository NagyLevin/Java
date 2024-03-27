package java.itk.ppke.hu;

//File/ProjectStructure... Settings-ekben 17es java verziót beállítani! (Project, Modules, SDKs)
public class Main {

    public static void main(String[] args) {

        Bird[] madarak = new Bird[4];
        madarak[0] = new Eagle();
        madarak[1] = new BaldEagle();
        madarak[2] = new Owl();
        //madarak[3] = new Penguin();   //A pingvin nem altípusa a madárnak, ez így nem működik
        madarak[3] = new Eagle();

        System.out.println( "___A madaraink röpködnek___");
        for( Bird elements : madarak )
        {
            System.out.print( elements.getClass().getSimpleName() + ": ");
            elements.fly();
        }
        System.out.println();


        new Penguin().fly();
        System.out.println("No, you don't!\n");     //diszkrimináció!


        Nocturnal[] baglyok = new Nocturnal[2];
        baglyok[0] = (Nocturnal) madarak[2];
        baglyok[1] = new Programmer();

        System.out.println( "___Az éjjeli baglyok bogarakra vadásznak___");
        for( Nocturnal elements : baglyok )
        {
            System.out.print( elements.getClass().getSimpleName() + ": ");
            elements.huntingBugs();
        }


    }
}

