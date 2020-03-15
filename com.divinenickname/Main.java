import dispatcher.Dispatcher;
import doctype.DOCXType;
import doctype.PDFType;
import doctype.TXTType;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DOCXType doc = new DOCXType("test1");
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.add(doc);
        //System.out.println(dispatcher.stopPrint().toString());
        dispatcher.add(new TXTType("TXT"));
        dispatcher.cancelLastDocPrint();
        //dispatcher.stopPrint();
        dispatcher.add(new TXTType("new TXT"));

        try {
            Thread.sleep(21000);
            for (int i = 0; i < dispatcher.getPrintedList().size(); i++) {
                System.out.println(dispatcher.getPrintedList().get(i).toString());
            }
            dispatcher.add(new PDFType("MyPDF"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Average print time: " + dispatcher.getAverageTime());

    }
}
