package main.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Grammar {
	private ArrayList<ArrayList<Rhs>> rules;
	
	private HashMap<Character, Integer> terminalMap;
	
	public Grammar() {
		terminalMap = new HashMap<Character, Integer>();
		rules = new ArrayList<ArrayList<Rhs>>();
	}
	
	public void addTerminal(char terminal, int nonTerminalID) {
		terminalMap.put(terminal, nonTerminalID);
	}
	
	public boolean terminalRule(char terminal, int nonTerminal) {
		if (!terminalMap.containsKey(terminal)) {
			return false;
		}
		return terminalMap.get(terminal) == nonTerminal;
	}
	
	public void addRule(int A, int B, int C) {
		while (rules.size() <= A) {
			rules.add(new ArrayList<Rhs>());
		}
		rules.get(A).add(new Rhs(B, C));
	}
	
	public Rhs getRule(int A, int n) {
		return rules.get(A).get(n);
	}
	
	public int ruleSize(int nonTerminal) {
		if (rules.size() <= nonTerminal) {
			return 0;
		}
		return rules.get(nonTerminal).size();
	}
	
	public abstract int getInitial();
}
