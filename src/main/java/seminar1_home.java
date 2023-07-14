//Первый семинар.
// 1. Выбросить случайное целое число в диапазоне от 0 до 2000 и сохранить в i
// 2. Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
// 3. Найти все кратные n числа в диапазоне от i до Short.MAX_VALUE сохранить в массив m1
// 4. Найти все некратные n числа в диапазоне от Short.MIN_VALUE до i и сохранить в массив m2
//
// Пункты реализовать в методе main
// *Пункты реализовать в разных методах
//
//  int i = new Random().nextInt(k); //это кидалка случайных чисел!)

import java.util.Random;

public class seminar1_home {
    public static int getRandomInt(){
        return new Random().nextInt(2001);
    }

    public static int getHigherByteNumber(int num){
        return Integer.toBinaryString(num).length();
    }

    public static int[] getArrayOfAliqout(int divider, int min, int max, boolean flagAliquot){
        int counter = 0;
        for (int j = min; j < max; j++) {
            if (flagAliquot) {
                if (j % divider == 0) counter++;
            }
            else {
                if (j % divider != 0) counter++;
            }
        }
        int[] resultArray = new int[counter];
        int index = 0;
        for (int j = min; j < max; j++) {
            if (flagAliquot) {
                if (j % divider == 0) {
                    resultArray[index] = j;
                    index++;
                }
            }
            else {
                if (j % divider != 0) {
                    resultArray[index] = j;
                    index++;
                }
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int i = getRandomInt();
        System.out.println("Случайное число из диапазона 0 .. 2000: " + i);
        int n = getHigherByteNumber(i);
        System.out.println("Номер старшего значащего бита числа   : " + n);

        int min = i;
        int max = Short.MAX_VALUE + 1;
        int[] m1 = getArrayOfAliqout(n, min, max, true);
        System.out.printf("Количество чисел, кратных %2d, в диапазоне от %4d до %d   : %d %n", n, min, max - 1, m1.length);

        min = Short.MIN_VALUE;
        max = i + 1;
        int m2[] = getArrayOfAliqout(n, min, max, false);
        System.out.printf("Количество чисел, НЕкратных %2d, в диапазоне от %d до %4d: %d %n", n, min, max - 1, m2.length);
    }
}

/*
Случайное число из диапазона 0 .. 2000: 1285
Номер старшего значащего бита числа   : 11
Количество чисел, кратных 11, в диапазоне от 1285 до 32767   : 2862
Количество чисел, НЕкратных 11, в диапазоне от -32768 до 1285: 30959
 */