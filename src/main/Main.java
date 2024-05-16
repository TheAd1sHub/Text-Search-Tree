package main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import controller.userinput.CommandRequester;
import debug.exceptions.ReadingSessionFailException;
import model.ExitStatuses;
import model.MessageTexts;
import model.SearchResultData;
import model.TextBinarySearchTree;
import debug.logs.MainLogger;
import model.input.interpreters.IntegerInputInterpreter;
import model.input.interpreters.NumericInputInterpreter;
import model.readers.ExternalDataReader;
import model.readers.ExternalTextDataReader;
import model.readers.filework.FileDataReader;
import model.readers.http.RawUrlDataReader;
import model.readers.http.UrlDataReader;
import model.readers.iterators.BorderedHttpResponseStreamIterator;
import model.net.UrlStreamReceiver;
import model.text.TextParser;
import model.validators.FileNameValidator;
import model.validators.UrlValidator;
import model.validators.Validator;
import view.MessagePrinter;
import view.SearchResultPrinter;

import javax.naming.OperationNotSupportedException;


@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static void init() {
        MainLogger.logMessage("Application launched.");

        ExitStatuses exitStatus = ExitStatuses.SUCCESS;

        MessagePrinter invalidInputNotificator = new MessagePrinter(
                MessageTexts.invalidInputNotificationText,
                false
        );

        int userSelection;
        try {
            // Logical block 1: Start menu; Reading option selection
            CommandRequester textSourceTypeRequester = new CommandRequester(MessageTexts.initialMenuText);
            String rawUserInput = textSourceTypeRequester.requestCommand();

            IntegerInputInterpreter interpreter = new IntegerInputInterpreter();
            userSelection = interpreter.interpret(rawUserInput);

            // Logical block 2: Selected option interpretation; Toolset preparation
            Validator validator = null;
            ExternalTextDataReader reader = null;

            String dataSourceRequestMessage = null;
            CommandRequester dataSourceRequester = null;

            // TODO: Change this switch() to a State Machine (?); Divide into separate methods
            switch (userSelection) {
                case 1: // Local file
                    validator = new FileNameValidator();

                    dataSourceRequestMessage = MessageTexts.localFilePathRequestText;
                    break;

                case 2: // Web File
                    throw new OperationNotSupportedException();

                    //break;

                case 3: // Web page contents
                    validator = new UrlValidator();
                    dataSourceRequestMessage = MessageTexts.webPageUrlAddressRequestText;

                    break;

                case 4: // Web page contents (RAW)
                    validator = new UrlValidator();
                    dataSourceRequestMessage = MessageTexts.webPageUrlAddressRequestText;

                    break;

                default:
                    throw new InputMismatchException();
            }

            // Logical block 3: Getting additional option-specific information; Validating input
            dataSourceRequester = new CommandRequester(dataSourceRequestMessage);
            String source = dataSourceRequester.requestCommand();

            if (!validator.isValid(source)) {
                throw new InputMismatchException();
            }

            CommandRequester tokenRequester = new CommandRequester(MessageTexts.soughtForTokenRequestText);
            String soughtForToken = tokenRequester.requestCommand();

            // Logical block 4: Initializing the exact readers
            switch (userSelection) {
                case 1:
                    reader = new FileDataReader(source);
                    break;

                case 2:
                    throw new UnsupportedOperationException();

                case 3:
                    reader = new UrlDataReader(source);
                    throw new OperationNotSupportedException();

                case 4:
                    reader = new RawUrlDataReader(source);
                    break;

                default:
                    throw new InputMismatchException();

            }


            // Logical block 5: Reading the given data and filling the tree
            TextBinarySearchTree binTree = new TextBinarySearchTree();
            while (reader.hasNext()) {
                List<String> words = TextParser.getWords(reader.next());
                binTree.insertAll(words);
            }
            reader.close();

            // Logical block 6: Running the search
            ArrayList<SearchResultData> hitsList = binTree.findWith(soughtForToken);

            // Logical block 6: Printing the search results
            SearchResultPrinter.displayFormatted(hitsList);

        } catch (InputMismatchException ex) {
            invalidInputNotificator.print();

            exitStatus = ExitStatuses.INVALID_INPUT;
        } catch (IOException e) {

            exitStatus = ExitStatuses.DATA_READING_FAILURE;
        } catch (OperationNotSupportedException e) {

            exitStatus = ExitStatuses.NOT_IMPLEMENTED;
        } catch (Exception e) {

            exitStatus = ExitStatuses.UNKNOWN_ERROR;
        }

        MainLogger.logMessage("Application work finished.");
        System.exit(exitStatus.code);

    }

    public static void main(String[] args) {
        init();

        String url = "https://pastebin.com/raw/r5KfUNn0"; // "Папа у Васи..."
        String url2 = "https://pastebin.com/raw/s5bFXyaM"; // О добром медведе
        String url2s = "https://pastebin.com/raw/Liy8Gqw6"; // Первое предложение из текста О добром медведе
        String url3 = "https://pastebin.com/raw/X5r6xFs5"; // Числительные с "Первое" по "Десятое"
        String token = "Восьмое";

        try {
            UrlStreamReceiver receiver = new UrlStreamReceiver(new URL(url));
            InputStream stream = receiver.openStream();
            BorderedHttpResponseStreamIterator reader = new BorderedHttpResponseStreamIterator(stream, "Папа", "класс");

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

        try (ExternalTextDataReader iterator = new RawUrlDataReader(url3)) {

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