package assignments.assignment3.user.menu;

import assignments.assignment3.MainMenu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import java.util.Arrays;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = true;
        if (choice == 1) {
            // Meminta masukan paket laundry dari user
            System.out.println("Masukan paket laundry:");
            System.out.println("+-------------Paket-------------+");
            System.out.println("| Express | 1 Hari | 12000 / Kg |");
            System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
            System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
            System.out.println("+-------------------------------+");
            String paket = in.nextLine();

            // Meminta masukan berat cucian dari user
            System.out.println("Masukan berat cucian anda [Kg]:");
            String tempBerat = in.nextLine();
            int berat = Integer.parseInt(tempBerat);
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }

            String  tawaran = "";
            boolean setrika = true;
            Boolean kurir   = true;

            // Menawarkan jasa setrika
            System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0\n[Ketik x untuk tidak mau]: ");
            tawaran = in.nextLine();
            if (tawaran.equals("x")) setrika = false;

            // Menawarkan jasa kurir
            System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
            tawaran = in.nextLine();
            if (tawaran.equals("x")) kurir = false;

            String tanggal = NotaManager.fmt.format(NotaManager.cal.getTime());
            Nota nota = new Nota(super.loginMember, berat, paket, tanggal);

            if (setrika) nota.addService(new SetrikaService());
            if (kurir)   nota.addService(new AntarService());

            super.loginMember.addNota(nota);
            NotaManager.addNota(nota);

            System.out.println("Nota berhasil dibuat!\n");
            logout = false;
        }

        else if (choice == 2) {
            Nota[] notaMember = super.loginMember.getNotaList();
            for (Nota nota : notaMember) System.out.println(nota);
            logout = false;
        }
        
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] tempMemberList = new Member[super.memberList.length + 1];

        System.arraycopy(super.memberList, 0, tempMemberList, 0, super.memberList.length);
        tempMemberList[super.memberList.length] = member;

        super.memberList = Arrays.copyOf(tempMemberList, tempMemberList.length);
    }
}