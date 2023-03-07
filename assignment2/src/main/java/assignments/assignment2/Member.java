/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;

import java.util.ArrayList;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int    bonusCounter;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
    // Setter Method
    public void setID           (String id) {this.id = id;}
    public void addBonusCounter () {
        this.bonusCounter++;
        this.bonusCounter %= 3;
    }

    // Getter Method
    public String getNama         () {return this.nama;}
    public String getID           () {return this.id;}
    public int    getBonusCounter () {return this.bonusCounter;}

    public static Member isIDExist (String id, ArrayList<Member> arrList) {
        for (Member pelanggan : arrList) {
            if (pelanggan.id.equals(id)) return pelanggan;
        }
        return null;
    }

    public String toString() {
        return String.format("Nama: %s\nNomor HP: %s\nID: %s\nBonus Counter: %d", this.nama, this.noHp, this.id, this.bonusCounter);
    }
}
