import java.io.*;
import java.util.*;

public class FileInOut {
    static FileInOut File = new FileInOut();
    fileSave fileSave = new fileSave();
    fileRead fileRead = new fileRead();
    Scanner sc = null;

}

class fileRead {
    static int loaded;
    static int rentloaded;
    static int historyloaded;
    private final String datafileName = "/Users/hanseol/data.txt";
    private final String historyfileName = "/Users/hanseol/history.txt";
    private final String rentfileName = "/Users/hanseol/rent.txt";
    private Scanner sc = null;


    ArrayList<Book> AllbookRead() throws FileNotFoundException {
        ArrayList<Book> res = new ArrayList<Book>();
        loaded = 0;


        sc = new Scanner(new File(datafileName));
        String line, rentid;


        while(sc.hasNextLine()) {
            line = sc.nextLine();

            loaded++;

        }
        this.sc = null;

        return res;
    }

    ArrayList<rentdata> AllrentdataRead() throws FileNotFoundException {
        ArrayList<rentdata> res = new ArrayList<rentdata>();
        rentloaded = 0;


        sc = new Scanner(new File(rentfileName));
        String line, rentid;

        this.sc = null;

        return res;
    }

    ArrayList<historydata> AllhistorydataRead() throws FileNotFoundException {
        ArrayList<historydata> res = new ArrayList<historydata>();
        historyloaded = 0;


        sc = new Scanner(new File(historyfileName));
        String line, rentid;
        this.sc = null;

        return res;
    }


    rentdata checkthebookrent(String a) throws IOException{
        if(this.sc == null) {
            sc = new Scanner(new File(rentfileName));
        }
        String line;
        rentdata res;
        line = sc.nextLine();
        if(sc.hasNextLine()) {
            if((res = checkthebookrent(a)) != null) {
                return res;
            }
        }
        StringTokenizer token = new StringTokenizer(line, "#");
        if(Objects.equals(a.toString(), token.nextToken())) {
            res = new rentdata(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken());
            this.sc = null;
            return res;
        }
        else {
            this.sc = null;
            return null;
        }


    }
}

class fileSave {
    void addbook(Book newone) {
        byte[] by;
        byte[] sharp = "#".getBytes();

        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/data.txt", true);
            by = newone.getID().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getBookname().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getWriter().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getCompany().getBytes();
            dir.write(by);
            dir.write("\n".getBytes());
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    void saveAllbooks(ArrayList<Book> Books) {
        byte[] by;
        byte[] sharp = "#".getBytes();
        OutputStream dir = null;
        try {
            dir = new FileOutputStream("/Users/hanseol/data.txt");
        for(int i = 0; i < Books.size(); i++) {
                by = Books.get(i).getID().getBytes();
                dir.write(by);
                dir.write(sharp);
                by = Books.get(i).getBookname().getBytes();
                dir.write(by);
                dir.write(sharp);
                by = Books.get(i).getWriter().getBytes();
                dir.write(by);
                dir.write(sharp);
                by = Books.get(i).getCompany().getBytes();
                dir.write(by);
                dir.write("\n".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void addrent(rentdata rent) throws IOException{
        OutputStream dir = new FileOutputStream("/Users/hanseol/rent.txt", true);

    }

    void saveallrent(ArrayList<rentdata> rents) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/rent.txt");
    }

    void saveallhistory(ArrayList<historydata> historys) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/history.txt");
        }

    void addhistory(historydata history) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/history.txt", true);
    }
}