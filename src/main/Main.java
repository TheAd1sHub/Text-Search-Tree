package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

import controller.userinput.CommandRequester;
import debug.exceptions.ReadingSessionFailException;
import model.readers.filework.FileDataStreamReader;
import model.readers.iterators.ExternalTextDataIterator;
import model.MessageTexts;
import model.SearchResultData;
import model.TextBinarySearchTree;
import debug.logs.MainLogger;
import model.input.interpreters.IntegerInputInterpreter;
import model.input.interpreters.NumericInputInterpreter;
import model.readers.iterators.ExternalDataIterator;
import model.readers.http.RawUrlDataIterator;
import model.readers.http.BorderedHttpResponseStreamReader;
import model.net.UrlStreamReceiver;
import model.readers.http.HttpResponseStreamReader;
import model.text.TextParser;
import model.validators.FileNameValidator;
import model.validators.Validator;
import view.MessagePrinter;
import view.SearchResultPrinter;


@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void init() {

        MessagePrinter invalidInputNotificator = new MessagePrinter(
                MessageTexts.invalidInputNotificationText,
                false
        );

        int userSelection;
        try {
            CommandRequester textSourceTypeRequester = new CommandRequester(MessageTexts.initialMenuText);
            String rawUserInput = textSourceTypeRequester.requestCommand();

            NumericInputInterpreter<Integer> interpreter = new IntegerInputInterpreter();
            userSelection = interpreter.interpret(rawUserInput);

            Validator validator = null;
            Iterator<String> reader = null;

            CommandRequester sourceDataRequester = null;

            // TODO: Change this switch() to a State Machine; Extract in a separate method
            switch (userSelection) {
                case 1: // Local file
                    validator = new FileNameValidator();
                    reader = new FileDataStreamReader("");

                    sourceDataRequester = new CommandRequester(MessageTexts.localFilePathRequestText);
                    break;

                case 2: // Web File

                    break;

                case 3: // Web page contents

                    break;

                case 4: // Web page contents (RAW)
                    InputStream readStream = null;
                    reader = new HttpResponseStreamReader(readStream);

                    break;

                default:
                    throw new InputMismatchException();
            }

            String source = sourceDataRequester.requestCommand();

            if (!validator.isValid(source)) {
                throw new InputMismatchException();
            }



            TextBinarySearchTree binTree = new TextBinarySearchTree();
            while (reader.hasNext()) {
                List<String> words = TextParser.getWords(reader.next());
                binTree.insertAll(words);
            }

        } catch(InputMismatchException ex) {
            invalidInputNotificator.print();

            System.exit(1);
        } catch (IOException e) {

            System.exit(-1);
        }


        System.exit(0);
    }

    public static void main(String[] args) {
        MainLogger.logMessage("Application launched.");

        String url = "https://pastebin.com/raw/r5KfUNn0"; // "Папа у Васи..."
        String url2 = "https://pastebin.com/raw/s5bFXyaM"; // О добром медведе
        String url2s = "https://pastebin.com/raw/Liy8Gqw6"; // Первое предложение из текста О добром медведе
        String url3 = "https://pastebin.com/raw/X5r6xFs5"; // Числительные с "Первое" по "Десятое"
        String token = "Восьмое";

        try {
            UrlStreamReceiver receiver = new UrlStreamReceiver(new URL(url));
            InputStream stream = receiver.openStream();
            BorderedHttpResponseStreamReader reader = new BorderedHttpResponseStreamReader(stream, "Папа", "класс");

            while (reader.hasNext()) {
                System.out.println(reader.next());
                //reader.next();
            }

            stream.close();
            receiver.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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

        } catch (ReadingSessionFailException ex) {
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