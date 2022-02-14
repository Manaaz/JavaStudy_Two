package lesson1.persons;

import lesson1.interfaces.Person;

public class Robot implements Person {

    final private int maxDistanceRun ;
    final private String name ;
    private boolean readyToBarrier;

    public Robot(String personName, int personDistanceRun) {
        this.name               = personName;
        this.maxDistanceRun     = personDistanceRun;
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
        readyToBarrier = false;
        System.out.println("Robots can`t jump!");
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
