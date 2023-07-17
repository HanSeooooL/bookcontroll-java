
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class GUI {

    static titleUI titleUI;
    final static int displaycount = 10;
    public static void program() throws IOException {
       titleUI = new titleUI();
    }

    /*static void reload_page(ArrayList<bookinfo> info, ArrayList<Book> books, int page, FileInOut file) throws IOException {
        books.clear();
        books = file.fileRead.pageRead(page, displaycount);
        for(int i = 0; i < file.fileRead.pageloded; i++) {
            info.add(new bookinfo(books.get(i), 10, 80 + (i * 20)));
        }
    }*/
}

class titleUI extends JFrame {
    //static ArrayList<bookinfo> bookinfos = new ArrayList<bookinfo>();
    static ArrayList<Book> Books;
    JPanel displaydata;
    static JTable table;
    JMenuBar jmenubar;
    JMenu fileJMenu, rentJMenu, viewJMenu;
    JMenuItem Addbookitemofmenu, updatebookitemofmenu, deletebookitemofmenu,
            rentbookitemofmenu, returnbookitemofmenu;
    JMenuItem viewallbookitemofmenu, viewrentbookitemofmenu, viewunrentedbookitemofmenu;
    String[] headings;
    static DefaultTableModel model;

    public titleUI() throws IOException {
        this.createComponents();
        this.setFrame();
        this.connectEventListener();

        reloadTable();
    }

    static void reloadTable() throws IOException {
        model.setNumRows(0);
        Books = FileInOut.File.fileRead.AllbookRead();

        for(int i = 0; i < FileInOut.File.fileRead.loaded; i++) {
            String rented, RentPerson, Rentday, willReturnDay;
            rentdata a = null;

            if(!Books.get(i).getRentID().equals("0")) {
                rented = "O";
                a = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(Books.get(i).getRentID()));
                RentPerson = a.getRentPerson();
                Rentday = a.getRentDay();
                willReturnDay = a.getwillReturnday();
            }
            else {
                rented = "X";
                RentPerson = " ";
                Rentday = " ";
                willReturnDay = " ";
            }
            Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                    RentPerson, Rentday, willReturnDay};
            model.addRow(data);
        }
    }

    static void SearchTable(int section, String Keyword) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (section == 0)  {    //도서명 검색
                if(!(table.getValueAt(i, 1).toString().contains(Keyword))) {
                    table.remove(i);
                }
            }
        }
    }

    static Book tableSelected(int i) {
        Integer.getInteger((String) table.getValueAt(i, 0));
        return Books.get(i);
    }

    static void seeunrentedbook() {
        model.setNumRows(0);
        String rented, RentPerson, Rentday, willReturnDay;
        for(int i = 0; i < FileInOut.File.fileRead.loaded; i++) {
            if(Books.get(i).getRentID().equals("0")) {
                rented = "X";
                RentPerson = " ";
                Rentday = " ";
                willReturnDay = " ";
                Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                        RentPerson, Rentday, willReturnDay};
                model.addRow(data);
            }
        }
    }

    static void seerentedbook() throws IOException {
        model.setNumRows(0);
        String rented, RentPerson, Rentday, willReturnDay;
        rentdata a = null;
        for(int i = 0; i < FileInOut.File.fileRead.loaded; i++) {
            if(!Books.get(i).getRentID().equals("0")) {
                rented = "O";
                a = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(Books.get(i).getRentID()));
                RentPerson = a.getRentPerson();
                Rentday = a.getRentDay();
                willReturnDay = a.getwillReturnday();
                System.out.println(Integer.toString(i + 1) + Books.get(i).getBookname() + Books.get(i).getWriter() + Books.get(i).getCompany() + rented +
                        RentPerson + Rentday + willReturnDay);
                Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                        RentPerson, Rentday, willReturnDay};
                model.addRow(data);
            }
        }
    }

    public void createComponents() {
        displaydata = new JPanel();

        Addbookitemofmenu = new JMenuItem("도서 등록");
        updatebookitemofmenu = new JMenuItem("도서 수정");
        deletebookitemofmenu = new JMenuItem("도서 삭제");
        rentbookitemofmenu = new JMenuItem("도서 대여");
        returnbookitemofmenu = new JMenuItem("도서 반납");
        viewallbookitemofmenu = new JMenuItem("모든 책 보기");
        viewrentbookitemofmenu = new JMenuItem("대여중인 책만 보기");
        viewunrentedbookitemofmenu = new JMenuItem("보유중인 책만 보기");

        Addbookitemofmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        updatebookitemofmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        deletebookitemofmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        rentbookitemofmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        returnbookitemofmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

        fileJMenu = new JMenu("정보 관리");
        fileJMenu.add(Addbookitemofmenu);
        fileJMenu.add(updatebookitemofmenu);
        fileJMenu.add(deletebookitemofmenu);

        rentJMenu = new JMenu("대여 관리");
        rentJMenu.add(rentbookitemofmenu);
        rentJMenu.add(returnbookitemofmenu);

        viewJMenu = new JMenu("보기 옵션");
        viewJMenu.add(viewallbookitemofmenu);
        viewJMenu.add(viewrentbookitemofmenu);
        viewJMenu.add(viewunrentedbookitemofmenu);

        jmenubar = new JMenuBar();
        jmenubar.add(fileJMenu);
        jmenubar.add(rentJMenu);
        jmenubar.add(viewJMenu);

        headings = new String[]{"번호", "도서명", "저자", "출판사", "대여 여부", "대여인", "대여 일자", "반납 일자"};
        model = new DefaultTableModel(headings, 0) {
            public boolean isCellEditable(int rowIndex, int mCollndex) {
                return false;
            }
        };
        table = new JTable(model);
        //this.model.addRow(headings);
        table.setGridColor(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        displaydata.setLocation(10, 60);
        displaydata.setSize(900, 100);

        Container contentPane = getContentPane();
        JScrollPane jScrollPane = new JScrollPane(table);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(jmenubar, BorderLayout.NORTH);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    public void setFrame() {
        setTitle("도서관리 프로그램");
        this.setBackground(Color.white);
        setLocation(100, 200);
        setPreferredSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setVisible(true);
    }

    public void connectEventListener() {
        titleJMenuEventListener titleJMenuEventListener = new titleJMenuEventListener(this);
        Addbookitemofmenu.addActionListener(titleJMenuEventListener);
        updatebookitemofmenu.addActionListener(titleJMenuEventListener);
        deletebookitemofmenu.addActionListener(titleJMenuEventListener);
        rentbookitemofmenu.addActionListener(titleJMenuEventListener);
        returnbookitemofmenu.addActionListener(titleJMenuEventListener);
        viewallbookitemofmenu.addActionListener(titleJMenuEventListener);
        viewrentbookitemofmenu.addActionListener(titleJMenuEventListener);
        viewunrentedbookitemofmenu.addActionListener(titleJMenuEventListener);
        titleJTableEventListener titleJTableEventListener = new titleJTableEventListener(this);
        table.addMouseListener(titleJTableEventListener);
        this.addWindowListener(titleJMenuEventListener);

    }
}

class addbookUI extends JFrame {
    JTextField bookname, writer, company;
    JButton addfinish, cancel;

    public addbookUI() {
        this.createComponents();
        this.setFrame();
        this.connectEventListener();

    }

    public void createComponents() {
        GridLayout grid = new GridLayout(3, 2);
        grid.setVgap(5);

        JPanel insertlines = new JPanel();
        JPanel choices = new JPanel();
        addfinish = new JButton("등록");
        cancel = new JButton("취소");
        bookname = new JTextField("");
        writer = new JTextField("");
        company = new JTextField("");

        insertlines.add(new JLabel("        도서명"));
        insertlines.add(bookname);
        insertlines.add(new JLabel("        저자"));
        insertlines.add(writer);
        insertlines.add(new JLabel("        출판사"));
        insertlines.add(company);
        insertlines.setLayout(grid);
        insertlines.setSize(500, 100);

        choices.add(addfinish);
        choices.add(cancel);
        choices.setLayout(new FlowLayout(FlowLayout.RIGHT));
        choices.setLocation(0, 100);
        choices.setSize(500, 50);

        Container c = getContentPane();
        c.setLayout(null);

        c.add(insertlines);
        c.add(choices);

    }

    public void setFrame() {
        setTitle("책 등록");
        this.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,180));
        this.pack();
        setVisible(true);
    }

    public void connectEventListener() {
        addbookUIEventListener addbookUIEventListener = new addbookUIEventListener(this);
        addfinish.addActionListener(addbookUIEventListener);
        cancel.addActionListener(addbookUIEventListener);
        this.addWindowListener(addbookUIEventListener);
    }
}

class updatebookUI extends JFrame{

    Book one;
    JTextField bookname, writer, company;
    JButton updatefinish, cancel;

    public updatebookUI(Book one) {
        this.one = one;
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();
    }

    public void createComponents() {
        GridLayout grid = new GridLayout(3, 2);
        grid.setVgap(5);

        JPanel insertlines = new JPanel();
        JPanel choices = new JPanel();
        updatefinish = new JButton("수정");
        cancel = new JButton("취소");
        bookname = new JTextField(one.getBookname());
        writer = new JTextField(one.getWriter());
        company = new JTextField(one.getCompany());

        insertlines.add(new JLabel("        도서명"));
        insertlines.add(bookname);
        insertlines.add(new JLabel("        저자"));
        insertlines.add(writer);
        insertlines.add(new JLabel("        출판사"));
        insertlines.add(company);
        insertlines.setLayout(grid);
        insertlines.setSize(500, 100);

        choices.add(updatefinish);
        choices.add(cancel);
        choices.setLayout(new FlowLayout(FlowLayout.RIGHT));
        choices.setLocation(0, 100);
        choices.setSize(500, 50);

        Container c = getContentPane();
        c.setLayout(null);

        c.add(insertlines);
        c.add(choices);
    }

    public void setFrame() {
        setTitle("도서정보 수정");
        this.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,180));
        this.pack();
        setVisible(true);
    }

    public void ConnectEventListener() {
        updatebookUIEventListener updatebookUIEventListener = new updatebookUIEventListener(this);
        updatefinish.addActionListener(updatebookUIEventListener);
        cancel.addActionListener(updatebookUIEventListener);
        this.addWindowListener(updatebookUIEventListener);
    }
}

class deleteBookUI extends JFrame{
    Book one;

    JLabel booknamedisplay, writerdisplay, companydisplay;

    public deleteBookUI() {
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();
    }

    public void createComponents() {
        booknamedisplay = new JLabel(one.getBookname());
        writerdisplay = new JLabel(one.getWriter());
        companydisplay = new JLabel(one.getCompany());

    }

    public void setFrame() {
        setTitle("책 삭제");
        this.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,180));
        this.pack();
        setVisible(true);
    }

    public void ConnectEventListener() {

    }
}

class rentbookUI extends JFrame {
    Book one;

    JButton rentfinish, cancel;
    JTextField rentname;
    Choice rentyear, rentmonth, rentday, returnyear, returnmonth, returnday;
    JLabel bookname, writer, company, rentnamee, rentdayy, returndayy, booknamee, writerr, companyy;


    public rentbookUI(Book one) {
        this.one = one;
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();
    }

    public void createComponents() {
        GridLayout grid = new GridLayout(6, 2);
        grid.setVgap(5);

        LineBorder border = new LineBorder(Color.BLACK, 1);

        bookname = new JLabel(one.getBookname());
        writer = new JLabel(one.getWriter());
        company = new JLabel(one.getCompany());

        booknamee = new JLabel("도서명");
        writerr = new JLabel("저자");
        companyy = new JLabel("출판사");
        booknamee.setHorizontalAlignment(JLabel.CENTER);
        writerr.setHorizontalAlignment(JLabel.CENTER);
        companyy.setHorizontalAlignment(JLabel.CENTER);
        bookname.setOpaque(true);
        bookname.setBackground(Color.WHITE);
        bookname.setBorder(border);
        writer.setOpaque(true);
        writer.setBackground(Color.WHITE);
        writer.setBorder(border);
        company.setOpaque(true);
        company.setBackground(Color.WHITE);
        company.setBorder(border);

        JPanel insertlines = new JPanel();
        JPanel choices = new JPanel();
        JPanel choicerentday = new JPanel();
        JPanel choicewillreturnday = new JPanel();
        insertlines.add(booknamee);
        insertlines.add(bookname);
        insertlines.add(writerr);
        insertlines.add(writer);
        insertlines.add(companyy);
        insertlines.add(company);
        rentfinish = new JButton("대여");
        cancel = new JButton("취소");
        rentname = new JTextField("");

        rentyear = new Choice();
        rentmonth = new Choice();
        rentday = new Choice();
        for(int i = 0; i < 5; i++) {
            rentyear.add(Integer.toString(2023 - i));
        }
        for(int i = 1; i < 13; i++) {
            rentmonth.add(Integer.toString(i));
        }
        for(int i = 1; i < 31; i++) {
            rentday.add(Integer.toString(i));
        }

        returnyear = new Choice();
        returnmonth = new Choice();
        returnday = new Choice();
        for(int i = 0; i < 5; i++) {
            returnyear.add(Integer.toString(2023 + i));
        }
        for(int i = 1; i < 13; i++) {
            returnmonth.add(Integer.toString(i));
        }
        for(int i = 1; i < 31; i++) {
            returnday.add(Integer.toString(i));
        }

        choicerentday.add(rentyear);
        choicerentday.add(rentmonth);
        choicerentday.add(rentday);

        choicewillreturnday.add(returnyear);
        choicewillreturnday.add(returnmonth);
        choicewillreturnday.add(returnday);

        rentnamee = new JLabel("대여인");
        rentdayy = new JLabel("대여일");
        returndayy = new JLabel("반납예정일");

        rentnamee.setHorizontalAlignment(JLabel.CENTER);
        rentdayy.setHorizontalAlignment(JLabel.CENTER);
        returndayy.setHorizontalAlignment(JLabel.CENTER);

        insertlines.add(rentnamee);
        insertlines.add(rentname);
        insertlines.add(rentdayy);
        insertlines.add(choicerentday);
        insertlines.add(returndayy);
        insertlines.add(choicewillreturnday);
        insertlines.setLayout(grid);
        insertlines.setSize(300, 220);

        choices.add(rentfinish);
        choices.add(cancel);
        choices.setLayout(new FlowLayout(FlowLayout.RIGHT));
        choices.setLocation(0, 220);
        choices.setSize(500, 50);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(insertlines);
        c.add(choices);
    }

    public void setFrame() {
        setTitle("도서 대여");
        this.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,350));
        this.pack();
        setVisible(true);
    }

    public void ConnectEventListener() {
        rentbookUIEventListener rentbookUIEventListener = new rentbookUIEventListener(this);
        rentfinish.addActionListener(rentbookUIEventListener);
        cancel.addActionListener(rentbookUIEventListener);
        this.addWindowListener(rentbookUIEventListener);
    }

}

class returnbookUI extends JFrame {

    Book one;
    public returnbookUI(Book a) {

    }
}