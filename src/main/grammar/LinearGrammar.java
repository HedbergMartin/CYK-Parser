package main.grammar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LinearGrammar extends Grammar<LinearRule> {

	public LinearGrammar(String fileName) {
		try {
			parseFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseFile(String fileName) throws FileNotFoundException,Exception {
		FileInputStream stream = new FileInputStream(fileName);
		Scanner fileScanner = new Scanner(stream, "ASCII");

		ArrayList<Rule> rules = new ArrayList<Rule>();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line.length() == 4) { //To non-terminals
				rules.add(new Rule(getNonTerminalID(line.charAt(0)), line.charAt(2), line.charAt(3)));
			} else if (line.length() == 3) { //To terminal
				addTerminal(getNonTerminalID(line.charAt(0)), line.charAt(2));
			} else if (line.length() != 0) {
				fileScanner.close();
				stream.close();
				throw new Exception("File format error");
			}
		}
		
		for (Rule r : rules) {
			if (isNonTerminal(r.left) && isNonTerminal(r.right)) {
				fileScanner.close();
				stream.close();
				throw new Exception("Two nonterminals on rhs!");
			}
			if (isNonTerminal(r.left)) {
				addRule(r.nont, new LinearRule(r.right, getNonTerminalID(r.left), true));
			} else {
				addRule(r.nont, new LinearRule(r.left, getNonTerminalID(r.right), false));
			}
		}
		
		fileScanner.close();
		stream.close();
	}
	
	public static ChomskyGrammar toChomsky(String fileName) throws Exception {
		ChomskyGrammar grammar = new ChomskyGrammar();
		FileInputStream stream = new FileInputStream(fileName);
		Scanner fileScanner = new Scanner(stream, "ASCII");

		ArrayList<Rule> rules = new ArrayList<Rule>();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line.length() == 4) { //To non-terminals
				rules.add(new Rule(grammar.getNonTerminalID(line.charAt(0)), line.charAt(2), line.charAt(3)));
			} else if (line.length() == 3) { //To terminal
				grammar.addTerminal(grammar.getNonTerminalID(line.charAt(0)), line.charAt(2));
			} else if (line.length() != 0) {
				fileScanner.close();
				stream.close();
				throw new Exception("File format error");
			}
		}
		
		for (Rule r : rules) {
			if (grammar.isNonTerminal(r.left) && grammar.isNonTerminal(r.right)) {
				fileScanner.close();
				stream.close();
				throw new Exception("Two nonterminals on rhs!");
			}
			grammar.addRule(r.nont, new ChomskyRule(grammar.getNonTerminalID(r.left), grammar.getNonTerminalID(r.right)));
		}
		
		fileScanner.close();
		stream.close();
		
		return grammar;
	}
	
	@Override
	public int getInitial() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Rule {
	int nont;
	char left, right;
	public Rule(int nont, char left, char right) {
		this.nont = nont;
		this.left = left;
		this.right = right;
	}
}

