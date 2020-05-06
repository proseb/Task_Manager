package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    private static String[][] tasks;
    private static final String FILE_NAME = "/home/seba/IdeaProjects/Task_Manager/src/pl/coderslab/test_file.csv";

    public static void main(String[] args) {
        tasks = readFromFile(FILE_NAME);
        displayAvailableOptions();

        try (Scanner input = new Scanner(System.in)) {
            while (input.hasNextLine()) {
                String inputFromUser = input.nextLine();

                switch (inputFromUser) {
                    case "add":
                        addTask();
                        break;
                    case "remove":
                        remove();
                        break;
                    case "list":
                        listAllTasks(tasks);
                        break;
                    case "exit":
                        exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please select a correct option.");
                }
            }
        }
    }//void main

    public static void displayAvailableOptions() {
        String[] availableOptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (int i = 0; i < availableOptions.length; i++) {
            System.out.println(availableOptions[i]);
        }
    }

    public static String[][] readFromFile(String fileName) {
        Path path = Paths.get(fileName);
//       sprawdzenie, czy plik istnieje.
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
    }
}