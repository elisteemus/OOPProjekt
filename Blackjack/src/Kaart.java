
public class Kaart {

    private int väärtus;
    private final String kirjeldus;
    private final String mast;

    public Kaart(int väärtus, String kirjeldus, String mast) {
        this.väärtus = väärtus;
        this.kirjeldus = kirjeldus;
        this.mast = mast;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public int getVäärtus() {
        return väärtus;
    }

    public String getMast(){
        return mast;
    }

    public void setVäärtus(int väärtus) {
        this.väärtus = väärtus;
    }

    @Override
    public String toString() {
        return mast + kirjeldus;
    }

}