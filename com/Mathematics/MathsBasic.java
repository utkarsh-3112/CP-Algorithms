
class MathsBasic {

    /* --------- GCD -------
        TC :  O(log ab)           */
    public static long gcd(long a, long b) {
        return a == 0 ? b : gcd(b % a, a);
    }
    // Can we find the numbers x, y such that ax + by = gcd(a, b)
    // There exists infinitely many pairs - this is Bezout's Lemma .
    // The algorithm to generate such pairs is called Extended Euclidean Algorithm.




    /* --------- Binary Exponentiation ------------
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

    private static int[] permute(int[] sequence, int[] permutation, int k) {
        while (k > 0) {
            if ((k & 1) == 1) {
                sequence = applyPermute(sequence, permutation);
            }
            permutation = applyPermute(permutation, permutation);
            k >>= 1;
        }
        return sequence;
    }

    private static int[] applyPermute(int[] sequence, int[] permutation) {
        int[] newSequence = new int[sequence.length];
        for (int i = 1; i < sequence.length; i++) {
            newSequence[i] = sequence[permutation[i]]; // newSequence[permutation[i]] = sequence[i]; Acc. to given Question
        }
        return newSequence;
    }

    /* -------------- Extended Euclid Theorem ----------------------
        TC : O(Log(ab))
        ax + by = gcd(a, b)
     */

    public static int extendedEuclid(int a, int b, int[] coff){
        if(b == 0){
            int gcd = a;
            coff[0] = 1;
            coff[1] = 0;
            return gcd;
        }
        int gcd = extendedEuclid(b, a % b, coff);
        int x = coff[1];
        int y = coff[0] - coff[1] * (a / b);
        coff[0] = x;
        coff[1] = y;
        return gcd;
    }
}