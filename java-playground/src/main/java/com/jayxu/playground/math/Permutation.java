/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.math;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

public class Permutation<T> {
	private T[] array;

	public Permutation(T[] array) {
		this.array = array;
	}

	public List<T[]> getPermutation() {
		return this.walk(0, new ArrayList<>());
	}

	private List<T[]> walk(int i, List<T[]> objects) {
		var l = this.array.length;

		if (i == l) {
			objects.add(this.array.clone());
		} else {
			for (var j = i; j < l; j++) {
				var tmp = this.array[i];
				this.array[i] = this.array[j];
				this.array[j] = tmp;

				this.walk(i + 1, objects);

				this.array[j] = this.array[i];
				this.array[i] = tmp;
			}
		}

		return objects;
	}

	public static void main(String[] args) {
		var w = new StopWatch();
		w.start();
		var res = new Permutation<>(
				new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
						.getPermutation();
		w.stop();

		System.out.println(res.size() + ", " + w);

//		for (var a : res) {
//			for (var i : a) {
//				System.out.print(i + "\t");
//			}
//
//			System.out.println();
//		}
	}
}
