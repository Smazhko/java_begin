import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;

public class seminar3_class {
    public static void main(String[] args) {
        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers1.add(new Random().nextInt(50) + 1);
            integers2.add(new Random().nextInt(3) + 1);
        }
        System.out.println(integers1);
        System.out.println(integers2);
        System.out.println("-".repeat(20));

        System.out.println(integers1.size());
        System.out.println("===============================");

        integers1.add(3);

        int counter = 0;
        ListIterator<Integer> myLI = integers1.listIterator();
        while(myLI.hasNext()){
//            System.out.println(myLI.nextIndex());
//            System.out.println(myLI.previousIndex());
//            System.out.println(myLI.next());
//            System.out.println("=======");
            myLI.next();
            counter ++;
            if (counter == 3) {
                myLI.remove();
            }
        }

        System.out.println(myLI); //java.util.ArrayList$ListItr@3feba861
        System.out.println(integers1);

        HashSet<Integer> mySet = new HashSet<>();
        System.out.println(mySet.getClass());
        mySet.add(1);
        mySet.add(1);
        mySet.add(7);
        System.out.println(mySet);

    }

}