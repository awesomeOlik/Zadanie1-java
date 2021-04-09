package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Jail extends Field{
    private final ArrayList<String> prisoners = new ArrayList<>();

    public Jail(String name, int position) {
        super(name, position);
    }

    public ArrayList<String> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(String fucker) {
        prisoners.add(fucker);
    }
}
