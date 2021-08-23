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

    public void markAsDone() {
        this.completed = true;
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + this + "\n");
    }

    abstract public String storageString();

}
