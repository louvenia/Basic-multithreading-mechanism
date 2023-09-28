package ex01;

public class Egg implements Runnable {
    private final Data dt;
    private final int number;

    public Egg(int number, Data dt) {
        this.dt = dt;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            dt.egg(number);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }
}
