package service;

import exception.StudentNotFoundException;
import model.Student;
import java.util.List;

public interface IStudentService {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student searchStudentById(int id) throws StudentNotFoundException;
    void updateStudent(int id, String newCourse, double newMarks) throws StudentNotFoundException;
    void deleteStudent(int id) throws StudentNotFoundException;
    void saveToFile();
    void loadFromFile();
}