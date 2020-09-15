package main;

import java.util.Enumeration;

public abstract class Input implements Enumeration<String> {

	int count;
	int increment;
	int end;
	String head, tail;
	
	public Input(int start, int increment, int end, String head, String tail) {
		this.count = start;
		this.increment = increment;
		this.end = end;
		this.head = head;
		this.tail = tail;
	}
	
	public Input(int start, int increment, int end) {
		this(start, increment, end, "", "");
	}

	@Override
	public boolean hasMoreElements() {
		return count <= end;
	}
}
