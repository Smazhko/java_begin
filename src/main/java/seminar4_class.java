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
                    printList(personsList);
                    break;
                case 3:
                    //вывести сортированных по фио
                    System.out.println("=".repeat(60));
                    System.out.println("========== СПИСОК ПРЕМИРУЕМЫХ (порядок: по ФИО) ==========");
                    sortListByName(personsList);
                    printList(personsList);
                    break;
                case 4:
                    //вывести сортированных по возрасту
                    System.out.println("=".repeat(60));
                    System.out.println("======  СПИСОК ПРЕМИРУЕМЫХ (порядок: по возрасту)  ========");
                    sortListbyAge(personsList);
                    printList(personsList);
                    break;
                case 5:
                    //вывести сортированных по полу и возрасту
                    System.out.println("=".repeat(60));
                    System.out.println("===  СПИСОК ПРЕМИРУЕМЫХ (порядок: по полу и возрасту)  =====");
                    sortListbyAge(personsList);
                    sortListbySex(personsList);
                    printList(personsList);
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


    public static void printList(List<String[]> personsList){
        for (String[] person: personsList) {
            String[] name = person[1].split(" ");
            System.out.printf("ФИО: %-20s ", name[0] + " " + name[1].charAt(0) + "." + name[2].charAt(0) + ".");
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
                    fwriter.write(person[0] + "," + person[1] + "," + person[2] + person[3] + "\n");
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
Фамилия Имя Отчество : Домашевский Владислав Александрович
Возраст (целое число): 24
Пол (м / ж)          : м
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
ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
ФИО: Иванов И.И.          Возраст: 25   Пол: М
ФИО: Кошовой А.В.         Возраст: 21   Пол: М
ФИО: Жаров В.В.           Возраст: 45   Пол: М
ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
ФИО: Домашевский В.А.     Возраст: 24   Пол: М
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
ФИО: Домашевский В.А.     Возраст: 24   Пол: М
ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
ФИО: Жаров В.В.           Возраст: 45   Пол: М
ФИО: Иванов И.И.          Возраст: 25   Пол: М
ФИО: Кошовой А.В.         Возраст: 21   Пол: М
ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
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
ФИО: Кошовой А.В.         Возраст: 21   Пол: М
ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
ФИО: Домашевский В.А.     Возраст: 24   Пол: М
ФИО: Иванов И.И.          Возраст: 25   Пол: М
ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
ФИО: Жаров В.В.           Возраст: 45   Пол: М
ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
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
ФИО: Сташко Е.К.          Возраст: 22   Пол: Ж
ФИО: Усманова Э.Э.        Возраст: 27   Пол: Ж
ФИО: Селиванова О.М.      Возраст: 28   Пол: Ж
ФИО: Осадчая М.П.         Возраст: 29   Пол: Ж
ФИО: Мокроусова Е.Л.      Возраст: 45   Пол: Ж
ФИО: Кошовой А.В.         Возраст: 21   Пол: М
ФИО: Домашевский В.А.     Возраст: 24   Пол: М
ФИО: Иванов И.И.          Возраст: 25   Пол: М
ФИО: Чагарный Д.В.        Возраст: 32   Пол: М
ФИО: Мокроусов В.С.       Возраст: 39   Пол: М
ФИО: Домашевский Р.П.     Возраст: 40   Пол: М
ФИО: Жаров В.В.           Возраст: 45   Пол: М
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