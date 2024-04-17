package comperator;

import java.util.Comparator;

public class newTableComperator implements Comparator<Table> {


    @Override
    public int compare(Table o1, Table o2) {
        if(o1.getNumberOfLegs() < o2.getNumberOfLegs()){
            return -1;
        }else if(o1.getNumberOfLegs() == o2.getNumberOfLegs()){return 0;}
        else{
            return 1;
        }


    }
}
