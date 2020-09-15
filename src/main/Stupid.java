package main;

public class Stupid extends Input {

	public Stupid(int start, int increment, int end) {
		super(start, increment, end);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String nextElement() {
		String next = "";
		for (int i = 0; i < count; i++) {
			next += "a";
		}
		return next;
	}
	
	

}
