// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {



        GUI.program();




        //titleUI title = new titleUI();
        /*FileInOut file = new FileInOut();

        Books = file.fileRead.pageRead(1, 1);

        for (int i = 0; i < Books.size(); i++) {
            Books.get(0).print_book();
        }*/




        /*HashMap<String, Book> map = new HashMap<String, Book>();

        Book newone = new Book("어린왕자", "생텍쥐페리", "갈리마르");
        Book newtwo = new Book("화성학", "백병동", "HARMONY");
        Book newthree = new Book("어린왕자", "먀", "no");
        programinside utilitys = new programinside();
        programinside.getDays getday = utilitys.new getDays();
        map.put(newone.getBookname(), newone);
        map.put(newtwo.getBookname(), newtwo);
        map.put(newthree.getBookname(), newthree);

        Set<String> keys = map.keySet();
        Collection<Book> values = map.values();

        for(Book value : values) {
            System.out.println(value.getWriter());
        }

        for(String key : keys) {
            System.out.println(key);
        }*/

        /*
        newone.rentthebook("한설", "20230606", "20230711");
        System.out.println(getday.checkHowyouDidntReturn(Bookvalue.getReturnday()));
        */

        /*Book a = new Book("어린왕자", "생텍쥐페리", "갈리마르");
        a.print_book();
        a.print_book();
        a.returnthebook();
        a.print_book();
        a.filesave();*/

    }
}

class Book{
    private String id;
    private String bookname;
    private String writer;
    private String company;
    private Boolean rent;
    private String rentname;
    private String rentday;
    private String willreturnday;

    Book(String booknamee, String writerr, String companyy) {
        this.bookname = booknamee;
        this.writer = writerr;
        this.company = companyy;
        this.rent = false;
        this.rentname = "_";
        this.rentday = "________";
        this.willreturnday = "_________";

    }

    Book() {
        this.bookname = "bookname";
        this.writer = "writer";
        this.company = "company";
        this.rent = false;
        this.rentname = "_";
        this.rentday = "________";
        this.willreturnday = "_________";
    }

    public void rentthebook(String rentname, String rentday, String returnday) {
        this.rent = true;
        this.rentname = rentname;
        this.rentday = rentday;
        this.willreturnday = returnday;
    }

    public void returnthebook() {
        this.rent = false;
        this.rentname = "_";
        this.rentday = "________";
        this.willreturnday = "________";
    }

    void print_book() {
        System.out.println(this.bookname);
        System.out.println(this.writer);
        System.out.println(this.company);
        if(this.rent) {
            System.out.println(this.rentname);
            System.out.println(this.rentday);
            System.out.println(this.willreturnday);
        }

    }
    String getBookname() {
        return this.bookname;
    }

    String getWriter() {
        return this.writer;
    }

    String getCompany() {
        return this.company;
    }

    Boolean getdiditRent() {
        return this.rent;
    }

    String getRentname() {
        if(this.rent) {
            return this.rentname;
        }
        else {
            return "_";
        }
    }

    String getRentday() {
        if(this.rent) {
            return this.rentday;
        }
        else {
            return "________";
        }
    }

    String getReturnday() {
        if(this.rent) {
            return this.willreturnday;
        }
        else return "________";
    }

    void setBookinfo(String bookname, String writer, String company) {
        this.bookname = bookname;
        this.writer = writer;
        this.company = company;
    }


    void filesave() {
        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/HelloWorld/data.txt");
            byte[] by = this.bookname.getBytes();
            byte[] sharp = "#".getBytes();
            dir.write(by);
            dir.write(sharp);
            by = this.writer.getBytes();
            dir.write(by);
            dir.write(sharp);
            by = this.company.getBytes();
            dir.write(by);
            dir.write(sharp);
            by = new byte[]{(byte) (this.rent ? 1 : 0)};
            dir.write(by);
            dir.write(sharp);
            by = this.rentname.getBytes();
            dir.write(by);
            dir.write(sharp);
            by = this.rentday.getBytes();
            dir.write(by);
            dir.write(sharp);
            by = this.willreturnday.getBytes();
            dir.write(by);
            sharp = "\n".getBytes();
            dir.write(sharp);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}