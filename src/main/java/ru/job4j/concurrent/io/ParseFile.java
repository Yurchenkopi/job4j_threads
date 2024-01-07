package ru.job4j.concurrent.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(final File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) {
        String output = "";
        int data;
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            while ((data = input.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }


    public static void main(String[] args) {
        ParseFile source = new ParseFile(
                Paths.get(".//data//io//ParseFile//source.txt").toFile());
        ContentSaver content = new SimpleContentSaver();
        System.out.println(source.getContent(f -> f < 0x80));
        content.save(source.getContent(f -> f < 0x80));
    }
}
