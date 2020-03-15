package doctype;

public class DOCXType extends AbstractFileType {
    /**
     * Time for print, seconds
     */
    public static final short PRINT_TIME = 15;

    /**
     * File extension type
     */
    public static final String TYPE = "docx";

    /**
     * A5 format
     */
    public static final int LETTER_HEIGHT = 210;
    public static final int LETTER_WIDTH = 148;

    public DOCXType(String name) {
        super(name, PRINT_TIME, LETTER_HEIGHT, LETTER_WIDTH, TYPE);
    }
}
