/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Arrays;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner    input         = new Scanner(System.in);
    private static SimpleDateFormat fmt           = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar         cal           = Calendar.getInstance();
    private static Nota[]           notaList      = new Nota[0];
    private static Member[]         memberList    = new Member[0];
    private static int              idNotaCounter = 0;

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
                default  -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
        input.close();
    }

    // Method untuk membuat member baru
    private static void handleGenerateUser() {
        // Meminta nama dan nomor telepon
        System.out.println("Masukan nama Anda:");
        String nama = input.nextLine();
        System.out.println("Masukan nomor handphone Anda:");
        String noHp = input.nextLine();

        // Validasi nomor telepon
        while (!noHp.matches("[0-9]+")) {
            System.out.println("Field nomor hp hanya menerima digit.");
            noHp = input.nextLine();
        }

        // Generate ID (menggunakan method dari TP01)
        String id = generateId(nama, noHp);

        // Cek ketersediaan ID
        if (Member.isExist(id, memberList) == null) {
            Member memberBaru = new Member(nama, noHp);
            memberBaru.setID(id);
            addMemberList(memberBaru);
            System.out.printf("Berhasil membuat member dengan ID %s!\n", id);  
        } else System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, noHp);
    }

    private static void handleGenerateNota() {
        // Meminta ID member dari user
        System.out.println("Masukan ID member:");
        String id = input.nextLine();

        // Cek ID
        Member tempMember = Member.isExist(id, memberList);
        if (tempMember == null) {
            System.out.printf("Member dengan %s tidak ditemukan!\n", id);
            return;
        }

        // Meminta paket laundry dari user
        System.out.println("Masukan paket laundry:");
        String paket = input.nextLine();

        // Validasi paket laundry1
        while (!paket.toLowerCase().equals("express") &&
               !paket.toLowerCase().equals("fast") &&
               !paket.toLowerCase().equals("reguler")
              ) {
                    if (paket.equals("?")) showPaket();
                    else System.out.printf("Paket %s tidak diketahui\n[ketik ? untuk mencari tahu jenis paket]\n", paket);
                    System.out.println("Masukkan paket laundry:");
                    paket = input.nextLine();
                }
        
        // Meminta berat cucian dari user
        System.out.println("Masukkan berat cucian Anda [Kg]:");
        String berat = input.nextLine();

        // Validasi berat cucian
        while (!berat.matches("[0-9]+") || berat.equals("0")) {
            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
            berat = input.nextLine();
        }

        // Cek minimum berat cucian
        int tempBerat = Integer.parseInt(berat);
        if (tempBerat < 2) {
            tempBerat = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }

        // Menambahkan nota
        String tanggalSekarang = fmt.format(cal.getTime());
        Nota   notaBaru        = new Nota(tempMember, paket, tempBerat, tanggalSekarang);
        notaBaru.setIDNota(idNotaCounter++);
        addNotaList(notaBaru);

        // Mengeluarkan output berhasil
        System.out.println("Berhasil menambahkan nota!");
        System.out.printf("[ID Nota = %d]\n", notaBaru.getIDNota());
        System.out.println(notaBaru.generateNota(id, paket, tempBerat, tanggalSekarang, tempMember));
        System.out.println("Status      	: Belum bisa diambil :(");
    }

    // Method untuk mengeluarkan list nota
    private static void handleListNota() {
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.length);
        if (notaList.length > 0) {
            for (Nota nota : notaList) {
                if (nota.getIsReady()) System.out.printf("- [%d] Status      	: Sudah dapat diambil!\n", nota.getIDNota());
                else                   System.out.printf("- [%d] Status      	: Belum bisa diambil :(\n", nota.getIDNota());
            }
        }
    }

    // Method untuk mengeluarkan list member
    private static void handleListUser() {
        System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.length);
        if (memberList.length > 0) for (Member member : memberList) System.out.printf("- %s : %s\n", member.getID(), member.getNama());
    }

    // Method untuk ambil cucian
    private static void handleAmbilCucian() {
        // Meminta ID Nota dari user
        System.out.println("Masukan ID nota yang akan diambil:");
        String idNota = input.nextLine();

        // Validasi format ID Nota
        while (!idNota.matches("[0-9]+")) {
            System.out.println("ID nota berbentuk angka!");
            idNota = input.nextLine();
        }

        // Cek keberadaan ID Nota
        Nota tempNota = Nota.isExist(idNota, notaList);
        if (tempNota != null) {
            if (tempNota.getIsReady()) {
                for (int i = 0; i < notaList.length; i++) {
                    if (notaList[i] == tempNota) {
                        deleteNotaList(tempNota, i);
                        break;
                    }
                }
                   System.out.printf("Nota dengan ID %d berhasil diambil!\n", tempNota.getIDNota());
            } else System.out.printf("Nota dengan ID %d gagal diambil!\n", tempNota.getIDNota());
        }     else System.out.printf("Nota dengan ID %s tidak ditemukan!\n", idNota);
    }

    private static void handleNextDay() {
        // Menambahkan hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1);

        // Cek semua laundry
        for (Nota nota : notaList) {
            nota.setSisaHariPengerjaan(nota.getSisaHariPengerjaan() - 1);
            if (nota.getSisaHariPengerjaan() == 0) nota.setIsReady();
            if (nota.getIsReady()) System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", nota.getIDNota());
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

    // Method untuk menambahkan member dalam memberList
    private static void addMemberList(Member member) {
        Member[] tempMemberList = new Member[memberList.length + 1];

        System.arraycopy(memberList, 0, tempMemberList, 0, memberList.length);
        tempMemberList[memberList.length] = member;

        memberList = Arrays.copyOf(tempMemberList, tempMemberList.length);
    }

    // Method untuk menambahkan member dalam notaList
    private static void addNotaList(Nota nota) {
        Nota[] tempNotaList = new Nota[notaList.length + 1];

        System.arraycopy(notaList, 0, tempNotaList, 0, notaList.length);
        tempNotaList[notaList.length] = nota;

        notaList = Arrays.copyOf(tempNotaList, tempNotaList.length);
    }

    // Method untuk menghapud member dalam notaList
    private static void deleteNotaList(Nota nota, int index) {
        Nota[] tempNotaList = new Nota[notaList.length - 1];

        System.arraycopy(notaList, 0, tempNotaList, 0, index);
        System.arraycopy(notaList, index + 1, tempNotaList, index, notaList.length - index - 1);

        notaList = Arrays.copyOf(tempNotaList, tempNotaList.length);
    }
}