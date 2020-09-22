package ex19;

public class Main {
    public static void main(String args[]){
        Television tv = new Television();
        tv.turnOn();
        tv.setChannel(199);
        tv.volDown();
        tv.upChannel();
        tv.upChannel();
        System.out.println(tv);
        tv.turnOff();
        tv.volDown();
        System.out.println(tv);
        tv.turnOn();
        System.out.println(tv);
    }
}
