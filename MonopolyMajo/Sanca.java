package sk.stuba.fei.uim.oop;

public class Sanca extends HraciaDoska {
    public Sanca(String meno, int indexpolicka) {
        super(meno, indexpolicka);
    }

    public void tahanieKariet(Hrac hrac, int ktoraKarta) {
        switch (ktoraKarta) {
            case 1:
                System.out.println("Na ulici ta zastavil náhodný okoloidúci a daroval ti 1000 bitcoinov.");
                hrac.setBitcoin(hrac.getBitcoin() + 1000);
                System.out.println("Zostatok na účte:" + hrac.getBitcoin());
                break;
            case 2:
                System.out.println("Daroval si na charitu 2000 bitcoinov.");
                hrac.setBitcoin(hrac.getBitcoin() - 2000);
                System.out.println("Zostatok na účte:" + hrac.getBitcoin());
                break;
            case 3:
                System.out.println("Choď do väzenia.");
                hrac.setKolaVoVazeni(2);
                hrac.setIndexPola(6);
                break;
            case 4:
                System.out.println("Choď na Štart,bez finančnej odmeny.");
                hrac.setIndexPola(0);
                break;
            case 5:
                System.out.println("Dostal si úplne bezvýznamné umelecke dielo.");
                System.out.println("       /\\   *  ");
                System.out.println("     /   \\  *  ");
                System.out.println("   /       \\   ");
                System.out.println(" /    ***    \\ ");
                System.out.println("|     ***     | ");
                System.out.println("|     ***     | ");
                System.out.println("|             | ");
                System.out.println("|_____________| ");
                break;
            default:
                break;
        }
    }
}
