package com.Mathematics;

/*
 *
 * @UtkarshAgarwal
 */
public class ModuloArithmetic {

    /*
        if a % mod == b % mod
        a^k % mod == b ^ k % mod
        eg 29 ^ 5 mod 3 ?
            29 % 3 = 2
            2 ^ 5 % 3 = 2 == 29 ^ 5 % 3
     */
    public static long moduloSubtraction(long a, long b, long mod) {
        if (a >= b) return (a - b) % mod;
        return (mod - (a - b)) % mod;
    }

    public static long moduloDivide(long a, long b, long mod) {
        a = a % mod;
        long inv = moduloInverse(b, mod);
        if (inv == -1) return -1;
        return ((inv * a) % mod);
    }

    public static long moduloExponentiation(long num, long pow, long mod) {
        num = num % mod;
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = res * num % mod;
            }
            num = num * num % mod;
            pow >>= 1;
        }
        return res;
    }

    // TC : log(a)
    public static long multiplyTwoNumbers(long a, long b, long mod) {
        long res = 0;
        while (a > 0) {
            if ((a & 1) == 1) {
                res = (res + b) % mod;
            }
            b = (b * 2) % mod;    // multiply b by 2 and a getting half after each iteration
            a >>= 1;
        }
        return res;
    }
    /* ----------------- Modulo Inverse ----------------------------
        TC : O(log ab)
        (a * inverse(a)) % mod = 1
        for modulo multiplicative inverse exist for a N under P than GCD(N, P) = 1
     */

    public static long moduloInverse(long num, long mod) {
        long[] coff = new long[2];
        long gcd = MathsBasic.extendedEuclid(num, mod, coff);
        if (gcd != 1) {
            return 0; // No inverse exist
        } else {
            long inverse = (coff[0] % mod + mod) % mod;
            return inverse;
        }
    }

    // Fermat Little Theorem
    // a ^(m - 1) % mod == 1 if a & m (m is a prime number) are co - prime (GCD(a, m) == 1)
    // modulo inverse of a is a ^ (m - 2)

    public static long moduloInverse2(long num, long mod) {
        return moduloExponentiation(num, mod - 2, mod);
    }


}
