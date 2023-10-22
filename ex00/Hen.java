package ex00;

public class Hen implements Runnable {
    private final int number;

    public Hen(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for(int i = 0; i < number; i++) {
            System.out.println("Hen");
        }
    }
}
