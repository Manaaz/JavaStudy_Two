package lesson5;

public class ThreadCounter {

    private int value;

    public int getValue() {
        return value;
    }

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

}
