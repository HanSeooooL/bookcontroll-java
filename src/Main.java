// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
public class Main {

    public static void main(String[] args) {

        Book newone = new Book("어린왕자", "생텍쥐페리", "갈리마르");
        Book.getV Bookvalue = newone.new getV();
        programinside utilitys = new programinside();
        programinside.getDays getday = utilitys.new getDays();
        newone.rentthebook("한설", "20230606", "20230711");
        System.out.println(getday.checkHowyouDidntReturn(Bookvalue.getReturnday()));


        /*Book a = new Book("어린왕자", "생텍쥐페리", "갈리마르");
        a.print_book();
        a.print_book();
        a.returnthebook();
        a.print_book();
        a.filesave();*/

    }
}

class Book{
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

    public class getV {
        String getBookname() {
            return bookname;
        }

        String getWriter() {
            return writer;
        }

        String getCompany() {
            return company;
        }

        Boolean getdiditRent() {
            return rent;
        }

        String getRentname() {
            if(rent) {
                return rentname;
            }
            else {
                return "_";
            }
        }

        String getRentday() {
            if(rent) {
                return rentday;
            }
            else {
                return "________";
            }
        }

        String getReturnday() {
            if(rent) {
                return willreturnday;
            }
            else return "________";
        }
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

class utilityss {
    public void filesave(Book a, String direction) {
        try {
            OutputStream Output = new FileOutputStream(direction);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}