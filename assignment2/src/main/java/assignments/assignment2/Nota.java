/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Nota {
    private int     idNota;
    private String  paket;
    private Member  member;
    private int     berat;
    private String  tanggalMasuk;
    private int     sisaHariPengerjaan;
    private boolean isReady;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member       = member;
        this.paket        = paket;
        this.tanggalMasuk = tanggalMasuk;
    }

    // Method Setter
    public void setIDNota(int idNota)                 {this.idNota = idNota;}
    public void setSisaHariPengerjaan(int jumlahHari) {this.sisaHariPengerjaan = jumlahHari;}
    public void setIsReady()                          {this.isReady = true;}

    // Method Getter
    public int getIDNota()              {return this.idNota;}
    public int getSisaHariPengerjaan () {return this.sisaHariPengerjaan;}
    public boolean getIsReady()         {return this.isReady;}

    // Method untuk generateNota
    public String generateNota(String id, String paket, int berat, String tanggalTerima, Member member){
        String hargaSatuan    = "";
        int harga             = 0;

        LocalDate date = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Menentukan harga dan lama laundry
        if (paket.toLowerCase().equals("express")) {
            hargaSatuan = "12000";
            harga      += berat * 12000;
            date        = date.plusDays(1);
            this.sisaHariPengerjaan = 1;
        } else if (paket.toLowerCase().equals("fast")) {
            hargaSatuan = "10000";
            harga      += berat * 10000;
            date        = date.plusDays(2);
            this.sisaHariPengerjaan = 2;
        } else if (paket.toLowerCase().equals("reguler")) {
            hargaSatuan = "7000";
            harga      += berat * 7000;
            date        = date.plusDays(3);
            this.sisaHariPengerjaan = 3;
        }

        String tanggalSelesai = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String hargaFinal = berat + " kg x " + hargaSatuan + " = " + harga;

        // Cek diskon untuk member
        if (member.getBonusCounter() == 2) hargaFinal += " = " + harga/2 + " (Discount member 50%!!!)";
        member.addBonusCounter();

        return "ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + hargaFinal + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
    }
    
    // Method cek apakah notaID sudah ada yang punya (Getter Nota by notaID)
    public static Nota isExist(String idNota, Nota[] notaList) {
        for (Nota nota : notaList) {
            int tempIDNota = Integer.parseInt(idNota);
            if (nota.idNota == tempIDNota) return nota;
        }   
        return null;
    }
}