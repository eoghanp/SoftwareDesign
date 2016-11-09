package Users;

public class Employee extends Person{

    private double salary;

    public Employee(String first, String last, String mail, String pass, String add, String phone, Double salary) {
        super(first, last, mail, pass, add, phone);
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "salary=" + salary + '}';
    }
    
}
