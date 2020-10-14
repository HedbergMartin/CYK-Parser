package main.grammar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChomskyGrammar extends Grammar<ChomskyRule> {
	
	public ChomskyGrammar() {
		
	}

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
		
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			if (line.length() == 4) { //To non-terminals
				addRule(
				getNonTerminalID(line.charAt(0)),
				new ChomskyRule(getNonTerminalID(line.charAt(2)), 
								getNonTerminalID(line.charAt(3)))
				);
			} else if (line.length() == 3) { //To terminal
				addTerminal(getNonTerminalID(line.charAt(0)), line.charAt(2));
			} else if (line.length() != 0) {
				fileScanner.close();
				stream.close();
				throw new Exception("File format error");
			}
		}
		
		fileScanner.close();
		stream.close();
	}

	@Override
	public int getInitial() {
		return 0;
	}
}
