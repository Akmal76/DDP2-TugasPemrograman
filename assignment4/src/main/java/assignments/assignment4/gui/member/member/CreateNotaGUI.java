/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(45, 30, 45, 30));

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
        // Membuat panel sementara
        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(1, 0, 1, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 10;

        // Membuat label paket
        paketLabel = new JLabel("Paket Laundry:");
        c.gridy = 0;
        tempPanel.add(paketLabel, c);

        // Membuat label berat
        beratLabel = new JLabel("Berat Cucian (Kg):");
        c.gridy = 1;
        tempPanel.add(beratLabel, c);

        // Membuat check box setrika
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / kg)");
        c.gridy = 2;
        tempPanel.add(setrikaCheckBox, c);

        // Membuat check box antar
        antarCheckBox = new JCheckBox("Tambah Antar Setrika (2000 / 4 kg pertama, kemudian 500 / kg)");
        c.gridy = 3;
        tempPanel.add(antarCheckBox, c);

        // Membuat combobox paket
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        c.gridx = 1;
        c.gridy = 0;
        tempPanel.add(paketComboBox, c);

        // Membuat kolom teks berat
        beratTextField = new JTextField();
        c.gridy = 1;
        tempPanel.add(beratTextField, c);

        // Membuat tombol show paket
        showPaketButton = new JButton("Show Paket");
        c.gridx = 2;
        c.gridy = 0;
        tempPanel.add(showPaketButton, c);

        // Membuat tombol buat nota
        createNotaButton = new JButton("Buat Nota");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.insets = new Insets(5, 0, 5, 0);
        tempPanel.add(createNotaButton, c);

        // Membuat tombol kembali
        backButton = new JButton("Kembali");
        c.gridy = 5;
        tempPanel.add(backButton, c);

        // Masukkan panel sementara ke panel utama
        mainPanel.add(tempPanel);

        // Event ketika tombol show paket diklik
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });

        // Event ketika tombol buat nota diklik
        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
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
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODOne
        // Validasi berat laundry
        String tempBerat = beratTextField.getText();
        if (!tempBerat.matches("[0-9]+") || tempBerat.equals("0")) {
            JOptionPane.showMessageDialog(this, "Berat Cucian harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);

            // Kosongkan properti yang digunakan
            beratTextField.setText("");
            paketComboBox.setSelectedIndex(0);
            setrikaCheckBox.setSelected(false);
            antarCheckBox.setSelected(false);
            return;
        }
        int berat = Integer.parseInt(tempBerat);
        if (berat < 2) {
            berat = 2;
            JOptionPane.showMessageDialog(this, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        String paket   = paketComboBox.getSelectedItem().toString();
        String tanggal = this.fmt.format(this.cal.getTime());
        Nota nota      = new Nota(this.memberSystemGUI.getLoggedInMember(), berat, paket, tanggal);

        // Handle setrika dan antar laundry
        if (setrikaCheckBox.isSelected()) nota.addService(new SetrikaService());
        if (antarCheckBox.isSelected())   nota.addService(new AntarService());

        this.memberSystemGUI.getLoggedInMember().addNota(nota);
        NotaManager.addNota(nota);
        JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Kosongkan properti yang digunakan
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODOne
        MainFrame.getInstance().navigateTo("MEMBER");

        // Kosongkan properti yang digunakan
        beratTextField.setText("");
        paketComboBox.setSelectedIndex(0);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }
}
