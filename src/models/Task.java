package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String title;
    private String subject;
    private String category;
    private String deadline; // stored as DD/MM
    private int priority;
    private boolean isCompleted;

    public Task(String title, String subject, String category, String deadline, int priority) {
        this.title = title;
        this.subject = subject;
        this.category = category;
        this.deadline = deadline;
        this.priority = priority;
        this.isCompleted = false;
    }

    public void update(String title, String subject, String category, String deadline, int priority) {
        this.title = title;
        this.subject = subject;
        this.category = category;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getCategory() { return category; }
    public int getPriority() { return priority; }
    public String getDeadline() { return deadline; }
    public boolean isCompleted() { return isCompleted; }

    public void markCompleted() { this.isCompleted = true; }

    public String toFileString() {
        return title + "||" + subject + "||" + category + "||" + deadline + "||" +
                priority + "||" + isCompleted;
    }

    public static Task fromFileString(String data) {
        String[] p = data.split("\\|\\|");
        if (p.length < 6) return null;
        Task t = new Task(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]));
        if (Boolean.parseBoolean(p[5])) t.markCompleted();
        return t;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "✔" : "✗") + "] " + title +
                " | Subject: " + subject +
                " | Category: " + category +
                " | Deadline: " + deadline +
                " | Priority: " + priority;
    }

    // Return LocalDate assuming current year. If parsing fails, return null.
    public LocalDate getDeadlineAsDate() {
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/yyyy");
            String[] parts = deadline.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = java.time.LocalDate.now().getYear();
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            return null;
        }
    }
}
