package main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import model.SearchResultData;
import model.TextBinarySearchTree;
import debug.logs.MainLogger;
import model.text.TextParser;
import view.SearchResultPrinter;


public class Main {
	@SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
		MainLogger.logMessage("Application launched.");

		String fileName = "C:\\Users\\Ad1s\\AppData\\Local\\Programs\\Java\\Projects\\Text-Search-Tree\\src\\testfile.txt";
		String token = "Слово";

		try {
            String text = Files.readString(Path.of(fileName));
			List<String> words = TextParser.getWords(text);

			TextBinarySearchTree binTree = new TextBinarySearchTree(words);

			ArrayList<SearchResultData> hitsList = binTree.findWith(token);

			SearchResultPrinter.displayFormatted(hitsList);

		} catch (Throwable ex) {
			MainLogger.logSevere(ex);
		}

		MainLogger.logMessage("Application work finished.");
    }
    
}