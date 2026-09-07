import exception.StudentNotFoundException;
import model.Student;
import service.AutoSaveThread;
import service.IStudentService;
import service.StudentServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IStudentService service = new StudentServiceImpl();
        
        // Spawn multithreaded auto-saver daemon
        Thread autoSave = new Thread(new AutoSaveThread(service));
        autoSave.setDaemon(true);
        autoSave.start();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n================ STUDENT MANAGEMENT SYSTEM ================");
            System.out.println("1. Add Student Record");
            System.out.println("2. View All Records");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Details");
            System.out.println("5. Delete Student Record");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // clear buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numerical choice.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Student ID (Integer): ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();

                        service.addStudent(new Student(id, name, age, course, marks));
                        System.out.println("Student record added successfully!");
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid numeric input entered. Operation aborted.");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    List<Student> records = service.getAllStudents();
                    if (records.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        System.out.println("\n--- Registered Students ---");
                        for (Student s : records) {
                            System.out.println(s.getDetails());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to Search: ");
                    try {
                        int searchId = sc.nextInt();
                        Student found = service.searchStudentById(searchId);
                        System.out.println("Record Found: " + found.getDetails());
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID format.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to Update: ");
                    try {
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        service.searchStudentById(updateId); // verify existence first

                        System.out.print("Enter New Course: ");
                        String newCourse = sc.nextLine();
                        System.out.print("Enter New Marks: ");
                        double newMarks = sc.nextDouble();

                        service.updateStudent(updateId, newCourse, newMarks);
                        System.out.println("Student details updated successfully!");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format.");
                        sc.nextLine();
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID to Delete: ");
                    try {
                        int deleteId = sc.nextInt();
                        service.deleteStudent(deleteId);
                        System.out.println("Record deleted successfully.");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID format.");
                        sc.nextLine();
                    }
                    break;

                case 6:
                    service.saveToFile();
                    System.out.println("Data saved to data/students.txt. Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid selection. Choose between 1 and 6.");
            }
        }
    }
}