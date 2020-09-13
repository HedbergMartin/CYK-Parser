package main.parser;

import main.grammar.Grammar;
import main.grammar.Rhs;

public class Parser {

	public static boolean parseNaive(String s, Grammar g) {
		return parseNaiveRec(s, g, g.getInitial(), 0, s.length());
	}
	


	private static boolean parseNaiveRec(String s, Grammar g, int nonTerminal, int i, int j) {
		if (i == j-1) {
			return g.isTerminalRule(nonTerminal, s.charAt(i));
		} else {
			Rhs rhs;
			for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
				rhs = g.getRule(nonTerminal, ruleNr);
				for (int k = i + 1; k <= j - 1; k++) {
					if (parseNaiveRec(s, g, rhs.B, i, k) && parseNaiveRec(s, g, rhs.C, k, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean parseBottomUp(String s, Grammar g) {
		boolean[][][] table = new boolean[s.length()][s.length()][g.amountOfNonTerminals];
		//for (int i = 0; i < s.length(); i++) {
		//	for (int j = 0; j < g.)
		//}
		return false;
	}
}
