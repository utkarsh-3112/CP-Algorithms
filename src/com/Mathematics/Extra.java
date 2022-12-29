package com.Mathematics;

public class Extra {
    private static int exponentOfNumInAFactorial(int n, int num) {
        int exp = 0;
        long x = num;
        while (n / x != 0) {
            exp += (n / x);
            x *= num;
        }
        return exp;
    }
}
