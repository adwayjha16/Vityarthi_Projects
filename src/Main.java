import java.util.*;
import services.TaskManager;
import services.FileHandler;
import models.Task;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskManager manager = new TaskManager();
        manager.getTasks().addAll(FileHandler.loadTasks());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Smart Study Planner Pro ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Undo Delete");
            System.out.println("6. Search Task");
            System.out.println("7. Sort Tasks");
            System.out.println("8. Show Deadline Alerts");
            System.out.println("9. Dashboard Analytics");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Subject: ");
                    String s = sc.nextLine();
                    System.out.print("Category (Assignment/Exam/Project/Revision/Daily/Custom): ");
                    String cat = sc.nextLine();
                    System.out.print("Deadline (DD/MM): ");
                    String d = sc.nextLine();
                    System.out.print("Priority (1-5): ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    manager.addTask(new Task(t, s, cat, d, p));
                    FileHandler.saveTasks(manager.getTasks());
                    break;
                case 2:
                    manager.displayTasksWithColors();
                    break;
                case 3:
                    System.out.print("Enter title to edit: ");
                    manager.editTask(sc.nextLine(), sc);
                    FileHandler.saveTasks(manager.getTasks());
                    break;
                case 4:
                    System.out.print("Enter title to delete: ");
                    manager.deleteTask(sc.nextLine());
                    FileHandler.saveTasks(manager.getTasks());
                    break;
                case 5:
                    manager.undoDelete();
                    FileHandler.saveTasks(manager.getTasks());
                    break;
                case 6:
                    System.out.print("Search keyword: ");
                    manager.searchTasks(sc.nextLine());
                    break;
                case 7:
                    manager.sortMenu(sc);
                    FileHandler.saveTasks(manager.getTasks());
                    break;
                case 8:
                    manager.showDeadlineAlerts();
                    break;
                case 9:
                    manager.showDashboard();
                    break;
                case 10:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
