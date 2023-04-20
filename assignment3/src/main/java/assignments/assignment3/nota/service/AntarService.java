/*
* DDP  - TP 03 Inheritance, Abstract Classes, and Interfaces
* 2022/2023 Genap
* CuciCuci III: CuciCuci Cuci
*/

package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean isWorkDone = false;

    @Override
    public String doWork() {
        isWorkDone = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        return isWorkDone;
    }

    @Override
    public long getHarga(int berat) {
        if (berat < 4) return 2000;
        return berat * 500;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
