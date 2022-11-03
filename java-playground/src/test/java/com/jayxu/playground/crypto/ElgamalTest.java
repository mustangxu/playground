/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.crypto;

import java.util.Arrays;

/**
 * @author xujiajing
 */
class ElgamalTest {

    public static void main(String[] args) throws Exception {
        var elgamal = new Elgamal(256);
        var en1 = elgamal.encrypt(new byte[] { 7 });
        var en2 = elgamal.encrypt(new byte[] { 17 });
        var en3 = elgamal.encrypt(new byte[] { 7 + 17 });

        System.out.println(Arrays.toString(en1));
        System.out.println(Arrays.toString(elgamal.decrypt(en1)));
        System.out.println(Arrays.toString(en2));
        System.out.println(Arrays.toString(elgamal.decrypt(en2)));
        System.out.println(Arrays.toString(en3));
        System.out.println(Arrays.toString(elgamal.decrypt(en3)));
    }
}
