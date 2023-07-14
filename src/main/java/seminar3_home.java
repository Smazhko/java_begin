/*Урок 3. Коллекции JAVA: Введение

Задание
Пусть дан произвольный список целых чисел.

1) Нужно удалить из него чётные числа
2) Найти минимальное значение
3) Найти максимальное значение
4) Найти среднее значение
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class seminar3_home {

    public static ArrayList<Integer> removeEvens(ArrayList<Integer> iList){
        for (int i = 0; i < iList.size();) {
            if (iList.get(i) % 2 == 0)
                iList.remove(i);
            else
                i++;
        }
        return iList;
    }

    public static int findMin(ArrayList<Integer> iList){
        int min = iList.get(0);
        for (int item : iList)
            if (item < min) min = item;
        return min;
    }


    public static int findMax(ArrayList<Integer> iList){
        int max = iList.get(0);
        for (int item : iList)
            if (item > max) max = item;
        return max;
    }


    public static double findAverage(ArrayList<Integer> iList){
        int summ = 0;
        for (int item : iList) {
            summ += item;
        }
        return (double) summ / iList.size();
    }

    public static double findMedian(ArrayList<Integer> iList){
        return (double) (findMax(iList) + findMin(iList)) / 2;
    }

    public static void main(String[] args) {
        ArrayList<Integer> randomInts = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            randomInts.add(new Random().nextInt(31));

        System.out.println("\nДан список случайных чисел:");
        System.out.println("\t" + randomInts);
        System.out.println("Отсортируем его по возрастанию:");
        Collections.sort(randomInts);
        System.out.println("\t" + randomInts);
        System.out.println("Удалим из него все чётные:");
        System.out.println("\t" + removeEvens(randomInts));
        System.out.println("Минимальный элемент списка:");
        System.out.printf("\t %d (встроенным методом: %d)\n", findMin(randomInts), Collections.min(randomInts));
        System.out.println("Максимальный элемент списка:");
        System.out.printf("\t %d (встроенным методом: %d)\n", findMax(randomInts), Collections.max(randomInts));
        System.out.println("Среднее арифметическое всех элементов:");
        System.out.printf("\t %.2f \n", findAverage(randomInts));
        System.out.println("Среднее значение списка:");
        System.out.printf("\t %.2f \n", findMedian(randomInts));
    }
}

/*
Дан список случайных чисел:
	[18, 2, 23, 16, 17, 25, 3, 27, 23, 22, 17, 9, 14, 13, 8, 13, 25, 30, 6, 24]
Отсортируем его по возрастанию:
	[2, 3, 6, 8, 9, 13, 13, 14, 16, 17, 17, 18, 22, 23, 23, 24, 25, 25, 27, 30]
Удалим из него все чётные:
	[3, 9, 13, 13, 17, 17, 23, 23, 25, 25, 27]
Минимальный элемент списка:
	 3 (встроенным методом: 3)
Максимальный элемент списка:
	 27 (встроенным методом: 27)
Среднее арифметическое всех элементов:
	 17,73
Среднее значение списка:
	 15,00
*/