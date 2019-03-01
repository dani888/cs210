public class Practice {
	public static void main(String[] args) {
		int[] array;
		array = new int[] {8, 2, 17, 85, 1, 5, 34};
		// for (int i = 0; i < array.length; i++) {
		// 	System.out.println(array[i]);

		// }
		// System.out.println(array[0]);
		// System.out.println(array[1]);
		// System.out.println(array[2]);
		// System.out.println(array[3]);
		// System.out.println(array[4]);
		// System.out.println(array[5]);
		// System.out.println(array[6]);
		for (int i = array.length - 1; i >= 0; i--) {
			System.out.println(array[i]);

		}
	}

}