package interfaceClass;

public class Gate {
    private String name;
    private int distance;
    private Payable ticket;
    public Gate(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public void setTicket(Payable ticket){
        this.ticket = ticket;
    }
    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
    public void enter(){
        if(ticket.getOrigin().contains(this) || !ticket.isValid()){
            ticket.setOrigin(null);
            close();
        }else{
            ticket.setOrigin(this);
            ticket.adjustValue();
            if(ticket.isValid())
                open();
            else
                close();
        }
    }
    public void exit(){
        ticket.setOrigin(null);
        close();
    }
    private void open(){
        System.out.println("abstractClass.Gate " + this.name + " is open");
    }
    private void close(){
        System.out.println("abstractClass.Gate "  + this.name + " is closed");
    }
}
