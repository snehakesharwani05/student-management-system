package model;

public class Student extends Person {
    private static final long serialVersionUID = 1L;
    private static int totalStudents = 0; // static variable tracking count

    private int studentId;
    private String course;
    private double marks;

    public Student(int studentId, String name, int age, String course, double marks) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
        this.marks = marks;
        totalStudents++;
    }

    public int getStudentId() { return studentId; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public static int getTotalStudents() {
        return totalStudents;
    }

    @Override
    public String getDetails() {
        return String.format("ID: %-5d | Name: %-15s | Age: %-3d | Course: %-12s | Marks: %-5.2f",
                studentId, getName(), getAge(), course, marks);
    }

    // CSV format for persistent text file storage
    public String toCsv() {
        return studentId + "," + getName() + "," + getAge() + "," + course + "," + marks;
    }

    public static Student fromCsv(String csv) {
        String[] parts = csv.split(",");
        return new Student(
            Integer.parseInt(parts[0].trim()),
            parts[1].trim(),
            Integer.parseInt(parts[2].trim()),
            parts[3].trim(),
            Double.parseDouble(parts[4].trim())
        );
    }
}