package model.data.parsers;

public final class FileNameParser {

    public String getName(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1) {

            return fileName;
        }

        String noExtensionName = fileName.substring(0, lastIndexOfDot);

        return noExtensionName;
    }

    public String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1) {

            return "";
        }

        String extension = fileName.substring(lastIndexOfDot);

        return extension;
    }
}
