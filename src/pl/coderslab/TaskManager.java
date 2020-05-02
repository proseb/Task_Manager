package pl.coderslab;



public class TaskManager {

    public static void main(String[] args) {

        displayAvailableOptions();
    }//void main

    public static void displayAvailableOptions() {
        String[] availableOptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (int i = 0; i < availableOptions.length; i++) {
            System.out.println(availableOptions[i]);
        }
    }
    
}