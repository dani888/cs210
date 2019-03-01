// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

public class ThreeSort {
	public static void main(String[] args) {
		Integer x1 =  Integer.parseInt(args[0]);
		Integer x2 =  Integer.parseInt(args[1]);
		Integer x3 =  Integer.parseInt(args[2]);
		Integer min = Math.min(Math.min(x1, x2), x3);
		Integer max = Math.max(Math.max(x1, x2), x3);
		Integer middle = (x1 != min && x1 != max ? x1 : (x2 != min && x2 != max ? x2 : x3));

		System.out.print(min + " " + middle + " " + max);

	}
}
