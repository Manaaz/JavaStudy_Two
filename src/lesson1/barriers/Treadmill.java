package lesson1.barriers;

import lesson1.interfaces.Barrier;
import lesson1.interfaces.Person;

public class Treadmill implements Barrier {

    final int distance;
    final String barrierName;

    public Treadmill(int treadmillDistance, String treadmillBarrierName) {
        this.distance = treadmillDistance;
        this.barrierName = treadmillBarrierName;
    }

    @Override
    public void pass(Person person) {
        System.out.print("barrier: " + barrierName + ", distance: " + distance + ". Result: ");
        person.run(distance);
    }
}
