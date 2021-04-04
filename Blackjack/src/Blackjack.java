import java.util.*;

public class Blackjack {

    public static void main(String[] args) {

        String[] mastid = {"♦", "♣", "♥", "♠"};                         //etteantud mastid ja kirjeldused
        String[] väärtused = {"A", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "J", "Q", "K"};

        Kaart[] algnePakk = new Kaart[52];
        int lugeja = 0;
        for (String mast : mastid) {                        //luuakse algne pakk segamata kujul
            for (int i = 0; i < väärtused.length; i++) {
                String kirjeldus = väärtused[i];
                if (i < 9)
                    algnePakk[lugeja] = new Kaart(i + 1, kirjeldus, mast);
                else {
                    algnePakk[lugeja] = new Kaart(10, kirjeldus, mast);
                }
                lugeja++;
            }
        }

        double raha = 200;
        Scanner input = new Scanner(System.in);
        System.out.println("Teretulemast mängu Blackjack! ");

        while (raha > 0) {                      //mäng kestab senikaua, kuni raha otsa saab
            Mängija mängija = new Mängija();
            Diiler diiler = new Diiler();

            System.out.print("Sul on " + raha + "€. Jätkamiseks sisesta panus: ");
            double panus = input.nextDouble();
            while (panus > raha) {
                System.out.println("Sul pole nii palju raha. Palun sisesta väiksem panus!");
                System.out.print("Sul on " + raha + "€. Jätkamiseks sisesta panus: ");
                panus = input.nextDouble();
            }
            raha -= panus;                                                          //panus võetakse rahast ära enne raundi algust
            System.out.println("Sinu panus on " + panus + ". Alustame roundiga.");
            System.out.println("=================================================================================");

            List<Kaart> pakk = new ArrayList<>();                                   //pakk, millega mäng käib
            Arrays.stream(algnePakk).spliterator().forEachRemaining(pakk::add);
            Collections.shuffle(pakk);

            for (int i = 0; i < 2; i++) {                                           //mängijale ja diilerile jagatakse kaks kaarti
                Kaart mängijaKaart = pakk.get((int) (Math.random() * pakk.size()));
                mängija.lisaKaart(mängijaKaart);
                pakk.remove(mängijaKaart);

                Kaart diileriKaart = pakk.get((int) (Math.random() * pakk.size()));
                diiler.lisaKaart(diileriKaart);
                pakk.remove(diileriKaart);
            }

            if (mängija.kas21()) {                   //vaadatakse juba alguses, kas 21 mängijal olemas
                if (diiler.kas21()) {
                    System.out.println("Sinu kaardid: " + mängija.getKaardid());
                    System.out.println("Diileri kaardid: " + diiler.getKaardid());
                    System.out.println("Push! Sinul ja diileril mõlemal blackjack. Saad " + panus + "€ tagasi.");
                    System.out.println("=================================================================================");
                    raha += panus;
                } else {
                    System.out.println("Sinu kaardid: " + mängija.getKaardid());
                    System.out.println("Diileri kaardid: " + diiler.getKaardid());
                    System.out.println("Blackjack! Võidad " + panus * 3 + "€.");
                    System.out.println("=================================================================================");
                    raha += 3 * panus;
                }
            } else {
                boolean algus = true;    //true korral on tegemist olukorraga pärast kaartide jagamist, false korral on vähemalt üks kaart juurde lisatud
                boolean dd = false;      //true korral käib double down
                while (true) {
                    System.out.println("Sinu kaardid: " + mängija.getKaardid());
                    System.out.println("Sinu kaartide summa: " + mängija.kaartideSumma());
                    System.out.println("Diileri kaardid: " + diiler.getKaardid().get(0) + ", (peidetud)");
                    System.out.println("---------------------------------------------------------------------------------");

                    if (mängija.kasÜle()) {         //üle 21 korral raund kaotatakse
                        System.out.println("Bust! Kaotad " + panus + "€.");
                        System.out.println("=================================================================================");
                        break;
                    }

                    if (mängija.kas21()) break;     //21 kättesaamisel läheb kontrollimiseks

                    if (algus && panus <= raha)
                        System.out.print("Sinu valikud: '2' (double down), '1' (hit), '0' (stand): ");
                    else System.out.print("Sinu valikud: '1' (hit), '0' (stand): ");
                    int valik = input.nextInt();

                    if (valik == 1) {           //läheb üks kaart juurde
                        Kaart mängijaKaart = pakk.get((int) (Math.random() * pakk.size()));
                        mängija.lisaKaart(mängijaKaart);
                        pakk.remove(mängijaKaart);
                        algus = false;
                    }

                    if (valik == 0) break;      //läheb kontrollimiseks

                    if (valik == 2 && algus && panus <= raha) {     //läheb double down
                        dd = true;
                        Kaart mängijaKaart = pakk.get((int) (Math.random() * pakk.size()));
                        mängija.lisaKaart(mängijaKaart);
                        pakk.remove(mängijaKaart);
                        raha -= panus;          //topeltpanus
                        panus *= 2;
                        System.out.println("Sinu kaardid: " + mängija.getKaardid());
                        System.out.println("Diileri kaardid: " + diiler.getKaardid());
                        if (mängija.kasÜle()) {
                            System.out.println("Bust! Kaotad " + panus + "€.");
                            System.out.println("=================================================================================");
                        }
                        break;
                    }

                }
                if (!mängija.kasÜle()) {
                    if (!dd) {              //vastasel juhul prindiks double downi korral järgnevad read kaks korda välja
                        System.out.println("Sinu kaardid: " + mängija.getKaardid());
                        System.out.println("Diileri kaardid: " + diiler.getKaardid());
                    }

                    while (diiler.kasAlla17()) {                        //diiler võtab kaarte, kuni saab vähemalt 17 punkti
                        Kaart diileriKaart = pakk.get((int) (Math.random() * pakk.size()));
                        diiler.lisaKaart(diileriKaart);
                        pakk.remove(diileriKaart);
                        System.out.println("Diiler võtab kaardi...");
                        System.out.println("Diileri kaardid: " + diiler.getKaardid());
                        System.out.println("---------------------------------------------------------------------------------");
                    }

                    //kontroll
                    if (diiler.kasÜle() || diiler.kaartideSumma() < mängija.kaartideSumma()) {
                        System.out.println("Sinu võit! Võidad " + panus * 2 + "€.");
                        System.out.println("=================================================================================");
                        raha += panus * 2;
                    } else if (diiler.kaartideSumma() > mängija.kaartideSumma()) {
                        System.out.println("Diileri võit! Kaotad " + panus + "€.");
                        System.out.println("=================================================================================");
                    } else if (diiler.kaartideSumma() == mängija.kaartideSumma()) {
                        System.out.println("Push! Mõlemal võrdne kaartide summa. Saad " + panus + "€ tagasi.");
                        System.out.println("=================================================================================");
                        raha += panus;
                    }
                }
            }
        }
        System.out.println("Sul on " + raha + "€.");
        System.out.println("Mäng läbi! Kaotasid kogu oma raha.");
    }
}
