package ex02;

public class CalcThread extends Thread {
    private final int[] arr;
    private final int id;
    private final int start;
    private final int end;
    private int sum;

    public CalcThread(int[] arr, int id, int start, int end) {
        this.id = id;
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public int getSum() {
        return sum;
    }

    public void printThread() {
        System.out.println("Thread " + id + ": from " + start + " to " + (end - 1) + " sum is " + sum);
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        printThread();
    }
}
