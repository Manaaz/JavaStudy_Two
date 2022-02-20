package lesson3;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        //quest 1:
        analyzeArray();

        //quest 2:
        Phonebook phonebook = new Phonebook();
        phonebook.add("Vasya", "111-11-11");
        phonebook.add("Vasya", "222-22-22");
        phonebook.add("Petya", "333-33-33");
        phonebook.add("Olya", "444-44-44");

        String phonebookName = "Vasya";
        System.out.println(phonebookName + " phone: " + phonebook.get(phonebookName));

        phonebookName = "Petya";
        System.out.println(phonebookName + " phone: " + phonebook.get(phonebookName));

        phonebookName = "Olya";
        System.out.println(phonebookName + " phone: " + phonebook.get(phonebookName));

    }

    static void analyzeArray() {

        String[] arr = {"Petya","Vasya","Petya","Olya","Sergey","Petya","Olya","Anton","Anya","Petya"};
        HashMap<String, Integer> uniqueItems = new HashMap<>();

        for (String element : arr) {

            int uniqueItemsElements = 1;
            if (uniqueItems.get(element) != null) {
                uniqueItemsElements = uniqueItems.get(element);
                uniqueItemsElements++;
            }

            uniqueItems.put(element, uniqueItemsElements);
        }

        System.out.println("Список уникальных значений с количеством повторов:");
        System.out.println(uniqueItems);

    }
}
