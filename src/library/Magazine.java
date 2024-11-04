package library;

public class Magazine extends Item {

    private int vol;

    public Magazine(int vol, String title, int number, int year) {
        super(title, number, year);
        this.vol = vol;
    }

    public int getVol() {
        return vol;
    }
}
