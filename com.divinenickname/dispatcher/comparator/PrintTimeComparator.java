package dispatcher.comparator;

import doctype.AbstractFileType;

import java.util.Comparator;

/**
 * Compare files by their print time.
 */
public class PrintTimeComparator implements Comparator<AbstractFileType> {
    @Override
    public int compare(AbstractFileType o1, AbstractFileType o2) {
        return Integer.compare(o1.getPrintTime(), o2.getPrintTime());
    }
}
