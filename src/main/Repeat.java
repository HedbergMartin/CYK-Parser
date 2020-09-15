package main;

public class Repeat extends Input {

	public Repeat(int start, int increment, int end) {
		super(start, increment, end);
	}
	
	public Repeat(int start, int increment, int end, String head, String tail) {
		super(start, increment, end, head, tail);
	}

	@Override
	public String nextElement() {
		String next = head;
		for (int i = 0; i < count; i++) {
			next += "()";
		}
		next += tail;
		count += increment;
		return next;
	}

}
