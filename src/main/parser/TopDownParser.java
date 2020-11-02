package main.parser;

import main.grammar.ChomskyGrammar;
import main.grammar.ChomskyRule;

public class TopDownParser {
	
	static byte[][][] table;
	static ChomskyGrammar grammar;
	static Result r;
	static String input;
	
	public static Result parse(String s, ChomskyGrammar g) {
		r = new Result();
		input = s;
		grammar = g;
		long start = System.nanoTime();
		
		//0 == null, 1 == no, 2 == yes
		table = new byte[g.getAmountOfNonTerminals()][input.length()][input.length()];
		r.wasFound = parseRec(g.getInitial(), 0, input.length());
		
		long end = System.nanoTime();
		r.time = (double)(end - start) / Result.NANOSEC;
		r.strLen = input.length();
		return r;
	}
	
	//nonTerminal + i * g.amountOfNonTerminals + (j-1) * g.amountOfNonTerminals * s.length()
	private static boolean parseRec(int nonTerminal, int i, int j) {
		int path;
		if ((path = table[nonTerminal][i][j-1]) != 0) {
			if (path == 2) {
				return true;
			} else {
				return false;
			}
		} else if (i == j-1) {
			return grammar.isTerminalRule(nonTerminal, input.charAt(i));
		} else {
			ChomskyRule rhs;
			for (int ruleNr = 0; ruleNr < grammar.ruleSize(nonTerminal); ruleNr++) {
				rhs = grammar.getRule(nonTerminal, ruleNr);
				for (int k = i + 1; k <= j - 1; k++) {
					if (parseRec(rhs.B, i, k) && parseRec(rhs.C, k, j)) {
						table[nonTerminal][i][j-1] = 2;
						return true;
					}
				}
			}
		}
		
		table[nonTerminal][i][j-1] = 1;
		return false;
	}
}
