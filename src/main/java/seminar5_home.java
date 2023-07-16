/*
Урок 5. Хранение и обработка данных ч2: множество коллекций Map

Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами,
их необходимо считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию
числа телефонов.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class seminar5_home {

public static void main(String[] args) {
    printMessage("Т Е Л Е Ф О Н Н Ы Й   С П Р А В О Ч Н И К");
    File dataFile = new File("sem5_data.txt");

    Map<String, List<String>> phonebook; //= new HashMap<>();
    phonebook = loadPhonebookFromFile(dataFile);
    boolean continueFlag = true;

    while (continueFlag) {
        switch (getMenuSelection()) {
            case 1: //добавить нового
                addNewRecord(phonebook, inputNew());
                savePhonebookToFile(dataFile, phonebook);
                printMessage(" У С П Е Ш Н О ");
                break;
            case 2: //вывести сортированных по имени
                loadPhonebookFromFile(dataFile);
                printPhonebook(sortByName(phonebook));
                break;
            case 3:
                printMessage("СОРТИРОВКА ПО КОЛИЧЕСТВУ НОМЕРОВ НА ЧЕЛОВЕКА");
                printPhonebook(sortByPhoneCount(phonebook));
                break;
            case 4:
                printMessage("Спасибо за использование программы. Хорошего дня!");
                continueFlag = false;
                break;
            default:
                System.out.println("Нет такого пункта в меню.");
        }
    }

}


public static Integer getMenuSelection(){
    String menu = """
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
""";
    Scanner inputMenu = new Scanner(System.in);

    System.out.print(menu);
    System.out.print("Выбор: ");
    String userInput = inputMenu.nextLine();
    while(!userInput.matches("[1-4]"))
    {
        System.out.print("Некорректный ввод. Попробуйте ещё раз: ");
        userInput = inputMenu.nextLine();
    }
    return Integer.parseInt(userInput);
}


public static String[] inputNew(){
    printMessage("ДОБАВЛЕНИЕ  НОВОЙ  ЗАПИСИ");
    Scanner userInput = new Scanner(System.in);

    System.out.print("Имя (ФИО) : ");
    String name = userInput.nextLine().replaceAll("[^0-9a-zA-Zа-яёА-ЯЁ\\. ]", "").strip(); // оставим в строке только буквы и пробелы и отрубим лишнее по краям
    System.out.print("телефон   : ");
    String phone = userInput.nextLine().replaceAll("[^\\(\\)0-9\\-\\+]", "").strip();// оставляем только цифры

    if (name.length() == 0){
        name = "Неизвестный";
    } else {
        name = name.substring(0,1).toUpperCase() + name.substring(1);
    }
    if (phone.length() == 0){phone = "- - -";}


    return new String[] {name, phone};
}


public static void addNewRecord(Map<String, List<String>> phonebook, String[] newRecord) {
    String name = newRecord[0];
    String phone = newRecord[1];

    if (phonebook.containsKey(name)){
        phonebook.get(name).add(phone);
    }
    else{
        phonebook.put(name, new ArrayList<>(Collections.singletonList(phone)));
    }
}


public static void printPhonebook(Map<String, List<String>> phonebook){
    int maxNameLength = 0;
    int maxPhoneLength = 0;

    for (var reccord: phonebook.entrySet()) {
        int currentNameLength = reccord.getKey().length();
        for (var phone: reccord.getValue()) {
            int currentPhoneLength = phone.length();
            if (currentPhoneLength > maxPhoneLength){
                maxPhoneLength = currentPhoneLength;
            }
        }
        if (currentNameLength > maxNameLength){
            maxNameLength = currentNameLength;
        }
    }
// ┌─┬─┐└─┴─┘│├─┼─┤
    System.out.println("┌" + "─".repeat(maxNameLength + 2) + "┬" + "─".repeat(maxPhoneLength + 2) + "┐");
    System.out.printf ("│ %-" + (maxNameLength) + "s │ %-" + (maxPhoneLength) + "s │\n", "ИМЯ", "ТЕЛЕФОН");
    System.out.println("├" + "─".repeat(maxNameLength + 2) + "┼" + "─".repeat(maxPhoneLength + 2) + "┤");

    int counter = 0;
    int lastRecord = phonebook.size();
    for (var reccord: phonebook.entrySet()) {
        counter++;
        String name = reccord.getKey();
        List<String> phonesList = reccord.getValue();

        System.out.printf("│ %-" + maxNameLength + "s ", name);
        for (int i = 0; i< phonesList.size(); i++){
            if (i==0){
                System.out.printf("│ %-" + maxPhoneLength + "s │\n", phonesList.get(i));
            } else {
                System.out.printf("│ " + " ".repeat( maxNameLength ) + " │ %-" + maxPhoneLength + "s │\n", phonesList.get(i));
            }
        }
        if (counter < lastRecord ) {
            System.out.println("├" + "─".repeat(maxNameLength + 2) + "┼" + "─".repeat(maxPhoneLength + 2) + "┤");
        } else {
            System.out.println("└" + "─".repeat(maxNameLength + 2) + "┴" + "─".repeat(maxPhoneLength + 2) + "┘");
        }
    }
}


public static Map<String, List<String>> sortByName(Map<String, List<String>> phonebook){
    return new TreeMap<>(phonebook);
}


public static LinkedHashMap<String, List<String>> sortByPhoneCount(Map<String, List<String>> phonebook) {
    int size = phonebook.size();
    HashMap<String, Integer> tempMap = new HashMap<>(size);

    for (var reccord: phonebook.entrySet()) {
        tempMap.put(reccord.getKey(), reccord.getValue().size());
    }

    LinkedHashMap<String, Integer> sortedMap1 =
            tempMap.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors
                    .toMap(Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

    LinkedHashMap<String, List<String>> sortedMap = new LinkedHashMap<>();

    for (var item: sortedMap1.entrySet()) {
        sortedMap.put(item.getKey(), phonebook.get(item.getKey()));
    }

    return sortedMap;
}

public static Map<String, List<String>> loadPhonebookFromFile(File dataFile) {
    Map<String, List<String>> phonebook = new HashMap<>();

    if (!dataFile.exists()) {
        try {
            dataFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    if(dataFile.canRead()) {
        try {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNextLine()) {
                addNewRecord(phonebook, fileScanner.nextLine().split(":"));
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("READ ERROR");
            throw new RuntimeException(e);
        }
    }
    return phonebook;
}


public static void savePhonebookToFile(File dataFile, Map<String, List<String>> phonebook){
    if (!dataFile.exists()) {
        try {
            dataFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    if(dataFile.canWrite()) {
        try (FileWriter fwriter = new FileWriter(dataFile, false)) {
            for (var record: phonebook.entrySet()) {
                String name = record.getKey();
                List<String> phonesList = record.getValue();

                for (int i = 0; i< phonesList.size(); i++){
                    fwriter.write(name + ":");
                    fwriter.write( phonesList.get(i)+ "\n");
                }
            }
            fwriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


public static void printMessage(String message){
    System.out.println("┌" + "─".repeat(message.length() + 2) + "┐");
    System.out.println("│ " +        message                        + " │");
    System.out.println("└" + "─".repeat(message.length() + 2) + "┘");
}
}

/*
┌───────────────────────────────────────────┐
│ Т Е Л Е Ф О Н Н Ы Й   С П Р А В О Ч Н И К │
└───────────────────────────────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 2
┌─────────────────────────────────┬───────────────────┐
│ ИМЯ                             │ ТЕЛЕФОН           │
├─────────────────────────────────┼───────────────────┤
│ Бехтерев Владимир Валентинович  │ +7(987)852-52-25  │
├─────────────────────────────────┼───────────────────┤
│ Вася                            │ 8(956)288-28-28   │
│                                 │ 12121212          │
├─────────────────────────────────┼───────────────────┤
│ Галя                            │ 57-47-22          │
│                                 │ 288-28-28         │
├─────────────────────────────────┼───────────────────┤
│ Дашенька                        │ - - -             │
├─────────────────────────────────┼───────────────────┤
│ Кононенко А.В.                  │ 959-52-25         │
├─────────────────────────────────┼───────────────────┤
│ Краснов В.А.                    │ 23123123123       │
│                                 │ 54984-165-4654    │
├─────────────────────────────────┼───────────────────┤
│ Луначарская Ульяна Вячеславовна │ 8(800)2000-500    │
├─────────────────────────────────┼───────────────────┤
│ Лёша                            │ 288-28-28         │
│                                 │ 12121212          │
│                                 │ 2165-123-465      │
├─────────────────────────────────┼───────────────────┤
│ Лёша 2                          │ 123-1231-123      │
├─────────────────────────────────┼───────────────────┤
│ Неизвестный                     │ - - -             │
│                                 │ 2323              │
├─────────────────────────────────┼───────────────────┤
│ Павел 4                         │ 57-88-325         │
├─────────────────────────────────┼───────────────────┤
│ Саша Р.                         │ 125-2574-124      │
│                                 │ 8(921)578-15-12   │
│                                 │ +1(9512)125-45-12 │
│                                 │ (495)548-12-12    │
└─────────────────────────────────┴───────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 1
┌───────────────────────────┐
│ ДОБАВЛЕНИЕ  НОВОЙ  ЗАПИСИ │
└───────────────────────────┘
Имя (ФИО) : лёша
телефон   : +38(050)653-01-13
┌─────────────────┐
│  У С П Е Ш Н О  │
└─────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 3
┌──────────────────────────────────────────────┐
│ СОРТИРОВКА ПО КОЛИЧЕСТВУ НОМЕРОВ НА ЧЕЛОВЕКА │
└──────────────────────────────────────────────┘
┌─────────────────────────────────┬───────────────────┐
│ ИМЯ                             │ ТЕЛЕФОН           │
├─────────────────────────────────┼───────────────────┤
│ Лёша                            │ 288-28-28         │
│                                 │ 12121212          │
│                                 │ 2165-123-465      │
│                                 │ +38(050)653-01-13 │
├─────────────────────────────────┼───────────────────┤
│ Саша Р.                         │ 125-2574-124      │
│                                 │ 8(921)578-15-12   │
│                                 │ +1(9512)125-45-12 │
│                                 │ (495)548-12-12    │
├─────────────────────────────────┼───────────────────┤
│ Вася                            │ 8(956)288-28-28   │
│                                 │ 12121212          │
├─────────────────────────────────┼───────────────────┤
│ Краснов В.А.                    │ 23123123123       │
│                                 │ 54984-165-4654    │
├─────────────────────────────────┼───────────────────┤
│ Галя                            │ 57-47-22          │
│                                 │ 288-28-28         │
├─────────────────────────────────┼───────────────────┤
│ Неизвестный                     │ - - -             │
│                                 │ 2323              │
├─────────────────────────────────┼───────────────────┤
│ Луначарская Ульяна Вячеславовна │ 8(800)2000-500    │
├─────────────────────────────────┼───────────────────┤
│ Кононенко А.В.                  │ 959-52-25         │
├─────────────────────────────────┼───────────────────┤
│ Дашенька                        │ - - -             │
├─────────────────────────────────┼───────────────────┤
│ Бехтерев Владимир Валентинович  │ +7(987)852-52-25  │
├─────────────────────────────────┼───────────────────┤
│ Лёша 2                          │ 123-1231-123      │
├─────────────────────────────────┼───────────────────┤
│ Павел 4                         │ 57-88-325         │
└─────────────────────────────────┴───────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 1
┌───────────────────────────┐
│ ДОБАВЛЕНИЕ  НОВОЙ  ЗАПИСИ │
└───────────────────────────┘
Имя (ФИО) :
телефон   : 4454454
┌─────────────────┐
│  У С П Е Ш Н О  │
└─────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 1
┌───────────────────────────┐
│ ДОБАВЛЕНИЕ  НОВОЙ  ЗАПИСИ │
└───────────────────────────┘
Имя (ФИО) : кто-то с домашнего
телефон   :
┌─────────────────┐
│  У С П Е Ш Н О  │
└─────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 6
Некорректный ввод. Попробуйте ещё раз: 7
Некорректный ввод. Попробуйте ещё раз: ываыва
Некорректный ввод. Попробуйте ещё раз: 3
┌──────────────────────────────────────────────┐
│ СОРТИРОВКА ПО КОЛИЧЕСТВУ НОМЕРОВ НА ЧЕЛОВЕКА │
└──────────────────────────────────────────────┘
┌─────────────────────────────────┬───────────────────┐
│ ИМЯ                             │ ТЕЛЕФОН           │
├─────────────────────────────────┼───────────────────┤
│ Лёша                            │ 288-28-28         │
│                                 │ 12121212          │
│                                 │ 2165-123-465      │
│                                 │ +38(050)653-01-13 │
├─────────────────────────────────┼───────────────────┤
│ Саша Р.                         │ 125-2574-124      │
│                                 │ 8(921)578-15-12   │
│                                 │ +1(9512)125-45-12 │
│                                 │ (495)548-12-12    │
├─────────────────────────────────┼───────────────────┤
│ Неизвестный                     │ - - -             │
│                                 │ 2323              │
│                                 │ 4454454           │
├─────────────────────────────────┼───────────────────┤
│ Вася                            │ 8(956)288-28-28   │
│                                 │ 12121212          │
├─────────────────────────────────┼───────────────────┤
│ Краснов В.А.                    │ 23123123123       │
│                                 │ 54984-165-4654    │
├─────────────────────────────────┼───────────────────┤
│ Галя                            │ 57-47-22          │
│                                 │ 288-28-28         │
├─────────────────────────────────┼───────────────────┤
│ Ктото с домашнего               │ - - -             │
├─────────────────────────────────┼───────────────────┤
│ Луначарская Ульяна Вячеславовна │ 8(800)2000-500    │
├─────────────────────────────────┼───────────────────┤
│ Дашенька                        │ - - -             │
├─────────────────────────────────┼───────────────────┤
│ Бехтерев Владимир Валентинович  │ +7(987)852-52-25  │
├─────────────────────────────────┼───────────────────┤
│ Павел 4                         │ 57-88-325         │
├─────────────────────────────────┼───────────────────┤
│ Кононенко А.В.                  │ 959-52-25         │
├─────────────────────────────────┼───────────────────┤
│ Лёша 2                          │ 123-1231-123      │
└─────────────────────────────────┴───────────────────┘
(1) добавить новую запись
(2) распечатать справочник
(3) распечатать справочник, сортированный по количеству номеров на человека
(4) ВЫХОД
Выбор: 4
┌───────────────────────────────────────────────────┐
│ Спасибо за использование программы. Хорошего дня! │
└───────────────────────────────────────────────────┘

 */