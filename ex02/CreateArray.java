package ex02;

public class CreateArray {
    private static final int MAX_VALUE = 1000;
    private final int[] arr;
    private final int size;

    public CreateArray(int size) {
        this.size = size;
        arr = new int[size];
    }
    public void fillingArray() {
        for(int i = 0; i < size; i++) {
            arr[i] = ((int)(Math.random() * 2001)) - MAX_VALUE;
        }
    }

    public int calculateSum() {
        int sum = 0;
        for(int i = 0; i < size; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public int getSize() {
        return size;
    }

    public int[] getArr() {
        return arr;
    }
}