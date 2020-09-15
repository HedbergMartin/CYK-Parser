package main;

public class OpenClose extends Input {

	public OpenClose(int start, int increment, int end) {
		super(start, increment, end);
	}
	
	public OpenClose(int start, int increment, int end, String head, String tail) {
		super(start, increment, end, head, tail);
	}

	@Override
	public String nextElement() {
		String next = head;
		for (int i = 0; i < count/2; i++) {
			next += "(";
		}
		for (int i = 0; i < count/2; i++) {
			next += ")";
		}
		next += tail;
		count += increment;
		return next;
	}

}
