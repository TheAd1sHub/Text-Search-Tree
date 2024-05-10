package model.filework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import model.SearchResultData;
import model.TextBinarySearchTree;

public final class FileContentsReader {
	private FileContentsReader() {
		;
	}
    
    public static String read(String fileName) throws FileNotFoundException, IOException {
    	
    	//ClassLoader classLoader = "".getClass().getClassLoader();
    	//InputStream stream = classLoader.getResourceAsStream(fileName);
    	//InputStreamReader streamReader = new InputStreamReader(stream);
    	//
    	//StringBuilder sb = new StringBuilder();
    	//
    	//try (BufferedReader br = new BufferedReader(streamReader)) {
    	//	String line;
		//	while ((line = br.readLine()) != null) {
    	//		sb.append(line).append('\n');
    	//	}
    	//}
    	
    	//String result = sb.toString();
		String result = Files.readString(Path.of(fileName));

    	return result;
    }
    
    public static String[] readLines(String fileName) throws FileNotFoundException, IOException {
    	String fileContents = read(fileName);
    	
    	String[] result = fileContents.split("\n");
    	
    	return result;
    }
}
