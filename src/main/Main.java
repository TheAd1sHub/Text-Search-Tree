package main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import debug.exceptions.HTTPReadingSessionFailException;
import model.SearchResultData;
import model.TextBinarySearchTree;
import debug.logs.MainLogger;
import model.iterators.ExternalDataIterator;
import model.iterators.RawUrlDataIterator;
import model.net.BorderedHttpResponseStreamReader;
import model.net.FullScopeHttpResponseStreamReader;
import model.net.UrlStreamReceiver;
import model.text.TextParser;
import view.SearchResultPrinter;


public class Main {
	@SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
        MainLogger.logMessage("Application launched.");


        String url = "https://pastebin.com/raw/r5KfUNn0"; // "Папа у Васи..."
        String url2 = "https://pastebin.com/raw/s5bFXyaM"; // О добром медведе
        String url2s = "https://pastebin.com/raw/Liy8Gqw6"; // Первое предложение из текста О добром медведе
        String url3 = "https://pastebin.com/raw/X5r6xFs5"; // Числительные с "Первое" по "Десятое"
        String token = "Восьмое";

        try {
            InputStream stream = new UrlStreamReceiver(new URL(url)).openStream();
            BorderedHttpResponseStreamReader reader = new BorderedHttpResponseStreamReader(stream, "Папа", "Папа");

            while (reader.hasNext()) {
                System.out.println(reader.next());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TextBinarySearchTree binTree = new TextBinarySearchTree();

        try (ExternalDataIterator iterator = new RawUrlDataIterator(url3)) {

			List<String> words;
			while (iterator.hasNext()) {
				String line = iterator.next();
				words = TextParser.getWords(line);
				binTree.insertAll(words);
			}

			ArrayList<SearchResultData> hitsList = binTree.findWith(token);

			SearchResultPrinter.displayFormatted(hitsList);

        } catch (HTTPReadingSessionFailException ex) {
            MainLogger.logSevere(ex);
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