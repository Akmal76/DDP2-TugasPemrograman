/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;

import assignments.assignment1.NotaGenerator;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private int     idNota;
    private String  paket;
    private Member  member;
    private int     berat;
    private String  tanggalMasuk;
    private boolean isReady;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.member       = member;
        this.paket        = paket;
        this.berat        = berat;
        this.tanggalMasuk = tanggalMasuk;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini

    // Setter method
    public void setIDNota(int idNota) {this.idNota = idNota;}

    public void setStatus(String tanggalSekarang) {
        // if (tanggalSekarang.equals(this.tanggalMasuk)) this.isReady = true;
        // else this.isReady = false;
        LocalDate date = LocalDate.parse(this.tanggalMasuk, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String paket = this.paket;

        if      (paket.toLowerCase().equals("express")) date = date.plusDays(1);
        else if (paket.toLowerCase().equals("fast"))    date = date.plusDays(2);
        else if (paket.toLowerCase().equals("reguler")) date = date.plusDays(3);

        String tempTanggal = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (tempTanggal.equals(tanggalSekarang) && !this.isReady) this.isReady = true;
        else this.isReady = false;
    }

    // Getter method
    public int getIDNota()     {return this.idNota;}
    public boolean getStatus() {return this.isReady;}

    public static Nota isIDNotaExist(String idNota, ArrayList<Nota> arrList) {
        for (Nota i : arrList) {
            int tempIDNota = Integer.parseInt(idNota);
            if (tempIDNota == i.idNota) return i;
        }
        return null;
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima, Member member){
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

        String hargaFinal = berat + " kg x " + hargaSatuan + " = " + harga;
        if (member.getBonusCounter() == 2) {
            harga /= 2;
            hargaFinal += " = " + harga + " (Discount member 50%!!!)";
        }
        member.addBonusCounter();

        return "ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + hargaFinal + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
    }

    public String toString() {
        return "ID Nota: " + this.idNota + "\nPaket: " + this.paket + "\nMember: " + this.member +
               "\nBerat: " + this.berat + "\nTanggal Masuk: " + this.tanggalMasuk + "\nIs Ready: " + this.isReady;
    }
}
