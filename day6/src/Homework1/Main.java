package Homework1;

public class Main {
    public static void main(String args[]){
        Video video = new Video("mkv","Home Alone","John.A.Smith");
        VideoProcessor processor = new VideoProcessor();
        processor.process(video);
    }
}
