package lesson1.interfaces;

public interface Person {
    void run(int distance);
    void jump(int distance);
    String getName();
    boolean getStatus();
    String getResultOfPass(int distance, int maxDistance, String action);
}
