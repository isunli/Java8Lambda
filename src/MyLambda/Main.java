package MyLambda;

import org.w3c.dom.ls.LSOutput;

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
            // employee.setAge(4); // mutable class, but final
            // employee = employees.get(1); // not final
            new Thread(()-> System.out.println("From thread ----------"+employee.getAge())).start(); // here local varialbe employee is effictivly final
        }

        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });



        String sillyString = doStringStuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(), employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

        UpperConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + s2.toUpperCase(); // assumed return
            return result;
        };
        sillyString = doStringStuff(uc, "aa", "bb");
        System.out.println(sillyString);

        AnotherClass anotherClass = new AnotherClass();
        String s = anotherClass.doSomething3();
        System.out.println(s);

    }

    public static String doStringStuff(UpperConcat uc, String s1, String s2) {
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

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
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

class AnotherClass {
    public String doSomething() {
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, "String1", "String2");
    }

    public String doSomething2() {
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff((s1, s2) -> {
            System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
            return s1.toUpperCase() + s2.toUpperCase();

        }, "String1", "String2"); // lambda is treated as a nested block of code instead of a new class
    }

    public String doSomething3(){
        final int i=0;
        int i2 = 0;

        UpperConcat uc2 = (s1,s2)->{

            System.out.println(i2);
            return s1.toUpperCase()+s2.toUpperCase();
        }; // imagine this code is running inside a code block

        {
            UpperConcat uc = new UpperConcat() {
                @Override
                public String upperAndConcat(String s1, String s2) {
                    System.out.println("i (within anonymous class) = "+i);
                    // local variable in anonymous class must be
                    // final (value copied from local variable to anonymous class,
                    // will get out of sync)

                    return s1.toUpperCase()+s2.toUpperCase();
                }


            };





            return Main.doStringStuff(uc, "String1","String2");
        }



    }
    public void printValue() {
        int number = 25;
        Runnable r = () -> {
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("The value is "+number);
        };
        new Thread(r).start();
    }

}