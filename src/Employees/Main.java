package Employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Cook", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("RR", 22);
        Employee charming = new Employee("PC", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);


        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

//        for(Employee employee: employees){
//            if(employee.getAge()>30){
//                System.out.println(employee.getName());
//            }
//        }

        printEmployeesByAge(employees,"Employees over 40",employee -> employee.getAge() > 40);
        printEmployeesByAge(employees,"Employees under 40",employee -> employee.getAge() <= 40);

    }

    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition){
        System.out.println(ageText);
        System.out.println("=====================================");
        for(Employee employee:employees){
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }
    }

}
