import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class FileInOut {
    static FileInOut File = new FileInOut();
    fileSave fileSave = new fileSave();
    fileRead fileRead = new fileRead();

}

class fileRead {
    int pageloded;
    ArrayList<Book> pageRead(int page, int howmany) throws IOException {
        ArrayList<Book> Books = new ArrayList<Book>();
        pageloded = 0;
        Book newone;
        int i = 0;
        String fileName = "/Users/hanseol/HelloWorld/data.txt";

        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine() && i < howmany) {
                newone = new Book();
                String line = sc.nextLine();
                if ((page - 1) * howmany > 0) {
                    page-= 1;
                    continue;
                }

                StringTokenizer token = new StringTokenizer(line, "#");
                newone.setBookinfo(token.nextToken(), token.nextToken(), token.nextToken());

                if(token.nextToken() == "1") {
                    newone.rentthebook(token.nextToken(), token.nextToken(), token.nextToken());
                }
                else newone.returnthebook();

                Books.add(newone);

                i++; pageloded++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        File file = new File("/Users/hanseol/HelloWorld/data.txt");
        BufferedReader br = new BufferedReader(new FileReader("/Users/hanseol/HelloWorld/data.txt"));

        String str;
        while((str = br.readLine()) != null && i < howmany) {

            if ((page - 1) * howmany > 0) {
                page-= 1;
                continue;
            }

            StringTokenizer token = new StringTokenizer(str, "#");
            newone.setBookinfo(token.nextToken(), token.nextToken(), token.nextToken());

            if(token.nextToken() == "1") {
                newone.rentthebook(token.nextToken(), token.nextToken(), token.nextToken());
            }
            else newone.returnthebook();

            Books.add(newone);

            i++; pageloded++;
        }
        br.close();(*/
        return (Books);
    }
}

class fileSave {
    void addbook(Book newone) {
        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/HelloWorld/data.txt", true);
            byte[] by = newone.getBookname().getBytes();
            byte[] sharp = "#".getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getWriter().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getCompany().getBytes();
            dir.write(by);
            dir.write(sharp);
            if(newone.getdiditRent() == false)
                by = "0".getBytes();
            else by = "1".getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getRentname().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getRentday().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getReturnday().getBytes();
            dir.write(by);
            sharp = "\n".getBytes();
            dir.write(sharp);

        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}