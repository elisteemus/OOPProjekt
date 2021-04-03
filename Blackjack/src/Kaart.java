public class Kaart {

    private int väärtus;                //kaardi punktide arv
    private final String kirjeldus;     //kaardi tüüp
    private final String mast;          //kaardi mast

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

    @Override
    public String toString() {
        return mast + kirjeldus;
    }
}
