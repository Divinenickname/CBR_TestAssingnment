package doctype;

public class JPEGType extends AbstractFileType {
    /**
     * Time for print, seconds
     */
    public static final short PRINT_TIME = 40;

    /**
     * File extension type
     */
    public static final String TYPE = "jpeg";

    /**
     * A6 format
     */
    public static final int LETTER_HEIGHT = 148;
    public static final int LETTER_WIDTH = 105;

    public JPEGType(String name) {
        super(name, PRINT_TIME, LETTER_HEIGHT, LETTER_WIDTH, TYPE);
    }
}
