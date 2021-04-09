package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int position;
    private int money;
    private final ArrayList<String> houses = new ArrayList<>();
    private int stayInPrison;
    private boolean inGame = true;

    public Player(String name, int position, int money) {
        super();
        this.name = name;
        this.position = position;
        this.money = money;
    }

    public void getHouse(String house) {
        houses.add(house);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<String> getHouses() {
        return houses;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getStayInPrison() {
        return stayInPrison;
    }

    public void setStayInPrison(int stayInPrison) {
        this.stayInPrison = stayInPrison;
    }

    @Override
    public String toString() {
        //return super.toString();
        return "[Player: "+ this.name + ", Money: " + this.money + ", Position: " + this.position+"]";
    }
}