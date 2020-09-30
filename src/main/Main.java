package main;

import main.grammar.ChomskyGrammar;
import main.grammar.Grammar;
import main.parser.Parser;
import main.parser.Result;

public class Main {

	public static void main(String[] args) {
		Grammar g = new ChomskyGrammar("parentheses.txt");

		runBottomUpBenchmarks(g);
		runTopDownSlow(g);
		runTopDownFast(g);
		runStupid();
	}
	
	public static void runBottomUpBenchmarks(Grammar g) {
		int increment = 100/2; //Divide by 2 becouse repeat/opener-closer has len of 2.
		int end = 2500/2;
		System.out.println("Bottom up ((...))\n");
		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")"), g);

		System.out.println("Bottom up ()...()\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()"), g);
		
		System.out.println("Bottom up ()...())\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", "", ")"), g);
		
		System.out.println("Bottom up )()...()\n");
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", ")", ""), g);
	}
	
	public static void runTopDownSlow(Grammar g) {
		int increment = 100/2;
		int end = 2500/2;
		System.out.println("Top down slow ((...))\n");
		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")"), g, 1);

		System.out.println("Top down slow ()...()(\n");
		runTopDownBenchmark(new Repeat(increment, increment, end, "()", "", "("), g, 1);
	}
	
	public static void runTopDownFast(Grammar g) {
		int increment = 400/2;
		int end = 10000/2;
		System.out.println("Top down fast ()...()\n");
		runTopDownBenchmark(new Repeat(increment, increment, end, "()"), g, 10);

		System.out.println("Top down fast )()...()\n");
		runTopDownBenchmark(new Repeat(increment, increment, end, "()", ")", ""), g, 10);
	}
	
	public static void runStupid() {
		Grammar g = new ChomskyGrammar("stupid.txt");
		int increment = 100/2;
		int end = 2500/2;
		System.out.println("Stupid bottom up\n");
		runBottomUpBenchmark(new Stupid(increment, increment, end), g);

		System.out.println("Stupid top down\n");
		runTopDownBenchmark(new Stupid(increment, increment, end), g, 1);
	}
	
	public static void runBottomUpBenchmark(Input i, Grammar g) {
		while (i.hasMoreElements()) {
			Parser.parseBottomUp(i.nextElement(), g).printExcel();
		}
	}
	
	public static void runTopDownBenchmark(Input i, Grammar g, int n) {
		while (i.hasMoreElements()) {
			Result res = new Result();
			String s = i.nextElement();
			for (int j = 0; j < n; j++) {
				Result r = Parser.parseTopDown(s, g);
				res.wasFound = r.wasFound;
				res.strLen = r.strLen;
				res.time += r.time;
			}
			res.time /= n;
			res.printExcel();
		}
	}

}
