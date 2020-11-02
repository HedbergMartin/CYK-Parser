package main.parser;

import main.grammar.ChomskyGrammar;
import main.grammar.ChomskyRule;

public class BottomUpParser {
	
	public static Result parse(String s, ChomskyGrammar g) {
		Result r = new Result();
		r.strLen = s.length();
		boolean found = false;
		boolean[][][] table = new boolean[g.getAmountOfNonTerminals()][s.length()][s.length()];
		
		long start = System.nanoTime();
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < g.getAmountOfNonTerminals(); j++) {
				table[j][0][i] = g.isTerminalRule(j, s.charAt(i));
			}
		}
		
		ChomskyRule rhs;
		for (int l = 1; l < s.length(); l++) {
			for (int i = 0; i < s.length() - l; i++) {
				nonTerminalLoop:
				for (int nonTerminal = 0; nonTerminal < g.getAmountOfNonTerminals(); nonTerminal++) {
					for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
						rhs = g.getRule(nonTerminal, ruleNr);

						for (int p = 0; p < l; p++) {
							if (table[rhs.B][p][i] && table[rhs.C][l-p-1][i+p+1]) {
								table[nonTerminal][l][i] = true;
								continue nonTerminalLoop;
							}
						}
					}
				}
			}
		}
		
		if (table[0][s.length()-1][0]) {
			found = true;
		}

		long end = System.nanoTime();
		r.time = (double)(end - start) / Result.NANOSEC;
		r.wasFound = found;
		return r;
	}
}
