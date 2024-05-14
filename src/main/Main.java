package main;

import java.lang.reflect.Array;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import debug.exceptions.HTTPReadingSessionFailException;
import model.SearchResultData;
import model.TextBinarySearchTree;
import debug.logs.MainLogger;
import model.net.HTTPRequestSender;
import model.net.HTTPResponseLazyReader;
import model.text.TextParser;
import view.SearchResultPrinter;


public class Main {
	@SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
		MainLogger.logMessage("Application launched.");



		String url = "https://pastebin.com/raw/r5KfUNn0";
		String token = "Папа";

		TextBinarySearchTree binTree = new TextBinarySearchTree();

		try {
			HTTPRequestSender sender = new HTTPRequestSender();
			HttpResponse<String> textResponse = sender.getResponseFromUrl(url);

            try (HTTPResponseLazyReader reader = new HTTPResponseLazyReader(textResponse)) {
                while (reader.hasNext()) {
                    String line = reader.next();

					System.out.println(line);

					List<String> words = TextParser.getWords(line);
					binTree.insertAll(words);
                }
            }

			ArrayList<SearchResultData> hitsList = binTree.findWith(token);

			SearchResultPrinter.displayFormatted(hitsList);

			//System.out.println(binTree.toString());

		} catch (HTTPReadingSessionFailException ex) {

		} catch (Throwable ex) {
			MainLogger.logSevere(ex);
		}

		MainLogger.logMessage("Application work finished.");

		return;

		/*
		String fileName = "C:\\Users\\yusere\\Documents\\Java Projects\\Text Search Tree\\src\\testfile.txt";
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
		*/
    }

}