import java.util.ArrayList;
import java.util.List;

public class Diiler {
    private List<Kaart> kaardid;
    private int ässasid;

    public Diiler() {
        kaardid=new ArrayList<>();
    }

    public int kaartideSumma() {
        int summa = 0;
        boolean juurde11 = false;
        for (Kaart k: kaardid) {
            if(summa<=10 && ässasid==1&& k.getKirjeldus().equals("A")){
                summa+=11;
                juurde11 = true;
            } else summa += k.getVäärtus();
        }
        if (juurde11 && summa>21) summa -=10;
        return summa;
    }

    public boolean kasAlla17() {return kaartideSumma()<17;}

    public boolean kasÜle() {
        return kaartideSumma() > 21;
    }

    public boolean kas21() {return  kaartideSumma()==21;}

    public List<Kaart> getKaardid() {
        return kaardid;
    }

    public void lisaKaart(Kaart kaart) {
        kaardid.add(kaart);
        if (kaart.getKirjeldus().equals("A")) this.ässasid++;
    }
}
