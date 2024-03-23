/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.experiment;

import java.util.function.BiFunction;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * @author jayxu
 */
public class VectorDemo {
    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static IntVector op(int[] aArray, int[] bArray,
            BiFunction<IntVector, IntVector, IntVector> operator) {
        var va = IntVector.fromArray(SPECIES, aArray, 0);
        var vb = IntVector.fromArray(SPECIES, bArray, 0);

        return operator.apply(va, vb);
    }

    void main() {
        int[] va = { 1, 2, 3, 4 };
        int[] vb = { 5, 6, 7, 8 };

        System.out.println(VectorDemo.op(va, vb, IntVector::add));
        System.out.println(VectorDemo.op(va, vb, IntVector::sub));
        System.out.println(VectorDemo.op(va, vb, IntVector::mul));
    }
}
