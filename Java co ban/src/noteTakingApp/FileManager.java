package noteTakingApp;
import java.util.*;

public class FileManager implements LocalPersistence{
    private String name;
    private List<Note> noteList;
    public FileManager(String name) {
        this.name = name;
        noteList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Note> getNotes() {
        return noteList;
    }

    @Override
    public void save(Note note) {
        noteList.add(note);
    }

    @Override
    public void update(Note note) {
       int index = noteList.indexOf(note);
       if(index!=-1)
       {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your new note name");
            String name = input.nextLine();
            note.setText(name);
            note.getImages().clear();
            note.retrieveAllSketches().clear();


            while(true){
                String location, topic,type;
                System.out.print("Enter your photo location: ");
                location = input.nextLine();
                System.out.print("Enter your photo topic: ");
                topic = input.nextLine();
                System.out.print("Enter your photo type: ");
                type = input.nextLine();
                note.addImage(new Image(location,topic,type));
                System.out.print("Continue: ");
                String ans = input.nextLine();
                if(ans.charAt(0)!='y')
                    break;
            }

           while(true){
               String topic,type;
               System.out.print("Enter your sketch topic: ");
               topic = input.nextLine();
               System.out.print("Enter your sketch type: ");
               type = input.nextLine();
               note.addSketch(new Sketch(topic,type));
               System.out.print("Continue: ");
               String ans = input.nextLine();
               if(ans.charAt(0)!='y')
                   break;
           }

       }
    }

    @Override
    public void delete(Note note) {
        noteList.remove(note);
    }

    public void update(String name){
        for(Note note: noteList)
            if(note.getText().equals(name)){
                update(note);
            }
    }
    public void sort(){
        Collections.sort(noteList, Comparator.comparing(Note::getText));
    }
}
