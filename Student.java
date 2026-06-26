import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private String name;
    private String studentID;
    private List<Double> grades;
    
    // Constructor
    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.grades = new ArrayList<>();
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getStudentID() {
        return studentID;
    }
    
    public List<Double> getGrades() {
        return grades;
    }
    
    // Add a grade
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade! Please enter a grade between 0 and 100.");
        }
    }
    
    // Calculate average
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    // Get letter grade
    public String getLetterGrade() {
        if (grades.isEmpty()) {
            return "N/A";
        }
        double average = calculateAverage();
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
    
    // Get highest grade
    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double max = grades.get(0);
        for (double grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }
    
    // Get lowest grade
    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double min = grades.get(0);
        for (double grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
    
    // Display student info
    public void displayInfo() {
        System.out.println("\n========== FINAL 8-SEMESTER REPORT ==========");
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentID);
        System.out.println("Semesters Recorded: " + grades.size() + " / 8");
        System.out.println("Semester-by-Semester Grades: " + grades);
        
        if (grades.isEmpty()) {
            System.out.println("Overall Average: N/A");
            System.out.println("Final Letter Grade: N/A");
            System.out.println("Best Semester Grade: N/A");
            System.out.println("Lowest Semester Grade: N/A");
        } else {
            System.out.println("Overall Average: " + String.format("%.2f", calculateAverage()));
            System.out.println("Final Letter Grade: " + getLetterGrade());
            System.out.println("Best Semester Grade: " + getHighestGrade());
            System.out.println("Lowest Semester Grade: " + getLowestGrade());
        }
        System.out.println("=============================================\n");
    }

    // ==========================================
    // Updated Main Method for 8 Semesters
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int TOTAL_SEMESTERS = 8; // Caps the tracker strictly to 8 semesters
        
        // 1. Get student identity
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        Student student = new Student(name, id);
        
        // 2. Loop line-by-line exactly 8 times
        System.out.println("\n--- Entering Grades for " + TOTAL_SEMESTERS + " Semesters ---");
        
        for (int i = 1; i <= TOTAL_SEMESTERS; i++) {
            while (true) { // Internal loop to force a valid input for the current semester
                System.out.print("Enter grade for Semester " + i + " (0-100): ");
                
                if (!scanner.hasNextDouble()) {
                    System.out.println("Error: Please enter a valid numerical grade.");
                    scanner.next(); // Clear invalid input
                    continue;
                }
                
                double grade = scanner.nextDouble();
                
                // Validate bounds before moving to the next semester
                if (grade >= 0 && grade <= 100) {
                    student.addGrade(grade);
                    break; // Successfully added, break inner loop to proceed to next semester
                } else {
                    System.out.println("Invalid grade! Must be between 0 and 100. Try again.");
                }
            }
        }
        
        // 3. Print the complete 8-semester breakdown
        student.displayInfo();
        scanner.close();
    }
}