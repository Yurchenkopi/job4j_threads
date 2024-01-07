package ru.job4j.concurrent.io;

import java.io.*;
import java.nio.file.Paths;

public class SimpleContentSaver implements ContentSaver {

    private final String path = ".//data//io//ParseFile//target.txt";

    private final File file = Paths.get(path).toFile();


    @Override
    public void save(String content) {
        try (OutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
