/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileCapitalizer {
	public static void main(String[] args) throws Exception {
		var original = new File(FileCapitalizer.class.getClassLoader()
				.getResource("io/original.txt").getFile());
		var target = new File(original.getParentFile(), "target.txt");

		System.out.println(target);

		if (!target.exists()) {
			target.createNewFile();
		}

		try (var r = new BufferedReader(
				new InputStreamReader(new FileInputStream(original)));
				var w = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(target)));) {
			String s = null;
			var counter = 0;

			while ((s = r.readLine()) != null) {
				counter += s.length();
				w.write(s.toUpperCase());
				w.write("\n");
			}

			System.out.println(counter + " characters capitalized");
		}
	}
}
