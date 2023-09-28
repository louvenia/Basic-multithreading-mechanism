package ex01;

public class Hen implements Runnable {
    private final Data dt;
    private final int number;

    public Hen(int number, Data dt) {
        this.dt = dt;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            dt.hen(number);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }
}
