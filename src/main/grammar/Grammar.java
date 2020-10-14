package main.grammar;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Grammar<Rhs> {
	private ArrayList<ArrayList<Rhs>> rules;
	
	private ArrayList<ArrayList<Character>> terminalRules;
	
	//Used during init only
	private HashMap<Character, Integer> nonTerminals = new HashMap<Character, Integer>();
	
	public Grammar() {
		terminalRules = new ArrayList<ArrayList<Character>>();
		rules = new ArrayList<ArrayList<Rhs>>();
	}
	
	public void addTerminal(int nonTerminalID, char terminal) {
		while (terminalRules.size() <= nonTerminalID) {
			terminalRules.add(new ArrayList<Character>());
		}
		terminalRules.get(nonTerminalID).add(terminal);
	}
	
	public boolean isTerminalRule(int nonTerminal, char terminal) {
		if (terminalRules.size() <= nonTerminal) {
			return false;
		}
		for (int i = 0; i < terminalRules.get(nonTerminal).size(); i++) {
			if (terminalRules.get(nonTerminal).get(i) == terminal) {
				return true;
			}
		}
		return false;
	}
	
	
	public void addRule(int A, Rhs rhs) {
		while (rules.size() <= A) {
			rules.add(new ArrayList<Rhs>());
		}
		rules.get(A).add(rhs);
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
	
	public int getNonTerminalID(char nonTerminal) {
		if (!nonTerminals.containsKey(nonTerminal)) {
			nonTerminals.put(nonTerminal, nonTerminals.size());
		}
		return nonTerminals.get(nonTerminal);
	}
	
	public boolean isNonTerminal(char key) {
		return nonTerminals.containsKey(key);
	}
	
	public int getAmountOfNonTerminals() {
		return nonTerminals.size();
	}
	
	public abstract int getInitial();
}
