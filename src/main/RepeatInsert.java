package main;

public class RepeatInsert extends Input {

	String pattern, head = "", middle = "", tail = "";
	
	public RepeatInsert(int start, int increment, int end, String pattern, String head, String middle, String tail) {
		super(start, increment, end);
		this.pattern = pattern;
		this.head = head;
		this.middle = middle;
		this.tail = tail;
	}

	@Override
	public String nextElement() {
		String next = head;
		for (int i = 0; i < count/2; i++) {
			next += pattern;
		}
		next += middle;
		for (int i = 0; i < count/2; i++) {
			next += pattern;
		}
		next += tail;
		count += increment;
		return next;
	}

	@Override
	public String getName() {
		return this.head + this.pattern + ".." + this.middle + ".." + this.pattern + this.tail;
	}
}
