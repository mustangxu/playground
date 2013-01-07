/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author ijay
 * 
 */
public class FileCapitalizer {
    public static void main(String[] args) {
        BufferedReader r = null;
        BufferedWriter w = null;

        try {
            File original = new File(
                "/Users/ijay/Documents/workspace/advanced_java_training/src/com/nazca/training/io/original.txt");
            File target = new File(
                "/Users/ijay/Documents/workspace/advanced_java_training/src/com/nazca/training/io/target.txt");

            r = new BufferedReader(new InputStreamReader(new FileInputStream(
                original)));
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                target, false)));

            if (!target.exists()) {
                target.createNewFile();
            }

            String s = null;
            int counter = 0;

            while ((s = r.readLine()) != null) {
                counter += s.length();
                w.write(s.toUpperCase());
                w.write("\n");
            }

            System.out.println(counter + " characters capitalized");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
