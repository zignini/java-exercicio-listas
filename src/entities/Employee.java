package entities;
public class Employee {

    private final int id;
    private final String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + String.format("%.2f", salary);
    }

    public int getId() {
        return id;
    }

    public void increaseSalary(double percentage) {
        this.salary += this.salary * (percentage / 100);
    }
}
