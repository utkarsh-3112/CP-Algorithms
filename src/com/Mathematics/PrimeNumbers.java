package com.Mathematics;
/*
 *
 * @UtkarshAgarwal
 */

import java.util.Arrays;

class PrimeNumbers {
    // ------- Primality Test ---------

        /* Naive
        TC : O(sqrt(n)) */

        public static boolean naive(int n){
            for(int i = 2 ; i * i <= n ; i++){
                if(n % i == 0)
                    return false;
            }
            return true;
        }
        // ------- Better -------
        // Any prime number can be express 6n + 1 or 6n - 1
        public static boolean better(int n){

            if(n == 1) return false;
            if(n == 2 || n == 3) return true;
            if(n % 2 == 0 || n % 3 == 0) return false;
            for(int i = 5 ; i * i <= n ; i += 6){
                if(n % i == 0 || n % (i + 2) == 0)
                    return false;
            }
            return true;
        }

        // ------ Fermat Primality Test --------

        public static boolean  probablyPrimeFermat(int n, int iter) {
            if (n < 4)
                return n == 2 || n == 3;

            for (int i = 0; i < iter; i++) {
                int a = (int) (2 + (Math.random() % (n - 3)));
                if (ModuloArithmetic.moduloExponentiation(a, n - 1, n) != 1)
                    return false;
            }
            return true;
        }

    /* ------------- Sieve Of Eratosthenes ----------
        TC : O(Nlog(logN))                              */
        public static boolean[] sieveOfEratosthenes(int n){
            boolean[] primeCheck = new boolean[n + 1];
            for(int i = 2; i <= n ; i++){
                primeCheck[i] = true;
            }
            for(int i = 2 ; i * i <= n ; i++) {
                if (primeCheck[i]) {
                    for (int j = i * i; j <= n; j = j + i)
                        primeCheck[j] = false;
                }
            }
            return primeCheck;
        }

    // Miller-Rabin primality test
    // Deterministic version
    /* --------- Segmented Sieve ------------
        TC : O((r - l + 1)loglog(r) + sqrt(r)loglog(sqrt(r)))
     */
        public static boolean[] segmentedSieve(long l, long r){
            boolean[] prime = sieveOfEratosthenes((int) Math.sqrt(r));
            boolean[] isPrime = new boolean[(int)(r - l + 1)];
            for(int i = 2 ; i < prime.length ; i++){
                if(!prime[i]) continue;
                for(long m = (l / i) ; m * i <= r ; m++){
                    isPrime[(int)(m * i - l)] = false;
                }
            }
            return isPrime;
        }

        /*
            Without preCompute\
            TC : O((r - l + 1)log(r) + sqrt(r))

            boolean[] isPrime = new boolean[(int)(r - l + 1)];
            Arrays.fill(isPrime, true);
            for(int i = 2 ; i <= Math.sqrt(r) ; i++){
                long m = Math.max((l + i - 1) / i , i);
                for(; m * i <= r ; m++){
                    isPrime[(int)(m * i - l)] = false;
                }
            }
            if(l == 1) isPrime[0] = false;
            return isPrime;

         */
}


