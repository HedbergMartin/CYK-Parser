package main;

import java.util.Enumeration;

public abstract class Input implements Enumeration<String> {

	int count;
	int increment;
	int end;
	
	public Input(int start, int increment, int end) {
		this.count = start;
		this.increment = increment;
		this.end = end;
	}

	@Override
	public boolean hasMoreElements() {
		return count <= end;
	}
	
	public abstract String getName();
}
