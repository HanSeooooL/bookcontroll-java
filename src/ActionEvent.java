import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;
import javax.swing.*;

public class ActionEvent{

}

class titleJMenuEventListener implements ActionListener, WindowListener {
    titleUI titleUI;

    public titleJMenuEventListener(titleUI titleUI) {
        this.titleUI = titleUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JMenuItem actionJMenuItem = (JMenuItem)e.getSource();
        if(actionJMenuItem.getText().equals("도서 등록")) {
            System.out.println("도서 등록");
            addbookUI addbook = new addbookUI();
        }
        else if(actionJMenuItem.getText().equals("도서 수정")) {
            System.out.println("도서 수정");
            int row = titleUI.table.getSelectedRow();
            if(row == -1) {
                errorUI errorUI = new errorUI("도서를 선택해주십시오.");
            }
            else {
                updatebookUI updatebook = new updatebookUI(titleUI.tableSelected(row));
            }
        }
        else if(actionJMenuItem.getText().equals("도서 삭제")) {
            System.out.println("도서 삭제");
            int row = titleUI.table.getSelectedRow();
            if(row == -1) {
                errorUI errorUI = new errorUI("도서를 선택해주십시오.");
            }
            else {
                try {
                    deleteBookUI deleteBookUI = new deleteBookUI(titleUI.tableSelected(row));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(actionJMenuItem.getText().equals("도서 대여")) {
            int row = titleUI.table.getSelectedRow();
            if(row == -1) {
                errorUI errorUI = new errorUI("도서를 선택해주십시오.");
            }
            else {
                rentbookUI rentbook = new rentbookUI(titleUI.tableSelected(row));
            }
        }
        else if(actionJMenuItem.getText().equals("도서 반납")) {
            System.out.println("도서 반납");
            int row = titleUI.table.getSelectedRow();
            if(row == -1) {
                errorUI errorUI = new errorUI("도서를 선택해주십시오.");
            }
            else {
                try {
                    returnbookUI returnbook = new returnbookUI(titleUI.tableSelected(row));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        else if(actionJMenuItem.getText().equals("모든 책 보기")) {
            System.out.println("모든 책 보기");
            titleUI.model.setNumRows(0);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(actionJMenuItem.getText().equals("대여중인 책만 보기")) {
            System.out.println("대여중인 책만 보기");
            try {
                titleUI.seerentedbook();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(actionJMenuItem.getText().equals("보유중인 책만 보기")) {
            System.out.println("보유중인 책만 보기");
            titleUI.seeunrentedbook();
        }
        else if(actionJMenuItem.getText().equals("모든 대여 내역 조회")) {
            System.out.println("모든 대여 내역 조회");
            try {
                viewhistoryUI viewhistory = new viewhistoryUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class titleSearchEventListener implements  ActionListener {
    titleUI titleUI;

    titleSearchEventListener(titleUI titleUI) {
        this.titleUI = titleUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJButton = (JButton)e.getSource();
        if(actionJButton.getText().equals("검색")) {
            titleUI.SearchTable(titleUI.Searchsection.getSelectedIndex(), titleUI.SearchKeyword.getText());
        }
    }
}

class titleJTableEventListener implements MouseListener{
    titleUI titleUI;

    public titleJTableEventListener(titleUI titleUI) {
        this.titleUI = titleUI;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int row = titleUI.table.getSelectedRow();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class addbookUIEventListener implements ActionListener, WindowListener {
    addbookUI addbookUI;

    public addbookUIEventListener(addbookUI addbookUI) {
        this.addbookUI = addbookUI;

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJbutton = (JButton)e.getSource();
        if(actionJbutton.getText().equals("등록")) {
            Book newone = new Book(addbookUI.bookname.getText(), addbookUI.writer.getText(), addbookUI.company.getText());
            FileInOut.File.fileSave.addbook(newone);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            addbookUI.dispose();
        }
        else if(actionJbutton.getText().equals("취소")) {
            addbookUI.dispose();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        addbookUI.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class updatebookUIEventListener implements ActionListener, WindowListener {
    updatebookUI updatebookUI;

    public updatebookUIEventListener(updatebookUI updatebookUI) {
        this.updatebookUI = updatebookUI;

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJbutton = (JButton)e.getSource();
        if(actionJbutton.getText().equals("수정")) {
            updatebookUI.one.setBookinfo(updatebookUI.bookname.getText(),updatebookUI.writer.getText(), updatebookUI.company.getText());
            FileInOut.File.fileSave.saveAllbooks(titleUI.Books);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FileInOut.File.fileSave.saveAllbooks(titleUI.Books);

            updatebookUI.dispose();
        }
        else if(actionJbutton.getText().equals("취소")) {
            updatebookUI.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        updatebookUI.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class rentbookUIEventListener implements ActionListener, WindowListener {

    rentbookUI rentbookUI;


    rentbookUIEventListener(rentbookUI rentbookUI) {
        this.rentbookUI = rentbookUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJbutton = (JButton)e.getSource();
        if(actionJbutton.getText().equals("대여")) {
            System.out.println("대여");
            rentdata newdata = new rentdata(rentbookUI.one.getID(), rentbookUI.rentname.getText(),
                    programinside.getDays.gluecalender(rentbookUI.rentyear.getItem(rentbookUI.rentyear.getSelectedIndex()),
                            rentbookUI.rentmonth.getItem(rentbookUI.rentmonth.getSelectedIndex()), rentbookUI.rentday.getItem(rentbookUI.rentday.getSelectedIndex())),
                    programinside.getDays.gluecalender(rentbookUI.returnyear.getItem(rentbookUI.returnyear.getSelectedIndex()),
                            rentbookUI.returnmonth.getItem(rentbookUI.returnmonth.getSelectedIndex()), rentbookUI.returnday.getItem(rentbookUI.returnday.getSelectedIndex())));
            rentbookUI.one.setRentID(newdata.getrentID());
            try {
                FileInOut.File.fileSave.addrent(newdata);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FileInOut.File.fileSave.saveAllbooks(titleUI.Books);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            rentbookUI.dispose();
        }
        else if(actionJbutton.getText().equals("취소")) {
            rentbookUI.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
class deletebookUIEventListener implements  ActionListener, WindowListener {

    deleteBookUI deleteBookUI;

    deletebookUIEventListener(deleteBookUI deleteBookUI) {
        this.deleteBookUI = deleteBookUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton ActionJButton = (JButton)e.getSource();

        if(ActionJButton.getText().equals("삭제")) {
            deletewarningUI deletewarning = new deletewarningUI(this.deleteBookUI);

        }
        else if (ActionJButton.getText().equals("취소")) {
            this.deleteBookUI.dispose();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class returnbookUIEventListener implements  ActionListener, WindowListener {

    returnbookUI returnbookUI;


    returnbookUIEventListener(returnbookUI returnbookUI) {
        this.returnbookUI = returnbookUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJButton = (JButton)e.getSource();
        if(actionJButton.getText().equals("반납")) {
            System.out.println("반납");
            try {
                titleUI.returnthebook(returnbookUI.one);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            returnbookUI.one.setRentID(null);
            FileInOut.File.fileSave.saveAllbooks(titleUI.Books);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.returnbookUI.dispose();
        }
        else if(actionJButton.getText().equals("취소")) {
            System.out.println("취소");
            this.returnbookUI.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class viewhistorySearchEventListener implements  ActionListener {
    viewhistoryUI viewhistoryUI;

    viewhistorySearchEventListener(viewhistoryUI viewhistoryUI) {
        this.viewhistoryUI = viewhistoryUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJButton = (JButton)e.getSource();
        if(actionJButton.getText().equals("검색")) {
            viewhistoryUI.SearchTable(viewhistoryUI.Searchsection.getSelectedIndex(), viewhistoryUI.SearchKeyword.getText());
        }
    }
}

class deletewarningUIEventListener implements ActionListener, WindowListener{

    deletewarningUI deletewarningUI;

    deletewarningUIEventListener(deletewarningUI deletewarningUI) {
        this.deletewarningUI = deletewarningUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJButton = (JButton)e.getSource();
        if(actionJButton.getText().equals("삭제")) {
            try {
                titleUI.deletethebook(deletewarningUI.deleteBookUI.one);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FileInOut.File.fileSave.saveAllbooks(titleUI.Books);
            try {
                titleUI.reloadTable();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.deletewarningUI.deleteBookUI.dispose();
            this.deletewarningUI.dispose();

        }
        else if(actionJButton.getText().equals("취소")) {
            this.deletewarningUI.dispose();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

class errorUIEventListener implements ActionListener, WindowListener {

    errorUI errorUI;

    public errorUIEventListener(errorUI errorUI) {
        this.errorUI = errorUI;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton actionJButton = (JButton)e.getSource();
        if(actionJButton.getText().equals("확인")) {
            this.errorUI.dispose();
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}