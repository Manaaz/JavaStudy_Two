package lesson2;

import java.sql.Array;

public class Main{

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        int count = 1;
        String[][] arr = initializeTestArray1();
        testMyArrayToExceptions(arr, count);
        count++;

        arr = initializeTestArray1();
        testMyArrayToExceptions(arr, count);
        count++;

        arr = initializeTestArray2();
        testMyArrayToExceptions(arr, count);
        count++;

        arr = initializeTestArray3();
        testMyArrayToExceptions(arr, count);
        count++;

        arr = initializeTestArray4();
        testMyArrayToExceptions(arr, count);
        count++;

        arr = initializeTestArray5();
        testMyArrayToExceptions(arr, count);
        count++;

    }

    public static void testMyArrayToExceptions(String[][] arr, int tryCount) {
        System.out.println("try " + tryCount + ":");
        try {
            reviewArrayToErrors(arr);
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Массив без ошибок.");
    }

    public static String[][] initializeTestArray1() {

        String[][] arr = new String[4][4];

        arr[0] = new String[]{"1","2","3","4"};
        arr[1] = new String[]{"test","2","3","4"};
        arr[2] = new String[]{"1","test","3"};
        arr[3] = new String[]{"1","2","3","4"};

        return arr;
    }

    public static String[][] initializeTestArray2() {

        String[][] arr = new String[4][4];

        arr[0] = new String[]{"1","2","3","4"};
        arr[1] = new String[]{"1","2","3","4"};
        arr[2] = new String[]{"1","test","3"};
        arr[3] = new String[]{"1","2","3","4"};

        return arr;
    }

    public static String[][] initializeTestArray3() {

        String[][] arr = new String[4][4];

        arr[0] = new String[]{"1","2","3","4"};
        arr[1] = new String[]{"1","2","3","4"};
        arr[2] = new String[]{"1","2","3"};
        arr[3] = new String[]{"1","2","3","4"};

        return arr;
    }
    public static String[][] initializeTestArray4() {

        String[][] arr = new String[4][4];

        arr[0] = new String[]{"1","2","3","4"};
        arr[1] = new String[]{"1","2","3","4"};
        arr[2] = new String[]{"1","2","3","4"};

        return arr;
    }
    public static String[][] initializeTestArray5() {

        String[][] arr = new String[4][4];

        arr[0] = new String[]{"1","2","3","4"};
        arr[1] = new String[]{"1","2","3","4"};
        arr[2] = new String[]{"1","2","3","4"};
        arr[3] = new String[]{"1","2","3","4"};

        return arr;
    }
    public static void reviewArrayToErrors(String[][] arr) throws MyArrayDataException, MyArraySizeException  {

        if(arr.length < 4) {
            throw new MyArraySizeException("Ошибка. Принятый массив arr содержит < 4 строк.");
        }

        for (int i = 0; i < 4; i++) {

            if (arr[i].length < 4) {
                throw new MyArraySizeException("Ошибка. Принятый массив arr["+i+"] содержит < 4 элементов.");
            }

            for (int j = 0; j < 4; j++) {

                String nextStringNumber = arr[i][j];

                if (!isNumeric(nextStringNumber)) {
                    throw new MyArrayDataException(
                            "Ошибка. В принятом массиве, элемент arr["+i+"]["+j+"] = '"+nextStringNumber+"' не является числом");
                }

            }

        }

    }

    private static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }

        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
