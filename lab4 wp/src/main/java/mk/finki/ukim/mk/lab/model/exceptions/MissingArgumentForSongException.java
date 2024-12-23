package mk.finki.ukim.mk.lab.model.exceptions;

public class MissingArgumentForSongException extends RuntimeException {

    public MissingArgumentForSongException() {
        super("You have to fill every input!");
    }
}
