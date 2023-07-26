// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DB.DB_connect();
        //GUI.program();

        /*int i = 0;
        while(i < 30) {
            Book newone = new Book();
            i++;
        }*/

        //addpersonUI addperson = new addpersonUI();

        /*
        String bookgenre, bookname, year, code;
        char one[], res;
        int month;

        code = "";

        System.out.println((int)'A');

        Scanner sc = new Scanner(System.in);
        System.out.print("주류: ");
        bookgenre = sc.next();
        System.out.print("연도: ");
        year = sc.next();
        System.out.print("월: ");
        month = sc.nextInt();
        System.out.print("책 이름: ");
        bookname = sc.next();

        one = bookname.toCharArray();
        System.out.println((int)one[0]);
        res = (char) (one[0] % 16);
        res = (char) (res > 9 ? res + 65 : res + 48);


        code = code.concat(bookgenre);
        code = code.concat(year.substring(2));
        if(month < 10) {
            code = code.concat("0");
            code = code.concat(Integer.toString(month));
        } else {
            code = code.concat(Integer.toString(month));
        }
        code = code.concat(Character.toString(res));
        code = code.concat("01");

        System.out.println(code);

         */









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
    private int rent;
    private String bookID;
    private String bookname;
    private String writer;
    private String company;

    Book(String booknamee, String writerr, String companyy) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        String lastitemcode;
        this.bookID = "";
        this.rent = 0;
        this.bookname = booknamee;
        this.writer = writerr;
        this.company = companyy;
        this.bookID = this.bookID.concat(Integer.toString(now.getYear()).substring(2));
        if(now.getMonthValue() < 10) {
            this.bookID = this.bookID.concat("0");
            this.bookID = this.bookID.concat(Integer.toString(now.getMonthValue()));
        } else {
            this.bookID = this.bookID.concat(Integer.toString(now.getMonthValue()));
        }
        if(titleUI.Books == null || titleUI.Books.size() == 0 ) {
            this.bookID = this.bookID.concat("001");

        }
        else {
        lastitemcode = titleUI.Books.get(titleUI.Books.size() - 1).getID();
        if(lastitemcode.substring(0, 2).equals(this.bookID.substring(0, 2))) {
            if(lastitemcode.substring(2, 4).equals(this.bookID.substring(2, 4))) {
                if(Integer.parseInt(lastitemcode.substring(4, 7)) < 9) {
                    this.bookID = this.bookID.concat("0");
                    this.bookID = this.bookID.concat("0");
                    this.bookID = this.bookID.concat(Integer.toString(Integer.parseInt(lastitemcode.substring(4, 7)) + 1));
                }
                else if (Integer.parseInt(lastitemcode.substring(4, 7)) < 99) {
                    this.bookID = this.bookID.concat("0");
                    this.bookID = this.bookID.concat(Integer.toString(Integer.parseInt(lastitemcode.substring(4, 7)) + 1));
                }
                else if (Integer.parseInt(lastitemcode.substring(4, 7)) < 999) {
                    this.bookID = this.bookID.concat(Integer.toString(Integer.parseInt(lastitemcode.substring(4, 7)) + 1));
                }
                else System.out.println("코드 카운트 범위를 초과했습니다.");
            }
            else this.bookID = this.bookID.concat("001");
        }
        else this.bookID = this.bookID.concat("001");
        }
    }


    Book(int rent, String bookID, String booknamee, String writerr, String companyy) {

        this.rent = rent;
        this.bookID = bookID;
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
        System.out.println(this.bookID);
        System.out.println(this.bookname);
        System.out.println(this.writer);
        System.out.println(this.company);
    }

    String getID() {
        return this.bookID;
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

    void setID(String a) {
        this.bookID = a;
    }

    int getrent() {
        return this.rent;
    }

    void setBookinfo(String bookname, String writer, String company) {
        this.bookname = bookname;
        this.writer = writer;
        this.company = company;
    }

    void switchrent() {
        if(this.rent == 0) this.rent = 1;
        else this.rent = 0;
    }
}

class rentdata {
    private String bookID;
    private String rentperson;
    private String rentday;
    private String willreturnday;
    private String phonenumber;

    rentdata(String bookID, String rentperson, String phonenumber,String rentday, String willreturnday) {
        this.bookID = bookID;
        this.rentperson = rentperson;
        this.rentday = rentday;
        this.willreturnday = willreturnday;
        this.phonenumber = phonenumber;
    }

    void print_rentdata() {
        System.out.println(this.bookID + " " + this.rentperson + " " + this.rentday + " " + this.willreturnday);
    }

    String getbookID() {return this.bookID;}
    String getRentPerson() {return this.rentperson;}
    String getRentDay() {return this.rentday;}
    String getwillReturnday() {return this.willreturnday;}
    String getphonenumber() {return this.phonenumber;}
    void setReturnday(String cal) {
        this.willreturnday = cal;
    }
    void deletemark() {this.bookID = "0";}

}

class personaldata {
    private String phonenumber;
    private String name;
    private String address;

    personaldata(String phonenumber, String name, String address) {
        this.phonenumber = phonenumber;
        this.name = name;
        this.address = address;
    }

    String getPhonenumber() { return this.phonenumber; }
    String getName() { return this.name; }
    String getAddress() { return this.address; }
}