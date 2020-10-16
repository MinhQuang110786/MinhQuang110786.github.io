package noteTakingApp;

import java.util.List;

public interface LocalPersistence {
    List<Note> getNotes();
    void save(Note note);
    void update(Note note);
    void delete(Note note);
}
