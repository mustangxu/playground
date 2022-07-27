package com.jayxu.playground.math;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.stream.LongStream;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.LongMath;

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

public abstract class AKS<T extends Number> {
    public static final LongAKS AKS_LONG = new LongAKS();
    public static final BigIntegerAKS AKS_BIG_INTEGER = new BigIntegerAKS();
    private static final boolean[] SIEVE_ARRAY;
    private static final int SIEVE_ERATOS_SIZE = 10_000_000;
    // private static final BigInteger SIEVE_ERATOS_SIZE_BI = BigInteger
    // .valueOf(SIEVE_ERATOS_SIZE);

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
        var start = System.currentTimeMillis();

        SIEVE_ARRAY = new boolean[SIEVE_ERATOS_SIZE + 1];
        SIEVE_ARRAY[1] = true;

        for (var i = 2; i * i <= SIEVE_ERATOS_SIZE; i++) {
            if (!SIEVE_ARRAY[i]) {
                for (var j = i * i; j <= SIEVE_ERATOS_SIZE; j += i) {
                    SIEVE_ARRAY[j] = true;
                }
            }
        }

        System.out.println("SIEVE_ARRAY initialized in "
            + (System.currentTimeMillis() - start) + " ms");
    }

    public static boolean isPrime(long input) {
        return isPrime(input, false);
    }

    public static boolean isPrime(BigInteger input) {
        return isPrime(input, false);
    }

    public static boolean isPrime(long input, boolean skipSieveArray) {
        return AKS_LONG.checkPrime(input, skipSieveArray);
    }

    public static boolean isPrime(BigInteger input, boolean skipSieveArray) {
        return AKS_BIG_INTEGER.checkPrime(input, skipSieveArray);
    }

    /* function to check if a given number is prime or not */
    protected abstract boolean checkPrime(T input, boolean skipSieveArray);

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

    protected boolean hitSieveArray(Number n) {
        var v = n.intValue();
        return v >= 0 && v <= SIEVE_ERATOS_SIZE && !SIEVE_ARRAY[v];
    }

    /* function given a and b, computes if a is power of b */
    protected abstract boolean findPowerOf(T bNum, int val);

    /* function, given a and b computes if a is a power of b */
    protected abstract boolean findPower(T n, int l);

    protected abstract T mPower(T x, T y, T n);

    private static class LongAKS extends AKS<Long> {
        @Override
        protected boolean checkPrime(Long input, boolean skipSieveArray) {
            if (input < 2) {
                return false;
            }

            if (!skipSieveArray && this.hitSieveArray(input)) { // hit
                                                                // SIEVE_ARRAY
                return true;
            }

            var log = (int) Math.log10(input);

            if (this.findPower(input, log)) {
                return false;
            }

            var totR = 2;
            for (var lowR = 2L; lowR < input; lowR++) {
                if (LongMath.gcd(lowR, input) != 1) {
                    return false;
                }

                totR = (int) lowR;
                if (this.hitSieveArray(lowR)) {
                    var quot = largestFactor(totR - 1);
                    var divisor = (totR - 1L) / quot;
                    var tm = (int) (4 * Math.sqrt(totR) * log);
                    var powOf = this.mPower(input, divisor, lowR);

                    if (quot >= tm && powOf != 1) {
                        break;
                    }
                }
            }

            var aLimit = (int) (2 * Math.sqrt(totR) * log);
            for (var i = 1; i < aLimit; i++) {
                var leftH = this.mPower(2L - i, input, input)
                    % input;
                var rightH = (this.mPower(2L, input, input) - i)
                    % input;

                if (leftH != rightH) {
                    return false;
                }
            }

            return true;
        }

        @Override
        protected boolean findPowerOf(Long bNum, int val) {
            var low = 10L;
            var high = low;
            var l = bNum.toString().length() / val + 1;
            low = (long) Math.pow(low, l - 1);
            high = (long) Math.pow(high, l) - 1;

            while (low <= high) {
                var mid = (low + high) / 2;
                var res = Math.pow(mid, val);

                if (res < bNum) {
                    low = mid + 1;
                } else if (res > bNum) {
                    high = mid - 1;
                } else {
                    return true;
                }
            }

            return false;
        }

        @Override
        protected boolean findPower(Long n, int l) {
            for (var i = 2; i < l; i++) {
                if (this.findPowerOf(n, i)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        protected Long mPower(Long x, Long y, Long n) {
            var m = y;
            var p = 1L;
            var z = x;

            while (m > 0) {
                while (m % 2 == 0) { // even number
                    m /= 2;
                    z = z * z % n;
                }

                m--;
                p = p * z % n;
            }

            return p;
        }
    }

    private static class BigIntegerAKS extends AKS<BigInteger> {
        @Override
        protected BigInteger mPower(BigInteger x, BigInteger y,
                BigInteger n) {
            var m = y;
            var p = ONE;
            var z = x;

            while (m.signum() == 1) {
                while (!m.testBit(0)) { // even number
                    m = m.shiftRight(1);
                    z = z.multiply(z).mod(n);
                }

                m = m.subtract(ONE);
                p = p.multiply(z).mod(n);
            }

            return p;
        }

        @Override
        protected boolean findPower(BigInteger n, int l) {
            for (var i = 2; i < l; i++) {
                if (this.findPowerOf(n, i)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        protected boolean findPowerOf(BigInteger bNum, int val) {
            var low = BigInteger.TEN;
            var high = low;
            var l = bNum.toString().length() / val + 1;
            low = low.pow(l - 1);
            high = high.pow(l).subtract(ONE);

            while (low.compareTo(high) <= 0) {
                var mid = low.add(high).shiftRight(1);
                var res = mid.pow(val);

                if (res.compareTo(bNum) < 0) {
                    low = mid.add(ONE);
                } else if (res.compareTo(bNum) > 0) {
                    high = mid.subtract(ONE);
                } else {
                    return true;
                }
            }

            return false;
        }

        @Override
        protected boolean checkPrime(BigInteger input, boolean skipSieveArray) {
            if (input.compareTo(TWO) < 0) {
                return false;
            }

            if (!skipSieveArray && this.hitSieveArray(input)) { // hit
                                                                // SIEVE_ARRAY
                return true;
            }

            var log = BigIntegerMath.log10(input, RoundingMode.DOWN);

            if (this.findPower(input, log)) {
                return false;
            }

            var totR = 2;

            for (var lowR = TWO; lowR.compareTo(input) < 0; lowR = lowR
                .add(ONE)) {
                if (lowR.gcd(input).compareTo(ONE) != 0) {
                    return false;
                }

                totR = lowR.intValue();
                if (this.hitSieveArray(lowR)) {
                    var quot = largestFactor(totR - 1);
                    var divisor = (totR - 1) / quot;
                    var tm = (int) (4 * Math.sqrt(totR) * log);
                    var powOf = this.mPower(input, BigInteger.valueOf(divisor),
                        lowR);

                    if (quot >= tm && powOf.compareTo(ONE) != 0) {
                        break;
                    }
                }
            }

            var aLimit = (int) (2 * Math.sqrt(totR) * log);
            for (var i = 1; i < aLimit; i++) {
                var aBigNum = BigInteger.valueOf(i);
                var leftH = this.mPower(TWO.subtract(aBigNum), input, input)
                    .mod(input);
                var rightH = this.mPower(TWO, input, input).subtract(aBigNum)
                    .mod(input);

                if (leftH.compareTo(rightH) != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        var max = 10_000L;

        var primes = LongStream.rangeClosed(1, max)
            .filter(n -> AKS.isPrime(n, true)).count();
        System.out.println("Checked [1 .. " + max + "] as Long DONE in "
            + (System.currentTimeMillis() - start) + " ms, got: "
            + primes + ", " + primes * 100. / max + "%");

        primes = LongStream.rangeClosed(1, max)
            .filter(n -> AKS.isPrime(BigInteger.valueOf(n), true)).count();
        System.out.println("Checked [1 .. " + max + "] as BigInteger DONE in "
            + (System.currentTimeMillis() - start) + " ms, got: "
            + primes + ", " + primes * 100. / max + "%");

        var n = new BigInteger("1311148946153119849132111651651616161651651");
        start = System.currentTimeMillis();
        var p = AKS.isPrime(n);
        System.out.println("Checked " + n + " DONE in "
            + (System.currentTimeMillis() - start) + " ms, prime: " + p);
    }
}
