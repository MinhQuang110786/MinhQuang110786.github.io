package noteTakingApp;

import java.util.ArrayList;
import java.util.List;

public class Note {
    private String note;
    private List<Image> photos;
    private List<Sketch> sketches;

    public Note(){}
    public Note(String note) {
        this.note = note;
        this.photos = new ArrayList<>();
        this.sketches = new ArrayList<>();
    }

    public void setText(String text){
        note = text;
    }

    public String getText(){
        return note;
    }

    public void addImage(Image image){
        if(!photos.contains(image))
            photos.add(image);
    }
    public List<Image> getImages(){
        return photos;
    }

    public void addSketch(Sketch sketch){
        if(!sketches.contains(sketch))
            sketches.add(sketch);
    }

    public List<Sketch> retrieveAllSketches(){
        return sketches;
    }

    @Override
    public String toString() {
        String photoStr = "";
        for (Image photo:photos) {
            photoStr+=photo.toString()+"\t";
        }

        String sketchStr = "";
        for(Sketch sketch: sketches){
            sketchStr+=sketch.toString()+ "\t";
        }
        return "Note("+note+") contains: " +
                "\nImages: " + photoStr +
                "\nSKetches: " + sketchStr;
    }
}
