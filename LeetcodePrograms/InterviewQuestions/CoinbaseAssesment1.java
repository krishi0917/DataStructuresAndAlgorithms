package LeetcodePrograms.InterviewQuestions;

import java.util.*;

public class CoinbaseAssesment1 {
    public static void main(String[] args) {
        WorkHoursRegister register = new WorkHoursRegister();

        // Add workers
        System.out.println(register.addWorker("John", "Developer", 160)); // true
        System.out.println(register.addWorker("Alice", "Developer", 180)); // true
        System.out.println(register.addWorker("John", "Developer", 200)); // false (duplicate)

        // Register sessions
        System.out.println(register.register("John", 1000));  // enter
        System.out.println(register.register("John", 1600));  // exit (10 mins)
        System.out.println(register.get("John"));             // 10

        // Top-N workers
        System.out.println(register.topNWorkers(2, "Developer")); // [John(10), Alice(0)]

        // Salary calculation (assuming hourly wage)
        long start = 0;
        long end = 2000;
        System.out.println("Salary: " + register.calcSalary("John", start, end));
    }
}


class Compensation {
    private int salary;
    private String position;

    public Compensation(int salary) {
        this.salary = salary;
        this.position = "Worker";
    }

    public void updatePosition(String newPosition, int newSalary) {
        this.position = newPosition;
        this.salary = newSalary;
    }

    public int getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }
}

class WorkSession {
    private Date timestamp;
    private String sessionType; // "enter" or "exit"

    public WorkSession(Date timestamp, String sessionType) {
        this.timestamp = timestamp;
        this.sessionType = sessionType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSessionType() {
        return sessionType;
    }
}

class Worker {
    private String workerId;
    private String position;
    private Compensation compensation;
    private List<WorkSession> workSessions = new ArrayList<WorkSession>();

    public Worker(String workerId, String position, Compensation compensation) {
        this.workerId = workerId;
        this.position = position;
        this.compensation = compensation;
    }

    public void addSession(WorkSession session) {
        workSessions.add(session);
    }

    public long getTotalTimeInMinutes() {
        if (workSessions.size() % 2 != 0) {
            return 0;
        }

        long totalMillis = 0;
        for (int i = 0; i < workSessions.size(); i += 2) {
            Date enter = workSessions.get(i).getTimestamp();
            Date exit = workSessions.get(i + 1).getTimestamp();
            totalMillis += (exit.getTime() - enter.getTime());
        }

        return totalMillis / (1000 * 60);
    }

    public double calculateSalary(Date start, Date end) {
        double total = 0.0;
        for (int i = 0; i < workSessions.size(); i += 2) {
            Date enter = workSessions.get(i).getTimestamp();
            Date exit = workSessions.get(i + 1).getTimestamp();

            if (enter.compareTo(start) >= 0 && exit.compareTo(end) <= 0) {
                long hoursWorked = (exit.getTime() - enter.getTime()) / (1000 * 60 * 60);
                total += hoursWorked * (compensation.getSalary() / 160.0);
            }
        }
        return total;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getPosition() {
        return position;
    }

    public Compensation getCompensation() {
        return compensation;
    }

    public List<WorkSession> getWorkSessions() {
        return workSessions;
    }
}

class WorkHoursRegister {
    private Map<String, Worker> workers = new HashMap<String, Worker>();

    public boolean addWorker(String workerId, String position, int salary) {
        if (workers.containsKey(workerId)) return false;
        Compensation comp = new Compensation(salary);
        Worker worker = new Worker(workerId, position, comp);
        workers.put(workerId, worker);
        return true;
    }

    public Long get(String workerId) {
        if (!workers.containsKey(workerId)) return null;
        return workers.get(workerId).getTotalTimeInMinutes();
    }

    public String register(String workerId, long timestampSeconds) {
        if (!workers.containsKey(workerId)) return "invalid_request";

        Worker worker = workers.get(workerId);
        Date timestamp = new Date(timestampSeconds * 1000);
        String type = worker.getWorkSessions().size() % 2 == 0 ? "enter" : "exit";

        worker.addSession(new WorkSession(timestamp, type));
        return "registered";
    }

    public List<String> topNWorkers(int n, String position) {
        List<Worker> filtered = new ArrayList<Worker>();
        for (Worker w : workers.values()) {
            if (w.getPosition().equals(position)) {
                filtered.add(w);
            }
        }

        Collections.sort(filtered, new Comparator<Worker>() {
            public int compare(Worker w1, Worker w2) {
                long t1 = w1.getTotalTimeInMinutes();
                long t2 = w2.getTotalTimeInMinutes();
                if (t1 != t2) return Long.compare(t2, t1); // Descending time
                return w1.getWorkerId().compareTo(w2.getWorkerId()); // Ascending name
            }
        });

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < Math.min(n, filtered.size()); i++) {
            Worker w = filtered.get(i);
            result.add(w.getWorkerId() + "(" + w.getTotalTimeInMinutes() + ")");
        }
        return result;
    }

    public double calcSalary(String workerId, long startSeconds, long endSeconds) {
        if (!workers.containsKey(workerId)) return 0.0;

        Worker worker = workers.get(workerId);
        Date start = new Date(startSeconds * 1000);
        Date end = new Date(endSeconds * 1000);

        return worker.calculateSalary(start, end);
    }
}
