package Employees;

import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Cook", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("R R", 22);
        Employee charming = new Employee("P C", 22);

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

        printEmployeesByAge(employees, "Employees over 40", employee -> employee.getAge() > 40);
        printEmployeesByAge(employees, "Employees under 40", employee -> employee.getAge() <= 40);
        printEmployeesByAge(employees, "Employees over 35", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <= 35;
            }
        });

        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.test(5));
        int a = 20;
        System.out.println(greaterThan15.test(a + 5));
        System.out.println(greaterThan15.and(lessThan100).test(30));



        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for(int i =0;i<10;i++){
            System.out.println(random.nextInt(1000));
            System.out.println(randomSupplier.get());
        }

        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(' ')+1);
            System.out.println("Last Name is"+lastName);
        });

        // ================================ Function Interface
        Function<Employee, String> getLastName = (Employee e) ->{
            return e.getName().substring(e.getName().indexOf(' ')+1);
        };
        String lastName = getLastName.apply(employees.get(2));
        System.out.println(lastName);

        Function<Employee, String> getFristName = employee -> {
            return employee.getName().substring(0,employee.getName().indexOf(' '));
        };
        for(Employee employee: employees){
            if(random.nextBoolean()){
                System.out.println(getAName(getFristName,employee));
            }else{
                System.out.println(getAName(getLastName,employee));
            }
        }
        Function<Employee, String> upperCase = e -> e.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0,name.indexOf(' '));
        Function chainFunction = upperCase.andThen(firstName);
        System.out.println(chainFunction.apply(employees.get(1)));

        BiFunction<String,Employee,String> concatAge = (String name, Employee employee)->{
            return name.concat(" "+employee.getAge());
        };
        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName,employees.get(0)));

        IntUnaryOperator incBy5 = i->i+5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s->s.toUpperCase();
        Consumer<String> c2 = s-> System.out.println(s);
        c1.andThen(c2).accept("hello");

    }
    private static String getAName(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        System.out.println("=====================================");
        for (Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }

}
