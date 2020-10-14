package main.grammar;

public class LinearRule {

	public int nont;
	public char term;
	public boolean nonToLeft;
	
	public LinearRule(char left, int right, boolean nonToLeft) {
		this.term = left;
		this.nont = right;
		this.nonToLeft = nonToLeft;
	}
	
	
}
