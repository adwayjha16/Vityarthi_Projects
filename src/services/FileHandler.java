package services;

import models.Task;
import java.io.*;
import java.util.*;

public class FileHandler {

    public static void saveTasks(ArrayList<Task> tasks) throws Exception {
        FileWriter fw = new FileWriter("tasks.txt");
        for (Task t : tasks) fw.write(t.toFileString() + "\n");
        fw.close();
    }

    public static ArrayList<Task> loadTasks() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("tasks.txt");
        if (!f.exists()) return tasks;
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task t = Task.fromFileString(line);
            if (t != null) tasks.add(t);
        }
        sc.close();
        return tasks;
    }
}
