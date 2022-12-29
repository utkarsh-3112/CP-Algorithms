package com.Mathematics;

import java.util.ArrayList;


public class MathsBasic {

    /* --------------------- GCD ---------------------------
       TC :  O(log ab)
      */
    public static long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }
    /*
     Can we find the numbers x, y such that ax + by = gcd(a, b)
     There exists infinitely many pairs - this is Bezout's Lemma .
     The algorithm to generate such pairs is called Extended Euclidean Algorithm.
    */

    /* ------------ Power of 2 ----------------- */
    public static boolean isPowerOfTwo (long x) {
     /* First x in the below expression is
     for the case when x is 0 */
        return x!=0 && ((x&(x-1)) == 0);
    }


    /* --------------------- Binary Exponentiation ------------------
        TC : O(log(n))                               */
    public static long binPow(long num, long pow) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = res * num;
            }
            num = num * num;
            pow >>= 1;
        }
        return res;
    }



    /* --------- Apply k Permutations on a sequence ------------
       TC : nLog(k)                                               */

    private static long[] permute(long[] sequence, long[] permutation, long k) {
        while (k > 0) {
            if ((k & 1) == 1) {
                sequence = applyPermute(sequence, permutation);
            }
            permutation = applyPermute(permutation, permutation);
            k >>= 1;
        }
        return sequence;
    }

    private static long[] applyPermute(long[] sequence, long[] permutation) {
        long[] newSequence = new long[sequence.length];
        for (int i = 1; i < sequence.length; i++) {
            newSequence[i] = sequence[(int) permutation[i]]; // newSequence[permutation[i]] = sequence[i]; Acc. to given Question
        }
        return newSequence;
    }

    /* -------------- Extended Euclid Theorem ----------------------
        TC : O(Log(ab))
        ax + by = gcd(a, b)
     */

    public static long extendedEuclid(long a, long b, long[] coff) {
        if (b == 0) {
            long gcd = a;
            coff[0] = 1;
            coff[1] = 0;
            return gcd;
        }
        long gcd = extendedEuclid(b, a % b, coff);
        long x = coff[1];
        long y = coff[0] - coff[1] * (a / b);
        coff[0] = x;
        coff[1] = y;
        return gcd;

        // iterative code
        //        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        //        while (b != 0)
        //        {
        //            long q = a / b;
        //            long r = a % b;
        //
        //            a = b;
        //            b = r;
        //
        //            temp = x;
        //            x = lastx - q * x;
        //            lastx = temp;
        //
        //            temp = y;
        //            y = lasty - q * y;
        //            lasty = temp;
        //        }
        //        coff[0] = lastx;
        //        coff[1] = lasty;
        //        return a;
    }

    /*
        ------------------------ Prime Factorization -------------------------
        TC : O(sqrt(N))
     */
    public static ArrayList<int[]> primeFactorization(long num) {
        ArrayList<int[]> factors = new ArrayList<>();
        for (int i = 2; (long) i * i <= num; i++) {
            if (num % i == 0) {
                int count = 0;
                while (num % i == 0) {
                    count++;
                    num = num / i;
                }
                factors.add(new int[] {i, count});
            }
        }
        if (num > 1) factors.add(new int[]{(int) num, 1});
        return factors;
    }

    // Prime Factorization using Sieve
    // TC : log(N)

    public static ArrayList<int[]> primeFactorizationSieve(int num) {
        ArrayList<int[]> factors = new ArrayList<>();
        int[] arr = new int[(int) (num + 1)];
        for (int i = 2; i <= num; i++) {
            if (arr[i] == 0) {
                for (int j = i; j <= num; j += i) {
                    if (arr[j] == 0)
                        arr[j] = i;
                }
            }
        }
        while (num != 1) {
            int x = arr[num];
            num = num / x;
            int count = 1;
            while (arr[num] == x) {
                count++;
                num = num / x;
            }
            factors.add(new int[]{x, count});
        }
        return factors;
    }

    /*
    ----------------------------------Matrix Functions----------------------------------
    `   TC : O(N ^ 3 * Log(N))
     */
    public static int[][] identityMatrix(int n) {
        int[][] idm = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) idm[i][j] = 1;
                else idm[i][j] = 0;
            }
        }
        return idm;
    }

    public static int[][] matrixMultiplication(int[][] A, int[][] B, int n) {
        int[][] res = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = 0;
                for (int k = 0; k < n; k++)
                    res[i][j] += A[i][k] * B[k][j];
            }
        }
        return res;
    }

    public static int[][] matrixExponentiation(int[][] matrix, int pow) {
        int n = matrix.length;
        int[][] I = identityMatrix(n);
        while (pow > 0) {
            if ((pow & 1) == 1) {
                I = matrixMultiplication(I, matrix, n);
            }
            matrix = matrixMultiplication(matrix, matrix, n);
            pow >>= 1;
        }
        return I;
    }

    /*
    ------------------- nth element of Recurrence Relation -----------------------
     Magic matrix = [A  B] for Fn = xF(n - 1) + yF(n - 2)
                    [C  D]
     for nth number [F(1)  F(2)] * ([A  B] ^ (n - 1)) = [F(n)  F(n - 1)]
                                    [C  D]
     [F(1)  F(2)] *  [0  1] = [F(2)  F(3)]
                     [1  1]
     if F(n) depends on k terms than magic matrix of order k * k and previous matrix of k
     */

    public static int getFibN(int[] ar, int n) {
        if (n <= 2) return ar[n];
        int[][] magicMatrix = {{0, 1}, {1, 1}};
        magicMatrix = matrixExponentiation(magicMatrix, n - 1);
        int Fn = (int) ((ar[1] * magicMatrix[0][0] + ar[2] * magicMatrix[2][1]) % (1e9 + 7));
        return Fn;
    }

    /*
        --------------------- Euler's Totient Function --------------------
        counts the number of positive integers upto n which are co - prime with n
        Phi(n) = # +ve integers co - prime with n
        Phi(PrimeNo) = PrimeNo - 1
        Phi(PrimeNo ^ x ) = PrimeNo ^ (x - 1)(PrimeNo - 1)
        Phi(N) = Phi(P1^x1 * p2^x2 * P3^x3 ...) = Phi(P1^x1)*Phi(P2^x2)*Phi(P3^x3).....
                                                = P1^(x1 - 1)(P1 - 1)*P2^(x2 - 1)(P2 - 1)............
                                                = N*((P1 - 1)/P1)*((P2 - 1)/P2)*......
     */
    // TC : O(sqrt(N))
    public static int eulerTotientFunction(int n) {
        ArrayList<int[]> primeFactors = primeFactorization(n);
        int res = n;
        for (int[] x : primeFactors) {
            res /= x[0];
            res *= (x[0] - 1);
        }
        return res;
    }

    //O(Nlog(log(N))
    public static int[] eulerTotientFunctionTillNUsingSieve(int n) {
        int[] phis = new int[n + 1];
        for (int i = 1; i <= n; i++)
            phis[i] = i;

        for (int i = 2; i <= n; i++) {
            if (phis[i] == i) {
                for (int j = i; j <= n; j += i) {
                    phis[j] /= i;
                    phis[j] *= (i - 1);
                }
            }
        }
        return phis;
    }

    /*
       GCD and Euler Totient Function Relation
       for
       ans = sum of GCD(i, N) for i = 1 to N
       Brute force TC : O(Q * NLog(N)) for q queries
       optimized TC : O(Q * Sqrt(N))
       Observation 1 : GCD(i, N) = one of the divisors of N
       Observation 2 : Instead of Running the loop from 1 to N, We can check for each divisor d of N how many numbers i are there with GCD(i, N) = d
       for N = 12 ans = (1) * 4
       GCD(x, n) = d
       GCD(x/d, n/d) = 1 so ETF of n/d gives no. of integers form 1 to n whose GCD with n is d this is how we use get count
     */

    public static int GCDAndETF(int n) {
        int res = 0;
        int[] phis = eulerTotientFunctionTillNUsingSieve(n);
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                int d1 = i;
                int d2 = n / i;
                res += d1 * phis[n / d1]; // getCount(d1, n)
                if (d1 != d2)
                    res += d2 * phis[n / d2]; // getCount(d2, n)
            }
        }
        return res;
    }

}