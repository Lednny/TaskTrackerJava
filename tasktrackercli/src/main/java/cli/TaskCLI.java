package cli;

import java.util.Scanner;

public class TaskCLI {
    private final TaskManager taskManager;
    private final Scanner scanner;

    public TaskCLI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Task-CLI");
        System.out.println("Ingresa 'help' para ver los comandos disponibles.");
        System.out.println();

        while (true) {
            System.out.println("-------------------------------------------------------------------");
            System.out.print("Task-CLI > ");
            String input = scanner.nextLine().trim();
            System.out.println();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] commandArgs = input.split(" ", 2);
            String command = commandArgs[0].toLowerCase();

            switch (command) {
                case "add":
                    handleAddTask(commandArgs);
                    break;
                case "list":
                    handleListTasks(commandArgs);
                    break;
                case "update":
                    handleUpdateTask(commandArgs);
                    break;
                case "delete":
                    handleDeleteTask(commandArgs);
                    break;
                case "done":
                    handleMarkTaskAsDone(commandArgs, true);
                    break;
                case "pending":
                    handleMarkTaskAsDone(commandArgs, false);
                    break;
                case "help":
                    handleHelp();
                    break;
                case "exit":
                    taskManager.SaveTasksToFile();
                    System.out.println("Saliendo del Task-CLI. Hasta luego!");
                    break;
                default:
                    System.out.println("Comando no reconocido. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void handleHelp() {
        System.out.println("Comandos disponibles:");
        System.out.println("-add <descripcion> - Añade una nueva tarea.");
        System.out.println("-list [status] - Lista todas las tareas o las tareas con un estado específico (pendiente, completada).");
        System.out.println("-update <id> <descripcion> - Actualiza la descripción de una tarea por su ID.");
        System.out.println("-delete <id> - Elimina una tarea por su ID.");
        System.out.println("-done <id> - Marca una tarea como completada por su ID.");
        System.out.println("-pending <id> - Marca una tarea como pendiente por su ID.");
        System.out.println("-exit - Sale del Task-CLI.");
        System.out.println();
    }

    private void handleAddTask(String[] commandArgs) {
        if (commandArgs.length > 0) {
            taskManager.addTask(commandArgs[0]);
        } else {
            System.out.println("Por favor, ingrese una descripción para la tarea.");
        }
    }
    

    private void handleListTasks(String[] commandArgs) {
        if(commandArgs.length > 1){
            taskManager.listTasks(commandArgs[1]);
        }else{
            taskManager.listTasks(null);
        }
    }

    private void handleUpdateTask(String[] commandArgs) {
        if (commandArgs.length > 1) {
            String[] updateArgs= commandArgs[1].split("", 2);
            if(updateArgs.length > 1){
                try{
                    int id = Integer.parseInt(updateArgs[0]);
                    taskManager.updateTask(id, updateArgs[1]);
                }catch(NumberFormatException e) {
                    System.out.println("ID de tarea inválido. Por favor, ingrese un número válido.");
                }
                }else{
                    System.out.println("Por favor, ingrese una descripción para la tarea.");
                }
                }else{
                    System.out.println("Por favor, ingrese el ID de la tarea a actualizar y la nueva descripción.");
                }
            }

        private void handleDeleteTask(String[] commandArgs) {
            if (commandArgs.length > 1) {
                try {
                    int id = Integer.parseInt(commandArgs[1]);
                    taskManager.deleteTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID de tarea inválido. Por favor, ingrese un número válido.");
                }
            } else {
                System.out.println("Por favor, ingrese el ID de la tarea a eliminar.");
            }
        }

        private void handleMarkTaskAsDone(String[] commandArgs, boolean mark) {
            if (commandArgs.length > 1) {
                try {
                    int id = Integer.parseInt(commandArgs[1]);
                    if(mark){
                        taskManager.markTaskAsDone(id);
                    } else {
                        taskManager.markTaskAsPending(id);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID de tarea inválido. Por favor, ingrese un número válido.");
                }
            } else {
                System.out.println("Por favor, ingrese el ID de la tarea a marcar como completada o pendiente.");
            }
        }
    }
