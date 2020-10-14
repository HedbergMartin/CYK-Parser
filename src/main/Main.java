package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.grammar.ChomskyGrammar;
import main.grammar.LinearGrammar;
import main.parser.Parser;
import main.parser.Result;

public class Main {
	
	public static String filename;

	public static void main(String[] args) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date date = new Date(System.currentTimeMillis());
		new File("output").mkdir();
		filename = "output/tests" + formatter.format(date) + ".txt";

		ChomskyGrammar cg = new ChomskyGrammar("parentheses.txt");
		LinearGrammar lg = new LinearGrammar("linear_grammar.txt");

//		runBottomUp(cg);
//		
//		runTopDown(cg);
//
//		runStupid(cg);
		
		runTDlinear(lg);
		try {
			runTDlinearToCNF(LinearGrammar.toChomsky("linear_grammar.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(String input) {
		try {
			FileWriter file = new FileWriter(filename, true);
			file.write(input);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void runBottomUp(ChomskyGrammar g) {
		runBottomUpBaseCase(g);
		runBottomUpEarlyRule(g);
		runBottomUpEarlyVsLate(g);
		runBottomUpUnknownClose(g);
	}

	public static void runTopDown(ChomskyGrammar g) {
		runTopDownBaseCase(g);
		runTopDownEarlyRule(g);
		runTopDownEarlyVsLate(g);
		runTopDownUnknownClose(g);
	}
	
	public static void runBottomUpBaseCase(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runBottomUpBenchmark(new Repeat(increment, increment, end, "()"), g);
		
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", ")", "("), g);

		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")"), g);
		
		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", ")", "("), g);
	}

	public static void runBottomUpEarlyRule(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		//See basecase
		
		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", "()", "()"), g);
	}

	public static void runBottomUpEarlyVsLate(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", ")", ""), g);
		
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", "", "("), g);

		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", ")", ""), g);
		
		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", "", "("), g);
	}

	public static void runBottomUpUnknownClose(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", "(", ""), g);
		
		runBottomUpBenchmark(new Repeat(increment, increment, end, "()", "", ")"), g);

		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", "(", ""), g);
		
		runBottomUpBenchmark(new OpenClose(increment, increment, end, "(", ")", "", ")"), g);
	}
	
	// Top-down part
	public static void runTopDownBaseCase(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runTopDownBenchmark(new Repeat(increment, increment, end, "()"), g, 1);
		
		runTopDownBenchmark(new Repeat(increment, increment, end, "()", ")", "("), g, 1);

		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")"), g, 1);
		
		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", ")", "("), g, 1);
	}

	public static void runTopDownEarlyRule(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		//See basecase
		//runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")"), g, 1);
		
		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", "()", "()"), g, 1);
	}

	public static void runTopDownEarlyVsLate(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runTopDownBenchmark(new Repeat(increment, increment, end, "()", ")", ""), g, 1);
		
		runTopDownBenchmark(new Repeat(increment, increment, end, "()", "", "("), g, 1);

		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", ")", ""), g, 1);
		
		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", "", "("), g, 1);
	}

	public static void runTopDownUnknownClose(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 2500/2;

		runTopDownBenchmark(new Repeat(increment, increment, end, "()", "(", ""), g, 1);
		
		runTopDownBenchmark(new Repeat(increment, increment, end, "()", "", ")"), g, 1);

		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", "(", ""), g, 1);
		
		runTopDownBenchmark(new OpenClose(increment, increment, end, "(", ")", "", ")"), g, 1);
	}

	// Linear
	public static void runTDlinear(LinearGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 10000/2;

		//runTopDownLinear(new Repeat(increment, increment, end, "()"), g, 1);
		
		//runTopDownLinear(new Repeat(increment, increment, end, "()", ")", "("), g, 1);

		//runTopDownLinear(new OpenClose(increment, increment, end, "a", ")"), g, 1);

		runTopDownLinear(new OpenClose(increment, increment, end, "a", "c", "", "", "b"), g, 1);
		
		runTopDownLinear(new OpenClose(increment, increment, end, "a", "c", "", "", "ba"), g, 1);
	}
	
	public static void runTDlinearToCNF(ChomskyGrammar g) {
		int increment = 100/2; //Divide by 2 because repeat/opener-closer has len of 2.
		int end = 10000/2;

		//runTopDownLinear(new Repeat(increment, increment, end, "()"), g, 1);
		
		//runTopDownLinear(new Repeat(increment, increment, end, "()", ")", "("), g, 1);

		//runTopDownLinear(new OpenClose(increment, increment, end, "a", ")"), g, 1);

		runTopDownBenchmark(new OpenClose(increment, increment, end, "a", "c", "", "", "b"), g, 1);
		
		runTopDownBenchmark(new OpenClose(increment, increment, end, "a", "c", "", "", "ba"), g, 1);
	}
	
	public static void runStupid() {
		ChomskyGrammar g = new ChomskyGrammar("stupid.txt");
		int increment = 100/2;
		int end = 2500/2;
		
		runBottomUpBenchmark(new Stupid(increment, increment, end), g);

		runTopDownBenchmark(new Stupid(increment, increment, end), g, 1);
	}
	
	public static void runBottomUpBenchmark(Input i, ChomskyGrammar g) {
		System.out.println("Bottom-up " + i.getName() + "\n");
		writeToFile("Bottom-up " + i.getName() + "\n");
		
		while (i.hasMoreElements()) {
			Result res = Parser.parseBottomUp(i.nextElement(), g);

			writeToFile(res.excelFormat() + "\n");
			System.out.println(res.excelFormat());
		}
		System.out.println("\n");
		writeToFile("\n");
	}
	
	public static void runNaive(Input i, ChomskyGrammar g) {
		System.out.println("Naive " + i.getName() + "\n");
		writeToFile("Naive " + i.getName() + "\n");
		
		while (i.hasMoreElements()) {
			String s = i.nextElement();
			Result res = Parser.parseNaive(s, g);

			writeToFile(res.excelFormat() + "\n");
			System.out.println(res.excelFormat());
		}
		System.out.println("\n");
		writeToFile("\n");
	}
	
	public static void runTopDownBenchmark(Input i, ChomskyGrammar g, int n) {
		System.out.println("Top-down " + i.getName() + "\n");
		writeToFile("Top-down " + i.getName() + "\n");
		
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
			
			writeToFile(res.excelFormat() + "\n");
			System.out.println(res.excelFormat());
		}
		System.out.println("\n");
		writeToFile("\n");
	}
	
	public static void runTopDownLinear(Input i, LinearGrammar g, int n) {
		System.out.println("Top-down Linear " + i.getName() + "\n");
		writeToFile("Top-down Linear " + i.getName() + "\n");
		
		while (i.hasMoreElements()) {
			System.gc();
			Result res = new Result();
			String s = i.nextElement();
			for (int j = 0; j < n; j++) {
				Result r = Parser.parseTopDown(s, g);
				res.wasFound = r.wasFound;
				res.strLen = r.strLen;
				res.time += r.time;
			}
			res.time /= n;
			
			writeToFile(res.excelFormat() + "\n");
			System.out.println(res.excelFormat());
		}
		System.out.println("\n");
		writeToFile("\n");
	}

}
