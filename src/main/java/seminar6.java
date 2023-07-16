import java.util.HashMap;
import java.util.Iterator;

public class seminar6 {

    public static void main(String[] args) {
        mySet<Integer> set01 = new mySet<>();
        System.out.println("==== Р Е А Л И З А Ц И Я   HashSet ====");
        System.out.println("Добавление 4165: " + set01.add(4165));
        System.out.println("Добавление 4417: " + set01.add(4417));
        System.out.println("Добавление null: " + set01.add(null));
        System.out.println("Добавление 4165: " + set01.add(4165));
        System.out.println("Добавление 4168: " + set01.add(4168));
        System.out.println("Добавление 4175: " + set01.add(4175));
        System.out.println("ПЕЧАТЬ КОЛЛЕКЦИИ:\n\t" + set01);
        System.out.println("Элемент под индексом 3:  " + set01.get(3));
        System.out.println("Элемент под индексом -1: " + set01.get(-1));
        System.out.println("Элемент под индексом 7:  " + set01.get(7));

        System.out.println("Удаление 4165:  " + set01.remove(4165));
        System.out.println("Удаление null:  " + set01.remove(null));
        System.out.println("Удаление 65536: " + set01.remove(65536));
        System.out.println("ПЕЧАТЬ КОЛЛЕКЦИИ:\n\t" + set01);

        Iterator<Integer> iterr = set01.iterator();

        System.out.print("ПЕЧАТЬ КОЛЛЕКЦИИ (через ITERATOR):\n\t");
        while (iterr.hasNext()) {
            System.out.printf("%d  ", iterr.next());
        }
        System.out.println();
    }
}

/*
class mySet<Integer> {
    ArrayList<Integer> newSet = new ArrayList<>();

    public boolean add(Integer value) {
        if (newSet.contains(value)) return false;
        newSet.add(value);
        return true;
    }
    public boolean remove(Integer value){
        if (newSet.contains(value)) {
            newSet.remove(value);
            return true;
        }
        return false;
    }
    public void print(){
        System.out.println(newSet.toString());
    }
}
*/

class mySet<T> {
    private HashMap<T, Object> newSet = new HashMap<>();
    private static final Object PLUG = new Object();


    public boolean add(T value) {
        return newSet.put(value, PLUG) == null;
    }


    public boolean remove(T value){
        return newSet.remove(value) == PLUG;
    }


    public String toString(){
        return newSet.keySet().toString();
    }


    public Iterator<T> iterator(){
        return newSet.keySet().iterator();
    }


    public T get(int index){
        if (index > 0 && index < newSet.size() - 1){
            Iterator<T> items = newSet.keySet().iterator();
            int counter = 0;
            while (counter != index){
                counter ++;
                items.next();
            }
            return items.next();
        }
        else{
            return null;
        }
    }
}


/*
==== Р Е А Л И З А Ц И Я   HashSet ====
Добавление 4165: true
Добавление 4417: true
Добавление null: true
Добавление 4165: false
Добавление 4168: true
Добавление 4175: true
ПЕЧАТЬ КОЛЛЕКЦИИ:
	[null, 4417, 4165, 4168, 4175]
Элемент под индексом 3:  4168
Элемент под индексом -1: null
Элемент под индексом 7:  null
Удаление 4165:  true
Удаление null:  true
Удаление 65536: false
ПЕЧАТЬ КОЛЛЕКЦИИ:
	[4417, 4168, 4175]
ПЕЧАТЬ КОЛЛЕКЦИИ (через ITERATOR):
	4417  4168  4175
 */