/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        JButton cuciButton = new JButton("It's nyuci time");
        JButton displayNotaButton = new JButton("Display List Nota");
        return new JButton[]{
                cuciButton, displayNotaButton
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODOne
        String listNota = "";
        for (Nota nota : NotaManager.notaList) listNota += nota.getNotaStatus() + "\n";
        if (listNota.equals("")) {
            JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota",  JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, listNota, "List Nota", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODOne
        JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);

        String detailNota = "";
        for (Nota nota : NotaManager.notaList) detailNota += nota.kerjakan() + "\n";
        if (detailNota.equals("")) {
            JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Results",  JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, detailNota, "Nyuci Results",  JOptionPane.INFORMATION_MESSAGE);
    }
}
