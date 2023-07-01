//Урок 2. Почему вы не можете не использовать API
//Формат сдачи: ссылка на подписанный git-проект.
//
//Задание
//
//1) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя
//StringBuilder или String. Данные для фильтрации приведены ниже в виде json-строки.
//Если значение null, то параметр не должен попадать в запрос.
//Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
//
//Дополнительные задания
//
//2) Дана json-строка (можно сохранить в файл и читать из файла)
//[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
// {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
// {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
//Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида:
//Студент [фамилия] получил [оценка] по предмету [предмет].
//Пример вывода:
//Студент Иванов получил 5 по предмету Математика.
//Студент Петрова получил 4 по предмету Информатика.
//Студент Краснов получил 5 по предмету Физика.

import java.io.*;

public class seminar2_home {

    public static String readFile(File fileToRead) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(fileToRead));
            String line = myReader.readLine();

            while (line != null) {
                result.append(line);
                line = myReader.readLine();
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("READ ERROR");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.toString();
    }

    public static String parseStringToSQL(String data){
        data = data.replaceAll("[{},\"]", "");
        StringBuilder result = new StringBuilder();

        String[] pairs = data.split(" ");
        String field = null;
        String value = null;

        for (int i = 0; i < pairs.length; i++) {
            field = pairs[i].split(":")[0];
            value = pairs[i].split(":")[1];
            if (!value.equals("null")) {
                result.append(field);
                result.append("='");
                result.append(value);
                result.append("', ");
            }
        }

        result.replace(result.length() - 2, result.length() - 1, "");
        return result.toString();
    }


    public static void parseJsonToPhrases(String data){
        StringBuilder phrase = new StringBuilder();

        // делим json на строки, в каждой ихз которых инфо по 1 студенту
        String[] jsonLines = data.split("\\Q},{\\E"); // между  \\Q и \\E всё принимается так, как печатается
        String[] pairs;

        // пробегаем по каждой строке
        for (int i = 0; i < jsonLines.length; i++){
            // чистим от скобок и кавычек
            jsonLines[i] = jsonLines[i].replaceAll("[\\[\\]\\{\\}\"]", "");
            // делим на пары по ЗАПЯТОЙ - в каждой строке получается по 3 пары поле-значение
            pairs = jsonLines[i].split(",");
            // удаляем сожержимое StringBuilder перед каждой новой фразой
            phrase.setLength(0);
            // в фразу попадает второе слово из трёх пар (только значение)
            phrase.append("Студент ");
            phrase.append(pairs[0].split(":")[1]);
            phrase.append(" получил(а) ");
            phrase.append(pairs[1].split(":")[1]);
            phrase.append(" по предмету ");
            phrase.append(pairs[2].split(":")[1]);
            phrase.append(".");
            System.out.println("* " + phrase.toString());
        }
    }


    public static void main(String[] args) {

        System.out.println("\n======= ПАРСИНГ ФАЙЛА 1 =======");
        File dataFile = new File("sem2_data1.txt");
        String data = readFile(dataFile);
        System.out.println("\nСтрока, полученная из файла:\n" + data);

        StringBuilder sqlRequest = new StringBuilder();
        sqlRequest.append("SELECT * FROM students WHERE ");
        sqlRequest.append(parseStringToSQL(data));

        System.out.println("Сформированный SQL запрос:\n" + sqlRequest);


        System.out.println("\n======= ПАРСИНГ ФАЙЛА 2 =======");
        dataFile = new File("sem2_data2.txt");
        data = readFile(dataFile);
        System.out.println("\nСтрока, полученная из файла:\n" + data);

        System.out.println("Фразы, полученные после обработки данных:");
        parseJsonToPhrases(data);
    }
/*
======= ПАРСИНГ ФАЙЛА 1 =======

Строка, полученная из файла:
{"surname":"Ivanov", "name":"null", "country":"Russia", "city":"Moscow", "age":"null"}
Сформированный SQL запрос:
SELECT * FROM students WHERE surname='Ivanov', country='Russia', city='Moscow'

======= ПАРСИНГ ФАЙЛА 2 =======

Строка, полученная из файла:
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Фразы, полученные после обработки данных:
* Студент Иванов получил(а) 5 по предмету Математика.
* Студент Петрова получил(а) 4 по предмету Информатика.
* Студент Краснов получил(а) 5 по предмету Физика.

*/

}
