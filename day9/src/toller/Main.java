package toller;

public class Main {
    public static void main(String[] args){
        Ticket oneWayTicket = new OneWayTicket(120);
        Ticket prepaidTicket = new PrepaidCard(200);
        Line.A.enter(oneWayTicket);
        Line.B.exit(oneWayTicket);
        System.out.println(Line.B);
        System.out.println(oneWayTicket);


        Line.B.enter(prepaidTicket);
        Line.D.exit(prepaidTicket);
        System.out.println(Line.D);
        System.out.println(prepaidTicket);



    }
}
