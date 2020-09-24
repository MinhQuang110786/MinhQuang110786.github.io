package Homework1;

public class GmailService implements EmailService {
    private String name;
    public GmailService(String name){
        this.name = name;
    }
    @Override
    public void sendEmail(String user){
        System.out.println("video send to " + user + "@"+this.name);
    }
}
