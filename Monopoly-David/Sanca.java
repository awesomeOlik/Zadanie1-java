package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Sanca extends Pozicia{
    private ArrayList<Integer> sanca=new ArrayList<>();

    public Sanca(int index,String nazov) {
        super(index,nazov);
        sanca.add(index);
    }

    void kartaSanca(Hrac hrac,int cisloKarty){
        switch (cisloKarty)
        {
            case 1:
                System.out.println("V herni si ziskal 1k ");
                hrac.setBank(hrac.getBank()+1000);
                System.out.println("Aktualne mas "+hrac.getBank());
                break;
            case 2:
                System.out.println("V herni si prehral 1k ");
                hrac.setBank(hrac.getBank()-1000);
                System.out.println("Aktualne mas "+hrac.getBank());
                break;
            case 3:
                System.out.println("Stretol si sa zo starym znamim a potrebuje od teba peniaze na nove auto pozicias mu 2k");
                hrac.setBank(hrac.getBank()-2000);
                System.out.println("Aktualne mas "+hrac.getBank());
                break;
            case 4:
                System.out.println("V herni si stratil 500 ");
                hrac.setBank(hrac.getBank()-500);
                System.out.println("Aktualne mas "+hrac.getBank());
                break;
            case 5:
                System.out.println("V herni si ziskal 2k ");
                hrac.setBank(hrac.getBank()+2000);
                System.out.println("Aktualne mas "+hrac.getBank());
                break;
            default:
                break;
        }
    }
}
