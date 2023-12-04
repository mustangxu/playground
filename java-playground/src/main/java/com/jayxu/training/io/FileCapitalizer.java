/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.mutable.MutableInt;

import lombok.SneakyThrows;

public class FileCapitalizer {
    @SneakyThrows
    public static void main(String[] args) {
        var target = File.createTempFile("target", ".txt");
        System.out.println(target.getAbsolutePath());

        try (var r = new BufferedReader(
            new InputStreamReader(FileCapitalizer.class.getClassLoader()
                .getResourceAsStream("io/original.txt")));
                var w = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(target)));) {
            var c = new MutableInt();
            r.lines().map(String::toUpperCase).forEach(s -> {
                try {
                    w.write(s);
                    w.newLine();
                    c.add(s.length());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            System.out.println(c.intValue() + " characters capitalized");
        }
    }
}
