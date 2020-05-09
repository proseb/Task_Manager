package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    static String[][] tasks;
    static final String FILE_NAME = "/home/seba/IdeaProjects/Task_Manager/src/pl/coderslab/tasks.csv";

    public static void main(String[] args) {
        tasks = readFromFile(FILE_NAME);
        displayAvailableOptions();

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String inputFromUser = input.nextLine();

            switch (inputFromUser) {
                case "add":
                    addNewTask();
                    System.out.println("New task successfully created." + "\n");
                    break;

                case "remove":
                    removeTask();
                    System.out.println("Task successfully removed." + "\n");
                    break;

                case "list":
                    listAllTasks(tasks);
                    break;

                case "exit":
                    saveChanges(FILE_NAME, tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye" + ConsoleColors.RESET);
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please select a correct option." + "\n");
            }
            displayAvailableOptions();
        }
    }

    public static void displayAvailableOptions() {
        String[] availableOptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (int i = 0; i < availableOptions.length; i++) {
            System.out.println(availableOptions[i]);
        }
    }

    public static String[][] readFromFile(String fileName) {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("File not exist.");
            System.exit(0);
        }

        String[][] tab = null;
        try {
            List<String> textLines = Files.readAllLines(path);
            tab = new String[textLines.size()][textLines.get(0).split(",").length];

            for (int i = 0; i < textLines.size(); i++) {
                String[] split = textLines.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return tab;
    }

    public static void listAllTasks(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void addNewTask() {
        Scanner inputFromUser = new Scanner(System.in);
        System.out.println("Please add task description:");
        String taskDescription = inputFromUser.nextLine();
        System.out.println("Please add task due date:");
        String taskDueDate = inputFromUser.nextLine();
        System.out.println("Is your task important? true/false");
        String taskImportance = inputFromUser.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = taskDescription;
        tasks[tasks.length - 1][1] = taskDueDate;
        tasks[tasks.length - 1][2] = taskImportance;

    }

    public static void removeTask() throws IndexOutOfBoundsException {
        System.out.println("Please select task to remove by typing its number:");

        Scanner inputFromUser = new Scanner(System.in);
        while (!inputFromUser.hasNextInt()) {
            inputFromUser.next();
            System.out.println("Please type number from 0 - " + (tasks.length - 1));
        }
        tasks = ArrayUtils.remove(tasks, inputFromUser.nextInt());
    }

    public static void saveChanges(String fileName, String[][] tab) {
        Path filePath = Paths.get(fileName);

        String[] lines = new String[tasks.length];
        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        try {
            Files.write(filePath, Arrays.asList(lines));
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
            e.getMessage();
            e.printStackTrace();
        }
    }
}