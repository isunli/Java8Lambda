package MyLambda;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Thread(new CodeToRun()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from the anonymous class");
            }
        }).start();

        new Thread(() -> {
            System.out.println("Printing from the lambda");
            System.out.println("Second line of lambda");
        }).start();
        // Thread constructor needs a runnable object
        // Runnable interface only have one method to implement (functional interface)
        // this method will injected to public void run()


        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Cook", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        // comparator is also a functional interface
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        Collections.sort(employees, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }


        String sillyString = doStingStuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(), employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase(); // assumed return
        sillyString = doStingStuff(uc, "aa", "bb");
        System.out.println(sillyString);

    }

    public static String doStingStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }


}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
}

class CodeToRun implements Runnable {
    @Override
    public void run() {
        System.out.println("Printing from the runnable");
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}