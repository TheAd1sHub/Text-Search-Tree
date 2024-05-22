package model.constants;

public final class SavePaths {

    private SavePaths() {
        ;
    }

    public static final String temp = System.getProperty("java.io.tmpdir");

    public static final String tempPageContents = temp + "\\BTS-source.txt";
}
