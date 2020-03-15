package dispatcher;

import doctype.AbstractFileType;

import java.util.List;

public interface IDispatcher {
    /**
     * Stop printing
     * @return list of unprinted documents
     */
    public void stopPrint();

    /**
     * Add file to print queue
     */
    public void add(AbstractFileType file);

    /**
     * Discard print last added document. Only if it wasn't printed.
     */
    public void cancelLastDocPrint();

    public List<AbstractFileType> getPrintedList();

    public int getAverageTime();
}
