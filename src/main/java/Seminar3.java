import java.util.*;

public class Seminar3 {

    Object object = new Object();

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list.add("Привет!");
        list.add("Как дела?");
        list.add(0,"Норм!)");

        list2.addAll(list);
        list2.addAll(0, list);

        System.out.println(list.get(1));

        list.size();
//        list.clear();

        list.remove(1);
        list.remove("Привет!");

        list.set(1, "New");

        list.indexOf("New");
        list.lastIndexOf("New");

        if (!list.contains("New")) {list.add("New");}

        System.out.println(list2.subList(1, list2.size()/2));

        list.ensureCapacity(123);
        list.trimToSize();

        list.isEmpty();

        List<Integer> iList = List.of(1,3,6,2,7);
        List<Integer> integers = Arrays.asList(1, 2, 6, 8);

        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();

        System.out.println();
        for (int i = 0; i < 10; i++) {
            integers1.add(new Random().nextInt(50));
            integers2.add(new Random().nextInt(50));
        }

        System.out.println(integers1);
        System.out.println(integers2);
        System.out.println("-".repeat(16));
        integers1.removeAll(integers2);
//        integers1.retainAll(integers2);
        integers1.addAll(integers2);
        System.out.println(integers1);

        integers1.sort((o1, o2) -> {
            if (o1 == 1) return 0;
            return o2 - o1;
        });

        System.out.println(integers1);

        for (int i = 0; i < integers1.size(); i++) {
            integers1.get(i);
        }

        for (int i: integers1) {
//            System.out.println(i);
        }

        ListIterator<Integer> iterator = integers1.listIterator(integers1.size()/2);
        while (iterator.hasPrevious()){
            int i = iterator.previous();
            int index = iterator.previousIndex();
            iterator.remove();
        }

        integers1.forEach(n -> System.out.println(n));
    }

}
