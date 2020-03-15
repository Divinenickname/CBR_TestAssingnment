package dispatcher.comparator;

import doctype.AbstractFileType;

import java.util.Comparator;

public class NameComparator implements Comparator<AbstractFileType> {
    @Override
    public int compare(AbstractFileType o1, AbstractFileType o2) {
        return o1.getName().compareTo(o2.getType());
    }
}
