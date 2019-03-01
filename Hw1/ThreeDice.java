// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.



public class ThreeDice {
	public static void main(String[] args) {
		System.out.print((int)((Math.random() * 6) + 1) + (int)((Math.random() * 6) + 1) + (int)((Math.random() * 6) + 1));
	}
}
