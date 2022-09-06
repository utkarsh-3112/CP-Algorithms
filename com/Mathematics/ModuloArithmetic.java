
/*
 *
 * @UtkarshAgarwal
 */
class ModuloArithmetic {

    public static long moduloExponentiation(long num, long pow, long mod){
        num = num % mod;
        long res = 1;
        while(pow > 0){
            if((pow & 1) == 1){
                res = res * num % mod;
            }
            num = num * num % mod;
            pow >>= 1;
        }
        return res;
    }

    // TC : log(a)
    public static long multiplyTwoNumbers(long a, long b, long mod){
        long res = 0;
        while(a > 0){
            if((a & 1) == 1){
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
     */

    public static int moduloInverse(int num, int mod){
        int[] coff = new int[2];
        int gcd = MathsBasic.extendedEuclid(num, mod, coff);
        if(gcd != 1){
            return 0; // No inverse exist
        }else{
            int inverse = (coff[0] % mod + mod) % coff[0];
            return inverse;
        }
    }
}
