# Vityarthi_Projects

📘 Smart Study Planner Pro

A powerful Java console-based productivity tool designed to help students efficiently manage study tasks with smart sorting, deadline alerts, categories, colored output, analytics, and undo functionality.

⸻

🚀 Features

✅ 1. Add Tasks

Create tasks with:
	•	Title
	•	Subject
	•	Category (Assignment, Exam, Project, Revision, Daily, Custom)
	•	Deadline (DD/MM)
	•	Priority (1–5)



✏️ 2. Edit Tasks

Modify any existing task’s:
	•	Title
	•	Subject
	•	Category
	•	Deadline
	•	Priority



🗑️ 3. Delete Tasks + Undo Delete

If you delete a task accidentally → restore it instantly using Undo Delete.



🔍 4. Search Tasks

Search by:
	•	Title keywords
	•	Subject
	•	Category



📊 5. Dashboard Analytics

Displays:
	•	Total tasks
	•	Completed tasks
	•	Pending tasks
	•	Tasks per subject
	•	Tasks per category

🗂️ 6. Sort Tasks

Sort your tasks by:
	•	Deadline
	•	Priority
	•	Subject
	•	Completion status



⏰ 7. Deadline Alerts

Automatically shows:
	•	⚠️ Tasks due within 3 days
	•	❌ Overdue tasks



🎨 8. Colored Terminal Output
	•	🟢 Green → Completed tasks
	•	🟡 Yellow → Due soon
	•	🔴 Red → Overdue
	•	⚪ Default → Normal tasks



📁 Project Structure

SmartStudyPlannerPro/
│
├── src/
│   ├── Main.java
│   ├── models/
│   │     └── Task.java
│   ├── services/
│   │     ├── TaskManager.java
│   │     ├── FileHandler.java
│   │     └── Color.java
│
├── tasks.txt
└── README.md


⸻

🛠️ Technologies Used
	•	Java 17+ / Java 25
	•	OOP (Classes, Objects, Encapsulation)
	•	File Handling
	•	ArrayList (Collections Framework)
	•	Packages & Modular Design



▶️ How to Run the Project

Using VS Code
	1.	Open the folder SmartStudyPlannerPro
	2.	Open src/Main.java
	3.	Press Run ▶️

Using Terminal

cd SmartStudyPlannerPro/src
javac Main.java
java Main


📦 Data Storage

All tasks are automatically saved to:

tasks.txt

This ensures data is kept even after closing the app.

⸻

🎯 Future Enhancements
	•	Export to CSV/JSON
	•	Login system with multiple users
	•	GUI version using JavaFX
	•	Reminder notifications

⸻

👨‍💻 Author

Adway Jha
VIT Bhopal University
GitHub: adwayjha16
