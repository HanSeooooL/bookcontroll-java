import java.io.*;
import java.util.*;

public class FileInOut {
    static FileInOut File = new FileInOut();
    fileSave fileSave = new fileSave();
    fileRead fileRead = new fileRead();
    Scanner sc;

    static int countup = 0;
    int res = -1;

    int checkwhereisrentdata(String searchcode) {
        if(this.sc == null) {

            try {
                sc = new Scanner(new File("/Users/hanseol/rent.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String line;
        line = sc.nextLine();
        if(sc.hasNextLine()) {
            countup++;
            if((res = checkwhereisrentdata(searchcode)) != -1)
                return res;
        }
        if(searchcode.equals(line.substring(0, searchcode.length()))) {
            sc = null;
            return countup;
        }
        else {
            countup--;
            return -1;
        }
    }

    int checkwhereisbookdata(String searchcode) {
        if(this.sc == null) {

            try {
                sc = new Scanner(new File("/Users/hanseol/data.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String line;
        if (sc.hasNextLine()){
            line = sc.nextLine();
            if (sc.hasNextLine()) {
                countup++;
                if ((res = checkwhereisbookdata(searchcode)) != -1)
                    return res;
            }
            if (searchcode.equals(line.substring(2, searchcode.length() + 2))) {
                return countup;
            } else return -1;
        }
        else return -1;
    }
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
        StringTokenizer token;
        String line;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            token = new StringTokenizer(line, "#");
            res.add(new Book(Integer.parseInt(token.nextToken()), token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()));
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

    rentdata checkthebookrent(String a) throws IOException{
        if(this.sc == null) {
            sc = new Scanner(new File(rentfileName));
        }
        String line, bookID;
        rentdata res;
        if(sc.hasNextLine()) {
            line = sc.nextLine();
            if (sc.hasNextLine()) {
                if ((res = checkthebookrent(a)) != null) {
                    return res;
                }
            }
            StringTokenizer token = new StringTokenizer(line, "#");
            bookID = token.nextToken();
            if (Objects.equals(a, bookID)) {
                res = new rentdata(bookID, token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken());
                this.sc = null;
                return res;
            } else {
                this.sc = null;
                return null;
            }
        }
        else {
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
            by = Integer.toString(newone.getrent()).getBytes();
            dir.write(by);
            dir.write(sharp);
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
                if(Books.get(i).getID() == null) {
                    continue;
                }
                by = Integer.toString(Books.get(i).getrent()).getBytes();
                dir.write(by);
                dir.write(sharp);
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
        byte[] by;
        byte[] sharp = "#".getBytes();
        by = rent.getbookID().getBytes();
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
    }

    void savereturnbook(Book a, String day) {
        int count;
        Scanner sc;
        count = FileInOut.File.checkwhereisrentdata(a.getID());

        if(count == -1) {
            System.out.println("찾지 못했습니다.");
        }
        else {
            ArrayList<rentdata> datas = new ArrayList<rentdata>();
            ArrayList<rentdata> beforedatas = new ArrayList<rentdata>();
            String line;
            try {
                sc = new Scanner(new File("/Users/hanseol/rent.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0; i < count; i++) {
                line = sc.nextLine();
                StringTokenizer token = new StringTokenizer(line, "#");
                beforedatas.add(new rentdata(token.nextToken(), token.nextToken(), token.nextToken(),token.nextToken(), token.nextToken()));
            }
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                StringTokenizer token = new StringTokenizer(line, "#");
                datas.add(new rentdata(token.nextToken(), token.nextToken(), token.nextToken(),token.nextToken(), token.nextToken()));
            }
            datas.get(0).setReturnday(day);
            try {
                OutputStream dir = new FileOutputStream("/Users/hanseol/rent.txt");
                byte[] by;
                byte[] sharp = "#".getBytes();
                for(int i = 0; i < beforedatas.size(); i++) {
                    by = beforedatas.get(i).getbookID().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = beforedatas.get(i).getRentPerson().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = beforedatas.get(i).getRentDay().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = beforedatas.get(i).getwillReturnday().getBytes();
                    dir.write(by);
                    dir.write("\n".getBytes());
                }
                for(int i = 0; i < datas.size(); i++) {
                    by = datas.get(i).getbookID().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = datas.get(i).getRentPerson().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = datas.get(i).getRentDay().getBytes();
                    dir.write(by);
                    dir.write(sharp);
                    by = datas.get(i).getwillReturnday().getBytes();
                    dir.write(by);
                    dir.write("\n".getBytes());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}