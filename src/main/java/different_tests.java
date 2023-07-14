import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class different_tests {
    public static void main(String[] args) {
        int number = 1;
        getObjClass(number);

        int[] a = new int[] {1, 9};
        int[] b = new int[3];
        System.arraycopy(a,0,b,0, a.length);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int item = queue.remove();
        queue.offer(2809);
        item = queue.poll();
        System.out.println("item to remove: " + item);
        System.out.println("queue: " + queue);

        queue.element();
        queue.peek();



    }

    public static void getObjClass (Object obj){
        System.out.println(obj.getClass());
    }
}
