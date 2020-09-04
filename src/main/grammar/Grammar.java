package main.grammar;

import java.util.HashMap;

public abstract class Grammar {
	private Integer[][] rules;
	
	private HashMap<Character, Integer> terminalMap;
	
	public Grammar() {
		terminalMap = new HashMap<Character, Integer>();
	}
	
	public void initRules(int n) {
		rules = new Integer[n][n];
	}
	
	public void addTerminal(char terminal, int nonTerminalID) {
		terminalMap.put(terminal, nonTerminalID);
	}
	
	public void addRule(int from, int left, int right) {
		rules[left][right] = from;
	}
}
