package lesson1.barriers;

import lesson1.interfaces.Barrier;
import lesson1.interfaces.Person;

public class Wall implements Barrier {

    final int distance;
    final String barrierName;

    public Wall(int wallDistance, String wallBarrierName) {
        this.distance = wallDistance;
        this.barrierName = wallBarrierName;
    }

    @Override
    public void pass(Person person) {
        System.out.print("barrier: " + barrierName + ", distance: " + distance + ". Result: ");
        person.jump(distance);
    }
}
