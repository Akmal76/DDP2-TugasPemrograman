/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

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
        // Membuat panel sementara
        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;

        // Membuat label judul
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        c.gridy = 0;
        tempPanel.add(titleLabel, c);

        // Membuat tombol login
        loginButton = new JButton("Login");
        c.gridy = 1;
        tempPanel.add(loginButton, c);

        // Membuat tombol register
        registerButton = new JButton("Register");
        c.gridy = 2;
        tempPanel.add(registerButton, c);

        // Membuat tombol next date
        toNextDayButton = new JButton("Next Day");
        c.gridy = 3;
        tempPanel.add(toNextDayButton, c);

        // Membuat label tanggal hari ini
        dateLabel = new JLabel("Hari ini:" + NotaManager.fmt.format(NotaManager.cal.getTime()), SwingConstants.CENTER);
        c.gridy = 4;
        tempPanel.add(dateLabel, c);

        // Masukkan panel sementara ke panel utama
        mainPanel.add(tempPanel);

        // Event ketika tombol login diklik
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToLogin();
            }
        });

        // Event ketika tombol register diklik
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleToRegister();
            }
        });

        // Event ketika tombol next day diklik
        toNextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextDay();
            }
        });
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() { MainFrame.getInstance().navigateTo("REGISTER"); }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo("LOGIN");
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        NotaManager.toNextDay();
        dateLabel.setText("Hari ini:" + NotaManager.fmt.format(NotaManager.cal.getTime()));
        JOptionPane.showMessageDialog(null, "Kamu tidur hari ini... zzz...", "Next Day", JOptionPane.INFORMATION_MESSAGE);
    }
}
