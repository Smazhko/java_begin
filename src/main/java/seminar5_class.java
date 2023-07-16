//Создать мапу, обобщение целое число и строка,
//заполнить 10 значениями, ключ случайное число от 0 до 1000 а значение, название буквы этого случайного числа.

//вывести в порядке сортировки значений


//Создать ещё одну мапу, обобщение целое число и строка,
//заполнить 10 значениями, ключ случайное число от 0 до 1000 а значение,
// название буквы этого случайного числа. Из первого удалить поля с соответствующими
// ключами второй мапы.


import java.util.*;


public class seminar5_class {
    public static void main(String[] args) {
        int size = 10;
        Map<Integer, String> myHashMap  = fillMap(size);
        Map<Integer, String> myHashMap2 = fillMap(size);

        System.out.println("Первая map (словарь):" + "=".repeat(50));
        System.out.println("\t" + myHashMap);
        System.out.println("Результат сортировки значений первой map по алфавиту:" + "=".repeat(50));
        System.out.println("\t" + sortByValues(myHashMap));
        System.out.println("Вторая map (словарь):" + "=".repeat(50));
        System.out.println("\t" + myHashMap2);
        System.out.println("Удаление из первой map значений с ключами, которые совпадают со второй map:" + "=".repeat(50));
        System.out.println("\t" + compareHashMapsByKeys(myHashMap, myHashMap2));

        System.out.println("Ещё один способ вывода map, отсортированного по values:");
        myHashMap
                .entrySet()                         // зайти во внутренности
                .stream()                           // превратить в поток
                .sorted(Map.Entry.comparingByValue()) // отсортировать по значению
                .forEach(System.out::println);      // вывести каждую пару значений, sout - lambda

    }


    public static Map<Integer, String> fillMap(int size){
        HashMap<Integer, String> newMap = new HashMap();
        Random rnd = new Random();
        int key = 0;
        for (int i = 0; i < size; i++) {
             key = rnd.nextInt(100) + 200;
            newMap.put(key, Character.getName(key) + " (" + Character.toString(key) + ")");
        }
        return newMap;
    }


    public static Map<Integer, String> fillTreeMap(int size){
        TreeMap<Integer, String> newMap = new TreeMap();


        for (int i = 0; i < size; i++) {
            int temp = new Random().nextInt(1001);
            newMap.put(temp, Character.getName(temp));
        }

        return newMap;
    }


    public static LinkedHashMap<Integer, String> sortByValues(Map<Integer, String> myMap){
        int size = myMap.size();
        LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<>(size);
        String[] iValues;
        iValues = myMap.values().toArray(String[]::new);

        Arrays.sort(iValues);

        for (int i = 0; i < size; i++) {
            for (int iKey: myMap.keySet()){
                if (iValues[i].equals(myMap.get(iKey))){
                    sortedMap.put(iKey, iValues[i]);
                }
            }
        }
        return sortedMap;
    }


    public static Map<Integer, String> compareHashMapsByKeys(Map<Integer, String> map1, Map<Integer, String> map2){
        for (int iKey: map2.keySet())
        {
            if(map1.containsKey(iKey)){
                map1.remove(iKey);
            }
        }
        return map1;
    }
}

/*
Первая map (словарь):==================================================
	{273=LATIN SMALL LETTER D WITH STROKE (đ), 225=LATIN SMALL LETTER A WITH ACUTE (á), 229=LATIN SMALL LETTER A WITH RING ABOVE (å),
	278=LATIN CAPITAL LETTER E WITH DOT ABOVE (Ė), 267=LATIN SMALL LETTER C WITH DOT ABOVE (ċ), 283=LATIN SMALL LETTER E WITH CARON (ě),
	251=LATIN SMALL LETTER U WITH CIRCUMFLEX (û), 221=LATIN CAPITAL LETTER Y WITH ACUTE (Ý), 253=LATIN SMALL LETTER Y WITH ACUTE (ý),
	255=LATIN SMALL LETTER Y WITH DIAERESIS (ÿ)}
Результат сортировки значений первой map по алфавиту:==================================================
	{278=LATIN CAPITAL LETTER E WITH DOT ABOVE (Ė), 221=LATIN CAPITAL LETTER Y WITH ACUTE (Ý), 225=LATIN SMALL LETTER A WITH ACUTE (á),
	229=LATIN SMALL LETTER A WITH RING ABOVE (å), 267=LATIN SMALL LETTER C WITH DOT ABOVE (ċ), 273=LATIN SMALL LETTER D WITH STROKE (đ),
	283=LATIN SMALL LETTER E WITH CARON (ě), 251=LATIN SMALL LETTER U WITH CIRCUMFLEX (û), 253=LATIN SMALL LETTER Y WITH ACUTE (ý),
	255=LATIN SMALL LETTER Y WITH DIAERESIS (ÿ)}
Вторая map (словарь):==================================================
	{240=LATIN SMALL LETTER ETH (ð), 290=LATIN CAPITAL LETTER G WITH CEDILLA (Ģ), 228=LATIN SMALL LETTER A WITH DIAERESIS (ä),
	230=LATIN SMALL LETTER AE (æ), 265=LATIN SMALL LETTER C WITH CIRCUMFLEX (ĉ), 220=LATIN CAPITAL LETTER U WITH DIAERESIS (Ü),
	205=LATIN CAPITAL LETTER I WITH ACUTE (Í), 285=LATIN SMALL LETTER G WITH CIRCUMFLEX (ĝ)}
Удаление из первой map значений с ключами, которые совпадают со второй map:==================================================
	{273=LATIN SMALL LETTER D WITH STROKE (đ), 225=LATIN SMALL LETTER A WITH ACUTE (á), 229=LATIN SMALL LETTER A WITH RING ABOVE (å),
	278=LATIN CAPITAL LETTER E WITH DOT ABOVE (Ė), 267=LATIN SMALL LETTER C WITH DOT ABOVE (ċ), 283=LATIN SMALL LETTER E WITH CARON (ě),
	251=LATIN SMALL LETTER U WITH CIRCUMFLEX (û), 221=LATIN CAPITAL LETTER Y WITH ACUTE (Ý), 253=LATIN SMALL LETTER Y WITH ACUTE (ý),
	255=LATIN SMALL LETTER Y WITH DIAERESIS (ÿ)}
Ещё один способ вывода map, отсортированного по values:
278=LATIN CAPITAL LETTER E WITH DOT ABOVE (Ė)
221=LATIN CAPITAL LETTER Y WITH ACUTE (Ý)
225=LATIN SMALL LETTER A WITH ACUTE (á)
229=LATIN SMALL LETTER A WITH RING ABOVE (å)
267=LATIN SMALL LETTER C WITH DOT ABOVE (ċ)
273=LATIN SMALL LETTER D WITH STROKE (đ)
283=LATIN SMALL LETTER E WITH CARON (ě)
251=LATIN SMALL LETTER U WITH CIRCUMFLEX (û)
253=LATIN SMALL LETTER Y WITH ACUTE (ý)
255=LATIN SMALL LETTER Y WITH DIAERESIS (ÿ)
*/