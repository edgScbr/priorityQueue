import processImplementation.Process;
import processImplementation.ProcessScheduler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProcessScheduler scheduler = new ProcessScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar Proceso");
            System.out.println("2. Ejecutar Proceso");
            System.out.println("3. Mostrar Elementos en Cola");
            System.out.println("4. Salir");
            System.out.print("Ingrese su elección: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consumir nueva línea después de nextInt()

                switch (choice) {
                    case 1:
                        // Agregando un nuevo proceso
                        System.out.print("Ingrese el Nombre del Proceso: ");
                        String processId = scanner.nextLine();

                        int priority = getValidIntegerInput(scanner, "Ingrese la Prioridad del Proceso (entero): ");
                        int burstTime = getValidIntegerInput(scanner, "Ingrese el Tiempo de Rafaga del Proceso (en ms): ");

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
                        System.out.println("Saliendo...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Entrada no válida! Por favor, ingrese un número válido.");
                scanner.nextLine();  // Limpiar la entrada no válida
            }
        }
    }

    // Método auxiliar para obtener una entrada de número entero válida con manejo de errores
    private static int getValidIntegerInput(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = scanner.nextInt();
                scanner.nextLine();  // Consumir nueva línea
                break;  // Salir del bucle si se ingresa un entero válido
            } catch (InputMismatchException e) {
                System.out.println("¡Entrada no válida! Solo se permiten números.");
                scanner.nextLine();  // Limpiar la entrada no válida
            }
        }
        return number;
    }
}
