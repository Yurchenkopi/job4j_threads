package ru.job4j.concurrent.io;

import java.io.*;
import java.nio.file.Paths;

public class SimpleContentSaver implements ContentSaver {

    File file = Paths.get(".//data//io//ParseFile//target.txt").toFile();

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
