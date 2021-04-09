package sk.stuba.fei.uim.oop;

public class Dane extends HraciaDoska{
    protected final int platbadane;
    {
        platbadane = 500;
    }

    public Dane(String meno, int indexpolicka) {
        super(meno, indexpolicka);
    }

    public void zaplatitDan(Hrac hrac){
        int kolkoZaplatit= hrac.getNehnutelnostiVoVlastnictve().size()*platbadane;
        hrac.setBitcoin(hrac.getBitcoin()-kolkoZaplatit);
        System.out.println("Zaplatil si za vsetky pozemky "+kolkoZaplatit+" Zostatok na ucte:"+hrac.getBitcoin());
    }
}
