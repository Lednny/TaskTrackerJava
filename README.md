# TaskTracker CLI

TaskTracker CLI is a simple command-line application for managing tasks, built in Java. It allows users to add, list, update, delete, and mark tasks as done or pending, storing all data in a local JSON file.

## Features
- Add new tasks with descriptions
- List all tasks or filter by status (pending, completed)
- Update the description of a task by its ID
- Delete tasks by ID
- Mark tasks as completed or pending
- All tasks are saved in a local `tasks.json` file

## Project Structure
```
tasktrackercli/
  pom.xml
  src/
    main/
      java/
        cli/
          TaskCLI.java
          TaskManager.java
        com/
          tasktracker/
            Main.java
        model/
          Task.java
      resources/
    test/
      java/
  tasks.json
```

## How to Run
1. Make sure you have Java (JDK 8+) installed.
2. Compile the project using your IDE or with Maven (if you have a `pom.xml`).
3. Run the main class:
   ```
   java -cp target/classes com.tasktracker.Main
   ```
4. Use the CLI commands to manage your tasks.

## CLI Commands
- `add <description>`: Add a new task
- `list [status]`: List all tasks or filter by status (`PENDIENTE POR HACER...`, `TAREA COMPLETADA!`)
- `update <id> <description>`: Update a task's description
- `delete <id>`: Delete a task by ID
- `done <id>`: Mark a task as completed
- `pending <id>`: Mark a task as pending
- `help`: Show available commands
- `exit`: Exit the application

 ![Image Alt](https://github.com/Lednny/TaskTrackerJava/blob/41b97223c954d6c85ae5c3829a6247b9e4eb3af5/TaskTracker1.png)
 ![Image Alt](https://github.com/Lednny/TaskTrackerJava/blob/41b97223c954d6c85ae5c3829a6247b9e4eb3af5/TaskTracker2.png)
 ![Image Alt](https://github.com/Lednny/TaskTrackerJava/blob/41b97223c954d6c85ae5c3829a6247b9e4eb3af5/TaskTracker3.png)
 ![Image Alt](https://github.com/Lednny/TaskTrackerJava/blob/41b97223c954d6c85ae5c3829a6247b9e4eb3af5/TaskTracker4.png)

## Data Persistence
All tasks are stored in a local `tasks.json` file in the project directory. The file is automatically updated when you add, update, or delete tasks.

## Author
Lednny

---
Feel free to improve or extend this project!
