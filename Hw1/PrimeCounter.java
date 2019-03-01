// PrimeCounter.java: takes an integer N as a command-line argument and writes
// the number of primes <= N.

import edu.princeton.cs.algs4.StdOut;

public class PrimeCounter {
    // Returns true if x is prime, and false otherwise.
    private static boolean isPrime(int x) {
        if (x == 2 || x == 3) {
            return true;
        }
        if (x % 2 == 0 || x % 3 == 0) {
            return false;
        }
        int check =  (int) (Math.sqrt(x) + 1);
        for (int i = 3; i <= check; i = i + 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Returns the number of primes <= N.
    private static int primes(int N) {
        Integer primeCount = 0;
        for (int i = 2; i < N; i++) {
            if (isPrime(i)) {
                primeCount++;
            }
        }
        return primeCount;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(primes(N));
    }
}












