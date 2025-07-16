package cli;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Task;

// Esta clase es la encargada de gestionar las tareas del Task Tracker CLI y de interactuar con el usuario a traves de la consola.
public class TaskManager {
    private List<Task> tasks = new ArrayList<Task>();
    private static final String FILE_NAME = System.getProperty("user.dir") + java.io.File.separator + "tasks.json";

    public TaskManager() {
        LoadTasksFromFile(); 
    }

    public void addTask( String description) {
        int newId = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getIdTask() + 1; 
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        SaveTasksToFile();
        System.out.println("Tarea añadida correctamente!: " + newTask);
    }

    // Este método lista todas las tareas almacenadas en la lista de tareas.
    public void SaveTasksToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(String.format("{\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createAt\": \"%s\", \"updateAt\": \"%s\"}",
                        task.getIdTask(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getUpdatedAt()));

                if (i < tasks.size() - 1) {
                    writer.write(",");
                } 
            }
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

    private void LoadTasksFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String json = reader.lines().collect(Collectors.joining(""));
            if(!json.isEmpty() && !json.equals("[]")){
                json = json.substring(1, json.length() - 1); 
                String[] taskArray = json.split("},\\s*\\{");
                for (String task : taskArray) {
                    task = task.replace("{", "").replace("}", ""); 
                    String[] taskSplit = task.split(",");
                    Map<String, String> taskMap = new HashMap<>();
                    for (String taskSplits : taskSplit) {
                        int index = taskSplits.indexOf(":");
                        if(index != 1){
                        String key = taskSplits.substring(0, index).replace("\"", "").trim();
                        String value = taskSplits.substring(index + 1).replace("\"", "").trim();
                        taskMap.put(key, value);
                    }
                }

                    Task objTask = new Task(
                        Integer.parseInt(taskMap.get("id")),
                        taskMap.get("description")
                    );
                objTask.setStatus(taskMap.get("status"));
                objTask.setCreatedAt(LocalDateTime.parse(taskMap.get("createAt")));
                objTask.setUpdatedAt(LocalDateTime.parse(taskMap.get("updateAt")));
                tasks.add(objTask);
            }
        }
        } catch (IOException e) {
            System.err.println("Error al cargar las tareas: " + e.getMessage());
        }
    }

    public void listTasks(String statusFilter){
        if(tasks.isEmpty()) {
            System.out.println("No hay tareas en la lista.");
            return;
        }

        List<Task> filteredTasks = tasks;
        if (statusFilter != null && !statusFilter.isEmpty()) {
            filteredTasks = tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(statusFilter))
                .collect(Collectors.toList());
        }
        if(filteredTasks.isEmpty()) {
            System.out.println("No hay tareas con el estado: " + statusFilter);
        } else {
            filteredTasks.forEach(System.out::println);
        }
    }

    public void updateTask(int id, String description) {
        if(tasks.isEmpty()) {
            System.out.println("No hay tareas en la lista para actualizar.");
        } else {
            for (Task task : tasks) {
                if (task.getIdTask() == id) {
                    task.setDescription(description);
                    break;
                }
            }
        }
    }

    public void deleteTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        boolean deleted = false;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getIdTask() == id) {
                iterator.remove();
                deleted = true;
                System.out.println("Tarea eliminada correctamente!");
                break;
            }
        }
        if (deleted) {
            SaveTasksToFile();
        }
    }

    public void markTaskAsDone(int id) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas en la lista para marcar como completadas.");
            } else {
            for (Task task : tasks) {
                if (task.getIdTask() == id) {
                    task.setStatus("TAREA COMPLETADA!");
                    System.out.println("Tarea marcada como completada!");
                    break;
                }
            }
        }
    }

    public void markTaskAsPending(int id) {
        for (Task task : tasks) {
            if (task.getIdTask() == id) {
                task.setStatus("PENDIENTE POR HACER...");
                System.out.println("Tarea marcada como pendiente!: " + task);
                return;
            }
        }
        System.out.println("Tarea no encontrada con ID: " + id);
    }
}
