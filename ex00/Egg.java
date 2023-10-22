package ex00;

public class Egg implements Runnable {
    private final int number;

    public Egg(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for(int i = 0; i < number; i++) {
            System.out.println("Egg");
        }
    }
}
