package processImplementation;

import priorityQueue.Entry;
import priorityQueue.SortedPriorityQueue;

public class ProcessScheduler {
    private SortedPriorityQueue<Integer, Process> queue;

    public ProcessScheduler() {
        // Priority Queue using natural order for priorities (smallest priority value first)
        queue = new SortedPriorityQueue<>();
    }

    // Method to add a process to the scheduler
    public void addProcess(int priority, Process process) {
        queue.insert(priority, process);
        System.out.println("Added: " + process + " with priority " + priority);
    }

    // Method to execute the process with the highest priority
    public void executeNextProcess() {
        if (queue.isEmpty()) {
            System.out.println("No more processes to execute.");
        } else {
            Entry<Integer, Process> entry = queue.removeMin();
            Process process = entry.getValue();
            System.out.println("Executing: " + process.getName() +
                    " with priority " + entry.getKey() +
                    " for " + process.getBurstTime() + "ms");
        }
    }

    // Display remaining processes
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No processes in the queue.");
        } else {
            System.out.println("Processes in queue: " + queue.size());
        }
    }
}

