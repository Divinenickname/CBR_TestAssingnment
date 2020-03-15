package doctype;

public abstract class AbstractFileType {
    private String data;
    private String name;
    private int printTime;
    private int letterHeight;
    private int letterWidth;
    private String type;

    public AbstractFileType(String name, int printTime, int letterHeight, int letterWidth, String type) {
        this.name = name;
        this.printTime = printTime;
        this.letterHeight = letterHeight;
        this.letterWidth = letterWidth;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrintTime() {
        return printTime;
    }

    private String formatter(String line, int length){
        StringBuilder sb = new StringBuilder();
        if(line.length()<length){
            for (int i = 0; i < length - line.length(); i++) {
                sb.append(" ");
            }
            line += sb.toString();
            sb = new StringBuilder();
        }
        return line;
    }

    @Override
    public String toString() {
        String delim = " | ";
        type = formatter(type, 4);
        String time = formatter(String.valueOf(printTime), 3);
        name = formatter(name, 15);
        return type + delim + time + delim + letterHeight + "x" + letterWidth + delim + name;
    }
}
