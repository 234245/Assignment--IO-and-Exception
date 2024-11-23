import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private int marks;

    // Constructor
    public Student(String name, int rollNumber, int marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Marks: " + marks;
    }
}

public class StudentManagement {
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        String filePath = "students.csv";
        loadStudentsFromCSV(filePath);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Display All Students");
            System.out.println("2. Search Student by Roll Number");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayAllStudents();
                case 2 -> searchStudent(scanner);
                case 3 -> updateStudent(scanner);
                case 4 -> deleteStudent(scanner);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to load students from a CSV file
    private static void loadStudentsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int rollNumber = Integer.parseInt(data[1]);
                int marks = Integer.parseInt(data[2]);
                students.add(new Student(name, rollNumber, marks));
            }
            System.out.println("Students loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    //display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Method to search for a student by roll number
    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to search: ");
        int rollNumber = scanner.nextInt();
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Method to update a student's marks
    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to update marks: ");
        int rollNumber = scanner.nextInt();
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.print("Enter new marks: ");
                int newMarks = scanner.nextInt();
                student.setMarks(newMarks);
                System.out.println("Marks updated successfully.");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // delete a student record
    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Roll Number to delete record: ");
        int rollNumber = scanner.nextInt();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRollNumber() == rollNumber) {
                iterator.remove();
                System.out.println("Student record deleted successfully.");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }
}
