package doctype;

public class TXTType extends AbstractFileType{
    /**
     * Time for print, seconds
     */
    public static final short PRINT_TIME = 4;

    /**
     * File extension type
     */
    public static final String TYPE = "txt";

    /**
     * A3 format
     */
    public static final int LETTER_HEIGHT = 420;
    public static final int LETTER_WIDTH = 297;

    public TXTType(String name) {
        super(name, PRINT_TIME, LETTER_HEIGHT, LETTER_WIDTH, TYPE);
    }
}
