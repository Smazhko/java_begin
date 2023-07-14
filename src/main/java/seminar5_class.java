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
Первая мапа (словарь):==================================================
	{224=LATIN SMALL LETTER A WITH GRAVE (à), 241=LATIN SMALL LETTER N WITH TILDE (ñ), 242=LATIN SMALL LETTER O WITH GRAVE (ò),
	246=LATIN SMALL LETTER O WITH DIAERESIS (ö), 262=LATIN CAPITAL LETTER C WITH ACUTE (Ć), 247=DIVISION SIGN (÷),
	249=LATIN SMALL LETTER U WITH GRAVE (ù), 217=LATIN CAPITAL LETTER U WITH GRAVE (Ù), 234=LATIN SMALL LETTER E WITH CIRCUMFLEX (ê),
	253=LATIN SMALL LETTER Y WITH ACUTE (ý)}
Результат сортировки значений первой мапы по алфавиту:==================================================
	{247=DIVISION SIGN (÷), 262=LATIN CAPITAL LETTER C WITH ACUTE (Ć), 217=LATIN CAPITAL LETTER U WITH GRAVE (Ù),
	224=LATIN SMALL LETTER A WITH GRAVE (à), 234=LATIN SMALL LETTER E WITH CIRCUMFLEX (ê), 241=LATIN SMALL LETTER N WITH TILDE (ñ),
	246=LATIN SMALL LETTER O WITH DIAERESIS (ö), 242=LATIN SMALL LETTER O WITH GRAVE (ò), 249=LATIN SMALL LETTER U WITH GRAVE (ù),
	253=LATIN SMALL LETTER Y WITH ACUTE (ý)}
Вторая мапа (словарь):==================================================
	{240=LATIN SMALL LETTER ETH (ð), 225=LATIN SMALL LETTER A WITH ACUTE (á), 291=LATIN SMALL LETTER G WITH CEDILLA (ģ),
	276=LATIN CAPITAL LETTER E WITH BREVE (Ĕ), 229=LATIN SMALL LETTER A WITH RING ABOVE (å), 280=LATIN CAPITAL LETTER E WITH OGONEK (Ę),
	297=LATIN SMALL LETTER I WITH TILDE (ĩ), 298=LATIN CAPITAL LETTER I WITH MACRON (Ī), 234=LATIN SMALL LETTER E WITH CIRCUMFLEX (ê),
	267=LATIN SMALL LETTER C WITH DOT ABOVE (ċ)}
Удаление из первой мапы значений с ключами, которые совпадают со второй мапой:==================================================
	{224=LATIN SMALL LETTER A WITH GRAVE (à), 241=LATIN SMALL LETTER N WITH TILDE (ñ), 242=LATIN SMALL LETTER O WITH GRAVE (ò),
	246=LATIN SMALL LETTER O WITH DIAERESIS (ö), 262=LATIN CAPITAL LETTER C WITH ACUTE (Ć), 247=DIVISION SIGN (÷),
	249=LATIN SMALL LETTER U WITH GRAVE (ù), 217=LATIN CAPITAL LETTER U WITH GRAVE (Ù), 253=LATIN SMALL LETTER Y WITH ACUTE (ý)}
Ещё один способ вывода отсортированного по values мапа:
247=DIVISION SIGN (÷)
262=LATIN CAPITAL LETTER C WITH ACUTE (Ć)
217=LATIN CAPITAL LETTER U WITH GRAVE (Ù)
224=LATIN SMALL LETTER A WITH GRAVE (à)
241=LATIN SMALL LETTER N WITH TILDE (ñ)
246=LATIN SMALL LETTER O WITH DIAERESIS (ö)
242=LATIN SMALL LETTER O WITH GRAVE (ò)
249=LATIN SMALL LETTER U WITH GRAVE (ù)
253=LATIN SMALL LETTER Y WITH ACUTE (ý)
*/