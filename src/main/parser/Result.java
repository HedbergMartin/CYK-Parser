package main.parser;

public class Result {
	final static long NANOSEC = 1000000000;
	
	public boolean wasFound = false;
	public int strLen = 0;
	//public long ops = 0;
	public double time = 0;
	
	public void print() {
		System.out.println("Was found: " + wasFound + ", Len: " + strLen + ", Time taken: " + time + ", Opetations: ");
	}
	
	public String excelFormat() {
		return strLen + "	" + time + "	" + wasFound;
	}

}
