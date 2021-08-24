package duke.task;

import duke.main.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract public class Task {
    protected boolean completed;
    protected String description;

    protected Task(String taskName) {
        this.description = taskName;
        this.completed = false;
    }

    @Override
    public String toString() {
        String check = this.completed ? "[X] " : "[ ] ";
        return check + description;
    }

    public static LocalDate parseTime(String time) throws DateTimeParseException {
        try {
            return LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("\t☹ OOPS!!! Please specify the time in the yyyy-mm-dd format.\n");
        }
    }

    public static String printTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public void markAsDone() {
        this.completed = true;
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + this + "\n");
    }

    abstract public String storageString();

}
