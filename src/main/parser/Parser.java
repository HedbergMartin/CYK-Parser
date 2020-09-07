package main.parser;

import main.grammar.Grammar;

public class Parser {

	public static boolean parseNaive(String s, Grammar g) {
		return parseNaiveRec(s, g, g.getInitial(), 0, s.length());
	}
	


	private static boolean parseNaiveRec(String s, Grammar g, int nonTerminal, int i, int j) {
		if (i == j-1) {
			return g.terminalRule(s.charAt(i), nonTerminal);
		} else {
			int C;
			for (int B = 0; B < g.rules.length; B++) {
				if ((C = g.rules[nonTerminal][B]) != -1) {
					for (int k = i + 1; k <= j - 1; k++) {
						if (parseNaiveRec(s, g, B, i, k) && parseNaiveRec(s, g, C, k, j)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
