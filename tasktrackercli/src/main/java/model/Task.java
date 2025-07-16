package model;
import java.time.LocalDateTime;

public class Task {

    //DEFINIR VARIABLES
    private int idTask;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //CONSTRUCTOR
    public Task(int idTask, String description) {
        this.idTask = idTask;
        this.description = description;
        this.status = "PENDIENTE POR HACER..."; 
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //GETTERS Y SETTERS
    public int getIdTask() {
        return idTask;
    }

    public void setId(int id) {
        this.idTask = id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now(); 
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now(); 
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ID: " + idTask + "\n" +
                " || Description: " + description + "\n" +
                " || Status: " + status + "\n" +
                " || CreatedAt: " + createdAt + "\n" +
                " || UpdatedAt: " + updatedAt + "\n";
    }
}
