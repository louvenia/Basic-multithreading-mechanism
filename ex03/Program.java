package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final String filesUrls = "./ex03/files_urls.txt";
    private static Object[] linesFile;
    private static final List<Thread> listThread = new ArrayList<>();
    private static int numberThreads;


    public static void main(String[] args) throws IOException {
        validationArgument(args);
        validationNumberThreads(args);

        BufferedReader fileReader = new BufferedReader(new FileReader(filesUrls));
        linesFile = fileReader.lines().toArray();
        if(linesFile.length == 0) {
            errorMessage("File is empty");
        }

        Consumer consumer = new Consumer(linesFile);
        createThreads(consumer);

        for(Thread thread : listThread) {
            thread.start();
        }

        fileReader.close();
    }

    private static void validationArgument(String[] args) {
        if(args.length != 1) {
            errorMessage("Invalid number of elements");
        } else if(!args[0].startsWith("--threadsCount=")) {
            errorMessage("Invalid program argument entry. Example: --threadsCount=n (number of threads)");
        }
    }

    private static void validationNumberThreads(String[] args) {
        try {
            numberThreads = Integer.parseInt(args[0].substring("--threadsCount=".length()));
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }
        if(numberThreads < 1) {
            errorMessage("The number of threads must be positive and greater than zero");
        }
    }

    private static void errorMessage(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

    private static void createThreads(Consumer consumer) {
        Thread thread;
        for(int i = 0; i < numberThreads; i++) {
            thread = new Thread(new Producer(consumer, i + 1, linesFile.length));
            listThread.add(thread);
        }
    }
}
