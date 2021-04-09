package sk.stuba.fei.uim.oop;

public class Chance extends Field{
    protected final int effect;


    public Chance(String name, int effect, int position) {
        super(name, position);
        this.effect = effect;
    }

    public void effectsDone(int eff, Player p, Jail jail) throws InterruptedException {
        switch(eff)
        {

            case 1:
                System.out.println("\nYou'll receive 2000 in cash.\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" $ ");
                    Thread.sleep(500);
                }
                p.setMoney(p.getMoney()+2000);
                break;

            case 2:
                System.out.println("\nYou're going straight to jail.\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" X ");
                    Thread.sleep(500);
                }
                p.setPosition(7);
                p.setStayInPrison(4);
                jail.setPrisoners(p.getName());
                break;

            case 3:
                System.out.println("\nYou're going straight to start you lucker.(+3000)\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" >>> ");
                    Thread.sleep(500);
                }
                p.setPosition(0);
                p.setMoney(p.getMoney()+3000);
                break;

            case 4:
                System.out.println("\nHaha, bad luck! Now pay!(-1500)\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" :( ");
                    Thread.sleep(500);
                }
                p.setMoney(p.getMoney()-1500);
                break;

            case 5:
                System.out.println("\nJust smile, could've been worse.\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" :) ");
                    Thread.sleep(500);
                }
                break;

            case 6:
                System.out.println("\nWhaaaat? Bruh, you've just got 3000 in cash!\n");
                for(int i=0; i<5;i++) {
                    System.out.print(" $$$ ");
                    Thread.sleep(500);
                }
                p.setMoney(p.getMoney()+3000);
                break;

            default:
                break;
        }
    }
}
