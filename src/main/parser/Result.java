package main.parser;

public class Result {
	
	public boolean wasFound;
	public int strLen;
	public int ops;
	public long nanoSec;
	
	public void print() {
		System.out.println("Was found: " + wasFound + ", Len: " + strLen + ", Time taken: " + nanoSec + ", Opetations: " + ops);
	}

}
