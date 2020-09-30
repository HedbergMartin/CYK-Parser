package main;

public class Stupid extends Input {

	public Stupid(int start, int increment, int end) {
		super(start, increment, end);
	}

	@Override
	public String nextElement() {
		String next = "";
		for (int i = 0; i < count; i++) {
			next += "a";
		}
		count += increment;
		return next;
	}
	
	

}
