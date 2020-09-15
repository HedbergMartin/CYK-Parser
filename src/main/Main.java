package main;

import main.grammar.ChomskyGrammar;
import main.grammar.Grammar;
import main.parser.Parser;
import main.parser.Result;

public class Main {

	public static void main(String[] args) {
		System.out.println(args[0]);
		Grammar g = new ChomskyGrammar(args[0]);
		String s = "((a))()()(((())))";
		Result naiveRes = Parser.parseNaive(s, g);
		Result bottomupRes = Parser.parseBottomUp(s, g);
		Result topdownRes = Parser.parseTopDown(s, g);
		naiveRes.print();
		bottomupRes.print();
		topdownRes.print();
	}

}
