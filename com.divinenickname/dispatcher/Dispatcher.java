package dispatcher;

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

    Thread t;

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
    public List<AbstractFileType> getPrintedList() {
        return printedList;
    }

    @Override
    public int getAverageTime() {
        if(totalPrintTime==0 || totalPrintedDocs==0) return 0;
        return totalPrintTime/totalPrintedDocs;
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
}
