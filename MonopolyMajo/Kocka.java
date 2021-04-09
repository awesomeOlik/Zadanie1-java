package sk.stuba.fei.uim.oop;

import java.util.concurrent.ThreadLocalRandom;

public class Kocka {
    protected int oKolkoPolicok;
    int hodKockou(){
        oKolkoPolicok = ThreadLocalRandom.current().nextInt(1, 7);
        return oKolkoPolicok;
    }
}
