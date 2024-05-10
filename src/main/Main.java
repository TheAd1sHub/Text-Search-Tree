package main;

import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.userinput.BaseUserInputReader;
import controller.userinput.FileNameInputReader;
import controller.userinput.SoughtForTokenInputReader;
import controller.userinput.UserInputReader;
import model.SearchResultData;
import model.TextBinarySearchTree;
import model.filework.FileContentsReader;
import org.w3c.dom.Text;
import view.MessagePrinter;
import view.SearchResultPrinter;

import static java.util.stream.Collectors.toList;


public class Main {
    public static void main(String[] args) {
		boolean isTrue = true;
		Boolean wrappedIsTrue = true;

		Boolean comparison = isTrue == wrappedIsTrue;



        //String[] arr = new String[] {"азбука", "заяц", "борис", "фаренгейт", "краюшка"};
        //TextBinarySearchTree binTree = new TextBinarySearchTree(Arrays.asList(arr));

		String fileName = "C:\\Users\\yusere\\Documents\\Java Projects\\Text Search Tree\\src\\testfile.txt";
		String token = "Папа";
		boolean ignoreTokenCase = true;

		try {
			//String[] words = Files
			//		.readString(Path.of(fileName))
			//		.replace("\n", " ")
			//		.replace("\r", "")
			//		.replaceAll("[^\\p{IsAlphabetic}\\s+]", "")
			//		.split(" ");

			//String[] words = Files
			//		.readString(Path.of(fileName))
			//		.replaceAll("\\S+", " ").split(" ");

			String text = Files.readString(Path.of(fileName));
			List<String> words = Pattern.compile("\\b\\p{L}+\\b")
										.matcher(text)
										.results()
										.map(MatchResult::group)
										.toList();

			TextBinarySearchTree binTree = new TextBinarySearchTree(words);

			ArrayList<SearchResultData> hitsList = binTree.findWith(token, ignoreTokenCase);

			SearchResultPrinter.displayFormatted(hitsList);

			//System.out.println(binTree.toString());



		} catch (Exception ex) {
			System.out.println(ex.getClass() + ": " + ex.getMessage());
		} catch (Throwable th) {
			System.out.println("What?..");
		}



    //    try {
    //    	MessagePrinter.printFileNameRequest();
    //    	String fileName = FileNameInputReader.read(true, true);
//
    //    	MessagePrinter.printElementToSearchRequest();
    //    	String token = UserInputReader.readAsString();
//
    //    	if (token.isEmpty()) {
    //    		throw new InputMismatchException("Cannot analyze empty token!");
    //    	}
//
	//		MessagePrinter.printIgnoreCaseToggleRequest();
    //    	boolean ignoreCase = UserInputReader.readAsBool();
//
	//		String[] words = FileContentsReader.read(fileName).split(" ");
//
	//		for (String word : words) {
	//			System.out.println(word);
	//		}
//
	//		binTree = new TextBinarySearchTree(Arrays.asList(words));
    //    	ArrayList<SearchResultData> hitsList = binTree.findWith(token, true);
    // 	    SearchResultPrinter.displayFormatted(hitsList);
//
    //    } catch (Exception ex) {
    //    	System.out.println(ex.getClass() + ": " + ex.getMessage());
    //    }
    //
    }
    
}