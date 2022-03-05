package lesson5;

import java.util.Arrays;

public class ThreadHome {

    public static void firstMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();

        // Создаем два массива для левой и правой части исходного
        int arrSize = arr.length;
        int arrHalfSize = arrSize/2;

        float[] leftHalf = new float[arrHalfSize];
        float[] rightHalf = new float[arrHalfSize];

        // Копируем в них значения из большого массива
        System.arraycopy(arr, 0, leftHalf, 0, arrHalfSize);
        System.arraycopy(arr, arrHalfSize, rightHalf, 0, arrHalfSize);

        // Запускает два потока и параллельно просчитываем каждый малый массив

        CalcModifyArr modifyArrLeft = new CalcModifyArr(leftHalf);
        CalcModifyArr modifyArrRight = new CalcModifyArr(rightHalf);

        Thread thread1 = new Thread(() -> {
            modifyArrLeft.calculateArrNumbers();
        });

        Thread thread2 = new Thread(() -> {
            modifyArrRight.calculateArrNumbers();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        leftHalf = modifyArrLeft.getArr();
        rightHalf = modifyArrRight.getArr();

        // Склеиваем малые массивы обратно в один большой
        float[] mergedArray = new float[arrSize];
        System.arraycopy(leftHalf, 0, mergedArray, 0, arrHalfSize);
        System.arraycopy(rightHalf, 0, mergedArray, arrHalfSize, arrHalfSize);

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }



}
