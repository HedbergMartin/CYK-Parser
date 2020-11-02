package main.parser;

import main.grammar.LinearGrammar;
import main.grammar.LinearRule;

public class LinearParser {
	
	static Result r;
	static String input;
	static LinearGrammar grammar;

	public static Result parse(String s, LinearGrammar g) {
		Result r = new Result();
		grammar = g;
		input = s;
		long start = System.nanoTime();
		
		//0 == null, 1 == no, 2 == yes
		r.wasFound = parseRec(grammar.getInitial(), 0, input.length());
		
		long end = System.nanoTime();
		r.time = (double)(end - start) / Result.NANOSEC;
		r.strLen = input.length();
		return r;
	}
	
	//nonTerminal + i * g.amountOfNonTerminals + (j-1) * g.amountOfNonTerminals * s.length()
	private static boolean parseRec(int nonTerminal, int i, int j) {
		if (i == j-1) {
			return grammar.isTerminalRule(nonTerminal, input.charAt(i));
		} else {
			for (int ruleNr = 0; ruleNr < grammar.ruleSize(nonTerminal); ruleNr++) {
				LinearRule rhs = grammar.getRule(nonTerminal, ruleNr);
				if (rhs.nonToLeft) {
					if ((rhs.term == input.charAt(j-1)) && parseRec(rhs.nont, i, j-1)) {
						return true;
					}
				} else {
					if ((rhs.term == input.charAt(i)) && parseRec(rhs.nont, i+1, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
