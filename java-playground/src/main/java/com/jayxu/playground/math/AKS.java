package com.jayxu.playground.math;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * This is am implementation of Agrawal–Kayal–Saxena primality test in
 * java.
 *
 * <pre>
 * The algorithm is -
 * 1. l <- log n
 * 2. for i<-2 to l
 *      a. if an is a power fo l
 *              return COMPOSITE
 * 3. r <- 2
 * 4. while r < n
 *      a. if gcd( r, n) != 1
 *              return COMPSITE
 *      b. if sieve marked n as PRIME
 *              q <- largest factor (r-1)
 *              o < - r-1 / q
 *              k <- 4*sqrt(r) * l
 *              if q > k and n <= r
 *                      return PRIME
 *      c. x = 2
 *      d. for a <- 1 to k
 *              if (x + a) ^n !=  x^n + mod (x^r - 1, n)
 *                      return COMPOSITE
 *      e. return PRIME
 * </pre>
 *
 * @author
 *         Arijit Banerjee
 *         Suchit Maindola
 *         Srikanth Manikarnike
 *
 * @see    https://github.com/smanikar/primality-testing/blob/master/proposal/src/AKS.java
 */

public class AKS {
    private static final boolean[] SIEVE_ARRAY;
    private static final int SIEVE_ERATOS_SIZE = 10_000_000;
    private static final BigInteger TWO = BigInteger.TWO;

    /**
     * <pre>
     * array to populate sieve array
     * the sieve array looks like this
     *
     *  y index -> 0 1 2 3 4 5 6 ... n
     *  x index    1
     *     |       2   T - T - T ...
     *     \/      3     T - - T ...
     *             4       T - - ...
     *             .         T - ...
     *             .           T ...
     *             n
     * </pre>
     */
    static {
        SIEVE_ARRAY = new boolean[SIEVE_ERATOS_SIZE + 1];
        SIEVE_ARRAY[1] = true;

        for (var i = 2; i * i <= SIEVE_ERATOS_SIZE; i++) {
            if (!SIEVE_ARRAY[i]) {
                for (var j = i * i; j <= SIEVE_ERATOS_SIZE; j += i) {
                    SIEVE_ARRAY[j] = true;
                }
            }
        }
    }

    public static boolean isPrime(long input) {
        return isPrime(BigInteger.valueOf(input));
    }

    /* function to check if a given number is prime or not */
    public static boolean isPrime(BigInteger input) {
        if (input.compareTo(TWO) < 0) {
            return false;
        }

        var log = (int) logBigNum(input);

        if (findPower(input, log)) {
            return false;
        }

        BigInteger lowR;
        var totR = 2;

        for (lowR = TWO; lowR.compareTo(input) < 0; lowR = lowR
            .add(BigInteger.ONE)) {
            if (lowR.gcd(input).compareTo(BigInteger.ONE) != 0) {
                return false;
            }

            totR = lowR.intValue();
            if (!SIEVE_ARRAY[totR]) {
                var quot = largestFactor(totR - 1);
                var divisor = (totR - 1) / quot;
                var tm = (int) (4 * Math.sqrt(totR) * log);
                var powOf = mPower(input, BigInteger.valueOf(divisor), lowR);

                if (quot >= tm && powOf.compareTo(BigInteger.ONE) != 0) {
                    break;
                }
            }
        }

        var aLimit = (int) (2 * Math.sqrt(totR) * log);
        for (var i = 1; i < aLimit; i++) {
            var aBigNum = BigInteger.valueOf(i);
            var leftH = mPower(TWO.subtract(aBigNum), input,
                input).mod(input);
            var rightH = mPower(TWO, input, input).subtract(aBigNum)
                .mod(input);

            if (leftH.compareTo(rightH) != 0) {
                return false;
            }
        }

        return true;
    }

    /* function that computes the log of a big number */
    private static double logBigNum(BigInteger bNum) {
        var str = "." + bNum.toString();
        return Math.log10(Double.parseDouble(str)) + (str.length() - 1);
    }

    /* function to compute the largest factor of a number */
    private static int largestFactor(int num) {
        if (num == 1) {
            return num;
        }

        var i = num;
        while (i > 1) {
            while (SIEVE_ARRAY[i]) {
                i--;
            }

            if (num % i == 0) {
                return i;
            }

            i--;
        }

        return num;
    }

    /* function given a and b, computes if a is power of b */
    private static boolean findPowerOf(BigInteger bNum, int val) {
        var low = BigInteger.TEN;
        var high = low;
        double len = bNum.toString().length() / val;
        var l = (int) Math.ceil(len);
        low = low.pow(l - 1);
        high = high.pow(l).subtract(BigInteger.ONE);

        BigInteger mid, res;
        while (low.compareTo(high) <= 0) {
            mid = low.add(high).shiftRight(1);
            res = mid.pow(val);

            if (res.compareTo(bNum) < 0) {
                low = mid.add(BigInteger.ONE);
            } else if (res.compareTo(bNum) > 0) {
                high = mid.subtract(BigInteger.ONE);
            } else {
                return true;
            }
        }

        return false;
    }

    /* function, given a and b computes if a is a power of b */
    private static boolean findPower(BigInteger n, int l) {
        for (var i = 2; i < l; i++) {
            if (findPowerOf(n, i)) {
                return true;
            }
        }

        return false;
    }

    private static BigInteger mPower(BigInteger x, BigInteger y, BigInteger n) {
        var m = y;
        var p = BigInteger.ONE;
        var z = x;

        while (m.compareTo(BigInteger.ZERO) > 0) {
            while (m.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                m = m.shiftRight(1);
                z = z.multiply(z).mod(n);
            }

            m = m.subtract(BigInteger.ONE);
            p = p.multiply(z).mod(n);
        }

        return p;
    }

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        var max = 10000L;

        var primes = LongStream.rangeClosed(1, max)
            .mapToObj(n -> new Object[] { n, AKS.isPrime(n) })
            .filter(o -> (boolean) o[1]).map(o -> o[0]).toList();

        System.out.println("Checked [1 .. " + max + "] DONE in "
            + (System.currentTimeMillis() - start) + " ms, got: "
            + primes.size() + ", " + primes.size() * 100. / max + "%");

        var n = new BigInteger("1311148946153119849132111651651616161651651");
        start = System.nanoTime();
        var p = AKS.isPrime(n);
        System.out.println("Checked " + n + " DONE in "
            + (System.nanoTime() - start) + " ns, prime: " + p);
    }
}
