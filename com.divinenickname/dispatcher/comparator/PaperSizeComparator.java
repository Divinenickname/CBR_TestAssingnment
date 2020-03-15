package dispatcher.comparator;

import doctype.AbstractFileType;

import java.util.Comparator;

/**
 * Compare files by their paper size.
 */
public class PaperSizeComparator implements Comparator<AbstractFileType> {
    @Override
    public int compare(AbstractFileType o1, AbstractFileType o2) {
        int size1 = o1.getPaperHeight() * o1.getPaperWidth();
        int size2 = o2.getPaperHeight() * o2.getPaperWidth();

        return Integer.compare(size1, size2);
    }
}
