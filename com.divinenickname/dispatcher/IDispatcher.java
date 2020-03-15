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

    /**
     * Default sorted collection
     */
    public void getPrintedList();

    /**
     *
     * @param arg see in implementation
     */
    public void getPrintedList(String arg);

    /**
     *
     * @return average printing time in seconds
     */
    public int getAverageTime();
}
