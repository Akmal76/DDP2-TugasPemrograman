/*
* DDP  - TP 03 Inheritance, Abstract Classes, and Interfaces
* 2022/2023 Genap
* CuciCuci III: CuciCuci Cuci
*/

package assignments.assignment3.nota;
import java.util.Arrays;
import java.time.*;
import java.time.format.DateTimeFormatter;

import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int idNota;
    private String tanggalMasuk;
    private boolean isDone;
    private boolean tepatWaktu;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member       = member;
        this.berat        = berat;
        this.paket        = paket;
        this.tanggalMasuk = tanggal;

        // Menetapkan sisa hari pengerjaan
        if      (this.paket.equalsIgnoreCase("express")) this.sisaHariPengerjaan = 1;
        else if (this.paket.equalsIgnoreCase("fast"))    this.sisaHariPengerjaan = 2;
        else if (this.paket.equalsIgnoreCase("reguler")) this.sisaHariPengerjaan = 3;

        // Menetapkan id nota
        this.idNota = totalNota;
        totalNota++;

        // Menyiapkan array services
        this.services = new LaundryService[1];
        this.services[0] = new CuciService();
    }

    // Method untuk menambahkan service ke array services
    public void addService(LaundryService service){
        LaundryService[] tempServices = new LaundryService[this.services.length + 1];

        System.arraycopy(this.services, 0, tempServices, 0, this.services.length);
        tempServices[this.services.length] = service;

        this.services = Arrays.copyOf(tempServices, tempServices.length);
    }

    // Method untuk mengerjakan satu layanan yang dilakukan oleh Employee
    public String kerjakan(){
        int counter = 1;
        for (LaundryService service : services) {
            if (!service.isDone()) {
                if (counter == services.length) {
                    this.isDone = true;
                    if (this.sisaHariPengerjaan >= 0) this.tepatWaktu = true;
                }
                return "Nota " + this.idNota + " : " + service.doWork();
            }
            counter++;
        }
        return "Nota " + this.idNota + " : Sudah selesai.";
    }

    // Method untuk mengurangi sisa hari pengerjaan
    public void toNextDay() {
        this.sisaHariPengerjaan--;
    }

    // Method untuk menghitung harga nota cucian
    public long calculateHarga(){
        if      (this.paket.equalsIgnoreCase("express")) this.baseHarga = 12000 * this.berat;
        else if (this.paket.equalsIgnoreCase("fast"))    this.baseHarga = 10000 * this.berat;
        else if (this.paket.equalsIgnoreCase("reguler")) this.baseHarga =  7000 * this.berat;

        if (this.sisaHariPengerjaan < 0 && !this.tepatWaktu) this.baseHarga += this.sisaHariPengerjaan * 2000;

        for (LaundryService service : services) this.baseHarga += service.getHarga(this.berat);

        if (this.baseHarga < 0) return 0;
        return this.baseHarga;
    }

    // Method untuk memperoleh status nota
    public String getNotaStatus(){
        if (this.isDone) return "Nota " + this.idNota + " : Sudah selesai.";
        return "Nota " + this.idNota + " : Belum selesai.";
    }

    @Override
    public String toString(){
        LocalDate date = LocalDate.parse(this.tanggalMasuk, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hargaPaket = "";

        if (this.paket.equalsIgnoreCase("express")) {
            hargaPaket = "12000";
            date       = date.plusDays(1);
        }
        else if (this.paket.equalsIgnoreCase("fast")) {
            hargaPaket = "10000";
            date       = date.plusDays(2);
        }
        else if (this.paket.equalsIgnoreCase("reguler")) {
            hargaPaket = "7000";
            date       = date.plusDays(3);
        }

        String tanggalSelesai = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String detailNota = "";
        detailNota += "[ID Nota = " + this.idNota + "]\n";
        detailNota += "ID    : " + this.member.getId() + "\n";
        detailNota += "Paket : " + this.paket + "\n";
        detailNota += "Harga :\n";

        long tempHarga = 0;
        if      (this.paket.equalsIgnoreCase("express")) tempHarga = 12000 * this.berat;
        else if (this.paket.equalsIgnoreCase("fast"))    tempHarga = 10000 * this.berat;
        else if (this.paket.equalsIgnoreCase("reguler")) tempHarga =  7000 * this.berat;
        detailNota += this.berat + " kg x " + hargaPaket + " = " + tempHarga + "\n";

        detailNota += "tanggal terima  : " + this.tanggalMasuk + "\n";
        detailNota += "tanggal selesai : " + tanggalSelesai + "\n";
        detailNota += "--- SERVICE LIST ---\n";

        for (LaundryService service : services) detailNota += "-" + service.getServiceName() + " @ Rp." + service.getHarga(this.berat) + "\n";

        this.baseHarga = calculateHarga();
        detailNota += "Harga Akhir: " + this.baseHarga;
        if (this.sisaHariPengerjaan < 0 && !this.tepatWaktu) detailNota += " Ada kompensasi keterlambatan " + -1 * this.sisaHariPengerjaan + " * 2000 hari";
        detailNota += "\n";

        return detailNota;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return this.paket;
    }

    public int getBerat() {
        return this.berat;
    }

    public String getTanggal() {
        return this.tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }
    public boolean isDone() {
        return this.isDone;
    }

    public LaundryService[] getServices(){
        return this.services;
    }
}
