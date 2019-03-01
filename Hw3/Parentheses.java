import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class Parentheses {
	// Return true if s has matching parentheses, and false otherwise.
	private static boolean match(String s) {
		Stack stack = new Stack();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '{':
				case '[':
				case '(':
					stack.push(c);
					break;
				case '}':
					if(stack.isEmpty() || !stack.pop().equals('{')){
						return false;
					}
					break;
				case ']':
					if(stack.isEmpty() || !stack.pop().equals('[')){
						return false;
					}
					break;
				case ')':
					if(stack.isEmpty() || !stack.pop().equals('(')){
						return false;
					}
					break;
			}
		}
		if(!stack.isEmpty()){
			return false;
		}
		return true;
	}

	// Test client. [DO NOT EDIT]
	public static void main(String[] args) {
		StdOut.println(match(StdIn.readAll().trim()));
	}
}
