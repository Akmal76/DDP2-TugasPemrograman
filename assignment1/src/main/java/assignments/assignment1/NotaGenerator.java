/*
* DDP 2 - TP 01 Methods
* 2022/2023 Genap
* CuciCuci by DekDepe
*/

package assignments.assignment1;

// Import package yang diperlukan
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    // Method main, program utama kalian berjalan disini.
    public static void main(String[] args) {
        // TODO: Implement interface menu utama

        // Inisialisasi variabel yang diperlukan
        String pilihan   = "";
        String nama      = "";
        String nomorHP   = "";
        String id        = "";
        String tanggal   = "";
        String paket     = "";
        String berat     = "";

        // Meminta pilihan menu dari user
        printMenu();
        System.out.printf("Pilihan : ");
        pilihan   = input.nextLine();
        System.out.println("================================");

        while (!pilihan.equals("0")) {
            // Validasi pilihan
            while (!pilihan.equals("0") && !pilihan.equals("1") && !pilihan.equals("2")) {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                printMenu();
                System.out.printf("Pilihan : ");
                pilihan   = input.nextLine();
                System.out.println("================================");
            }
            if (pilihan.equals("0")) break;

            // Meminta nama dan nomor handphone dari user
            System.out.println("Masukkan nama Anda:");
            nama    = input.nextLine();
            System.out.println("Masukkan nomor handphone Anda:");
            nomorHP = input.nextLine();

            // Validasi nomor handphone pengguna
            while (!nomorHP.matches("[0-9]+")) {
                System.out.println("Nomor hp hanya menerima digit");
                nomorHP = input.nextLine();
            }

            // Menjalankan perintah menu
            if (pilihan.equals("1")) {
                id = generateId(nama, nomorHP);
                System.out.printf("ID Anda : %s\n", id);

            } else if (pilihan.equals("2")) {
                // Meminta tanggal terima, paket laundry, dan berat cucian dari user
                System.out.println("Masukkan tanggal terima:");
                tanggal = input.nextLine();

                System.out.println("Masukkan paket laundry:");
                paket = input.nextLine();

                // Validasi paket laundry
                if (paket.equals("?")) showPaket();
                while (!paket.toLowerCase().equals("express") &&
                    !paket.toLowerCase().equals("fast") &&
                    !paket.toLowerCase().equals("reguler")
                    ) {
                        System.out.println("Paket hemat tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry:");
                        paket = input.nextLine();
                        if (paket.equals("?")) showPaket();
                    }

                System.out.println("Masukkan berat cucian Anda [Kg]:");
                berat = input.nextLine();

                // Validasi berat cucian
                while (!berat.matches("[0-9]+")) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    berat = input.nextLine();
                }

                int beratTemp = Integer.parseInt(berat);
                if (beratTemp < 2) {
                    beratTemp = 2;
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                }

                // Mengeluarkan nota
                System.out.println("Nota Laundry");
                System.out.println(generateNota(generateId(nama, nomorHP), paket, beratTemp, tanggal));
            }

            // Meminta pilihan menu dari user
            printMenu();
            System.out.printf("Pilihan : ");
            pilihan   = input.nextLine();
            System.out.println("================================");
        }

        // Mengucapkan terima kasih kepada user
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
        input.close();
    }

    // Method untuk menampilkan menu di NotaGenerator.
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    // Method untuk menampilkan paket.
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
    * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
    *
    * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
    */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        String id = "";

        // Bila ada spasi pada nama, cukup ambil kata pertamanya saja
        int indeksAkhir = nama.indexOf(" ");
        if (indeksAkhir != -1) nama = nama.substring(0, indeksAkhir).toUpperCase();
        else                   nama = nama.toUpperCase();
        id = nama + "-" + nomorHP;

        // CheckSum !
        int checkSum = 0;
        for (int i = 0; i < id.length(); i++) {
            if      ('A' <= id.charAt(i) && id.charAt(i) <= 'Z') checkSum += id.charAt(i) - 64;
            else if ('0' <= id.charAt(i) && id.charAt(i) <= '9') checkSum += id.charAt(i) - 48;
            else                                                 checkSum += 7;
        }
        checkSum %= 100;
        if      (checkSum > 99) { id += "-" + Integer.toString(checkSum % 100); }
        else if (checkSum < 10) { id += "-0" + Integer.toString(checkSum); }
        else                    { id += "-" + Integer.toString(checkSum);       }

        return id;
    }

    /**
     *
    * Method untuk membuat Nota.
    * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
    *
    * @return string nota dengan format di bawah:
    *         <p>ID    : [id]
    *         <p>Paket : [paket]
    *         <p>Harga :
    *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
    *         <p>Tanggal Terima  : [tanggalTerima]
    *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
    */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        String hargaSatuan    = "";
        int harga             = 0;

        LocalDate date = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Menentukan harga dan lama laundry
        if (paket.toLowerCase().equals("express")) {
            hargaSatuan = "12000";
            harga      += berat * 12000;
            date        = date.plusDays(1);
        } else if (paket.toLowerCase().equals("fast")) {
            hargaSatuan = "10000";
            harga      += berat * 10000;
            date        = date.plusDays(2);
        } else if (paket.toLowerCase().equals("reguler")) {
            hargaSatuan = "7000";
            harga      += berat * 7000;
            date        = date.plusDays(3);
        }

        String tanggalSelesai = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return "ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + berat + " kg x " + hargaSatuan + " = " +
            harga + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
    }
}