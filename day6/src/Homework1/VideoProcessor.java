package Homework1;

public class VideoProcessor {
    public void process(Video video){
        VideoEncoder encoder = new VideoEncoderH264("H264");
        encoder.encode(video);
        VideoDatabase database = new SqlDatabase("SQL");
        database.store(video);
        EmailService emailService = new GmailService("gmail");
        emailService.sendEmail(video.getUploader());

    }
}
