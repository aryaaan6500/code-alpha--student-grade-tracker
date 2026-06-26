import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGradeTracker {
    private List<Student> students;
    private Scanner scanner;
    
    // Constructor
    public StudentGradeTracker() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    // Add a new student
    public void addStudent(String name, String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                System.out.println("Student with this ID already exists!");
                return;
            }
        }
        students.add(new Student(name, studentID));
        System.out.println("✓ Student added successfully!");
    }
    
    // Find student by ID
    public Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }
    
    // Add grade to student
    public void addGradeToStudent(String studentID, double grade) {
        Student student = findStudent(studentID);
        if (student != null) {
            student.addGrade(grade);
            System.out.println("✓ Grade added successfully!");
        } else {
            System.out.println("✗ Student not found!");
        }
    }
    
    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n✗ No students in the system!");
            return;
        }
        System.out.println("\n========== ALL STUDENTS ==========");
        for (Student student : students) {
            System.out.println(student.getName() + " (ID: " + student.getStudentID() + 
                             ") - Average: " + String.format("%.2f", student.calculateAverage()) +
                             " - Grade: " + student.getLetterGrade());
        }
        System.out.println("==================================\n");
    }
    
    // Display student details
    public void displayStudentDetails(String studentID) {
        Student student = findStudent(studentID);
        if (student != null) {
            student.displayInfo();
        } else {
            System.out.println("✗ Student not found!");
        }
    }
    
    // Remove student
    public void removeStudent(String studentID) {
        Student student = findStudent(studentID);
        if (student != null) {
            students.remove(student);
            System.out.println("✓ Student removed successfully!");
        } else {
            System.out.println("✗ Student not found!");
        }
    }
    
    // Calculate class average
    public void displayClassStatistics() {
        if (students.isEmpty()) {
            System.out.println("✗ No students in the system!");
            return;
        }
        
        double totalAverage = 0;
        double highestAverage = 0;
        double lowestAverage = 100;
        String topStudent = "";
        String bottomStudent = "";
        
        for (Student student : students) {
            double avg = student.calculateAverage();
            totalAverage += avg;
            
            if (avg > highestAverage) {
                highestAverage = avg;
                topStudent = student.getName();
            }
            if (avg < lowestAverage) {
                lowestAverage = avg;
                bottomStudent = student.getName();
            }
        }
        
        System.out.println("\n========== CLASS STATISTICS ==========");
        System.out.println("Total Students: " + students.size());
        System.out.println("Class Average: " + String.format("%.2f", totalAverage / students.size()));
        System.out.println("Highest Average: " + String.format("%.2f", highestAverage) + " (" + topStudent + ")");
        System.out.println("Lowest Average: " + String.format("%.2f", lowestAverage) + " (" + bottomStudent + ")");
        System.out.println("=======================================\n");
    }
    
    // Interactive menu
    public void showMenu() {
        System.out.println("\n===== STUDENT GRADE TRACKER =====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View All Students");
        System.out.println("4. View Student Details");
        System.out.println("5. View Class Statistics");
        System.out.println("6. Remove Student");
        System.out.println("7. Exit");
        System.out.println("==================================");
        System.out.print("Enter your choice (1-7): ");
    }
    
    // Run the application
    public void run() {
        boolean running = true;
        
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine().trim();
                    addStudent(name, studentID);
                    break;
                    
                case "2":
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter grade (0-100): ");
                    try {
                        double grade = Double.parseDouble(scanner.nextLine().trim());
                        addGradeToStudent(id, grade);
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input! Please enter a valid number.");
                    }
                    break;
                    
                case "3":
                    displayAllStudents();
                    break;
                    
                case "4":
                    System.out.print("Enter student ID: ");
                    String detailID = scanner.nextLine().trim();
                    displayStudentDetails(detailID);
                    break;
                    
                case "5":
                    displayClassStatistics();
                    break;
                    
                case "6":
                    System.out.print("Enter student ID to remove: ");
                    String removeID = scanner.nextLine().trim();
                    removeStudent(removeID);
                    break;
                    
                case "7":
                    System.out.println("Thank you for using Student Grade Tracker. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("✗ Invalid choice! Please enter 1-7.");
            }
        }
        
        scanner.close();
    }
    
    // Main method
    public static void main(String[] args) {
        StudentGradeTracker tracker = new StudentGradeTracker();
        tracker.run();
    }
}
