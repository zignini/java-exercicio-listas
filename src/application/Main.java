package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
        Fazer um programa para ler um número inteiro N e depois os
        dados (id, nome e salário) de N funcionários. Não deve haver
        repetição de id. Em seguida, efetuar o aumento de X por cento
        no salário de determinado funcionário. Para isso, o programa
        deve ler um id e o valor X. Se o id informado não existir,
        mostrar uma mensagem e abortar a operação. Ao final, mostrar
        a listagem atualizada dos funcionários, conforme exemplos.
        Lembre-se de usar a técnica de encapsulamento para não
        permitir que o salário possa ser mudado livremente. Um
        salário só pode ser aumentado com base em uma operação de
        aumento por porcentagem dada.

        Exemplo:
        How many employees will be registered? [3]

        Employee #1:
        Id: [333]
        Name: [Maria Brown]
        Salary: [4000.0]

        Employee #2:
        Id: [536]
        Name: [Alex Grey]
        Salary: [3000.0]

        Employee #3:
        Id: [772]
        Name: [Bob Green]
        Salary: [5000.0]

        Enter the employee id that will have salary increase: [536]
        Enter the percentage: [10.0]

        List of employees:

        333, Maria Brown, 4000.00
        536, Alex Grey, 3300.00
        772, Bob Green, 5000.00
        */
        
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        System.out.print("How many employees will be registered? ");
        int amountOfEmployees = sc.nextInt();
        sc.nextLine();

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < amountOfEmployees; i++) {
            System.out.println("Employee #" + ((int) i + 1) + ": ");

            System.out.print("Id: ");
            int id = sc.nextInt();
            sc.nextLine();

            while (employeeListHasId(employees, id)) {
                System.out.print("Id taken! Try again: ");
                id = sc.nextInt();
                sc.nextLine();
            }

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            Employee newEmployee = new Employee(id, name, salary);
            employees.add(newEmployee);
        }

        System.out.print("Enter the employee id that will have " +
                "salary increase: ");
        int id = sc.nextInt();
        sc.nextLine();
        Employee employee = findEmployeeById(employees, id);

        if (employee != null) {
            System.out.print("Enter the percentage: ");
            double percentage = sc.nextDouble();
            sc.nextLine();

            employee.increaseSalary(percentage);
        } else {
            System.out.println("This id does not exist!");
        }

        for (Employee anyEmployee : employees) {
            System.out.println(anyEmployee);
        }

        sc.close();
    }

//    public static Employee findEmployee (List<Employee> list,
//                                         int id) {
//        for (Employee employee : list) {
//            if (employee.getId() == id) {
//                return employee;
//            }
//        }
//        return null;
//    }

    public static Employee findEmployeeById (List<Employee> list,
                                         int id) {
        return list.stream().filter(emp ->
                emp.getId() == id).findFirst().orElse(null);
    }

    public static boolean employeeListHasId (List<Employee> list,
                                             int id) {
        Employee employee = findEmployeeById(list, id);
        return employee != null;
    }
}
