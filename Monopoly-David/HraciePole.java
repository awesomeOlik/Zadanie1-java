package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HraciePole
{
    private ArrayList<Hrac> hracy=new ArrayList<>();
    private ArrayList<Pozicia> pozicie=new ArrayList<>();

    public void Start()
    {
        int cisloKarty=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Zadaj pocet hracov: ");
        int pocet=0;
        pocet=funkciaNaTryCatch();
        for (int i=0; i<pocet;i++)
        {
            System.out.println("Zadaj meno: ");
            String meno=scanner.next();
            hracy.add(new Hrac(meno));
        }
        pozicie.add(new Pozicia(0,"Start"));
        pozicie.add(new Miesta(1,"Bacuch",500,350,null));
        pozicie.add(new Miesta(2,"VelkyMeder",600,450,null));
        pozicie.add(new Sanca(3,"Policko Sanca"));
        pozicie.add(new Miesta(4,"Terchova",700,550,null));
        pozicie.add(new Miesta(5,"Zuberec",800,650,null));
        pozicie.add(new Vazenie(6,"Vazenie"));
        pozicie.add(new Miesta(7,"Podhajska",1000,750,null));
        pozicie.add(new Miesta(8,"Zdiar",1100,900,null));
        pozicie.add(new Sanca(9,"Policko Sanca"));
        pozicie.add(new Miesta(10,"Donovaly",1200,1000,null));
        pozicie.add(new Miesta(11,"Sturovo",1300,1100,null));
        pozicie.add(new PozemkoveDane(12,"Dane",200));
        pozicie.add(new Miesta(13,"DolnyKubin",1500,1200,null));
        pozicie.add(new Miesta(14,"Habovka",1600,1300,null));
        pozicie.add(new Sanca(15,"Policko Sanca"));
        pozicie.add(new Miesta(16,"LiptovskyMikulas",1700,1500,null));
        pozicie.add(new Miesta(17,"Zazriva",1800,1600,null));
        pozicie.add(new Vazenie(18,"Ides do vazenia"));
        pozicie.add(new Miesta(19,"Oscadnica",2000,1700,null));
        pozicie.add(new Miesta(20,"SpaniaDolina",2100,1800,null));
        pozicie.add(new Sanca(21,"Policko Sanca"));
        pozicie.add(new Miesta(22,"Bojnice",2200,2000,null));
        pozicie.add(new Miesta(23,"TatranskaStrba",2300,2100,null));
        while (hraKonci(hracy))
        {
            for (Hrac hrac : hracy)
            {
                System.out.println("Hrac "+hrac.getMeno()+" je na rade");
                //Check ci sa nachadza vo vazeni
                if (hrac.isVazenie())
                {
                    System.out.println("Hrac "+hrac.getMeno()+" je vo vazeni");
                    if(hrac.getKolaVoVazeni()==0) {
                        hrac.setVazenie(false);
                    }
                    hrac.setKolaVoVazeni(hrac.getKolaVoVazeni()-1);
                }
                else
                {
                    System.out.println("Stlac lubovolny znak pre hod kockou");
                    scanner.next();
                    Kocka kocka = new Kocka();
                    kocka.HodnotaKocky();
                    System.out.println("Hodil si " + kocka.getHodnota());
                    int novaPozicia = hrac.getPozicia() + kocka.getHodnota();

                    //Prechod cez Start
                    if (novaPozicia > 23) {
                        hrac.setBank(hrac.getBank() + 300);
                        novaPozicia = novaPozicia - pozicie.size();
                        System.out.println("Presiel si startom, dostavas 300");
                    }
                    hrac.setPozicia(novaPozicia);
                    Pozicia poziciaPo = pozicie.get(hrac.getPozicia());
                    System.out.println("Si na policku: " + poziciaPo.getNazov());

                    //Ides do vazenia
                    if (hrac.getPozicia() == 18) {
                        hrac.setVazenie(true);
                        hrac.setKolaVoVazeni(2);
                        hrac.setPozicia(6);

                    //Pozemkove Dane
                    } else if (hrac.getPozicia() == 12) {
                        PozemkoveDane dane = (PozemkoveDane) poziciaPo;
                        ArrayList<Miesta> pocet_pozemkov = hrac.getVlastnictvo();
                        hrac.setBank(hrac.getBank() - dane.getVyskaDane() * (pocet_pozemkov.size()));
                        System.out.println(hrac.getMeno() + " musi zapaltit za svoje pozemky " + dane.getVyskaDane() * (pocet_pozemkov.size()));
                        System.out.println("Zostatok na ucet je " + hrac.getBank());


                    //Policko sanca a tahanie karty
                    } else if (poziciaPo instanceof Sanca){
                        Sanca sanca = (Sanca) poziciaPo;
                        sanca.kartaSanca(hrac,cisloKarty);
                        cisloKarty++;
                        if (cisloKarty>=6)
                        {
                            cisloKarty=1;
                        }
                    //Policko Miesta a ich kupa alebo
                    } else if (poziciaPo instanceof Miesta) {
                        Miesta nehnutelnosti = (Miesta) poziciaPo;
                        //Kontrola kupy
                        if (nehnutelnosti.jeKupene()) {
                            System.out.println("Tento pozemok vlastni "+nehnutelnosti.getVlastnik().getMeno());
                            if (nehnutelnosti.getVlastnik().getMeno().equals(hrac.getMeno()))
                            {
                                System.out.println("Si na svojom pozemku");
                            }else {
                                if (hrac.getBank() < nehnutelnosti.getDan()) {
                                    nehnutelnosti.getVlastnik().setBank(nehnutelnosti.getVlastnik().getBank() + hrac.getBank());
                                    System.out.println("Za pobyt musis zaplatit " + nehnutelnosti.getDan());
                                    hrac.setBank(-1);
                                } else {
                                    hrac.setBank(hrac.getBank() - nehnutelnosti.getDan());
                                    nehnutelnosti.getVlastnik().setBank(nehnutelnosti.getVlastnik().getBank() + nehnutelnosti.getDan());
                                    System.out.println("Za pobyt musis zaplatit " + nehnutelnosti.getDan());
                                    System.out.println("Zostatok na ucet je " + hrac.getBank());
                                }
                            }
                        //Kupa mesta
                        } else {
                            System.out.println("V banke mas: " + hrac.getBank() + " a  cena " + nehnutelnosti.getNazov() + " je " + nehnutelnosti.getCena());
                            System.out.println("Ak to chces kupit stlac Y ak nie stlac iny znak");
                            String kupa = scanner.next();
                            if (kupa.equals("y") || kupa.equals("Y")) {
                                if (hrac.getBank() < nehnutelnosti.getCena()) {
                                    System.out.println("Ani to neskusaj nemas dost penazi.");
                                } else {
                                    hrac.setBank(hrac.getBank() - nehnutelnosti.getCena());
                                    nehnutelnosti.setVlastnik(hrac);
                                    hrac.getVlastnictvo().add(nehnutelnosti);
                                    System.out.println(hrac.getMeno() + " kupil " + nehnutelnosti.getNazov());
                                    System.out.println("Zostatok na ucet je " + hrac.getBank());
                                }
                            }
                        }
                    }
                }
                if (hrac.getBank()<0)
                {
                    for (Miesta miesto: hrac.getVlastnictvo())
                    {
                        miesto.setVlastnik(null);
                    }
                    hrac.getVlastnictvo().clear();
                    System.out.println("\n--------------");
                    System.out.println("HRAC "+hrac.getMeno()+" ZBANKROTOVAL.");
                    System.out.println("--------------\n");
                    hracy.remove(hrac);
                    break;
                }
                System.out.println("\n********Vymena hraca**********\n");
            }
        }
    }
    //Kontra hry
    public boolean hraKonci(ArrayList<Hrac> hracy)
    {
        //Ak hra len 1 hrac
        if (hracy.size()==1)
        {
            Hrac vyherca = hracy.get(0);
            System.out.println("Vyhercom sa stava "+vyherca.getMeno()+" so ziskom "+vyherca.getBank()+". \\o/ [potlesk v pozadi]");
            return false;
        }
        return true;
    }

    public int funkciaNaTryCatch()
    {
        Scanner scanner1=new Scanner(System.in);
        int pocet=0;
        while (pocet<2) {
            try {
                pocet = scanner1.nextInt();
                if (pocet <= 1) {
                    throw new SmallNumberException("SmallNumber");
                }
                return pocet;
            } catch (SmallNumberException error) {
                System.out.println("Zadal si zle cislo");
            } catch (InputMismatchException e) {
                System.out.println("Preco si zadal String?");
            }
            scanner1.nextLine();
        }
        return pocet;
    }
}
