package main;

public class OpenClose extends Input {

	String opener, closer, head = "", tail = "";
	
	public OpenClose(int start, int increment, int end, String opener, String closer) {
		super(start, increment, end);
		this.opener = opener;
		this.closer = closer;
	}
	
	public OpenClose(int start, int increment, int end, String opener, String closer, String head, String tail) {
		this(start, increment, end, opener, closer);
		this.head = head;
		this.tail = tail;
	}

	@Override
	public String nextElement() {
		String next = head;
		for (int i = 0; i < count; i++) {
			next += opener;
		}
		for (int i = 0; i < count; i++) {
			next += closer;
		}
		next += tail;
		count += increment;
		return next;
	}

	@Override
	public String getName() {
		return this.head + this.opener + this.opener + ".." + this.closer + this.closer + this.tail;
	}
}
