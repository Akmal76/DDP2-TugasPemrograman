/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner     input       = new Scanner(System.in);
    private static SimpleDateFormat  fmt         = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar          cal         = Calendar.getInstance();
    private static ArrayList<Nota>   notaList    = new ArrayList<Nota>();
    private static ArrayList<Member> memberList  = new ArrayList<Member>();
    private static int               countIDNota = 0;
    // private static Nota[]   notaList;
    // private static Member[] memberList;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
        input.close();
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user

        // Meminta nama dan nomor handphone dari user
        System.out.println("Masukkan nama Anda:");
        String nama    = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda:");
        String noHp    = input.nextLine();

        // Validasi nomor handphone pengguna
        while (!noHp.matches("[0-9]+")) {
            System.out.println("Field nomor hp hanya menerima digit.");
            noHp = input.nextLine();
        }

        // Generate ID
        String id = generateId(nama, noHp);

        // Cek ID dan menambahkan nota
        if (Member.isIDExist(id, memberList) == null) {
            Member memberBaru = new Member(nama, noHp);
            memberBaru.setID(id);
            memberList.add(memberBaru);  
            System.out.printf("Berhasil membuat member dengan ID %s!\n", id);  
        } else System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHp);

        // printDaftarMember(memberList.toArray());
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian

        // Meminta ID member dari user
        System.out.println("Masukan ID member:");
        String id = input.nextLine();

        // Cek ID
        Member memberTemp = Member.isIDExist(id, memberList);
        if (memberTemp == null) {
            System.out.printf("Member dengan %s tidak ditemukan!\n", id);
            return;
        }

        // Meminta paket laundry dari user
        System.out.println("Masukan paket laundry:");
        String paket = input.nextLine();

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
        String berat = input.nextLine();

        // Validasi berat cucian
        while (!berat.matches("[0-9]+")) {
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            berat = input.nextLine();
        }

        // Cek minimum berat cucian
        int beratTemp = Integer.parseInt(berat);
        if (beratTemp < 2) {
            beratTemp = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }

        // Menambahkan nota
        String tanggal = fmt.format(cal.getTime());
        Nota notaBaru = new Nota(memberTemp, paket, beratTemp, tanggal);
        notaBaru.setIDNota(countIDNota);
        countIDNota++;
        notaList.add(notaBaru);

        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]\n", notaBaru.getIDNota());
        System.out.println(Nota.generateNota(id, paket, beratTemp, tanggal, memberTemp));
        System.out.println("Status      	: Belum bisa diambil :(");
        // System.out.println("Member Count: " + memberTemp.getBonusCounter());
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem

        // Cek ketersediaan nota
        if (notaList.size() == 0) System.out.println("Terdaftar 0 nota dalam sistem.");
        else {
            System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
            for (Nota struk : notaList) {
                if (struk.getStatus()) System.out.printf("- [%d] Status      	: Sudah dapat diambil!\n", struk.getIDNota());
                else                   System.out.printf("- [%d] Status      	: Belum bisa diambil :(\n", struk.getIDNota());
            }
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem

        // Cek ketersediaan member
        if (memberList.size() == 0) System.out.println("Terdaftar 0 member dalam sistem.");
        else {
            System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
            for (Member pelanggan : memberList) System.out.printf("- %s : %s\n", pelanggan.getID(), pelanggan.getNama());
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian

        // Meminta ID Nota dari user
        System.out.println("Masukan ID nota yang akan diambil:");
        String idNota = input.nextLine();

        // Validasi format ID Nota
        while (!idNota.matches("[0-9]+")) {
            System.out.println("ID nota berbentuk angka!");
            idNota = input.nextLine();
        }

        // Validasi keberadaan ID Nota
        Nota tempNota = Nota.isIDNotaExist(idNota, notaList);
        if (tempNota != null) {
            if (tempNota.getStatus()) {
                for (int i = 0; i < notaList.size(); i++) {
                    if (notaList.get(i) == tempNota) {
                        notaList.remove(i);
                        break;
                    }
                }
                System.out.printf("Nota dengan ID %d berhasil diambil!\n", tempNota.getIDNota());
            } else System.out.printf("Nota dengan ID %d gagal diambil!\n", tempNota.getIDNota());
        } else System.out.printf("Nota dengan ID %s tidak ditemukan!\n", idNota);
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari

        // Menambahkan hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1);
        
        // Cek semua laundry
        String tanggal = fmt.format(cal.getTime());
        for (Nota i : notaList) {
            i.setStatus(tanggal);
            if (i.getStatus()) System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", i.getIDNota());
        }

        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    // Debug Method
    private static void printDaftarMember(ArrayList<Member> arrList) {
        System.out.println("----------------DEBUG----------------");
        for (Member i : arrList) {
            System.out.println(i);
            System.out.println("----------------------------------");
        }
    }

    private static void printDaftarNota(ArrayList<Nota> arrList) {
        System.out.println("----------------DEBUG----------------");
        for (Nota i : arrList) {
            System.out.println(i);
            System.out.println("----------------------------------");
        }
    }
}
