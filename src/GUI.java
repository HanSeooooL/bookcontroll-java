
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class GUI {

    static titleUI titleUI;

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
    static JTable table;
    JPanel SearchPanel;
    JMenuBar jmenubar;
    JMenu fileJMenu, rentJMenu, viewJMenu;
    JMenuItem Addbookitemofmenu, updatebookitemofmenu, deletebookitemofmenu,
            rentbookitemofmenu, returnbookitemofmenu, viewhistoryitemofmenu;
    JMenuItem viewallbookitemofmenu, viewrentbookitemofmenu, viewunrentedbookitemofmenu;
    JLabel Search;
    Choice Searchsection;
    JButton Searchstart;
    JTextField SearchKeyword;
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
            String rented, RentPerson, Rentday, willReturnDay, didntReturnday;
            rentdata a = null;

            if(!Books.get(i).getRentID().equals("0")) {
                rented = "O";
                a = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(Books.get(i).getRentID()));
                RentPerson = a.getRentPerson();
                Rentday = a.getRentDay();
                willReturnDay = a.getwillReturnday();
                didntReturnday = Integer.toString(programinside.getDays.checkHowyouDidntReturn(a.getwillReturnday()));
            }
            else {
                rented = "X";
                RentPerson = " ";
                Rentday = " ";
                willReturnDay = " ";
                didntReturnday = " ";
            }
            Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                    RentPerson, Rentday, willReturnDay, didntReturnday};
            model.addRow(data);
        }
    }

    static void SearchTable(int section, String Keyword) {
        ArrayList<Object[]> res = new ArrayList<Object[]>();
        for (int i = 0; i < table.getRowCount(); i++) {
            if (section == 0)  {    //도서명 검색
                if((table.getValueAt(i, 1).toString().contains(Keyword))) {
                    res.add(new Object[] {table.getValueAt(i, 0).toString(), table.getValueAt(i, 1).toString(), table.getValueAt(i, 2).toString(),
                            table.getValueAt(i, 3).toString(), table.getValueAt(i, 4).toString(), table.getValueAt(i, 5).toString(), table.getValueAt(i, 6).toString(),
                            table.getValueAt(i, 7).toString(), table.getValueAt(i, 8).toString()});
                }
            }
        }
        model.setNumRows(0);
        for(int i = 0; i < res.size(); i++) {
            model.addRow(res.get(i));
        }
    }

    static Book tableSelected(int i) {
        i = Integer.parseInt(table.getValueAt(i, 0).toString());
        return Books.get(i - 1);
    }

    static void seeunrentedbook() {
        model.setNumRows(0);
        String rented, RentPerson, Rentday, willReturnDay, didntReturnday;
        for(int i = 0; i < FileInOut.File.fileRead.loaded; i++) {
            if(Books.get(i).getRentID().equals("0")) {
                rented = "X";
                RentPerson = " ";
                Rentday = " ";
                willReturnDay = " ";
                didntReturnday = " ";
                Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                        RentPerson, Rentday, willReturnDay, didntReturnday};
                model.addRow(data);
            }
        }
    }

    static void seerentedbook() throws IOException {
        model.setNumRows(0);
        String rented, RentPerson, Rentday, willReturnDay, didntReturnday;
        rentdata a = null;
        for(int i = 0; i < FileInOut.File.fileRead.loaded; i++) {
            if(!Books.get(i).getRentID().equals("0")) {
                rented = "O";
                a = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(Books.get(i).getRentID()));
                RentPerson = a.getRentPerson();
                Rentday = a.getRentDay();
                willReturnDay = a.getwillReturnday();
                didntReturnday = Integer.toString(programinside.getDays.checkHowyouDidntReturn(a.getwillReturnday()));
                Object[] data = {Integer.toString(i + 1), Books.get(i).getBookname(), Books.get(i).getWriter(), Books.get(i).getCompany(), rented,
                        RentPerson, Rentday, willReturnDay, didntReturnday};
                model.addRow(data);
            }
        }
    }

    static void returnthebook(Book a) throws IOException {
        ArrayList<rentdata> rents = FileInOut.File.fileRead.AllrentdataRead();
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        historydata history;
        for(int i = 0; i < rents.size(); i++) {
            if(rents.get(i).getrentID().toString().equals(a.getRentID())) {
                history = new historydata(rents.get(i).getbookID(), rents.get(i).getRentPerson(), rents.get(i).getRentDay(),
                        programinside.getDays.gluecalender(Integer.toString(now.getYear()), Integer.toString(now.getMonthValue()), Integer.toString(now.getDayOfMonth())));
                FileInOut.File.fileSave.addhistory(history);
                rents.get(i).markrentID();
                break;
            }
        }
        FileInOut.File.fileSave.saveallrent(rents);
    }

    public void createComponents() {

        Addbookitemofmenu = new JMenuItem("도서 등록");
        updatebookitemofmenu = new JMenuItem("도서 수정");
        deletebookitemofmenu = new JMenuItem("도서 삭제");
        rentbookitemofmenu = new JMenuItem("도서 대여");
        returnbookitemofmenu = new JMenuItem("도서 반납");
        viewallbookitemofmenu = new JMenuItem("모든 책 보기");
        viewrentbookitemofmenu = new JMenuItem("대여중인 책만 보기");
        viewunrentedbookitemofmenu = new JMenuItem("보유중인 책만 보기");
        viewhistoryitemofmenu = new JMenuItem("모든 대여 내역 조회");

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
        rentJMenu.addSeparator();
        rentJMenu.add(viewhistoryitemofmenu);

        viewJMenu = new JMenu("보기 옵션");
        viewJMenu.add(viewallbookitemofmenu);
        viewJMenu.add(viewrentbookitemofmenu);
        viewJMenu.add(viewunrentedbookitemofmenu);

        jmenubar = new JMenuBar();
        jmenubar.add(fileJMenu);
        jmenubar.add(rentJMenu);
        jmenubar.add(viewJMenu);

        headings = new String[]{"번호", "도서명", "저자", "출판사", "대여 여부", "대여인", "대여 일자", "반납 일자", "연체일"};
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
        table.setAutoCreateRowSorter(true);
        TableRowSorter sorter = new TableRowSorter(table.getModel());
        table.setRowSorter(sorter);

        Search = new JLabel("검색:");

        Searchsection = new Choice();
        Searchsection.add("도서명");
        Searchsection.add("저자");
        Searchsection.add("출판사");
        Searchsection.add("대여인");
        Searchsection.setLocation(10, 400);

        SearchKeyword = new JTextField("");
        SearchKeyword.setPreferredSize(new Dimension(200, 20));

        Searchstart = new JButton("검색");

        SearchPanel = new JPanel();
        SearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        SearchPanel.add(Search);
        SearchPanel.add(Searchsection);
        SearchPanel.add(SearchKeyword);
        SearchPanel.add(Searchstart);

        Container contentPane = getContentPane();
        JScrollPane jScrollPane = new JScrollPane(table);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(jmenubar, BorderLayout.NORTH);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        contentPane.add(SearchPanel, BorderLayout.SOUTH);
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
        titleSearchEventListener titleSearchEventListener = new titleSearchEventListener(this);
        Searchstart.addActionListener(titleSearchEventListener);
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

    rentdata rentdata;
    JPanel displayrentinfo, buttonplace, centerplace;
    JLabel booknamee, writerr, companyy, rentpersonn, rentdayy, willreturndayy;
    JLabel bookname, writer, company, rentperson, rentday, willreturnday;
    JButton returnfinish, cancel;

    public deleteBookUI(Book a) throws IOException {
        this.one = a;
        if(!this.one.getRentID().equals("0")) {
            rentdata = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(one.getRentID()));
        }
        else {
            rentdata = new rentdata(null, this.one.getID(), " ", " ", " ");
        }
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();
    }

    public void createComponents() {
        //GridBagLayout grid = new GridBagLayout();
        //GridBagConstraints gbc = new GridBagConstraints();
        GridLayout grid = new GridLayout(7, 2);
        grid.setVgap(20);
        grid.setHgap(5);
        LineBorder border = new LineBorder(Color.BLACK, 1);

        this.bookname = new JLabel(this.one.getBookname());
        this.writer = new JLabel(this.one.getWriter());
        this.company = new JLabel(this.one.getCompany());
        this.rentperson = new JLabel(this.rentdata.getRentPerson());
        this.rentday = new JLabel(this.rentdata.getRentDay());
        this.willreturnday = new JLabel(this.rentdata.getwillReturnday());


        this.bookname.setPreferredSize(new Dimension(450, 20));

        this.booknamee = new JLabel("도서명");
        this.writerr = new JLabel("저자");
        this.companyy = new JLabel("출판사");
        this.rentpersonn = new JLabel("대여인");
        this.rentdayy = new JLabel("대여일");
        this.willreturndayy = new JLabel("반납 예정일");

        this.booknamee.setHorizontalAlignment(JLabel.CENTER);
        this.writerr.setHorizontalAlignment(JLabel.CENTER);
        this.companyy.setHorizontalAlignment(JLabel.CENTER);
        this.rentpersonn.setHorizontalAlignment(JLabel.CENTER);
        this.rentdayy.setHorizontalAlignment(JLabel.CENTER);
        this.willreturndayy.setHorizontalAlignment(JLabel.CENTER);


        this.returnfinish = new JButton("삭제");
        this.cancel = new JButton("취소");

        this.displayrentinfo = new JPanel();
        this.displayrentinfo.setLayout(grid);
        this.displayrentinfo.add(this.booknamee);
        this.displayrentinfo.add(this.bookname);
        this.displayrentinfo.add(this.writerr);
        this.displayrentinfo.add(this.writer);
        this.displayrentinfo.add(this.companyy);
        this.displayrentinfo.add(this.company);
        this.displayrentinfo.add(this.rentpersonn);
        this.displayrentinfo.add(this.rentperson);
        this.displayrentinfo.add(this.rentdayy);
        this.displayrentinfo.add(this.rentday);
        this.displayrentinfo.add(this.willreturndayy);
        this.displayrentinfo.add(this.willreturnday);

        bookname.setOpaque(true);
        bookname.setBackground(Color.WHITE);
        bookname.setBorder(border);
        writer.setOpaque(true);
        writer.setBackground(Color.WHITE);
        writer.setBorder(border);
        company.setOpaque(true);
        company.setBackground(Color.WHITE);
        company.setBorder(border);
        rentperson.setOpaque(true);
        rentperson.setBackground(Color.WHITE);
        rentperson.setBorder(border);
        rentday.setOpaque(true);
        rentday.setBackground(Color.WHITE);
        rentday.setBorder(border);
        willreturnday.setOpaque(true);
        willreturnday.setBackground(Color.WHITE);
        willreturnday.setBorder(border);

        this.buttonplace = new JPanel();
        this.buttonplace.add(returnfinish);
        this.buttonplace.add(cancel);
        this.buttonplace.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.centerplace = new JPanel();
        this.centerplace.setLayout(new BorderLayout());
        this.centerplace.add(this.displayrentinfo, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(this.centerplace, BorderLayout.CENTER);
        c.add(this.buttonplace, BorderLayout.SOUTH);

    }

    public void setFrame() {
        setTitle("도서 삭제");
        this.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,350));
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
        grid.setVgap(20);
        grid.setHgap(5);

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
        rentname.setHorizontalAlignment(JTextField.CENTER);

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
        c.setLayout(new BorderLayout());

        c.add(insertlines, BorderLayout.CENTER);
        c.add(choices, BorderLayout.SOUTH);
    }

    public void setFrame() {
        setTitle("도서 대여");
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,350));
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
    rentdata rentdata;
    JPanel displayrentinfo, buttonplace, centerplace;
    JLabel booknamee, writerr, companyy, rentpersonn, rentdayy, willreturndayy, todayy;
    JLabel bookname, writer, company, rentperson, rentday, willreturnday, today;
    JButton returnfinish, cancel;
    public returnbookUI(Book a) throws IOException {
        this.one = a;
        rentdata = FileInOut.File.fileRead.checkthebookrent(UUID.fromString(one.getRentID()));
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();

    }

    public void createComponents() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        //GridBagLayout grid = new GridBagLayout();
        //GridBagConstraints gbc = new GridBagConstraints();
        GridLayout grid = new GridLayout(7, 2);
        grid.setVgap(20);
        grid.setHgap(5);
        LineBorder border = new LineBorder(Color.BLACK, 1);

        this.bookname = new JLabel(this.one.getBookname());
        this.writer = new JLabel(this.one.getWriter());
        this.company = new JLabel(this.one.getCompany());
        this.rentperson = new JLabel(this.rentdata.getRentPerson());
        this.rentday = new JLabel(this.rentdata.getRentDay());
        this.willreturnday = new JLabel(this.rentdata.getwillReturnday());
        this.today = new JLabel(programinside.getDays.gluecalender(Integer.toString(now.getYear()), Integer.toString(now.getMonthValue()), Integer.toString(now.getDayOfMonth())));


        this.bookname.setPreferredSize(new Dimension(450, 20));

        this.booknamee = new JLabel("도서명");
        this.writerr = new JLabel("저자");
        this.companyy = new JLabel("출판사");
        this.rentpersonn = new JLabel("대여인");
        this.rentdayy = new JLabel("대여일");
        this.willreturndayy = new JLabel("반납 예정일");
        this.todayy = new JLabel("오늘 날짜");

        this.booknamee.setHorizontalAlignment(JLabel.CENTER);
        this.writerr.setHorizontalAlignment(JLabel.CENTER);
        this.companyy.setHorizontalAlignment(JLabel.CENTER);
        this.rentpersonn.setHorizontalAlignment(JLabel.CENTER);
        this.rentdayy.setHorizontalAlignment(JLabel.CENTER);
        this.willreturndayy.setHorizontalAlignment(JLabel.CENTER);
        this.todayy.setHorizontalAlignment(JLabel.CENTER);


        this.returnfinish = new JButton("반납");
        this.cancel = new JButton("취소");

        this.displayrentinfo = new JPanel();
        this.displayrentinfo.setLayout(grid);
        this.displayrentinfo.add(this.booknamee);
        this.displayrentinfo.add(this.bookname);
        this.displayrentinfo.add(this.writerr);
        this.displayrentinfo.add(this.writer);
        this.displayrentinfo.add(this.companyy);
        this.displayrentinfo.add(this.company);
        this.displayrentinfo.add(this.rentpersonn);
        this.displayrentinfo.add(this.rentperson);
        this.displayrentinfo.add(this.rentdayy);
        this.displayrentinfo.add(this.rentday);
        this.displayrentinfo.add(this.willreturndayy);
        this.displayrentinfo.add(this.willreturnday);
        this.displayrentinfo.add(this.todayy);
        this.displayrentinfo.add(this.today);

        bookname.setOpaque(true);
        bookname.setBackground(Color.WHITE);
        bookname.setBorder(border);
        writer.setOpaque(true);
        writer.setBackground(Color.WHITE);
        writer.setBorder(border);
        company.setOpaque(true);
        company.setBackground(Color.WHITE);
        company.setBorder(border);
        rentperson.setOpaque(true);
        rentperson.setBackground(Color.WHITE);
        rentperson.setBorder(border);
        rentday.setOpaque(true);
        rentday.setBackground(Color.WHITE);
        rentday.setBorder(border);
        willreturnday.setOpaque(true);
        willreturnday.setBackground(Color.WHITE);
        willreturnday.setBorder(border);
        today.setOpaque(true);
        today.setBackground(Color.WHITE);
        today.setBorder(border);

        this.buttonplace = new JPanel();
        this.buttonplace.add(returnfinish);
        this.buttonplace.add(cancel);
        this.buttonplace.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.centerplace = new JPanel();
        this.centerplace.setLayout(new BorderLayout());
        this.centerplace.add(this.displayrentinfo, BorderLayout.CENTER);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(this.centerplace, BorderLayout.CENTER);
        c.add(this.buttonplace, BorderLayout.SOUTH);

    }

    public void setFrame() {
        setTitle("도서 반납");
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,350));
        this.pack();
        setVisible(true);
    }

    public void ConnectEventListener() {
        returnbookUIEventListener returnbookUIEventListener = new returnbookUIEventListener(this);
        returnfinish.addActionListener(returnbookUIEventListener);
        cancel.addActionListener(returnbookUIEventListener);
        this.addWindowListener(returnbookUIEventListener);
    }
}

class viewhistoryUI extends JFrame{

    ArrayList<rentdata> rentdata;

    public viewhistoryUI() {

    }

    public void createComponents() {

    }

    public void setFrame() {
        setTitle("대여내역 조회");
        this.setBackground(Color.white);
        setLocation(100, 200);
        setPreferredSize(new Dimension(1000, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setVisible(true);
    }

    public void ConnectEventListener() {

    }
}

class errorUI extends JFrame {
    String msg;
    JLabel message;
    JButton accept;
    JPanel buttonspace;
    public errorUI(String msg) {
        this.msg = msg;
        this.createComponents();
        this.setFrame();
        this.ConnectEventListener();

    }

    public void createComponents() {
        this.message = new JLabel(this.msg);
        this.message.setHorizontalAlignment(JLabel.CENTER);

        this.accept = new JButton("확인");

        this.buttonspace = new JPanel();
        this.buttonspace.add(this.accept);

        Container c = getContentPane();
        GridLayout grid = new GridLayout(2, 1);
        c.setLayout(grid);
        c.add(this.message);
        c.add(this.buttonspace);
    }

    public void setFrame() {
        this.setTitle("에러");
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(400, 150));
        this.pack();
        this.setVisible(true);
    }

    public void ConnectEventListener() {
        errorUIEventListener errorUIEventListener = new errorUIEventListener(this);
        this.accept.addActionListener(errorUIEventListener);
        this.addWindowListener(errorUIEventListener);

    }
}