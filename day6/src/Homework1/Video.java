package Homework1;

public class Video {
    private String fileName;
    private String title;
    private String uploader;

    public Video(String fileName, String title, String uploader) {
        this.fileName = fileName;
        this.title = title;
        this.uploader = uploader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
