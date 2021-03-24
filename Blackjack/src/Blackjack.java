import java.util.*;

public class Blackjack {

    public static void main(String[] args) {
        String[] mastid = {"♦", "♣", "♥", "♠"};
        String[] väärtused = {"2", "3", "4", "5", "6", "7",
                "8", "9", "10", "J", "Q", "K", "A"};

        Kaart[] algnePakk = new Kaart[52];
        int lugeja = 0;
        for (String mast: mastid){
            for (int i = 0; i < väärtused.length; i++) {
                String kirjeldus = väärtused[i];
                if (i < 9)
                    algnePakk[lugeja] = new Kaart(i+2, kirjeldus, mast);
                else if (i == väärtused.length-1){
                    algnePakk[lugeja] = new Kaart(1, kirjeldus, mast);
                } else {
                    algnePakk[lugeja] = new Kaart(10, kirjeldus,mast);
                }
                lugeja++;
            }
        }
        List<Kaart> pakk = new ArrayList<>();
        Arrays.stream(algnePakk).spliterator().forEachRemaining(pakk::add);
        /*
        for (int i = 0; i < mastid.length; i++) {
            for (int j = 0; j < väärtused.length; j++) {
                String kirjeldus = mastid[i] + " " + väärtused[j];
                if (j < 9)
                    algnePakk[lugeja] = new Kaart(j+2, kirjeldus);
                else if (j == väärtused.length-1){
                    algnePakk[lugeja] = new Kaart(11, kirjeldus);
                } else {
                    algnePakk[lugeja] = new Kaart(10, kirjeldus);
                }
                lugeja++;
            }
        }

         */

        //kontrollisin sellega kas teeb õiged kaardid:
        //Arrays.stream(algnePakk).iterator().forEachRemaining(System.out::println);

        //for (Kaart k: pakk) System.out.println(k);

        Mängija mängija = new Mängija();
        Diiler diiler = new Diiler();
        /*
        Scanner input = new Scanner(System.in);
        System.out.print("Teretulemast mängu Blackjack! Alustamiseks sisesta panus: ");
        int panus = input.nextInt();
        System.out.println("Sinu panus on " + panus);

         */
        String jätkamine = "jah";
        while (jätkamine.equals("jah")){
            System.out.println("Alustame uue mänguga");
            segaKaardid(pakk);
            System.out.println(pakk);
            for (int i = 0; i < 2; i++) {
                Kaart mängija_kaart=pakk.get((int) (Math.random() * pakk.size()));
                mängija.lisaKaart(mängija_kaart);
                System.out.println(mängija_kaart);
                pakk.remove(mängija_kaart);                            //siin viskab erindi

                Kaart diileri_kaart=pakk.get((int) (Math.random() * pakk.size()));
                diiler.lisaKaart(pakk.get((int) (Math.random() * pakk.size())));
                if (i > 0) System.out.println(diileri_kaart);
                pakk.remove(diileri_kaart);
            }
            System.out.println("Sinu kaardid: "+ mängija.getKaardid());
            System.out.println("Diileri kaardid: "+ diiler.getKaardid());
            System.out.println(pakk);
        }





    }


    public static void segaKaardid(List<Kaart> pakk) {
        Collections.shuffle(pakk);
    }
    //test


}
