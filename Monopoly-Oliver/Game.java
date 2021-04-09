package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game{
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    ArrayList<Player> player = new ArrayList<>();

    public void creatingGame() throws InterruptedException {
        int numberOfPlayers = checkInput();
        if(numberOfPlayers == 1) {
            System.out.println("\nPlease, just go away and get some help.\n");
            System.exit(1);
        }

        Player[] players = new Player[numberOfPlayers];
        ArrayList<String> names = new ArrayList<>();
        String name = "";

        System.out.println("Write those names: ");
        int i=0;
        while(i<numberOfPlayers)
        {
            try{
                name = scan.next();
                if(names.contains(name))
                    throw new NameTakenException("name taken");
                else {
                    names.add(name);
                    players[i] = new Player(name, 0, 10000);
                    player.add(players[i]);
                    i++;
                }
            }catch(NameTakenException e){
                System.out.println("\nName taken.\n");
            }
        }

        House[] houses = {new House("Budova FEI", 1000, 500, false,1),
                new House("Mladost", 1500, 750, false,3),
                new House("Sturak", 1500, 750, false,4),
                new House("Vietnamese restaurant", 1750, 875, false,6),
                new House("Chinese restaurant", 1750, 875, false,8),
                new House("Japanese restaurant", 1750, 875, false,9),
                new House("Terno", 2000, 1000, false,10),
                new House("Billa", 2000, 1000, false,11),
                new House("Lidl", 2000, 1000, false,13),
                new House("Brothel", 2250, 1125,false,15),
                new House("Club", 2250, 1125,false,16),
                new House("Pub", 2250, 1125,false,18),
                new House("IKEA", 2500, 1250,false,20),
                new House("OBI", 2750, 1375, false,21),
                new House("Eurovea", 3000, 1500, false,22),
                new House("Aupark", 3500, 1750, false,23)};

        Chance[] chances = {new Chance("Chance", 0, 2),
                new Chance("Chance", 0, 5),
                new Chance("Chance", 0, 14),
                new Chance("Chance", 0, 17)};

        playGame(players, players.length, houses, houses.length, chances);
    }

    public int checkInput() {
        int value = 0;
        int num = 0;
        while(value<2){
            try{
                System.out.println("Give me a number of players: ");
                value = scan.nextInt();
                if(value <= 1)
                    throw new SmallNumberException("Small number.");
                return value;
            }
            catch(InputMismatchException e){
                num++;
                System.out.println("Write NUMBER dumbass!");
            }catch(SmallNumberException s) {
                num++;
                System.out.println("Only higher number than 1!");
            }
            if(num >= 5)
                return 1;
            scan.nextLine();
        }
        return value;
    }

    public int diceRoller() { return rand.nextInt(6); }

    public void playGame(Player[] players, int len, House[] houses, int lenH, Chance[] chances) throws InterruptedException {

        Jail jail = new Jail("Jail", 19);
        Tax tax = new Tax("Tax", 0, 12);
        int eff = rand.nextInt(5)+1;
        int stillPlaying = len;

        while(stillPlaying > 1) {
            for (int i = 0; i < len; i++) {
                if (!players[i].isInGame())
                    System.out.println("[" + players[i].getName() + " is loser!]");
                else
                    System.out.println(players[i].toString());
            }
            for (int i = 0; i < len; i++) {
                System.out.println("\n-------------------------------------\n");

                Player p = players[i];

                if (p.isInGame()) {
                    System.out.println("Player: " + p.getName() + "\n");
                    System.out.println("Money: " + p.getMoney() + "\n");
                    System.out.println("Current position: " + p.getPosition() + "\n");
                    System.out.println("Houses: " + p.getHouses() + "\n\n");
                }
                if (everythingOk(p, jail, tax)) {
                    for (int j = 0; j < lenH; j++) {
                        House h = houses[j];
                        if (h.getPosition() == p.getPosition()) {
                            if (!h.isTaken())
                                buyingHouse(p, h);
                            else if (p.getHouses().contains(h.getName())) {
                                System.out.println(p.getName() + ", you own this property.");
                                Thread.sleep(3000);
                            } else {
                                payingPlayer(p, h);
                                repurchase(p, h);
                            }
                        }
                    }
                    for (int je = 0; je < 4; je++) {
                        Chance ch = chances[je];
                        if (ch.getPosition() == p.getPosition()) {
                            eff++;
                            if (eff > 6)
                                eff = 1;
                            ch.effectsDone(eff, p, jail);
                        }
                    }
                    if (checkRuined(p)) {
                        gettingOutOfGame(p, houses);
                        stillPlaying--;
                    }
                }
                System.out.println("\n-------------------------------------\n");
            }
        }
        setWinner(players);
    }

    public boolean everythingOk(Player p, Jail jail, Tax tax) throws InterruptedException {
        while(true) {
            if(jail.getPrisoners().contains(p.getName())) {
                p.setStayInPrison(p.getStayInPrison()-1);
                if(p.getStayInPrison()==0) {
                    jail.getPrisoners().remove(p.getName());
                    continue;
                }
                System.out.println("\nYou are staying in prison for "+p.getStayInPrison()+" more rounds/round.");
                Thread.sleep(3000);
                return false;
            }
            if(p.isInGame())
                rollingNMoving(p);
            else
                return false;
            if(p.getPosition() == jail.getPosition()) {
                System.out.println("You are being locked in prison for 3 rounds!");
                Thread.sleep(3000);
                p.setPosition(7);
                jail.setPrisoners(p.getName());
                p.setStayInPrison(4);
                return false;
            }
            if(p.getPosition() == tax.getPosition()) {
                payingTaxes(p, tax);
            }
            if(p.getPosition() == 7) {
                System.out.println("\nYou're visiting your grandma in prison.\n");
                Thread.sleep(3000);
            }
            return true;
        }
    }

    public void rollingNMoving(Player p) throws InterruptedException {
        String rolling;
        do {
            System.out.println("Player "+p.getName()+" roll the dice.(write 'r')");
            rolling = scan.next();
        }while(!rolling.equals("r"));

        int thrw = 1+diceRoller();
        int dice = thrw+p.getPosition();
        if(dice>23) {
            dice = dice-23;
            System.out.println("You are passing start! +3000");
            Thread.sleep(1000);
            p.setMoney(p.getMoney()+3000);
        }
        p.setPosition(dice);
        System.out.println("You threw "+thrw);
        for(int i=0; i<thrw;i++) {
            System.out.print(". ");
            Thread.sleep(500);
        }
        System.out.println("\n");
    }

    public void buyingHouse(Player p, House h) throws InterruptedException {
        String answ;
        System.out.println("Do you wanna buy "+h.getName()+"? cost: "+h.getPrice()+" (y/n)");
        answ = scan.next();
        if(answ.equals("y")) {
            if(checkMoney(p.getMoney(), h.getPrice())) {
                p.getHouse(h.getName());
                p.setMoney(p.getMoney()-h.getPrice());
                h.setTaken(true);
                h.setOwner(p.getName());
                System.out.println("-"+h.getPrice()+". Remaining cash: "+p.getMoney());
                Thread.sleep(3000);
            }
        }else if(answ.equals("n")) {
            System.out.println("Saving.");
        }else
            buyingHouse(p,h);
    }

    public void payingPlayer(Player p, House h) throws InterruptedException {
        for (Player player : this.player) {
            if (player.getName().equals(h.getOwner()) && !(h.getOwner().equals(p.getName()))) {
                System.out.println("\nPlayer " + p.getName() + " is paying " + h.getValue() + " to player " + player.getName() + ". Remaining cash: "+(p.getMoney()-h.getValue())+"\n");
                p.setMoney(p.getMoney() - h.getValue());
                if(p.getMoney()<=0)
                    player.setMoney(player.getMoney() + h.getValue()+p.getMoney());
                else
                    player.setMoney(player.getMoney() + h.getValue());
                Thread.sleep(3000);
            }
        }
    }

    public void repurchase(Player p, House h) throws InterruptedException {
        if(p.getMoney()>(h.getPrice()+h.getValue()) && !(h.getOwner().equals(p.getName()))) {
            System.out.println("\nDo you want repurchase " + h.getOwner() + "'s property " + h.getName() + "?(y/n) cost: " + (h.getPrice() + h.getValue()) + "\n");
            String answer = scan.next();
            if (answer.equals("y")) {
                p.setMoney(p.getMoney() - (h.getPrice() + h.getValue()));
                for (Player player : this.player) {
                    if (player.getName().equals(h.getOwner())) {
                        player.setMoney(player.getMoney() + (h.getPrice() + h.getValue()));
                        player.getHouses().remove(h.getName());
                    }
                }
                h.setOwner(p.getName());
                p.getHouse(h.getName());
                System.out.println("-"+(h.getPrice() + h.getValue())+". Remaining cash: "+p.getMoney());
                Thread.sleep(3000);
            }else if(answer.equals("n")) {
                System.out.println("Maybe nex time then.");
                Thread.sleep(2000);
            }else
                repurchase(p,h);
        }else {
            System.out.println("You don't have enough money to repurchase this property!");
            Thread.sleep(3000);
        }
    }

    public boolean checkMoney(int money, int price) throws InterruptedException {
        if((money - price) <= 0 ) {
            System.out.println("You don't have enough money to buy this property.");
            Thread.sleep(3000);
            return false;
        }
        return true;
    }

    public boolean checkRuined(Player p) { return p.getMoney() <= 0; }

    public void gettingOutOfGame(Player p, House[] houses) throws InterruptedException {
        System.out.println("\n*******************************\n \tYOU ARE RUINED\n \tYOU ARE OUT\n \t"+ p.getName() +"\n*******************************\n");
        for (House house : houses)
            if (p.getName().equals(house.getOwner())) {
                house.setOwner("Free");
                house.setTaken(false);
            }
        player.remove(p);
        p.getHouses().clear();
        p.setMoney(0);
        p.setPosition(69);
        p.setInGame(false);
        Thread.sleep(2000);
    }

    public void payingTaxes(Player p, Tax tax) throws InterruptedException {
        System.out.println("Pay taxes pal! (" + tax.getTax(p)+")");
        p.setMoney(p.getMoney()-tax.getTax(p));
        Thread.sleep(3000);
    }

    public void setWinner(Player[] players) {
        for (Player p : players) {
            if (p.isInGame()) {
                System.out.println("\n\n*******************************\n" +
                        "\tTHE WINNER IS:\n\n" +
                        "\t" + p.getName() + "\n\n" +
                        "\tCash: "+p.getMoney()+"\n" +
                        "\n*******************************\n");
                break;
            }
        }
    }
}