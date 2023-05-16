/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        // TODOne
        // Membuat panel baru
        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 5;
        c.ipadx = 400;

        // Membuat label input nama
        nameLabel = new JLabel("Masukkan nama Anda:");
        c.gridy = 0;
        tempPanel.add(nameLabel, c);

        // Membuat kolom teks input nama
        nameTextField = new JTextField();
        c.gridy = 1;
        tempPanel.add(nameTextField, c);

        // Membuat label input nomor handphone
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");
        c.gridy = 2;
        tempPanel.add(phoneLabel, c);

        // Membuat kolom teks input nomor handphone
        phoneTextField = new JTextField();
        c.gridy = 3;
        tempPanel.add(phoneTextField, c);

        // Membuat label input password
        passwordLabel = new JLabel("Masukkan password Anda:");
        c.gridy = 4;
        tempPanel.add(passwordLabel, c);

        // Membuat kolom teks input password
        passwordField = new JPasswordField();
        c.gridy = 5;
        tempPanel.add(passwordField, c);

        // Membuat tombol register
        registerButton = new JButton("Register");
        c.gridy = 6;
        tempPanel.add(registerButton, c);

        // Membuat tombol kembali
        backButton = new JButton("Kembali");
        c.gridy = 7;
        tempPanel.add(backButton, c);

        // Masukkan panel sementara ke panel utama
        mainPanel.add(tempPanel);

        // Event ketika tombol register diklik
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
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

        // Kosongkan kolom teks yang digunakan
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODOne
        // Handle ketika ada field yang belum terisi
        if (nameTextField.getText().equals("") || phoneTextField.getText().equals("") || passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Semua field diatas wajib diisi!", "Empty Field", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Membuat member baru
        Member member = loginManager.register(nameTextField.getText(), phoneTextField.getText(), passwordField.getText());
        // Jika member sudah ada, maka keluarkan alert box error
        if (member == null) {
            JOptionPane.showMessageDialog(null, "User dengan nama " + nameTextField.getText() + " dan nomor hp " + phoneTextField.getText() + " sudah ada!", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Jika member belum ada, maka keluarkan alert box berhasil
        loginManager.register(nameTextField.getText(), phoneTextField.getText(), passwordField.getText());
        JOptionPane.showMessageDialog(null, "Berhasil membuat user dengan ID " + member.getId() + "!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

        // Kosongkan kolom teks yang digunakan
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");

        MainFrame.getInstance().navigateTo("HOME");
    }
}