package main.parser;

import main.grammar.Grammar;
import main.grammar.Rhs;

public class Parser {
	
	final static long NANOSEC = 1000000000;

	public static Result parseNaive(String s, Grammar g) {
		Result r = new Result();
		r.ops = 0;
		long start = System.nanoTime();
		r.wasFound = parseNaiveRec(r, s, g, g.getInitial(), 0, s.length());
		long end = System.nanoTime();
		r.time = (double)(end - start) / NANOSEC;
		r.strLen = s.length();
		return r;
	}
	


	private static boolean parseNaiveRec(Result r, String s, Grammar g, int nonTerminal, int i, int j) {
		r.ops++;
		if (i == j-1) {
			return g.isTerminalRule(nonTerminal, s.charAt(i));
		} else {
			Rhs rhs;
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
	
	public static Result parseBottomUp(String s, Grammar g) {
		Result r = new Result();
		r.ops = 0;
		r.strLen = s.length();
		long start = System.nanoTime();
		boolean found = false;
		boolean[][][] table = new boolean[g.amountOfNonTerminals][s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < g.amountOfNonTerminals; j++) {
				table[j][0][i] = g.isTerminalRule(j, s.charAt(i));
				r.ops++;
			}
		}
		
		for (int l = 1; l < s.length(); l++) {
			for (int i = 0; i < s.length() - l; i++) {
				for (int p = 0; p < l; p++) {
					Rhs rhs;
					for (int nonTerminal = 0; nonTerminal < g.amountOfNonTerminals; nonTerminal++) {
						for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
							rhs = g.getRule(nonTerminal, ruleNr);
							
							if (table[rhs.B][p][i] && table[rhs.C][l-p-1][i+p+1]) {
								table[nonTerminal][l][i] = true;
							}
							r.ops++;
						}
					}
				}
			}
		}
		
		if (table[0][s.length()-1][0]) {
			found = true;
		}

		long end = System.nanoTime();
		r.time = (double)(end - start) / NANOSEC;
		r.wasFound = found;
		return r;
	}
	
	public static Result parseTopDown(String s, Grammar g) {

		Result r = new Result();
		r.ops = 0;
		long start = System.nanoTime();
		//0 == null, 1 == no, 2 == yes
		int[][][] table = new int[g.amountOfNonTerminals][s.length()][s.length()];
		r.wasFound = parseTopDownRec(r, s, g, table, g.getInitial(), 0, s.length());
		long end = System.nanoTime();
		r.time = (double)(end - start) / NANOSEC;
		r.strLen = s.length();
		return r;
	}
	
	private static boolean parseTopDownRec(Result r, String s, Grammar g, int[][][] table, int nonTerminal, int i, int j) {
		r.ops++;
		int path;
		if ((path = table[nonTerminal][i][j-1]) != 0) {
			if (path == 2) {
				return true;
			} else {
				return false;
			}
		} else if (i == j-1) {
			return g.isTerminalRule(nonTerminal, s.charAt(i));
		} else {
			Rhs rhs;
			for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
				rhs = g.getRule(nonTerminal, ruleNr);
				for (int k = i + 1; k <= j - 1; k++) {
					if (parseTopDownRec(r, s, g, table, rhs.B, i, k) && parseTopDownRec(r, s, g, table, rhs.C, k, j)) {
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
