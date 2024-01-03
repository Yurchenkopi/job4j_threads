package ru.job4j.concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Wget implements Runnable {

    private static final String URL_REGEX =
            "https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}"
                    + "\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";

    private static final String NUM_REGEX =
            "[-+]?\\d+";

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        System.out.println("Downloading...");
        String[] temp = url.split("/");
        var file = new File(".\\data\\wget\\" + temp[temp.length - 1]);
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            var dataBuffer = new byte[speed];
            int bytesRead;
            int bytesInBuffer = 0;
            var startAt = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                bytesInBuffer += bytesRead;
                output.write(dataBuffer, 0, bytesRead);
                if (bytesInBuffer > speed) {
                    var downloadTime = System.currentTimeMillis() - startAt;
                    if (downloadTime < 1000) {
                        Thread.sleep(1000 - downloadTime);
                    }
                    bytesInBuffer = 0;
                    startAt = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf(
                    "File %s was downloaded.%sFile size: %d bytes",
                    file, System.lineSeparator(),  Files.size(file.toPath())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void validate(String[] args) {
        Pattern urlPattern = Pattern.compile(URL_REGEX);
        Pattern numPattern = Pattern.compile(NUM_REGEX);
        if (args.length <= 1) {
            throw new IllegalArgumentException("One or more parameters are empty");
        }
        if (!urlPattern.matcher(args[0]).find()) {
            throw new IllegalArgumentException("Incorrect url parameter");
        }
        if (!numPattern.matcher(args[1]).find()) {
            throw new IllegalArgumentException("Incorrect speed limit parameter");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

}
