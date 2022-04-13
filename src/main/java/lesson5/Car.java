package lesson5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    final private Race race;
    final  private int speed;
    final private String name;
    final private CountDownLatch cdlStart;
    final private CountDownLatch cdlEnd;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch cdlStart, CountDownLatch cdlEnd) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cdlStart = cdlStart;
        this.cdlEnd = cdlEnd;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlStart.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cdlStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if(MainClass.raceWinner.isEmpty()) {
            MainClass.raceWinner = getName();
        }
        cdlEnd.countDown();

    }
}