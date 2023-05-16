/*
 * Akmal Ramadhan - 2206081534
 * DDP 2 - TP 04 GUI, Event-driven programming
 * 2022/2023 Genap
 * CuciCuci IV: Goodbye, Dek Depe!
 */

package assignments.assignment4.gui.member;

public interface Loginable {
    boolean login(String id, String password);
    void logout();
    String getPageName();
}
