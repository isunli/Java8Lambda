package MyStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList("N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "O71");
        List<String> gNumbers = new ArrayList<>();
        someBingoNumbers.forEach(e->{
            if(e.toUpperCase().startsWith("G")){
                gNumbers.add(e);
                //System.out.println(e);
            }
        });

        gNumbers.sort((s1,s2)->s1.compareTo(s2));
        gNumbers.forEach(s-> System.out.println(s));

        // Stream
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .forEach(System.out::println); // terminal operation

        Stream<String> ioNumberStream = Stream.of("I26","I17","I29","O71");
        Stream<String> inNumberStream = Stream.of("N40","N36","I26","I17","I29","O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream,inNumberStream);
        //System.out.println(concatStream.distinct().count());
        // peak is not a terminal operation
        System.out.println("----------------------------------");
        System.out.println(concatStream.distinct().peek(System.out::println).count());

        Employee john = new Employee("John Dow", 30);
        Employee jane = new Employee("Jane Deer", 30);
        Employee jack = new Employee("Jack Hill", 30);
        Employee snow = new Employee("Snow White", 30);

        Department hr = new Department("Human Resources");
        hr.addEmployee(john);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(d -> d.getEmployees().stream())
                .forEach(System.out::println);

//        List<String> sortedGNumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList());
        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                // constructor, add, combine
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for (String s: sortedGNumbers){
            System.out.println(s);
        }
        Map<Integer, List<Employee>> groupedByAge = departments
                .stream()
                .flatMap(d -> d.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));


        departments.stream() // get yougest employee
                .flatMap(d -> d.getEmployees().stream())
                .reduce((e1,e2)->e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println); // all operation is lazy, will perform on terminal operation

        var s1 = Stream.of("ABC","AC","BAA","CCCC","XY","ST")
                .filter(s->{
                    System.out.println(s);
                    return s.length() == 3;
                });
        System.out.println("starting terminal op");
        s1.count();





    }
}
