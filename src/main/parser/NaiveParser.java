package main.parser;

import main.grammar.ChomskyGrammar;
import main.grammar.ChomskyRule;
import main.grammar.LinearGrammar;
import main.grammar.LinearRule;

public class NaiveParser {
	
	final static long NANOSEC = 1000000000;

	public static Result parse(String s, ChomskyGrammar g) {
		Result r = new Result();
		long start = System.nanoTime();
		r.wasFound = parseNaiveRec(r, s, g, g.getInitial(), 0, s.length());
		long end = System.nanoTime();
		r.time = (double)(end - start) / NANOSEC;
		r.strLen = s.length();
		return r;
	}

	private static boolean parseNaiveRec(Result r, String s, ChomskyGrammar g, int nonTerminal, int i, int j) {
		if (i == j-1) {
			return g.isTerminalRule(nonTerminal, s.charAt(i));
		} else {
			ChomskyRule rhs;
			for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
				rhs = g.getRule(nonTerminal, ruleNr);
				for (int k = i + 1; k <= j - 1; k++) {
					if (parseNaiveRec(r, s, g, rhs.B, i, k) && parseNaiveRec(r, s, g, rhs.C, k, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
}
