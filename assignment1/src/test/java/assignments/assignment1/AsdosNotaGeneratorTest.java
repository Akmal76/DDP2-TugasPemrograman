package assignments.assignment1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AsdosNotaGeneratorTest {

    @Test
    public void testGenerateId1() {
        assertEquals("A-1-09", NotaGenerator.generateId("a d d p", "1"));
    }

    @Test
    public void testGenerateId2() {
        assertEquals("A-010000000000000000000-09", NotaGenerator.generateId("a dprog", "010000000000000000000"));
    }

    @Test
    public void testGenerateId3() {
        assertEquals("3%#?-098283849028300234120-07", NotaGenerator.generateId("3%#? ddp", "098283849028300234120"));
    }

    @Test
    public void testGenerateId4() {
        assertEquals("@DD--9223372036854775807-17", NotaGenerator.generateId("@dd- p", "9223372036854775807"));
    }

    @Test
    public void testGenerateId5() {
        assertEquals("DASAR-29993-82", NotaGenerator.generateId("DaSaR DaSaR PemroGraman", "29993"));
    }

    @Test
    public void testGenerateId6() {
        assertEquals("000-00000001-08", NotaGenerator.generateId("000 dor", "00000001"));
    }

    @Test
    public void testGenerateId7() {
        assertEquals("AA-000000000-09", NotaGenerator.generateId("AA bandung", "000000000"));
    }

    @Test
    public void testGenerateId8() {
        assertEquals("XXXXYYZZ-2134004312-25", NotaGenerator.generateId("XxxXyYzz AABBccD", "2134004312"));
    }

    @Test
    public void testGenerateId9() {
        assertEquals("F@5ILK0M-0987623-05", NotaGenerator.generateId("f@5ilk0m uw1w", "0987623"));
    }

    @Test
    public void testGenerateId10() {
        assertEquals("&%#@0RG-990090990909-23", NotaGenerator.generateId("&%#@0RG", "990090990909"));
    }

    @Test
    public void testGenerateId11() {
        assertEquals("0-00100000000000-08", NotaGenerator.generateId("0 0 0 0 0 0 0 0", "00100000000000"));
    }

    @Test
    public void testGenerateId12() {
        assertEquals("B-0000000000000-09", NotaGenerator.generateId("b j habibie", "0000000000000"));
    }

    @Test
    public void testGenerateId13() {
        assertEquals("REGII-445020893345601-09", NotaGenerator.generateId("Regii", "445020893345601"));
    }

    @Test
    public void testGenerateId14() {
        assertEquals("CYN-1235678900000000111111122222222-13",
                NotaGenerator.generateId("Cyn thia Klopper", "1235678900000000111111122222222"));
    }

    @Test
    public void testGenerateId15() {
        assertEquals("THOMAS-0835732526-24", NotaGenerator.generateId("Thomas Shelby", "0835732526"));
    }

    @Test
    public void testGenerateId16() {
        assertEquals("V3NT3@-99999999-48", NotaGenerator.generateId("v3Nt3@ #H3 84RD", "99999999"));
    }

    @Test
    public void testGenerateId17() {
        assertEquals("AA-000-09", NotaGenerator.generateId("aa na nah", "000"));
    }
    
    @Test
    public void testGenerateId18() {
        assertEquals("WE-0765342345678-95", NotaGenerator.generateId("We Bare Bears", "0765342345678"));
    }

    @Test
    public void testGenerateId19() {
        assertEquals("/];[;';P']-12345667821232-38", NotaGenerator.generateId("/];[;';p'] Test Nama", "12345667821232"));
    }

    @Test
    public void testGenerateNota1() {
        String solution = "ID    : LUKE-08125325233-90\n" + "Paket : reguler\n" + "Harga :\n" + "2 kg x 7000 = 14000\n" + "Tanggal Terima  : 15/02/2023\n" + "Tanggal Selesai : 18/02/2023";
        assertEquals(solution, NotaGenerator.generateNota("LUKE-08125325233-90", "reguler", 2, "15/02/2023"));
    }

    @Test
    public void testGenerateNota2() {
        String solution = "ID    : DASAR-29993-82\n" + "Paket : reguler\n" + "Harga :\n" + "2 kg x 7000 = 14000\n" + "Tanggal Terima  : 31/01/2023\n" + "Tanggal Selesai : 03/02/2023";
        assertEquals(solution, NotaGenerator.generateNota("DASAR-29993-82", "reguler", 2, "31/01/2023"));
    }

    @Test
    public void testGenerateNota3() {
        String solution = "ID    : THOMAS-0835732526-24\n" + "Paket : fast\n" + "Harga :\n" + "10 kg x 10000 = 100000\n" + "Tanggal Terima  : 13/02/2023\n" + "Tanggal Selesai : 15/02/2023";
        assertEquals(solution, NotaGenerator.generateNota("THOMAS-0835732526-24", "fast", 10, "13/02/2023"));
    }

    @Test
    public void testGenerateNota4() {
        String solution = "ID    : CYN-1235678900000000111111122222222-13\n" + "Paket : fast\n" + "Harga :\n" + "8 kg x 10000 = 80000\n" + "Tanggal Terima  : 28/02/2023\n" + "Tanggal Selesai : 02/03/2023";
        assertEquals(solution, NotaGenerator.generateNota("CYN-1235678900000000111111122222222-13", "fast", 8, "28/02/2023"));
    }

    @Test
    public void testGenerateNota5() {
        String solution = "ID    : B-0000000000000-09\n" + "Paket : fast\n" + "Harga :\n" + "2 kg x 10000 = 20000\n" + "Tanggal Terima  : 14/12/2022\n" + "Tanggal Selesai : 16/12/2022";
        assertEquals(solution, NotaGenerator.generateNota("B-0000000000000-09", "fast", 2, "14/12/2022"));
    }

    @Test
    public void testGenerateNota6() {
        String solution = "ID    : @DD--9223372036854775807-17\n" + "Paket : fast\n" + "Harga :\n" + "7 kg x 10000 = 70000\n" + "Tanggal Terima  : 19/11/2022\n" + "Tanggal Selesai : 21/11/2022";
        assertEquals(solution, NotaGenerator.generateNota("@DD--9223372036854775807-17", "fast", 7, "19/11/2022"));
    }

    @Test
    public void testGenerateNota7() {
        String solution = "ID    : CIP-082181914778-91\n" + "Paket : Express\n" + "Harga :\n" + "10 kg x 12000 = 120000\n" + "Tanggal Terima  : 10/06/2023\n" + "Tanggal Selesai : 11/06/2023";
        assertEquals(solution, NotaGenerator.generateNota("CIP-082181914778-91", "Express", 10, "10/06/2023"));
    }

    @Test
    public void testGenerateNota8() {
        String solution = "ID    : ANDRE-888-73\n" + "Paket : express\n" + "Harga :\n" + "17 kg x 12000 = 204000\n" + "Tanggal Terima  : 10/10/2020\n" + "Tanggal Selesai : 11/10/2020";
        assertEquals(solution, NotaGenerator.generateNota("ANDRE-888-73", "express", 17, "10/10/2020"));
    }

    @Test
    public void testGenerateNota9() {
        String solution = "ID    : BEBEK-000-32\n" + "Paket : EXPRESS\n" + "Harga :\n" + "2 kg x 12000 = 24000\n" + "Tanggal Terima  : 28/02/2023\n" + "Tanggal Selesai : 01/03/2023";
        assertEquals(solution, NotaGenerator.generateNota("BEBEK-000-32", "EXPRESS", 2, "28/02/2023"));
    }

    @Test
    public void testGenerateNota10() {
        String solution = "ID    : HAMID-081345673249-94\n" + "Paket : eXpResS\n" + "Harga :\n" + "22 kg x 12000 = 264000\n" + "Tanggal Terima  : 31/03/2023\n" + "Tanggal Selesai : 01/04/2023";
        assertEquals(solution, NotaGenerator.generateNota("HAMID-081345673249-94", "eXpResS", 22, "31/03/2023"));
    }

    @Test
    public void testGenerateNota11() {
        String solution = "ID    : V3NT3@-99999999-48\n" + "Paket : ReGUleR\n" + "Harga :\n" + "2 kg x 7000 = 14000\n" + "Tanggal Terima  : 29/02/2024\n" + "Tanggal Selesai : 03/03/2024";
        assertEquals(solution, NotaGenerator.generateNota("V3NT3@-99999999-48", "ReGUleR", 2, "29/02/2024"));
    }

    @Test
    public void testGenerateNota12() {
        String solution = "ID    : AA-000-09\n" + "Paket : REGULER\n" + "Harga :\n" + "20 kg x 7000 = 140000\n" + "Tanggal Terima  : 28/02/2024\n" + "Tanggal Selesai : 02/03/2024";
        assertEquals(solution, NotaGenerator.generateNota("AA-000-09", "REGULER", 20, "28/02/2024"));
    }

}
