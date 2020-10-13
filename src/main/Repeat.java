package main;

public class Repeat extends Input {

	String pattern, head = "", tail = "";
	
	public Repeat(int start, int increment, int end, String pattern) {
		super(start, increment, end);
		this.pattern = pattern;
	}
	
	public Repeat(int start, int increment, int end, String pattern, String head, String tail) {
		this(start, increment, end, pattern);
		this.head = head;
		this.tail = tail;
	}

	@Override
	public String nextElement() {
		String next = head;
		for (int i = 0; i < count; i++) {
			next += pattern;
		}
		next += tail;
		count += increment;
		return next;
	}

	@Override
	public String getName() {
		return this.head + this.pattern + ".." + this.pattern + this.tail;
	}
}
