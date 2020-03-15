package dispatcher.comparator;

import doctype.AbstractFileType;

import java.util.Comparator;

/**
 * Compare files by their type.
 */
public class TypeComparator implements Comparator<AbstractFileType> {

    @Override
    public int compare(AbstractFileType o1, AbstractFileType o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
