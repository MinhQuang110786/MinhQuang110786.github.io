package noteTakingApp;


public class Image {
    private String location;
    private String topic;
    private String type;

    public Image(String location, String topic, String type) {
        this.location = location;
        this.topic = topic;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "This photo about " + topic + " saved in " + type + " captured at " + location;
    }
}
