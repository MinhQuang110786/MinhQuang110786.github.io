package ex19;

public class Television {
    private boolean isOn;
    private int channel;
    private byte volume;
    public Television(){
        isOn = false;
        channel = 1;
        volume = 50;
    }

    public void turnOn(){
        isOn = true;

    }
    public void turnOff(){
        isOn = false;
    }
    public void setChannel(int channel){
        if(isOn){
            if(channel>0 && channel<=200)
                this.channel = channel;
        }
    }

    public void upChannel(){
        if(isOn){
            if(channel<200)
                channel++;
            else
                channel=1;
        }
    }

    public void downChannel(){
        if(isOn){
            if(channel>1)
                channel--;
            else
                channel = 200;
        }
    }

    public void volUp(){
        if(isOn){
            if(volume<100)
                volume++;
        }
    }

    public void volDown(){
        if(isOn){
            if(volume>0)
                volume--;
        }
    }

    public String toString(){
        if(isOn){
            return "Your TV is ON, channel at " + channel + " and volume: " + volume;
        }else
            return "Yor TV is OFF";

    }
}
