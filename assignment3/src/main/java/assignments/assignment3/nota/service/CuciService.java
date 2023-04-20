/*
* DDP  - TP 03 Inheritance, Abstract Classes, and Interfaces
* 2022/2023 Genap
* CuciCuci III: CuciCuci Cuci
*/

package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private boolean isWorkDone = false;

    @Override
    public String doWork() {
        isWorkDone = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        return isWorkDone;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
