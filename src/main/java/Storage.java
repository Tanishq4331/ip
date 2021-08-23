import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
        load();
    }

    public TaskList load() {
        try {
            List<Task> taskList = Files.lines(filePath).map((line) -> {
                String[] fragments = line.split(" \\| ");
                String type = fragments[0];
                boolean done = Boolean.parseBoolean(fragments[1]);
                String description = fragments[2];
                String time = fragments.length == 4 ? fragments[3] : null;
                Task foundTask;
                switch (type) {
                    case "T":
                        foundTask = new ToDo(description, done);
                        break;
                    case "D":
                        foundTask = new Deadline(description, time, done);
                        break;
                    case "E":
                        foundTask = new Event(description, time, done);
                        break;
                    default:
                        throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
                }
                return foundTask;
            }).collect(Collectors.toList());
            return new TaskList(taskList);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! Your tasks might be corrupted.");
        }
    }

    public void updateStorage(TaskList tasklist) {
        try {
            Files.write(filePath, tasklist.formatStorage(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't store any changes you make. \n");
        }
    }

    public void resetTasks() {
        try {
            System.out.println("\tClearing tasks...\n");
            Files.newBufferedWriter(filePath);
            System.out.println("\tYou can now start anew...\n");
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! Continuing without saving.\n");
        }
    }

}
