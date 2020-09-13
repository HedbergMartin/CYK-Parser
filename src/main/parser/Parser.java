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
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < g.amountOfNonTerminals; j++) {
				table[0][i][j] = g.isTerminalRule(j, s.charAt(i));
			}
		}
		
		for (int l = 1; l < s.length(); l++) {
			for (int i = 0; i < s.length() - l; i++) {
				for (int p = 0; p < l; p++) {
					Rhs rhs;
					for (int nonTerminal = 0; nonTerminal < g.amountOfNonTerminals; nonTerminal++) {
						for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
							rhs = g.getRule(nonTerminal, ruleNr);
							
							if (table[p][i][rhs.B] && table[l-p-1][i+p+1][rhs.C]) {
								table[l][i][nonTerminal] = true;
							}
						}
					}
				}
			}
		}
		
		/*for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				System.out.print("X: " + i + " Y: " + j);
				for (int nonTerminal = 0; nonTerminal < g.amountOfNonTerminals; nonTerminal++) {
					for (int ruleNr = 0; ruleNr < g.ruleSize(nonTerminal); ruleNr++) {
						Rhs rhs = g.getRule(nonTerminal, ruleNr);
						
					}
				}
			}
		}*/
		System.out.println(s.length()-1);
		System.out.println(table[s.length()-1][0][0]);
		if (table[s.length()-1][0][0]) {
			return true;
		}
		return false;
	}
}
