package doctype;

public class PDFType extends AbstractFileType {
    /**
     * Time for print, seconds
     */
    public static final short PRINT_TIME = 12;

    /**
     * File extension type
     */
    public static final String TYPE = "pdf";

    /**
     * A4 format
     */
    public static final int LETTER_HEIGHT = 297;
    public static final int LETTER_WIDTH = 210;

    public PDFType(String name) {
        super(name, PRINT_TIME, LETTER_HEIGHT, LETTER_WIDTH, TYPE);
    }
}
