package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean isWorkDone = false;

    @Override
    public String doWork() {
        isWorkDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return isWorkDone;
    }

    @Override
    public long getHarga(int berat) {
        return 1000 * berat;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
