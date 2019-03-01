// Ramanujan.java: Prints the integers <= N (command-line argument) that
// can be expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.StdOut;

public class Ramanujan {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);


		// System.out.println("n is " + N);
		for (int a = 1; a <= Math.cbrt(N); a++) {
			for (int b = a; b <= Math.cbrt(N - Math.pow(a, 3)); b++) {
				for (int c = a + 1; c <= Math.cbrt(N); c++) {
					for (int d = c; d <= Math.cbrt(N - Math.pow(c, 3)); d++) {
						if (Math.pow(a, 3) + Math.pow(b, 3) == Math.pow(c, 3) + Math.pow(d, 3)) {
							System.out.print((Math.pow(a, 3) + Math.pow(b, 3)) + " = ");
							System.out.print(a + "^3 + " + b + "^3 = ");
							System.out.print(c + "^3 + " + d + "^3");
							System.out.println();
						}
					}
				}
			}
		}
	}
}
