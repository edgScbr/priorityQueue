import processImplementation.Process;
import processImplementation.ProcessScheduler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProcessScheduler scheduler = new ProcessScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Process");
            System.out.println("2. Execute Process");
            System.out.println("3. Display Queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline after nextInt()

                switch (choice) {
                    case 1:
                        // Adding a new process
                        System.out.print("Enter Process ID: ");
                        String processId = scanner.nextLine();

                        int priority = getValidIntegerInput(scanner, "Enter Process Priority (integer): ");
                        int burstTime = getValidIntegerInput(scanner, "Enter Process Burst Time (in ms): ");

                        Process process = new Process(processId, burstTime);
                        scheduler.addProcess(priority, process);
                        break;

                    case 2:
                        scheduler.executeNextProcess();
                        break;

                    case 3:
                        scheduler.displayQueue();
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
            }
        }
    }

    // Helper method to get a valid integer input with error handling
    private static int getValidIntegerInput(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                scanner.nextLine();  // Consume the newline
                break;  // Break out of the loop if a valid integer is entered
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please only numbers are allowed.");
                scanner.nextLine();  // Clear the invalid input
            }
        }
        return number;
    }
}

