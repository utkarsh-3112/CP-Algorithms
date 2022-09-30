package com.Mathematics;
/*
 *
 * @UtkarshAgarwal
 */

class PrimeNumbers {
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
                        primeCheck[i] = false;
                }
            }
            return primeCheck;
        }

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
                    return true;
            }
            return false;
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

    // Miller-Rabin primality test
    // Deterministic version


}
