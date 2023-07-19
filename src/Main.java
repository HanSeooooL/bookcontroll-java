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
    private UUID Bookid;
    private UUID rentid;
    private String bookname;
    private String writer;
    private String company;

    Book(UUID rentID, UUID bookID, String booknamee, String writerr, String companyy) {
        this.rentid = rentID;
        this.Bookid = bookID;
        this.bookname = booknamee;
        this.writer = writerr;
        this.company = companyy;

    }

    Book(String booknamee, String writerr, String companyy) {
        Bookid = UUID.randomUUID();
        rentid = null;
        this.bookname = booknamee;
        this.writer = writerr;
        this.company = companyy;

    }

    Book() {
        this.bookname = "bookname";
        this.writer = "writer";
        this.company = "company";
    }

    void print_book() {
        System.out.println(this.bookname);
        System.out.println(this.writer);
        System.out.println(this.company);
        System.out.println(this.rentid);

    }

    UUID getID() {
        return this.Bookid;
    }
    String getRentID() {
        if (this.rentid != null) {
        return this.rentid.toString();
        }
    else return "0";

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

    void setID(UUID a) {
        this.Bookid = a;
    }
    void setRentID(UUID a) {this.rentid = a;}

    void setBookinfo(String bookname, String writer, String company) {
        this.bookname = bookname;
        this.writer = writer;
        this.company = company;
    }
}

class rentdata {
    private UUID bookID;
    private UUID rentID;
    private String rentperson;
    private String rentday;
    private String willreturnday;

    rentdata(UUID rentID, UUID bookID, String rentperson, String rentday, String willreturnday) {
        this.bookID = bookID;
        this.rentID = rentID;
        this.rentperson = rentperson;
        this.rentday = rentday;
        this.willreturnday = willreturnday;
    }

    rentdata(UUID bookID, String rentperson, String rentday, String willreturnday) {
        this.rentID = UUID.randomUUID();
        this.bookID = bookID;
        this.rentperson = rentperson;
        this.rentday = rentday;
        this.willreturnday = willreturnday;
    }

    void print_rentdata() {
        System.out.println(this.bookID + " " + this.rentID + " " + this.rentperson + " " + this.rentday + " " + this.willreturnday);
    }

    UUID getbookID() {return bookID;}
    UUID getrentID() {return rentID;}
    String getRentPerson() {return rentperson;}
    String getRentDay() {return rentday;}
    String getwillReturnday() {return willreturnday;}
    void markrentID() {
        this.rentID = null;
    }

}

class historydata {
    private UUID bookID;
    private String rentperson;
    private String rentday;
    private String returnday;

    historydata(UUID bookId, String rentperson, String rentday, String returnday) {
        this.bookID = bookId;
        this.rentperson = rentperson;
        this.rentday = rentday;
        this.returnday = returnday;
    }
    UUID getbookID() {return bookID;}
    String getRentPerson() {return rentperson;}
    String getRentDay() {return rentday;}
    String getReturnday() {return returnday;}
    void markbookID() {this.bookID = null;}

}