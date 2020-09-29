package abstractClass;

public class Main {
    public static void main(String args[]){

        Ticket ticket1 = new OneWayTicket();
        Gate gateA = new Gate("A",0);
        Gate gateB = new Gate("B",10);
        Gate gateC = new Gate("C",20);
        Gate gateD = new Gate("D",35);
        Line line = new Line(gateA,gateB,gateC,gateD);

        gateA.setTicket(ticket1);
        gateA.enter();
        gateC.setTicket(ticket1);
        gateC.enter();
        gateC.exit();
        System.out.println(ticket1.getOrigin());
        System.out.println("The ticket fee is " + ticket1.getFee());

        Ticket ticket2 = new PrepaidCard(200);
        gateB.setTicket(ticket2);
        gateB.enter();
        gateC.setTicket(ticket2);
        gateC.enter();

        System.out.println(ticket2.getFee());

    }
}
