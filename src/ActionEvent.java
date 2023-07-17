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
            updatebookUI updatebook = new updatebookUI(titleUI.tableSelected(row));
        }
        else if(actionJMenuItem.getText().equals("도서 삭제")) {
            System.out.println("도서 삭제");
            int row = titleUI.table.getSelectedRow();
        }
        else if(actionJMenuItem.getText().equals("도서 대여")) {
            int row = titleUI.table.getSelectedRow();
            rentbookUI rentbook = new rentbookUI(titleUI.tableSelected(row));
        }
        else if(actionJMenuItem.getText().equals("도서 반납")) {
            System.out.println("도서 반납");
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
            rentbookUI.one.setRentID(newdata.getrentID().toString());
            try {
                FileInOut.File.fileSave.addhistory(newdata);
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