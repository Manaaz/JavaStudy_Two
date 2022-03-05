package lesson5;

public class Main {

    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];
    float[] arr2 = arr.clone();

    public static void main(String[] args) throws InterruptedException {

        //quest 1:
        ThreadCounter counter = new ThreadCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.decrement();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(counter.getValue());


        //quest 2:
        ThreadHome.firstMethod();
        ThreadHome.secondMethod();
    }

}
