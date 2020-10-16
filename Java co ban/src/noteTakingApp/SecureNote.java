package noteTakingApp;

public class SecureNote extends Note implements Crypto{
    private int passwordHash;

    public SecureNote(String note) {
        super(note);
        this.passwordHash = hash(note);
    }

    @Override
    public int hash(String input) {
        return input.hashCode();
    }
}
