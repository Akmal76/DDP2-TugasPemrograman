/*
* DDP  - TP 03 Inheritance, Abstract Classes, and Interfaces
* 2022/2023 Genap
* CuciCuci III: CuciCuci Cuci
*/

package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        String id = "";
        int indeksAkhir = nama.indexOf(" ");
        
        if (indeksAkhir != -1) nama = nama.substring(0, indeksAkhir).toUpperCase();
        else                   nama = nama.toUpperCase();
        id = nama + "-" + employeeCount;
        employeeCount++;

        return id;
    }
}
