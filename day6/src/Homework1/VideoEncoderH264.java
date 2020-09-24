package Homework1;

public class VideoEncoderH264 implements VideoEncoder{
    private String name;
    public VideoEncoderH264(String name){
        this.name = name;
    }
    @Override
    public void encode(Video video){
        System.out.println(video.getTitle() + "."+video.getFileName()+" encoded by " + this.name);
    }
}
