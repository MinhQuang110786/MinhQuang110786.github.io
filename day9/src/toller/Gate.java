package toller;

public class Gate {
    private String name;
    private int distance;
    private int dis;
    private int fare;
    public Gate(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public void enter(Ticket ticket){
        if(ticket.isValid()) {
            ticket.setOrigin(this);
            open();
        }else
            close();
    }

    public void exit(Ticket ticket){
        if(ticket.isValid()){
            dis = this.distance - ticket.getOrigin().distance;
            fare = Line.getFare(dis);
            if(ticket instanceof OneWayTicket){
                open();
                ticket.setOrigin(null);
                ((OneWayTicket)ticket).setValid(false);
            }
            else if(fare<= ticket.getValue())
            {
                ticket.adjustValue(fare);
                open();
            }
            else
                close();
        }
        else
            close();

    }

    public void open(){
        System.out.println("Gate " + name  + " is open");
    }
    public void close(){
        System.out.println("Gate " + name + " is close");
    }

    @Override
    public String toString() {
        return "Distance: " + dis + "\tFare: " + fare;
    }
}
