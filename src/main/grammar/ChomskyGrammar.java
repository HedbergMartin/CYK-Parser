package main.grammar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChomskyGrammar extends Grammar {

	public ChomskyGrammar(String fileName) {
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
		
		HashMap<Character, Integer> nonTerminals = new HashMap<Character, Integer>();
		ArrayList<String> nonTerminalLines = new ArrayList<String>();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line.length() == 4) { //To non-terminals
				nonTerminalLines.add(""+
				getNonTerminalID(nonTerminals, line.charAt(0)) +
				getNonTerminalID(nonTerminals, line.charAt(2)) +
				getNonTerminalID(nonTerminals, line.charAt(3))
				);
			} else if (line.length() == 3) { //To terminal
				addTerminal(line.charAt(2), getNonTerminalID(nonTerminals, line.charAt(0)));
			} else if (line.length() != 0) {
				fileScanner.close();
				stream.close();
				throw new Exception("File format error");
			}
		}
		
		initRules(nonTerminals.size());
		
		for (String line: nonTerminalLines) {
			addRule(line.charAt(0) - '0', line.charAt(1)  - '0', line.charAt(2) - '0');
		}
		
		fileScanner.close();
		stream.close();
	}
	
	private int getNonTerminalID(HashMap<Character, Integer> nonTerminals, char nonTerminal) {
		if (!nonTerminals.containsKey(nonTerminal)) {
			nonTerminals.put(nonTerminal, nonTerminals.size());
		}
		return nonTerminals.get(nonTerminal);
	}

	@Override
	public int getInitial() {
		return 0;
	}
}
