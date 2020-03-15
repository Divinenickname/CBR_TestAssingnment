package dispatcher;

import doctype.AbstractFileType;

import java.util.*;

public class Dispatcher implements IDispatcher, Runnable{
    /**
     * How many docs have been printed
     */
    private int printCount;

    private int totalPrintingTime;

    private boolean isAlive = false;

    /**
     * Queue for storing printing queue
     */
    private  Deque<AbstractFileType> printQueue = new ArrayDeque<>();

    /**
     * List of printed files
     */
    private List<AbstractFileType> printedList = new ArrayList<AbstractFileType>();


    @Override
    public List<AbstractFileType> stopPrint() {
        Thread.currentThread().interrupt();
        return new ArrayList<AbstractFileType>(printQueue);
    }

    @Override
    public void add(AbstractFileType file) {
        printQueue.addLast(file);
        System.out.println("Document added");

        if(!isAlive){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isAlive = true;
                    while (printQueue.size()!=0){
                        try {
                            print();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    isAlive = false;
                }
            }).start();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancelLastDocPrint() {
        printQueue.pollLast();
        System.out.println("Document removed");
    }

    @Override
    public List<AbstractFileType> getPrintedList() {
        return printedList;
    }

    /**
     * Wait new doc for adding to {@link #printQueue}
     */
    @Override
    public void run() {
        System.out.println("servStart");

        while (Thread.currentThread().isInterrupted()){
            try {
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void print() throws InterruptedException {
        AbstractFileType doc = printQueue.poll();
        if(doc!=null){
            System.out.println("Printing..." + doc.getName());
            for (int i = 0; i < doc.getPrintTime(); i++) {
                // TODO вернуть 1000мс
                Thread.sleep(150);
                System.out.println(doc.getPrintTime()-i);
            }
            printCount++;
            printedList.add(doc);
            totalPrintingTime += doc.getPrintTime();
        }
        else {
            System.out.println("Printing is finished");
        }

    }
}
