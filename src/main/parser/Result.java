package main.parser;

public class Result {
	
	public boolean wasFound;
	public int strLen;
	public long ops;
	public double time;
	
	public void print() {
		System.out.println("Was found: " + wasFound + ", Len: " + strLen + ", Time taken: " + time + ", Opetations: " + ops);
	}
	
	public void printExcel() {
		System.out.println(strLen + "	" + time + "	" + ops);
	}

}
