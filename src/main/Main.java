package main;

import main.grammar.ChomskyGrammar;
import main.grammar.Grammar;
import main.parser.Parser;
import main.parser.Result;

public class Main {

	public static void main(String[] args) {
		Grammar g = new ChomskyGrammar("parentheses.txt");
//		String s = "((a))()()(((())))";
//		Result naiveRes = Parser.parseNaive(s, g);
		
//		Parser.parseBottomUp(new OpenClose(100, 100, 5000).nextElement(), g).print();
//		Result topdownRes = Parser.parseTopDown(s, g);
//		naiveRes.print();
//		bottomupRes.print();
//		topdownRes.print();

		//Parser.parseNaive("a", g).print();
		//Parser.parseNaive("aa", g).print();
		
		runBottomUpBenchmarks(g);
		runTopDownSlow(g);
		runTopDownFast(g);
		runStupid();
	}
	
	public static void runBottomUpBenchmarks(Grammar g) {
		int increment = 100;
		int end = 5000;
		System.out.println("Bottom up ((...))\n");
		runBottomUpBenchmark(new OpenClose(increment, increment, end), g);

		System.out.println("Bottom up ()...()\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end), g);
		
		System.out.println("Bottom up ()...())\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end, "", ")"), g);
		
		System.out.println("Bottom up )()...()\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end, ")", ""), g);
	}
	
	public static void runTopDownSlow(Grammar g) {
		int increment = 100;
		int end = 5000;
		System.out.println("Top down slow ((...))\n");
		runTopDownBenchmark(new OpenClose(increment, increment, end), g);

		System.out.println("Top down slow ()...()(\n");
		runTopDownBenchmark(new Repeat(increment, increment, end, "", "("), g);
	}
	
	public static void runTopDownFast(Grammar g) {
		int increment = 400;
		int end = 10000;
		System.out.println("Top down fast ((...))\n");
		runTopDownBenchmark(new OpenClose(increment, increment, end), g);

		System.out.println("Top down fast )()...()\n");
		runTopDownBenchmark(new Repeat(increment, increment, end, "", "("), g);
	}
	
	public static void runStupid() {
		Grammar g = new ChomskyGrammar("stupid.txt");
		int increment = 100;
		int end = 5000;
		System.out.println("Stupid bottom up\n");
		runBottomUpBenchmark(new Stupid(increment, increment, end), g);

		System.out.println("Stupid top down\n");
		runTopDownBenchmark(new Stupid(increment, increment, end), g);
	}
	
	public static void runBottomUpBenchmark(Input i, Grammar g) {
		while (i.hasMoreElements()) {
			Parser.parseBottomUp(i.nextElement(), g).printExcel();
		}
	}
	
	public static void runTopDownBenchmark(Input i, Grammar g) {
		while (i.hasMoreElements()) {
			Parser.parseTopDown(i.nextElement(), g).printExcel();
		}
	}

}
