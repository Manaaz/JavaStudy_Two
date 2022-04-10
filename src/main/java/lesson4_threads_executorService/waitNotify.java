package lesson4_threads_executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class waitNotify {

    private final Object mon = new Object();
    private volatile String currentLetter = "A";

    static String[] items = {"A", "B", "C"};
    static int[] counters = {0, 0, 0};
    static int thisCout = 0;
    final static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void main() {
        waitNotify waitNotifyObj = new waitNotify();

        for(int i = 0; i < 3; i++) {
            int k = i;
            executorService.execute(() -> {
                try {
                    waitNotifyObj.printLetter(items[k]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    public void printLetter(String letter) throws InterruptedException {

        synchronized (mon) {
            try {
                while(true) {
                    if (currentLetter.equals(letter)) {
                        mon.wait();
                    }

                    if (counters[thisCout] == 5) {
                        break;
                    }

                    System.out.print(items[thisCout]);
                    counters[thisCout]++;

                    if (thisCout + 1 == items.length) {
                        thisCout = 0;
                    } else {
                        thisCout++;
                    }

                    currentLetter = items[thisCout];

                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
