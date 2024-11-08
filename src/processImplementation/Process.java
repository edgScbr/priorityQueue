package processImplementation;

public class Process {
    private String name;
    private int burstTime;  // Tiempo requerido para que el proceso sea completado

    public Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getBurstTime() {
        return burstTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", burstTime=" + burstTime +
                '}';
    }
}

