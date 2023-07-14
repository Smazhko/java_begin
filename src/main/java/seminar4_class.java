//реализовать ввод фамилии, имени, отчества, возраста и пола
//вывести в порядке ввода
//сортировать по алфавиту
//сортировать по возрасту
//сортировать по полу и возрасту

import java.io.*;
import java.util.*;

public class seminar4_class {
    public static void main(String[] args) {
        System.out.println("\n======== РАБОТА СО СПИСКОМ ЛЮДЕЙ ДЛЯ ПРЕМИРОВАНИЯ  =========");
        File dataFile = new File("sem4_data.txt");

        ArrayList<String[]> personsList;
        personsList = loadListFromFile(dataFile);
        boolean continueFlag = true;

        while (continueFlag) {
            switch (getMenuSelection()) {
                case 1:
                    //добавить нового
                    personsList.add(getNewPerson(personsList));
                    saveListToFile(dataFile, personsList);
                    break;
                case 2:
                    //вывести на экран всех как есть
                    System.out.println("=".repeat(60));
                    System.out.println("==== СПИСОК ПРЕМИРУЕМЫХ (порядок: по мере добавления) =====");
                    sortListByID(personsList);
                    printList(personsList, false);
                    break;
                case 3:
                    //вывести сортированных по фио
                    System.out.println("=".repeat(60));
                    System.out.println("========== СПИСОК ПРЕМИРУЕМЫХ (порядок: по ФИО) ==========");
                    sortListByName(personsList);
                    printList(personsList, true);
                    break;
                case 4:
                    //вывести сортированных по возрасту
                    System.out.println("=".repeat(60));
                    System.out.println("======  СПИСОК ПРЕМИРУЕМЫХ (порядок: по возрасту)  ========");
                    sortListbyAge(personsList);
                    printList(personsList, false);
                    break;
                case 5:
                    //вывести сортированных по полу и возрасту
                    System.out.println("=".repeat(60));
                    System.out.println("===  СПИСОК ПРЕМИРУЕМЫХ (порядок: по полу и возрасту)  =====");
                    sortListbyAge(personsList);
                    sortListbySex(personsList);
                    printList(personsList, false);
                    break;
                case 6:
                    System.out.println("Премия уже ждёт! Увидимся! (^.^)");
                    continueFlag = false;
                    break;
                default:
                    System.out.println("Нет такого пункта в меню.");
            }
        }
    }


    public static Integer getMenuSelection(){
            String menu = """
            (1) добавить нового человека в список
            (2) вывести список (по порядку добавления)
            (3) вывести список, сортированный по ФИО
            (4) вывести список, сортированный возрасту
            (5) вывести список, сортированный по полу и возрасту
            (6) ВЫХОД
            """;
        System.out.print("=".repeat(60) + "\n" + menu);
        System.out.print("Выберите пункт меню: ");
        Scanner inputMenu = new Scanner(System.in);
        return inputMenu.nextInt();
    }


    public static String[] getNewPerson(List<String[]> personsList) {
        System.out.println("=".repeat(60));
        System.out.println("=========  ДОБАВЛЕНИЕ  НОВОГО  ЧЕЛОВЕКА  В  СПИСОК =========");
        String[] person = new String[4];
        Scanner userInput = new Scanner(System.in);
        person[0] = getNewID(personsList);
        System.out.print("Фамилия Имя Отчество : ");
        person[1] = userInput.nextLine().replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").strip(); // оставим в строке только буквы и пробелы и отрубим лишнее по краям
        System.out.print("Возраст (целое число): ");
        person[2] = userInput.nextLine().replaceAll("^\\D", "").strip();// оставляем только цифры
        System.out.print("Пол (м / ж)          : ");
        person[3] = userInput.nextLine().toUpperCase().replaceAll("[^МЖ]","").strip();
        return person;
    }


    public static String getNewID(List<String[]> personsList){
        int newID = 0;
        int size = personsList.size();
        List<Integer> idArray = new ArrayList<>();
        if (personsList.size() != 0){
            for (int i = 0; i < size; i++) {
                idArray.add(Integer.parseInt(personsList.get(i)[0]));
            }
            newID = Collections.max(idArray) + 1;
        }
        return String.valueOf(newID);
    }


    public static void printList(List<String[]> personsList, boolean fullNameFlag){
        int counter = 1;
        for (String[] person: personsList) {
            System.out.printf("%-4s", counter++);
            if (fullNameFlag){
                System.out.printf("ФИО: %-40s ", person[1]);
            } else{
                String[] name = person[1].split(" ");
                System.out.printf("ФИО: %-20s ", name[0] + " " + name[1].charAt(0) + "." + name[2].charAt(0) + ".");
            }

            System.out.printf("Возраст: %-3s  Пол: %1s", person[2], person[3]);
            System.out.println();
        }
    }


    public static void sortListByID (List<String[]> personsList){
        Collections.sort(personsList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
            }
        });
        //personsList.sort((o1, o2) -> o1[0].compareTo(o2[0])); //КОРОТКАЯ ЗАМЕНА ЛЯМБДОЙ
    }


    public static void sortListByName (List<String[]> personsList){
        Collections.sort(personsList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });
        //personsList.sort((o1, o2) -> o1[1].compareTo(o2[1])); //КОРОТКАЯ ЗАМЕНА ЛЯМБДОЙ
    }


    public static void sortListbyAge (List<String[]> personsList){
        Collections.sort(personsList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
            }
        });
        //personsList.sort((o1, o2) -> o1[2].compareTo(o2[2])); //КОРОТКАЯ ЗАМЕНА ЛЯМБДОЙ
    }

    public static void sortListbySex (List<String[]> personsList){
        Collections.sort(personsList, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[3].compareTo(o2[3]);
            }
        });
        // personsList.sort((o1, o2) -> o1[3].compareTo(o2[3])); //КОРОТКАЯ ЗАМЕНА ЛЯМБДОЙ
    }


    public static ArrayList<String[]> loadListFromFile(File dataFile) {
        ArrayList<String[]> personsList = new ArrayList<>();

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if(dataFile.canWrite()) {
            try {
                Scanner fileScanner = new Scanner(dataFile);

                while (fileScanner.hasNextLine()) {
                    personsList.add(fileScanner.nextLine().split(","));
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("READ ERROR");
                throw new RuntimeException(e);
            }
        }

        return personsList;
    }


    public static void saveListToFile(File dataFile, List<String[]> personsList){

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if(dataFile.canWrite()) {
            try (FileWriter fwriter = new FileWriter(dataFile, false)) {
                for (String[] person : personsList) {
                    fwriter.write(person[0] + "," + person[1] + "," + person[2] + "," + person[3] + "\n");
                }
                fwriter.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}

/*
======== РАБОТА СО СПИСКОМ ЛЮДЕЙ ДЛЯ ПРЕМИРОВАНИЯ  =========
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 1
============================================================
=========  ДОБАВЛЕНИЕ  НОВОГО  ЧЕЛОВЕКА  В  СПИСОК =========
Фамилия Имя Отчество : Хорева Кристина Олеговна
Возраст (целое число): 22
Пол (м / ж)          : ж
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 2
============================================================
==== СПИСОК ПРЕМИРУЕМЫХ (порядок: по мере добавления) =====
1   ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
2   ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
3   ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
4   ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
5   ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
6   ФИО: Иванов И.И.          Возраст: 25   Пол: М
7   ФИО: Кошовой А.В.         Возраст: 21   Пол: М
8   ФИО: Жаров В.В.           Возраст: 45   Пол: М
9   ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
10  ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
11  ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
12  ФИО: Домашевский В.А.     Возраст: 24   Пол: М
13  ФИО: Хорева К.О.          Возраст: 22   Пол: Ж
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 3
============================================================
========== СПИСОК ПРЕМИРУЕМЫХ (порядок: по ФИО) ==========
1   ФИО: Домашевский Владислав Александрович      Возраст: 24   Пол: М
2   ФИО: Домашевский Родион Петрович              Возраст: 40   Пол: М
3   ФИО: Жаров Владимир Венедиктович              Возраст: 45   Пол: М
4   ФИО: Иванов Иван Иванович                     Возраст: 25   Пол: М
5   ФИО: Кошовой Александр Владимирович           Возраст: 21   Пол: М
6   ФИО: Мокроусов Валерий Сергеевич              Возраст: 39   Пол: М
7   ФИО: Мокроусова Екатерина Леонидовна          Возраст: 45   Пол: Ж
8   ФИО: Осадчая Марина Петровна                  Возраст: 29   Пол: Ж
9   ФИО: Селиванова Ольга Михайловна              Возраст: 28   Пол: Ж
10  ФИО: Сташко Елена Константиновна              Возраст: 22   Пол: Ж
11  ФИО: Усманова Эльвира Экремовна               Возраст: 27   Пол: Ж
12  ФИО: Хорева Кристина Олеговна                 Возраст: 22   Пол: Ж
13  ФИО: Чагарный Дмитрий Викторович              Возраст: 32   Пол: М
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 4
============================================================
======  СПИСОК ПРЕМИРУЕМЫХ (порядок: по возрасту)  ========
1   ФИО: Кошовой А.В.         Возраст: 21   Пол: М
2   ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
3   ФИО: Хорева К.О.          Возраст: 22   Пол: Ж
4   ФИО: Домашевский В.А.     Возраст: 24   Пол: М
5   ФИО: Иванов И.И.          Возраст: 25   Пол: М
6   ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
7   ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
8   ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
9   ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
10  ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
11  ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
12  ФИО: Жаров В.В.           Возраст: 45   Пол: М
13  ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 5
============================================================
===  СПИСОК ПРЕМИРУЕМЫХ (порядок: по полу и возрасту)  =====
1   ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
2   ФИО: Хорева К.О.          Возраст: 22   Пол: Ж
3   ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
4   ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
5   ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
6   ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
7   ФИО: Кошовой А.В.         Возраст: 21   Пол: М
8   ФИО: Домашевский В.А.     Возраст: 24   Пол: М
9   ФИО: Иванов И.И.          Возраст: 25   Пол: М
10  ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
11  ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
12  ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
13  ФИО: Жаров В.В.           Возраст: 45   Пол: М
============================================================
(1) добавить нового человека в список
(2) вывести список (по порядку добавления)
(3) вывести список, сортированный по ФИО
(4) вывести список, сортированный возрасту
(5) вывести список, сортированный по полу и возрасту
(6) ВЫХОД
Выберите пункт меню: 6
Премия уже ждёт! Увидимся! (^.^)
 */