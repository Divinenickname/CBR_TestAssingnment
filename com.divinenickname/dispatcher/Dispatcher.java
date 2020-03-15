package dispatcher;

import dispatcher.comparator.NameComparator;
import dispatcher.comparator.PaperSizeComparator;
import dispatcher.comparator.PrintTimeComparator;
import dispatcher.comparator.TypeComparator;
import doctype.AbstractFileType;

import java.util.*;

public class Dispatcher implements IDispatcher{
    /**
     * How many docs have been printed
     */
    private int totalPrintedDocs;

    private int totalPrintTime;

    private boolean isAlive = false;
    private boolean stopped = false;
    private AbstractFileType currentPrintDoc;

    private Thread t;

    /**
     * Queue for storing printing queue
     */
    private  Deque<AbstractFileType> printQueue = new ArrayDeque<>();

    /**
     * List of printed files
     */
    private List<AbstractFileType> printedList = new ArrayList<AbstractFileType>();


    @Override
    public void stopPrint() {
        t.interrupt();
        stopped = true;
        System.out.println("Printing cancelled \nUnprinted files: ");
        System.out.println(currentPrintDoc.getName() + "." + currentPrintDoc.getType());
        for (AbstractFileType abstractFileType : printQueue) {
            System.out.println(abstractFileType.getName() + "." + abstractFileType.getType());
        }
        System.out.println();
    }

    @Override
    public void add(AbstractFileType file) {
        printQueue.addLast(file);
        System.out.println("Document " + file.getFullName() + " added\n");

        if(!isAlive & !stopped){
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    isAlive = true;
                    while (printQueue.size()!=0 && !stopped){
                        print();
                    }
                    isAlive = false;
                }
            });
            t.start();

            //Small delay for fix unexpected NPE
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancelLastDocPrint() {
        printQueue.pollLast();
        System.out.println("Document removed\n");
    }

    @Override
    public void getPrintedList() {
        for (AbstractFileType abstractFileType : printedList) {
            System.out.println(abstractFileType.toString());
        }
    }

    @Override
    public int getAverageTime() {
        if(totalPrintTime==0 || totalPrintedDocs==0) return 0;
        return totalPrintTime/totalPrintedDocs;
    }

    @Override
    public void getPrintedList(String arg) {
        if(arg.equals(Filters.time.name())){
            Collections.sort(printedList, new PrintTimeComparator());
        }
        else if(arg.equals(Filters.size.name())){
            Collections.sort(printedList, new PaperSizeComparator());
        }
        else if(arg.equals(Filters.type.name())){
            Collections.sort(printedList, new TypeComparator());
        }
        else if(arg.equals(Filters.name.name())){
            Collections.sort(printedList, new NameComparator());
        }

        for (AbstractFileType abstractFileType : printedList) {
            System.out.println(abstractFileType.toString());
        }
    }

    private void print() {
        currentPrintDoc = printQueue.poll();
        if(currentPrintDoc!=null){
            System.out.println("Printing... " + currentPrintDoc.getFullName() +"\n");
            for (int i = 0; i < currentPrintDoc.getPrintTime(); i++) {
                if(stopped || t.isInterrupted()){
                    return;
                }
                else {
                    try {
                        Thread.sleep(1000);
                        System.out.println(currentPrintDoc.getPrintTime()-i);
                    } catch (InterruptedException e) {
                        return;
                        // Теоретически делать так нельзя, это исключение бросается потому что мы прервали поток.
                        //e.printStackTrace();
                    }

                }

            }
            totalPrintedDocs++;
            printedList.add(currentPrintDoc);
            totalPrintTime += currentPrintDoc.getPrintTime();
        }
        else {
            System.out.println("Printing is finished\n");
        }

    }

    /**
     * Filter list
     */
    private enum Filters {
        type,
        name,
        size,
        time
    }
}
