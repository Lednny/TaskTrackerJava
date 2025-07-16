package com.tasktracker;
import cli.TaskCLI;
import cli.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskCLI taskCLI = new TaskCLI(new TaskManager());
        taskCLI.start();
    }

}