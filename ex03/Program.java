package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static final String filesUrls = "./ex03/files_urls.txt";
    private Object[] linesFile;
    private final List<Thread> listThread = new ArrayList<>();
    private int numberThreads;


    public static void main(String[] args) {
        Program p = new Program();

        p.validationArgument(args);
        p.validationNumberThreads(args);

        try(BufferedReader fileReader = new BufferedReader(new FileReader(filesUrls))) {
            p.linesFile = fileReader.lines().toArray();
            if(p.linesFile.length == 0) {
                p.errorMessage("File is empty");
            }

            Consumer consumer = new Consumer(p.linesFile);
            p.createThreads(consumer);

            for(Thread thread : p.listThread) {
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validationArgument(String[] args) {
        if(args.length != 1) {
            errorMessage("Invalid number of elements");
        } else if(!args[0].startsWith("--threadsCount=")) {
            errorMessage("Invalid program argument entry. Example: --threadsCount=n (number of threads)");
        }
    }

    private void validationNumberThreads(String[] args) {
        try {
            numberThreads = Integer.parseInt(args[0].substring("--threadsCount=".length()));
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }
        if(numberThreads < 1) {
            errorMessage("The number of threads must be positive and greater than zero");
        }
    }

    private void errorMessage(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

    private void createThreads(Consumer consumer) {
        Thread thread;
        for(int i = 0; i < numberThreads; i++) {
            thread = new Thread(new Producer(consumer, i + 1, linesFile.length));
            listThread.add(thread);
        }
    }
}
