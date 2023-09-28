package ex03;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Consumer {
    private static int numberFiles = 0;
    private final Object[] linesFile;
    private final Path directory = Paths.get("./ex03/Download/");

    public Consumer(Object[] linesFile) {
        this.linesFile = linesFile;
        if(!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public void downloadFile(int id) throws InterruptedException, IOException {
        boolean nextFile;
        synchronized (this) {
            numberFiles++;
            nextFile = true;
        }
        if(nextFile) {
            try {
                String[] line = linesFile[numberFiles - 1].toString().split("\\s+");
                printStart(id, line[0]);
                URL url = new URL(line[1]);
                String fileName = getFileName(url);
                try(InputStream in = new BufferedInputStream(url.openStream());
                    FileOutputStream fos = new FileOutputStream(fileName)) {
                    byte[] data = new byte[1024];
                    int count;
                    while ((count = in.read(data, 0, 1024)) != -1) {
                        fos.write(data, 0, count);
                    }
                }
                printFinish(id, line[0]);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    private static void printFinish(int id, String numberUrl) {
        System.out.println("Thread-" + id + " finish download file number " + numberUrl);
    }

    private static void printStart(int id, String numberUrl) {
        System.out.println("Thread-" + id + " start download file number " + numberUrl);
    }

    public String getFileName(URL url) {
        String[] fileName = url.toString().split("/");
        return directory + "/" + fileName[fileName.length - 1];
    }

    public synchronized int getFileCount() {
        return numberFiles;
    }
}
