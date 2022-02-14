package lesson1.persons;

import lesson1.interfaces.Person;

public class Cat implements Person {

    final private int maxDistanceRun ;
    final private int maxDistanceJump ;
    final private String name ;
    private boolean readyToBarrier;

    public Cat(String personName, int personDistanceRun, int personDistanceJump) {
        this.name               = personName;
        this.maxDistanceRun     = personDistanceRun;
        this.maxDistanceJump    = personDistanceJump;
        this.readyToBarrier     = true;
    }

    @Override
    public void run(int distance) {
        String result = getResultOfPass(distance, maxDistanceRun, "run");
        if (!result.isEmpty()) {
            System.out.println(result);
        }
    }

    @Override
    public void jump(int distance) {
        String result = getResultOfPass(distance, maxDistanceJump, "jump");
        if (!result.isEmpty()) {
            System.out.println(result);
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean getStatus() {
        return readyToBarrier;
    }

    @Override
    public String getResultOfPass(int distance, int maxDistance, String action) {
        String result = "couldn't " + action + ". " + name + " stop.";
        if (readyToBarrier && distance <= maxDistance) {
            result = action;
        } else if (readyToBarrier) {
            readyToBarrier = false;
        }
        return result;
    }
}
