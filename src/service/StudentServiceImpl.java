package service;

import exception.StudentNotFoundException;
import model.Student;

import java.io.*;
import java.util.*;

public class StudentServiceImpl implements IStudentService {
    // HashMap for O(1) key lookups by ID
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private final String filePath = "data/students.txt";

    public StudentServiceImpl() {
        loadFromFile();
    }

    @Override
    public synchronized void addStudent(Student student) {
        studentMap.put(student.getStudentId(), student);
    }

    @Override
    public synchronized List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public synchronized Student searchStudentById(int id) throws StudentNotFoundException {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException("Student with ID " + id + " not found.");
        }
        return studentMap.get(id);
    }

    @Override
    public synchronized void updateStudent(int id, String newCourse, double newMarks) throws StudentNotFoundException {
        Student s = searchStudentById(id);
        s.setCourse(newCourse);
        s.setMarks(newMarks);
    }

    @Override
    public synchronized void deleteStudent(int id) throws StudentNotFoundException {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException("Cannot delete: Student ID " + id + " does not exist.");
        }
        studentMap.remove(id);
    }

    @Override
    public synchronized void saveToFile() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : studentMap.values()) {
                bw.write(s.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("File write error: " + e.getMessage());
        }
    }

    @Override
    public synchronized void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student s = Student.fromCsv(line);
                    studentMap.put(s.getStudentId(), s);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("File loading error: " + e.getMessage());
        }
    }
}