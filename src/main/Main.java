package main;

import main.grammar.ChomskyGrammar;
import main.grammar.Grammar;
import main.parser.Parser;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0]);
		Grammar g = new ChomskyGrammar(args[0]);
		String s = "((a))()()(((())))";
		//String s = "()(a)(())";
		if (Parser.parseTopDown(s, g)) {
			System.out.println(s + " is in grammar");
		} else {
			System.out.println("Nope " + s);
		}
	}

}
