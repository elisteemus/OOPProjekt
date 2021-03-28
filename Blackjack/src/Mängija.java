import java.util.ArrayList;
import java.util.List;

public class Mängija {

    private List<Kaart> kaardid;
    private int ässasid;

    public Mängija() {
        kaardid = new ArrayList<>();
    }

    public int kaartideSumma() {
        boolean juurde11= false;
        int summa = 0;
        for (Kaart k : kaardid) {
            if (summa <= 10 && ässasid == 0 && k.getKirjeldus().equals("A")) {
                summa += 11;
                juurde11=true;
            } else summa += k.getVäärtus();
        }
        if(juurde11 && summa>21) summa-=10;
        return summa;
    }

    public boolean kasÜle() {
        return kaartideSumma() > 21;
    }

    public boolean kas21() {
        return kaartideSumma() == 21;
    }

    public List<Kaart> getKaardid() {
        return kaardid;
    }

    public void setKaardid(List<Kaart> kaardid) {
        this.kaardid = kaardid;
    }

    public void setÄssasid(int ässasid) {
        this.ässasid = ässasid;
    }

    public void lisaKaart(Kaart kaart) {
        kaardid.add(kaart);
        if (kaart.getKirjeldus().equals("A")) this.ässasid++;
    }

}
