package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final int CARS_HALF_COUNTER = CARS_COUNT/2;
    public static String raceWinner = "";

    final static CountDownLatch cdlStartRace = new CountDownLatch(CARS_COUNT);
    final static CountDownLatch cdlEndRace = new CountDownLatch(CARS_COUNT);
    final static Semaphore smpTunnel = new Semaphore(CARS_HALF_COUNTER);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(smpTunnel), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(  race,
                                20 + (int) (Math.random() * 10),
                                cdlStartRace,
                                cdlEndRace);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        cdlStartRace.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        cdlEndRace.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("Победитель: " + raceWinner);
    }
}
