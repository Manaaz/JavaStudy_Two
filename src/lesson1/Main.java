package lesson1;

import lesson1.barriers.Treadmill;
import lesson1.barriers.Wall;
import lesson1.interfaces.Barrier;
import lesson1.interfaces.Person;
import lesson1.persons.Cat;
import lesson1.persons.Human;
import lesson1.persons.Robot;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        List<Person> personsList = initializePersons();
        List<Barrier> barriersList = initializeBarriers();

        for (Person nextPerson : personsList) {

            System.out.println("Person " + nextPerson.getName() + " start trials.");

            for (Barrier nextBarrier : barriersList) {

                if (nextPerson.getStatus()) {
                    nextBarrier.pass(nextPerson);
                } else {
                    break;
                }

            }

            System.out.println("Person " + nextPerson.getName() + " stop trials.");

        }

    }

    public static List<Person> initializePersons() {

        List<Person> personsList = new ArrayList<>();

        Person person = new Cat("Kuzya", 30, 2);
        personsList.add(person);
        person = new Cat("Vasya", 15, 3);
        personsList.add(person);
        person = new Cat("Sema", 30, 4);
        personsList.add(person);

        person = new Human("Petr Petrovich", 50, 1);
        personsList.add(person);
        person = new Human("Ilon Musk", 20, 2);
        personsList.add(person);
        person = new Human("Sergey Shumkov", 10, 3);
        personsList.add(person);

        person = new Robot("Robot vacuum cleaner 0001", 40);
        personsList.add(person);
        person = new Robot("Model courier 0002", 50);
        personsList.add(person);
        person = new Robot("Model run master 9000", 500);
        personsList.add(person);

        return personsList;
    }

    public static List<Barrier> initializeBarriers() {

        List<Barrier> barriersList = new ArrayList<>();
        int barrierNumber = 1;

        Barrier barrier = new Treadmill(5, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Treadmill(8, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Wall(1, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Treadmill(12, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Wall(2, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Treadmill(17, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Wall(1, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Treadmill(20, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Treadmill(25, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Wall(1, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        barrier = new Wall(3, "treadmill " + barrierNumber);
        barriersList.add(barrier);
        barrierNumber++;

        return barriersList;

    }

}
