// DayOfWeek.java: Takes three integers m (for month), d (for day), and y
// (for year) as command-line arguments and writes the day of the week (0 for
// Sunday, 1 for Monday, and so on) D, calculated as follows:
//
// y0 = y - (14 - m) / 12
// x0 = y0 + y0 / 4 - y0 / 100 + y0 / 400
// m0 = m + 12 * ((14 - m) / 12) - 2
// D = (d + x0 + 31 * m0 / 12) % 7

public class DayOfWeek {
	public static void main(String[] args) {
		Integer m = Integer.parseInt(args[0]);
		Integer d = Integer.parseInt(args[1]);
		Integer y = Integer.parseInt(args[2]);

		Integer y0 = (y - (14 - m)) / 12;
		Integer x0 = y0 + (y0 / 4) - (y0 / 100) + (y0 / 400);
		Integer m0 = m + 12 * ((14 - m) / 12) - 2;
		Integer day = (d + x0 + 31 * m0 / 12) % 7;
		System.out.println(day);
	}
}
