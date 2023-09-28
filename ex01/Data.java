package ex01;

 public class Data {
    private boolean threadFlag = false;

    public void egg(int number) throws InterruptedException {
        for(int i = 0; i < number; i++) {
            synchronized (this) {
                while (threadFlag) {
                    wait();
                }
                System.out.println("Egg");
                threadFlag = true;
                notify();
                Thread.sleep(1);
            }
        }
    }

    public void hen(int number) throws InterruptedException {
        for(int i = 0; i < number; i++) {
            synchronized (this) {
                while (!threadFlag) {
                    wait();
                }
                System.out.println("Hen");
                threadFlag = false;
                notify();
                Thread.sleep(1);
            }
        }
    }
}