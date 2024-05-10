package model.filework;

public final class FileNameAnalyzer {
	private FileNameAnalyzer() {
		;
	}
	
	public static String getExtension(String fileName) throws IllegalArgumentException {
		int dotIndex = fileName.lastIndexOf('.');
		
		if (dotIndex == -1) {
			throw new IllegalArgumentException("Cannot get file extension! The given file does not have any.");
		}
		
		String extension = fileName.substring(dotIndex);
		
		return extension;
	}
	
	public static String getName(String fileName) throws IllegalArgumentException {
		int dotIndex = fileName.lastIndexOf('.');
		
		if (dotIndex == -1) {
			
			return fileName;
		}
		
		String name = fileName.substring(0, fileName.lastIndexOf('.'));
		
		return name;
	}
	
}
