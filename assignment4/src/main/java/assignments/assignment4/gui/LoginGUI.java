/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.member.MemberSystemGUI;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        // Membuat panel baru
        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 5;
        c.ipadx = 450;

        // Membuat label input ID
        idLabel = new JLabel("Masukkan ID Anda:");
        c.gridy = 0;
        tempPanel.add(idLabel, c);

        // Membuat kolom teks input ID
        idTextField = new JTextField();
        c.gridy = 1;
        tempPanel.add(idTextField, c);

        // Membuat label input password
        passwordLabel = new JLabel("Masukkan password Anda:");
        c.gridy = 2;
        tempPanel.add(passwordLabel, c);

        // Membuat kolom teks input password
        passwordField = new JPasswordField();
        c.gridy = 3;
        tempPanel.add(passwordField, c);

        // Membuat tombol login
        loginButton = new JButton("Login");
        c.gridy = 4;
        tempPanel.add(loginButton, c);

        // Membuat tombol kembali
        backButton = new JButton("Kembali");
        c.gridy = 5;
        tempPanel.add(backButton, c);

        // Masukkan panel sementara ke panel utama
        mainPanel.add(tempPanel);

        // Event ketikatombol login diklik
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Event ketika tombol kembali diklik
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo("HOME");

        // Kosongkan kolom teks input id
        idTextField.setText("");
        passwordField.setText("");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODOne
        // Handle ketika ada field yang belum terisi
        if (idTextField.getText().equals("") || passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ketika ID atau password tidak ditemukan
        if (!MainFrame.getInstance().login(idTextField.getText(), passwordField.getText())) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid", "Login Failed", JOptionPane.ERROR_MESSAGE);
            idTextField.setText("");
            passwordField.setText("");
            return;
        }

        // Kosongkan kolom teks input id
        idTextField.setText("");
        passwordField.setText("");
    }
}