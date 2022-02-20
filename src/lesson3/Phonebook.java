package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {

    HashMap<String, ArrayList<String>> phonebookData = new HashMap<>();

    public void add(String name, String phoneNumber) {

        ArrayList<String> elementList = phonebookData.get(name);

        if(elementList != null) {
            elementList.add(phoneNumber);
        } else {
            elementList = new ArrayList<String>();
            elementList.add(phoneNumber);
            phonebookData.put(name, elementList);
        }

    }

    public String get(String name) {

        ArrayList<String> elementList = phonebookData.get(name);
        return elementList.toString();

    }
}
