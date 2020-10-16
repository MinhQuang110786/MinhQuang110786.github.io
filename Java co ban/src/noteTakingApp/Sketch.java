package noteTakingApp;

public class Sketch {
    private String topic;
    private String type;

    public Sketch(String topic, String type) {
        this.topic = topic;
        this.type = type;
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
        return "This handwritten about " + topic + " saved in "+ type;
    }
}
