// WindChill.java: Takes two doubles t and v as command-line arguments and
// writes the wind chill w, calculated as w=35.74+0.6215t+(0.4275t-35.75)v^0.16.

public class WindChill {
	public static void main(String[] args) {
		double w;
		double t =  Integer.parseInt(args[0]);
		double v =  Integer.parseInt(args[1]);
		w = 35.74 + (0.6215 * t) + ((0.4275 * t) - 35.75) * Math.pow(v, 0.16);
		System.out.println(w);

	}
}


