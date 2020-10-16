package noteTakingApp;
import java.sql.SQLOutput;
import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        var images = new ArrayList<Image>();
        var sketches = new ArrayList<Sketch>();
        for(int i=0;i<2;i++){
            System.out.print("Enter the location of photo: ");
            String location = input.nextLine();
            System.out.print("Enter the topic of photo: ");
            String topic = input.nextLine();
            System.out.print("Enter the type of photo: ");
            String type = input.nextLine();
            images.add(new Image(location,topic,type));
        }

        for(int i=0;i<3;i++){

            System.out.print("Enter the topic of sketch: ");
            String topic = input.nextLine();
            System.out.print("Enter the type of sketch: ");
            String type = input.nextLine();
            sketches.add(new Sketch(topic,type));
        }

        var notes = new ArrayList<Note>();
        for(int i=0;i<3;i++){
            System.out.print("Enter the note name: ");
            String name = input.nextLine();
            notes.add(new Note(name));
        }
        notes.get(0).addImage(images.get(0));
        notes.get(0).addImage(images.get(1));

        notes.get(1).addSketch(sketches.get(1));
        notes.get(1).addImage(images.get(1));
        notes.get(1).addSketch((sketches.get(0)));
        notes.get(1).addSketch(sketches.get(1));

        notes.get(2).addImage(images.get(1));
        notes.get(2).addSketch(sketches.get(1));
        notes.get(2).addSketch((sketches.get(2)));

        var driver = new FileManager("Disk A");
        driver.save(notes.get(0));
        driver.update(notes.get(0));
        driver.save(notes.get(2));
        driver.sort();
        System.out.println("The " + driver.getName() + " have notes: ");
        for(Note note: driver.getNotes())
            System.out.println(note);


        var cloud = new NetWorkController("Google Drive");
        cloud.create(notes.get(0));
        cloud.create(notes.get(1));
        cloud.sort();
        System.out.println("The " + cloud.getName() + " have notes: ");
        for(Note note: cloud.getNotes())
            System.out.println(note);
        cloud.delete(notes.get(0));
        System.out.println("After deleting, cloud have: ");
        for(Note note: cloud.getNotes())
            System.out.println(note);
    }
}
