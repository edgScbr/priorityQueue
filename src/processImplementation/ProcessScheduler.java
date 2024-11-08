package processImplementation;

import priorityQueue.Entry;
import priorityQueue.SortedPriorityQueue;

public class ProcessScheduler {
    private SortedPriorityQueue<Integer, Process> queue;

    public ProcessScheduler() {
        // Cola de prioridad usando el orden natural para prioridades (el valor de prioridad más bajo primero)
        queue = new SortedPriorityQueue<>();
    }

    // Método para agregar un proceso al planificador
    public void addProcess(int priority, Process process) {
        queue.insert(priority, process);
        System.out.println("Agregado: " + process + " con prioridad " + priority);
    }

    // Método para ejecutar el proceso con la mayor prioridad
    public void executeNextProcess() {
        if (queue.isEmpty()) {
            System.out.println("No hay más procesos para ejecutar.");
        } else {
            Entry<Integer, Process> entry = queue.removeMin();
            Process process = entry.getValue();
            System.out.println("Ejecutando: " + process.getName() +
                    " con prioridad " + entry.getKey() +
                    " por " + process.getBurstTime() + "ms");
        }
    }

    // Mostrar los procesos restantes
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No hay procesos en la cola.");
        } else {
            System.out.println("Procesos en la cola: " + queue.size());
        }
    }
}
