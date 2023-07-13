
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;

public class GUI {

}

class titleUI extends JFrame {
    public titleUI() {
        setTitle("도서관리 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        Container contentPane = getContentPane();
        JPanel buttons = new JPanel();
        Typeinfo typeinfo = new Typeinfo();
        bookinfo data[] = new bookinfo[5];

        contentPane.setLayout(null);
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton bt_add = new JButton("등록");
        JButton bt_update = new JButton("수정");
        JButton bt_delete = new JButton("삭제");
        buttons.add(bt_add);
        buttons.add(bt_update);
        buttons.add(bt_delete);
        contentPane.add(buttons);
        contentPane.add(typeinfo);

        buttons.setLocation(10, 10);
        buttons.setSize(500, 50);

        bt_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addbookUI addbook = new addbookUI();
            }
        });

        setSize(1000, 500);
        setVisible(true);
    }


}

class bookinfo extends JPanel {
    JLabel bookname;
    JLabel Writer;
    JLabel company;
    JLabel rented;
    JLabel rentname;
    JLabel rentday;
    JLabel willreturnday;

    bookinfo(Book one, int x, int y) {
        this.bookname = new JLabel(one.getBookname());
        this.Writer = new JLabel(one.getWriter());
        this.company = new JLabel(one.getCompany());
        if(one.getdiditRent()) {
            this.rented = new JLabel("O");
            this.rentname = new JLabel(one.getRentname());
            this.rentday = new JLabel(one.getRentday());
            this.willreturnday = new JLabel(one.getReturnday());
        }
        else {
            this.rented = new JLabel("X");
            this.rentname = new JLabel(" ");
            this.rentday = new JLabel(" ");
            this.willreturnday = new JLabel(" ");
        }

        LineBorder border = new LineBorder(Color.black);
        GridLayout grid = new GridLayout(1, 7);
        grid.setVgap(0);
        this.setLocation(x, y);
        this.setSize(980, 20);

        this.setLabeltextdirection(JLabel.CENTER);

        this.makeLineBorders(border);
        this.setLabelsSize(20, 10);

    }

    bookinfo() {

    }
    void addLables() {
        this.add(this.bookname);
        this.add(this.Writer);
        this.add(this.company);
        this.add(this.rented);
        this.add(this.rentname);
        this.add(this.rentday);
        this.add(this.willreturnday);
    }

    void makeLineBorders(LineBorder border) {
        this.bookname.setBorder(border);
        this.Writer.setBorder(border);
        this.company.setBorder(border);
        this.rented.setBorder(border);
        this.rentname.setBorder(border);
        this.rentday.setBorder(border);
        this.willreturnday.setBorder(border);
    }
    void setLabeltextdirection(int direction) {
        this.bookname.setHorizontalAlignment(direction);
        this.Writer.setHorizontalAlignment(direction);
        this.company.setHorizontalAlignment(direction);
        this.rented.setHorizontalAlignment(direction);
        this.rentname.setHorizontalAlignment(direction);
        this.rentday.setHorizontalAlignment(direction);
        this.willreturnday.setHorizontalAlignment(direction);
    }
    void setLabelsSize(int width, int height) {
        this.bookname.setSize(width, height);
        this.Writer.setSize(width, height);
        this.company.setSize(width, height);
        this.rented.setSize(width, height);
        this.rentname.setSize(width, height);
        this.rentday.setSize(width, height);
        this.willreturnday.setSize(width, height);
    }
}

class Typeinfo extends bookinfo {

    Typeinfo(){
        this.bookname = new JLabel("도서명");
        this.Writer = new JLabel("저자");
        this.company = new JLabel("출판사");
        this.rented = new JLabel("대여 여부");
        this.rentname = new JLabel("대여인");
        this.rentday = new JLabel("대여 일자");
        this.willreturnday = new JLabel("반납 예정 일자");

        LineBorder border = new LineBorder(Color.black);
        GridLayout grid = new GridLayout(1, 7);
        grid.setVgap(0);
        this.setLocation(10, 60);
        this.setSize(980, 20);

        this.setLabelsSize(20, 10);

        this.setLabeltextdirection(JLabel.CENTER);

        this.makeLineBorders(border);

        this.addLables();


        this.setLayout(grid);



    }

}

class addbookUI extends JFrame {
    public addbookUI() {
        setTitle("책 등록");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(3, 2);
        grid.setVgap(5);

        Container c = getContentPane();
        c.setLayout(null);
        JPanel insertlines = new JPanel();
        JPanel choices = new JPanel();
        JButton addfinish = new JButton("등록");
        JButton cancel = new JButton("취소");
        JTextField bookname = new JTextField("");
        JTextField writer = new JTextField("");
        JTextField company = new JTextField("");

        insertlines.add(new JLabel("        도서명"));
        insertlines.add(bookname);
        insertlines.add(new JLabel("        저자"));
        insertlines.add(writer);
        insertlines.add(new JLabel("        출판사"));
        insertlines.add(company);
        insertlines.setLayout(grid);

        choices.add(addfinish);
        choices.add(cancel);
        choices.setLayout(new FlowLayout(FlowLayout.RIGHT));

        c.add(insertlines);
        choices.setSize(500, 50);
        insertlines.setSize(500, 100);
        choices.setLocation(0, 100);
        c.add(choices);

        addfinish.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        setSize(500, 180);
        setVisible(true);
    }
}