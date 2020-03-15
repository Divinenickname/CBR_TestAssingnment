package dispatcher.comparator;

import doctype.AbstractFileType;

import java.util.Comparator;

/**
 * Compare files by their paper size.
 */
public class PaperSizeComparator implements Comparator<AbstractFileType> {
    @Override
    public int compare(AbstractFileType o1, AbstractFileType o2) {
        int size1 = o1.getLetterHeight() * o1.getLetterWidth();
        int size2 = o2.getLetterHeight() * o2.getLetterWidth();

        return Integer.compare(size1, size2);
    }
}
