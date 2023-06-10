import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String grade;
    
    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getGrade() {
        return grade;
    }
}

public class StudentManagementSystem {
    private List<Student> students;
    
    public StudentManagementSystem() {
        students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void displayStudents() {
        for (Student student : students) {
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Grade: " + student.getGrade());
            System.out.println("-----------------------------");
        }
    }
    
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        
        Student student1 = new Student("John Doe", 18, "A");
        sms.addStudent(student1);
        
        Student student2 = new Student("Jane Smith", 17, "B");
        sms.addStudent(student2);
        
        sms.displayStudents();
    }
}
