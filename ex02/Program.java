package ex02;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private CreateArray arr;
    private final List<CalcThread> threadList = new ArrayList<>();

    public static void main(String[] args) {
        int sum = 0;
        int numberElements, numberThreads;
        Program p = new Program();

        p.validationArgument(args);
        numberElements = p.numberParse(args[0],"--arraySize=");
        numberThreads = p.numberParse(args[1], "--threadsCount=");
        p.validationParameters(numberElements, numberThreads);
        p.createArr(numberElements);
        p.addThreadList(numberElements, numberThreads);

        for (CalcThread th : p.threadList) {
            try {
                th.join();
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }

        for(CalcThread th : p.threadList) {
            sum += th.getSum();
        }

        System.out.println("Sum by threads: " + sum);
    }

    private void validationArgument(String[] args) {
        if(args.length != 2) {
            errorMessage("Invalid number of elements");
        } else if(!args[0].startsWith("--arraySize=")) {
            errorMessage("Invalid program argument entry. Example: --arraySize=n (number of array elements)");
        } else if(!args[1].startsWith("--threadsCount=")) {
            errorMessage("Invalid program argument entry. Example: --threadsCount=n (number of threads)");
        }
    }

    private int numberParse(String argument, String nameArg) {
        int number = 0;
        try {
            number = Integer.parseInt(argument.replace(nameArg, ""));
        } catch (RuntimeException error) {
            error.printStackTrace();
            System.exit(-1);
        }
        return number;
    }

    private void errorMessage(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

    private void validationParameters(int numberElements, int numberThreads) {
        if(numberElements < 1 || numberThreads < 1) {
            errorMessage("The number of array elements and threads must be positive and greater than zero");
        } else if(numberElements > 2000000 || numberThreads > numberElements) {
            errorMessage("The number of array elements cannot exceed 2,000,000, and the number of threads cannot exceed the number of array elements");
        }
    }

    private void createArr(int numberElements) {
        arr = new CreateArray(numberElements);
        arr.fillingArray();
        System.out.println("Sum: " + arr.calculateSum());
    }

    private void addThreadList(int numberElements, int numberThreads) {
        int start = 0, end = 0;
        CalcThread thread;
        int elementsThread = numberElements / numberThreads;
        for(int i = 1; i < numberThreads + 1; i++) {
            if (i != numberThreads) {
                if(i != 1) {
                    start = end;
                }
                end = start + elementsThread;
                thread = new CalcThread(arr.getArr(), i, start, end);
                threadList.add(thread);
                thread.start();
            } else {
                thread = new CalcThread(arr.getArr(), i, end, numberElements);
                threadList.add(thread);
                thread.start();
            }
        }
    }
}

