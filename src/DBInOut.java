import java.sql.SQLException;
import java.util.ArrayList;

public class DBInOut {
    static DBIn DBIn = new DBIn();
    static DBOut DBOut = new DBOut();

}

class DBOut {
    void addBook(Book a) {
        try {
            DB.ps = DB.con.prepareStatement("insert into bookcontroll.Book(initialcode, bookname, writer, company, rented) value(?, ?, ?, ?, ?);");
            DB.ps.setString(1, a.getID());
            DB.ps.setString(2, a.getBookname());
            DB.ps.setString(3, a.getWriter());
            DB.ps.setString(4, a.getCompany());
            DB.ps.setInt(5, 0);
            DB.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateBook(Book a) {
        try {
            DB.ps = DB.con.prepareStatement("update bookcontroll.Book set bookname = ?, writer = ?, company = ? where initialcode = ?;");
            DB.ps.setString(1, a.getBookname());
            DB.ps.setString(2, a.getWriter());
            DB.ps.setString(3, a.getCompany());
            DB.ps.setString(4, a.getID());
            DB.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void deletetheBook(String code) {
        try {
            DB.ps = DB.con.prepareStatement("delete from bookcontroll.rentdata where bookcode = ?;");
            DB.ps.setString(1, code);
            DB.ps.executeUpdate();
            DB.ps = DB.con.prepareStatement("delete from bookcontroll.Book where initialcode = ?;");
            DB.ps.setString(1, code);
            DB.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void renttheBook(rentdata a) {
        try {
            DB.ps = DB.con.prepareStatement("insert into bookcontroll.rentdata(bookcode, rentperson, rentday, returnday) value(?, ?, ?, ?);");
            DB.ps.setString(1, a.getbookID());
            DB.ps.setString(2, a.getRentPerson());
            DB.ps.setString(3, a.getRentDay());
            DB.ps.setString(4, a.getwillReturnday());
            DB.ps.executeUpdate();
            DB.ps = DB.con.prepareStatement("update bookcontroll.Book set rented = 1 where initialcode = ?");
            DB.ps.setString(1, a.getbookID());
            DB.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void returntheBook(String code, String day) {
        try {
            //DB.ps = DB.con.prepareStatement("select * bookcon")
            DB.ps = DB.con.prepareStatement("update bookcontroll.rentdata set returnday = ? " +
                    "where returnday = (select returnday from (select max(cast(returnday as unsigned)) as returnday from bookcontroll.rentdata where bookcode = ?) as returnday_t)");
            DB.ps.setString(1, day);
            DB.ps.setString(2, code);
            DB.ps.executeUpdate();
            DB.ps = DB.con.prepareStatement("update bookcontroll.Book set rented = 0 where bookcode = ?");
            DB.ps.setString(1, code);
            DB.ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

class DBIn{

    ArrayList<Book> readAllBook() {
        ArrayList<Book> res;
        try {
            res = new ArrayList<Book>();
            DB.rs = DB.stmt.executeQuery("select * from bookcontroll.Book;");
            while(DB.rs.next()) {
                res.add(new Book(DB.rs.getInt("rented"), DB.rs.getString("initialcode"),
                        DB.rs.getString("bookname"), DB.rs.getString("writer"), DB.rs.getString("company")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    rentdata checkrentdata(String code) {
        rentdata res = null;

        try {
            DB.ps = DB.con.prepareStatement("select * from bookcontroll.rentdata where bookcode = ?");
            DB.ps.setString(1, code);
            DB.rs = DB.ps.executeQuery();
            while(DB.rs.next()) {
                res = new rentdata(DB.rs.getString("bookcode"), DB.rs.getString("rentperson"),
                        DB.rs.getString("rentday"), DB.rs.getString("returnday"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}



