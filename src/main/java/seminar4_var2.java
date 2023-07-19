import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
