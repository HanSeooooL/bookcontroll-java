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

            StringTokenizer token = new StringTokenizer(line, "#");
            rentid = token.nextToken();
            if(rentid.equals("0")) {
                res.add(new Book(token.nextToken(), token.nextToken(), token.nextToken()
                , token.nextToken()));
            }
            else {
                res.add(new Book(token.nextToken(), token.nextToken(), token.nextToken()
                        , token.nextToken()));
            }
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

        while(sc.hasNextLine()) {
            line = sc.nextLine();

            StringTokenizer token = new StringTokenizer(line, "#");
            res.add(new rentdata(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()));
            rentloaded++;

        }
        this.sc = null;

        return res;
    }

    ArrayList<historydata> AllhistorydataRead() throws FileNotFoundException {
        ArrayList<historydata> res = new ArrayList<historydata>();
        historyloaded = 0;


        sc = new Scanner(new File(historyfileName));
        String line, rentid;

        while(sc.hasNextLine()) {
            line = sc.nextLine();

            StringTokenizer token = new StringTokenizer(line, "#");
            res.add(new historydata(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()));
            historyloaded++;

        }
        this.sc = null;

        return res;
    }


    rentdata checkthebookrent(UUID a) throws IOException{
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
        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/data.txt", true);
            byte[] by;
            byte[] sharp = "#".getBytes();
            by = newone.getID().toString().getBytes();
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
            sharp = "\n".getBytes();
            dir.write(sharp);

        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    void saveAllbooks(ArrayList<Book> Books) {
        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/data.txt");
            byte[] by;
            byte[] sharp = "#".getBytes();
            for(int i = 0; i < Books.size(); i++) {
                if(Books.get(i).getID() == null) continue;
                by = Books.get(i).getID().toString().getBytes();
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

        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    void addrent(rentdata rent) throws IOException{
        OutputStream dir = new FileOutputStream("/Users/hanseol/rent.txt", true);
        byte[] by;
        byte[] sharp = "#".getBytes();
        by = rent.getbookID().toString().getBytes();
        dir.write(by);
        dir.write(sharp);
        by = rent.getRentPerson().getBytes();
        dir.write(by);
        dir.write(sharp);
        by = rent.getRentDay().getBytes();
        dir.write(by);
        dir.write(sharp);
        by = rent.getwillReturnday().getBytes();
        dir.write(by);
        dir.write("\n".getBytes());

    }

    void saveallrent(ArrayList<rentdata> rents) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/rent.txt");
        byte[] by;
        byte[] sharp = "#".getBytes();
        for(int i = 0; i < rents.size(); i++) {

            by = rents.get(i).getbookID().toString().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = rents.get(i).getRentPerson().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = rents.get(i).getRentDay().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = rents.get(i).getwillReturnday().getBytes();
            dir.write(by);
            dir.write("\n".getBytes());
        }
    }

    void saveallhistory(ArrayList<historydata> historys) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/history.txt");
        byte[] by;
        byte[] sharp = "#".getBytes();
        for(int i = 0; i < historys.size(); i++) {

            if(historys.get(i).getbookID() == null) continue;
            by = historys.get(i).getbookID().toString().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = historys.get(i).getRentPerson().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = historys.get(i).getRentDay().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = historys.get(i).getReturnday().getBytes();
            dir.write(by);
            dir.write("\n".getBytes());
        }
    }

    void addhistory(historydata history) throws IOException {
        OutputStream dir = new FileOutputStream("/Users/hanseol/history.txt", true);
        byte[] by = history.getbookID().toString().getBytes();
        byte[] sharp = "#".getBytes();
        dir.write(by);
        dir.write(sharp);
        by = history.getRentPerson().getBytes();
        dir.write(by);
        dir.write(sharp);
        by = history.getRentDay().getBytes();
        dir.write(by);
        dir.write(sharp);
        by = history.getReturnday().getBytes();
        dir.write(by);
        dir.write("\n".getBytes());
    }
}