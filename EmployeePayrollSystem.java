import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
}

public class EmployeePayrollSystem {
    private List<Employee> employees;
    
    public EmployeePayrollSystem() {
        employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public void displayEmployees() {
        for (Employee employee : employees) {
            System.out.println("Name: " + employee.getName());
            System.out.println("Salary: $" + employee.getSalary());
            System.out.println("-----------------------------");
        }
    }
    
    public static void main(String[] args) {
        EmployeePayrollSystem eps = new EmployeePayrollSystem();
        
        Employee employee1 = new Employee("John Doe", 5000.0);
        eps.addEmployee(employee1);
        
        Employee employee2 = new Employee("Jane Smith", 6000.0);
        eps.addEmployee(employee2);
        
        eps.displayEmployees();
    }
}
