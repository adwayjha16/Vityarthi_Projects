package services;

import models.Task;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Task lastDeleted = null;

    public void addTask(Task t) { tasks.add(t); }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : tasks) System.out.println(t);
    }

    // colored output version
    public void displayTasksWithColors() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        LocalDate today = LocalDate.now();
        for (Task t : tasks) {
            LocalDate d = t.getDeadlineAsDate();
            String line = t.toString();
            if (t.isCompleted()) {
                System.out.println(Color.GREEN + line + Color.RESET);
            } else if (d != null && d.isBefore(today)) {
                System.out.println(Color.RED + line + " (OVERDUE)" + Color.RESET);
            } else if (d != null && ChronoUnit.DAYS.between(today, d) <= 3) {
                System.out.println(Color.YELLOW + line + " (DUE SOON)" + Color.RESET);
            } else {
                System.out.println(line);
            }
        }
    }

    public void editTask(String title, Scanner sc) {
        for (Task t : tasks) {
            if (t.getTitle().equalsIgnoreCase(title)) {
                System.out.print("New Title (leave blank to keep): ");
                String nt = sc.nextLine();
                if (nt.isEmpty()) nt = t.getTitle();
                System.out.print("New Subject (leave blank to keep): ");
                String ns = sc.nextLine();
                if (ns.isEmpty()) ns = t.getSubject();
                System.out.print("New Category (leave blank to keep): ");
                String nc = sc.nextLine();
                if (nc.isEmpty()) nc = t.getCategory();
                System.out.print("New Deadline (DD/MM) (leave blank to keep): ");
                String nd = sc.nextLine();
                if (nd.isEmpty()) nd = t.getDeadline();
                System.out.print("New Priority (1-5) (enter 0 to keep): ");
                int np = sc.nextInt(); sc.nextLine();
                if (np == 0) np = t.getPriority();

                t.update(nt, ns, nc, nd, np);
                System.out.println("Task updated!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    public void deleteTask(String title) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.getTitle().equalsIgnoreCase(title)) {
                lastDeleted = t;
                it.remove();
                System.out.println("Task deleted! Use 'Undo Delete' to restore.");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    public void undoDelete() {
        if (lastDeleted != null) {
            tasks.add(lastDeleted);
            System.out.println("Restored: " + lastDeleted.getTitle());
            lastDeleted = null;
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void searchTasks(String keyword) {
        boolean found = false;
        for (Task t : tasks) {
            if (t.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                t.getSubject().toLowerCase().contains(keyword.toLowerCase()) ||
                t.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No matching tasks.");
    }

    public void sortMenu(Scanner sc) {
        System.out.println("Sort by: 1) Deadline 2) Priority 3) Subject 4) Status");
        int c = sc.nextInt(); sc.nextLine();
        switch(c) {
            case 1: sortByDeadline(); break;
            case 2: sortByPriority(); break;
            case 3: sortBySubject(); break;
            case 4: sortByStatus(); break;
            default: System.out.println("Invalid option.");
        }
    }

    public void sortByDeadline() {
        tasks.sort((a,b) -> {
            LocalDate da = a.getDeadlineAsDate();
            LocalDate db = b.getDeadlineAsDate();
            if (da == null && db == null) return 0;
            if (da == null) return 1;
            if (db == null) return -1;
            return da.compareTo(db);
        });
        System.out.println("Sorted by deadline.");
        displayTasksWithColors();
    }

    public void sortByPriority() {
        tasks.sort((a,b) -> Integer.compare(b.getPriority(), a.getPriority()));
        System.out.println("Sorted by priority (high to low).");
        displayTasksWithColors();
    }

    public void sortBySubject() {
        tasks.sort((a,b) -> a.getSubject().compareToIgnoreCase(b.getSubject()));
        System.out.println("Sorted by subject.");
        displayTasksWithColors();
    }

    public void sortByStatus() {
        tasks.sort((a,b) -> Boolean.compare(a.isCompleted(), b.isCompleted()));
        System.out.println("Sorted by completion status (pending first).");
        displayTasksWithColors();
    }

    public void showDeadlineAlerts() {
        LocalDate today = LocalDate.now();
        boolean any = false;
        for (Task t : tasks) {
            if (t.isCompleted()) continue;
            LocalDate d = t.getDeadlineAsDate();
            if (d == null) continue;
            long days = ChronoUnit.DAYS.between(today, d);
            if (d.isBefore(today)) {
                System.out.println(Color.RED + "OVERDUE: " + t + Color.RESET);
                any = true;
            } else if (days <= 3) {
                System.out.println(Color.YELLOW + "DUE SOON ("+days+" days): " + t + Color.RESET);
                any = true;
            }
        }
        if (!any) System.out.println("No urgent tasks in the next 3 days.");
    }

    public void showDashboard() {
        System.out.println("\n=== Dashboard Analytics ===");
        long total = tasks.size();
        long completed = tasks.stream().filter(Task::isCompleted).count();
        long pending = total - completed;
        System.out.println("Total Tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);

        HashMap<String, Integer> subjectCount = new HashMap<>();
        HashMap<String, Integer> categoryCount = new HashMap<>();
        for (Task t : tasks) {
            subjectCount.put(t.getSubject(), subjectCount.getOrDefault(t.getSubject(), 0) + 1);
            categoryCount.put(t.getCategory(), categoryCount.getOrDefault(t.getCategory(), 0) + 1);
        }

        System.out.println("\nTasks Per Subject:");
        for (String s : subjectCount.keySet()) {
            System.out.println(s + ": " + subjectCount.get(s));
        }
        System.out.println("\nTasks Per Category:");
        for (String c : categoryCount.keySet()) {
            System.out.println(c + ": " + categoryCount.get(c));
        }
    }

    public ArrayList<Task> getTasks() { return tasks; }
}
