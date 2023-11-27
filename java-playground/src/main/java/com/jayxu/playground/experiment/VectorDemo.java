/// **
// * Authored by jayxu @2022
// */
// package com.jayxu.playground.experiment;
//
// import jdk.incubator.vector.IntVector;
// import jdk.incubator.vector.VectorSpecies;
//
/// **
// * @author jayxu
// */
// public class VectorDemo {
// private static final VectorSpecies<Integer> SPECIES =
/// IntVector.SPECIES_PREFERRED;
//
// public static void add(int[] aArray, int[] bArray) {
// var va = IntVector.fromArray(SPECIES, aArray, 0);
// var vb = IntVector.fromArray(SPECIES, bArray, 0);
//
// va.add(vb);
// }
//
// public static void main(String[] args) {
// VectorDemo.add(new int[] { 1, 2, 3, 4 }, new int[] { 5, 6, 7, 8 });
// }
// }
