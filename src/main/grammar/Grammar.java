package main.grammar;

import java.util.ArrayList;

public abstract class Grammar {
	private ArrayList<ArrayList<Rhs>> rules;
	
	private ArrayList<Character> terminalRules;
	
	public int amountOfNonTerminals = 0;
	
	public Grammar() {
		terminalRules = new ArrayList<Character>();
		rules = new ArrayList<ArrayList<Rhs>>();
	}
	
	public void addTerminal(int nonTerminalID, char terminal) {
		while (terminalRules.size() <= nonTerminalID) {
			terminalRules.add((char) 0);
		}
		terminalRules.set(nonTerminalID, terminal);
	}
	
	public boolean isTerminalRule(int nonTerminal, char terminal) {
		if (terminalRules.size() <= nonTerminal) {
			return false;
		}
		return terminalRules.get(nonTerminal) == terminal;
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
