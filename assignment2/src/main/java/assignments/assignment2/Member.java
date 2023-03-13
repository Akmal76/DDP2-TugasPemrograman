/*
* DDP 2 - TP 02 Objects & Classes
* 2022/2023 Genap
* CuciCuci Open Membership
*/

package assignments.assignment2;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int    bonusCounter;

    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }

    // Method Setter
    public void setID (String id) {this.id = id;}

    // Method Getter
    public String getNama         () {return this.nama;}
    public String getID           () {return this.id;}
    public int    getBonusCounter () {return this.bonusCounter;}

    // Method untuk menambahkan bonusCounter
    public void addBonusCounter() {
        this.bonusCounter++;
        this.bonusCounter %= 3;
    }

    // Method cek apakah ID sudah ada yang punya (Getter Member by ID)
    public static Member isExist(String id, Member[] memberList) {
        for (Member member : memberList) {
            if (member.id.equals(id)) return member;
        }
        return null;
    }
}