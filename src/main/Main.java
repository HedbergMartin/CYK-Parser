package main;

import main.grammar.ChomskyGrammar;
import main.grammar.Grammar;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0]);
		Grammar g = new ChomskyGrammar(args[0]);
	}

}
