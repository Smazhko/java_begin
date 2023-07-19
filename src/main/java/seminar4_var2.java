/*
Урок 4. Хранение и обработка данных ч1: приоритетные коллекции
Организовать ввод и хранение данных пользователей. ФИО возраст, пол и выход из режима ввода информации
вывод в формате Фамилия И.О. возраст пол
добавить возможность выхода или вывода списка отсортированного по возрасту!)
    *реализовать сортировку по возрасту с использованием индексов
    *реализовать сортировку по возрасту и полу с использованием индексов
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class seminar4_var2 {
    public static void main(String[] args) {

        ArrayList<String> surnamesList = new ArrayList<>(Arrays.asList(
                "Корнеева",
                "Иванов",
                "Кузнецова",
                "Гусарев",
                "Гончарова",
                "Панковецкий",
                "Рыбакова",
                "Кончаловский",
                "Дмитриева",
                "Жуковский"));
        ArrayList<String> namesList = new ArrayList<>(Arrays.asList(
                "Ирина",
                "Евгений",
                "Ольга",
                "Сергей",
                "Анастасия",
                "Николай",
                "Людмила",
                "Александр",
                "Дарья",
                "Дмитрий"));
        ArrayList<String> fathersnamesList = new ArrayList<>(Arrays.asList(
                "Евгеньевна",
                "Олегович",
                "Сергеевна",
                "Владимирович",
                "Владимировна",
                "Владимирович",
                "Алексеевна",
                "Константинович",
                "Никитична",
                "Федорович"));
        ArrayList<String> ageList = new ArrayList<>(Arrays.asList(
                "21",
                "23",
                "29",
                "30",
                "30",
                "30",
                "29",
                "23",
                "22",
                "34"));
        ArrayList<String> genderList = new ArrayList<>(Arrays.asList(
                "Ж",
                "М",
                "Ж",
                "М",
                "Ж",
                "М",
                "Ж",
                "М",
                "Ж",
                "М"));
        boolean breakFlag = false;
        Scanner input = new Scanner(System.in);

        while (!breakFlag){
            System.out.print("Добавить новый элемент списка? (введите 1, если ДА, 0 - если НЕТ) ... ");
            String userChoise = input.nextLine();
            if (userChoise.contains("1")){
                addRecord(surnamesList, namesList, fathersnamesList, ageList, genderList);
            } else if (userChoise.contains("0")) {
                breakFlag = true;
            }
        }

        System.out.println("СПИСОК БЕЗ СОРТИРОВКИ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList);
        System.out.println("СОРТИРОВКА ПО ФАМИЛИИ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList, byOrder(surnamesList));
        System.out.println("СОРТИРОВКА ПО ВОЗРАСТУ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList, byOrder(ageList));
        System.out.println("СОРТИРОВКА ПО ПОЛУ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList, byOrder(genderList));
        System.out.println("СОРТИРОВКА ПО ПОЛУ и ФАМИЛИИ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList, byOrder(genderList, surnamesList));
        System.out.println("СОРТИРОВКА ПО ПОЛУ и ВОЗРАСТУ:");
        printList(surnamesList, namesList, fathersnamesList, ageList, genderList, byOrder(genderList, ageList));

    }


    public static void addRecord (List<String> surnamesLst,
                                  List<String> namesLst,
                                  List<String> fathersnamesLst,
                                  List<String> ageLst,
                                  List<String> genderLst){
        Scanner input = new Scanner(System.in);

        System.out.print("Введите Фамилию  : ");
        String newSurname = input.nextLine();
        if (!newSurname.isEmpty()){
            newSurname = newSurname.substring(0,1).toUpperCase() + newSurname.substring(1).toLowerCase();
        } else {
            newSurname = "Инкогнито";
        }

        System.out.print("Введите Имя      : ");
        String newName = input.nextLine();
        if (!newName.isEmpty()){
            newName = newName.substring(0,1).toUpperCase() + newName.substring(1).toLowerCase();
        } else {
            newName = "Мистер";
        }

        System.out.print("Введите Отчество : ");
        String newFathersname = input.nextLine();
        if (!newFathersname.isEmpty()){
            newFathersname = newSurname.substring(0,1).toUpperCase() + newFathersname.substring(1).toLowerCase();
        } else {
            newFathersname = "Икс";
        }

        System.out.print("Введите возраст  : ");
        String newAge = input.nextLine();
        if (newAge.isEmpty()){
            newAge = "25";
        }
        System.out.print("Введите пол      : ");
        String newGender = input.nextLine();
        if (newGender.isEmpty()){
            newGender = "М";
        }
        surnamesLst.add(newSurname);
        namesLst.add(newName);
        fathersnamesLst.add(newFathersname);
        ageLst.add(newAge);
        genderLst.add(newGender);
    }


    public static int[] byOrder(List<String> listToSort){
        int size = listToSort.size();
        int[] foundIndex = new int[size];
        ArrayList<String> listBeforeSort = new ArrayList<>(listToSort);
        ArrayList<String> listAfterSort  = new ArrayList<>(listToSort);

        listAfterSort.sort(String::compareTo);

        for (int i = 0; i < size; i++) {
            foundIndex[i] = listBeforeSort.indexOf(listAfterSort.get(i));
            listBeforeSort.set(foundIndex[i], null);
        }
        return foundIndex;
    }


    public static int[] byOrder(List<String> importantList,
                                List<String> secondList){
        int size = importantList.size();
        int[] foundIndex = new int[size];
        ArrayList<String> listBeforeSort = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            listBeforeSort.add(importantList.get(i)+secondList.get(i));
        }

        ArrayList<String> listAfterSort  = new ArrayList<>(listBeforeSort);

        listAfterSort.sort(String::compareTo);

        for (int i = 0; i < size; i++) {
            foundIndex[i] = listBeforeSort.indexOf(listAfterSort.get(i));
            listBeforeSort.set(foundIndex[i], null);
        }
        return foundIndex;
    }


    public static void printList(List<String> surnamesLst,
                                 List<String> namesLst,
                                 List<String> fathersnamesLst,
                                 List<String> ageLst,
                                 List<String> genderLst){
        int size = namesLst.size();
        int[] sortOrder = new int[size];
        for (int i = 0; i < size; i++) {sortOrder[i] = i;}
        printList(surnamesLst, namesLst, fathersnamesLst, ageLst, genderLst, sortOrder);
    }


    public static void printList(List<String> surnamesLst,
                                 List<String> namesLst,
                                 List<String> fathersnamesLst,
                                 List<String> ageLst,
                                 List<String> genderLst,
                                 int[] sortOrder){
        int size = surnamesLst.size();
        for (int i = 0; i < size; i++) {
            System.out.printf("%-20s %-3s %s %n",
                    surnamesLst.get(sortOrder[i]) + " "
                            + namesLst.get(sortOrder[i]).charAt(0) + "."
                            + fathersnamesLst.get(sortOrder[i]).charAt(0) + ".",
                    ageLst.get(sortOrder[i]),
                    genderLst.get(sortOrder[i]));
        }
        System.out.println("-=".repeat(20) + "КОНЕЦ СПИСКА");
    }
}

/*
Добавить новый элемент списка? (введите 1, если ДА, 0 - если НЕТ) ... 1
Введите Фамилию  :
Введите Имя      :
Введите Отчество : oinoin
Введите возраст  : 45
Введите пол      : М
Добавить новый элемент списка? (введите 1, если ДА, 0 - если НЕТ) ... 0
СПИСОК БЕЗ СОРТИРОВКИ:
Корнеева И.Е.        21  Ж
Иванов Е.О.          23  М
Кузнецова О.С.       29  Ж
Гусарев С.В.         30  М
Гончарова А.В.       30  Ж
Панковецкий Н.В.     30  М
Рыбакова Л.А.        29  Ж
Кончаловский А.К.    23  М
Дмитриева Д.Н.       22  Ж
Жуковский Д.Ф.       34  М
Инкогнито М.И.       45  М
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
СОРТИРОВКА ПО ФАМИЛИИ:
Гончарова А.В.       30  Ж
Гусарев С.В.         30  М
Дмитриева Д.Н.       22  Ж
Жуковский Д.Ф.       34  М
Иванов Е.О.          23  М
Инкогнито М.И.       45  М
Кончаловский А.К.    23  М
Корнеева И.Е.        21  Ж
Кузнецова О.С.       29  Ж
Панковецкий Н.В.     30  М
Рыбакова Л.А.        29  Ж
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
СОРТИРОВКА ПО ВОЗРАСТУ:
Корнеева И.Е.        21  Ж
Дмитриева Д.Н.       22  Ж
Иванов Е.О.          23  М
Кончаловский А.К.    23  М
Кузнецова О.С.       29  Ж
Рыбакова Л.А.        29  Ж
Гусарев С.В.         30  М
Гончарова А.В.       30  Ж
Панковецкий Н.В.     30  М
Жуковский Д.Ф.       34  М
Инкогнито М.И.       45  М
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
СОРТИРОВКА ПО ПОЛУ:
Корнеева И.Е.        21  Ж
Кузнецова О.С.       29  Ж
Гончарова А.В.       30  Ж
Рыбакова Л.А.        29  Ж
Дмитриева Д.Н.       22  Ж
Иванов Е.О.          23  М
Гусарев С.В.         30  М
Панковецкий Н.В.     30  М
Кончаловский А.К.    23  М
Жуковский Д.Ф.       34  М
Инкогнито М.И.       45  М
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
СОРТИРОВКА ПО ПОЛУ и ФАМИЛИИ:
Гончарова А.В.       30  Ж
Дмитриева Д.Н.       22  Ж
Корнеева И.Е.        21  Ж
Кузнецова О.С.       29  Ж
Рыбакова Л.А.        29  Ж
Гусарев С.В.         30  М
Жуковский Д.Ф.       34  М
Иванов Е.О.          23  М
Инкогнито М.И.       45  М
Кончаловский А.К.    23  М
Панковецкий Н.В.     30  М
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
СОРТИРОВКА ПО ПОЛУ и ВОЗРАСТУ:
Корнеева И.Е.        21  Ж
Дмитриева Д.Н.       22  Ж
Кузнецова О.С.       29  Ж
Рыбакова Л.А.        29  Ж
Гончарова А.В.       30  Ж
Иванов Е.О.          23  М
Кончаловский А.К.    23  М
Гусарев С.В.         30  М
Панковецкий Н.В.     30  М
Жуковский Д.Ф.       34  М
Инкогнито М.И.       45  М
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=КОНЕЦ СПИСКА
 */