package duke.main;

public class DukeException extends IllegalArgumentException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}