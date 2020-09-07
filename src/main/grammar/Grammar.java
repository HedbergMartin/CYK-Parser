package main.grammar;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Grammar {
	public int[][] rules;
	
	private HashMap<Character, Integer> terminalMap;
	
	public Grammar() {
		terminalMap = new HashMap<Character, Integer>();
	}
	
	public void initRules(int n) {
		rules = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(rules[i], -1);
		}
		//System.out.println(Arrays.deepToString(rules));
	}
	
	public void addTerminal(char terminal, int nonTerminalID) {
		terminalMap.put(terminal, nonTerminalID);
	}
	
	public boolean terminalRule(char terminal, int nonTerminal) {
		return terminalMap.get(terminal) == nonTerminal;
	}
	
	public void addRule(int A, int B, int C) {
		rules[A][B] = C;
	}
	
	public abstract int getInitial();
}
