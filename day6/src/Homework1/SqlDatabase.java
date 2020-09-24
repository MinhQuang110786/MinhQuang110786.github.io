package Homework1;

public class SqlDatabase implements VideoDatabase {
    private String name;
    public SqlDatabase(String name){
        this.name = name;
    }
    @Override
    public void store(Video video){
        System.out.println( video.getTitle() + " stored in " + this.name);
    }
}
