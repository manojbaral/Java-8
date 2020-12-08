package com.manoj.lamda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Manoj Baral on 12/3/2020.
 */
public class Main {
    public static void main(String[] args) {

        //Challenges
        System.out.println("\n*********Challenge 2*************8");
        Challenge challenge =new Challenge();
        System.out.println(challenge.lamdaFunction.apply("1234567890"));


        Employee jay = new Employee("jay", 10);
        Employee john = new Employee("John Doe", 20);
        Employee hashtag = new Employee("Hash Tag", 30);
        Employee manoj = new Employee("Manoj Baral", 40);
        Employee slim = new Employee("Slim Jim", 50);
        Employee java = new Employee("java Back-end", 60);
        Employee python = new Employee("python scripting", 70);
        Employee angular = new Employee("angular front-end", 80);
        Employee node = new Employee("node Server-Side", 90);
        Employee sql = new Employee("sql Relationship-Database", 100);

        Department hr = new Department("Human Resources Department");
        hr.addEmployee(jay);
        hr.addEmployee(john);
        hr.addEmployee(hashtag);
        hr.addEmployee(manoj);
        hr.addEmployee(slim);

        Department programmer = new Department("Programmer Department");
        programmer.addEmployee(java);
        programmer.addEmployee(python);
        programmer.addEmployee(angular);
        programmer.addEmployee(node);
        programmer.addEmployee(sql);

        //Streams
        List<String> someBigoNumer = Arrays.asList(
            "N40", "N36",
            "B12", "B6",
            "G53", "G59", "G60", "G50",
            "I26", "I17", "I29",
            "071"
        );

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(programmer);

        departments.stream()
            .flatMap(department -> department.getEmployees().stream())
            .forEach(System.out::println);

//        System.out.println("\n****************************");
//        List<String> sortedGNumbers=someBigoNumer
//            .stream()
//            .map(String::toUpperCase)
//            .filter(s->s.startsWith("G"))
//            .sorted()
//            .collect(Collectors.toList());

        //collect and add all
        System.out.println("\n############################");
        List<String> sortedGNumbers = someBigoNumer
            .stream()
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("G"))
            .sorted()
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for (String s : sortedGNumbers) {
            System.out.println(s);
        }

        Map<Integer, List<Employee>> groupByAge = departments.stream()
            .flatMap(department -> department
                .getEmployees()
                .stream())
            .collect(Collectors.groupingBy(employee -> employee.getAge()));

        departments.stream()
            .flatMap(department ->
                department.
                    getEmployees().
                    stream())
            .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
            .ifPresent(System.out::println);

        //Filter Method
        Stream.of("ABC", "BAA", "AC", "CCCC", "XY", "ST")
            .filter(s -> {
                System.out.println(s);
                return s.length() == 3;
            })
            .count();

        List<String> gNumers = new ArrayList<>();

        someBigoNumer.forEach(number -> {
            if (number.startsWith("G")) {
                System.out.println(number);
            }
        });

        gNumers.sort(String::compareTo);
        gNumers.forEach((String s) -> System.out.println(s));

        someBigoNumer.stream()
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("G"))
            .sorted()
            .forEach(System.out::println);

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "071");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "071");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("\n==============================");
        System.out.println(concatStream
            .distinct()
            .peek(System.out::println)
            .count());


        new Thread(() -> {
            System.out.println("Printing from the Runnable");
            System.out.println("Line 2");
            System.out.format("This is line %d\n", 3);

        }).start();


        AnotherClass anotherClass = new AnotherClass();

        String s = anotherClass.doSomethings();
        System.out.println(s);

        List<Employee> employees = new ArrayList<>();
        employees.add(jay);
        employees.add(john);
        employees.add(hashtag);
        employees.add(manoj);
        employees.add(slim);
        employees.add(java);
        employees.add(python);
        employees.add(angular);
        employees.add(node);
        employees.add(sql);

        //Functional interface
        Function<Employee, String> getLastName = (Employee employee) -> employee.getName().substring(employee.getName().indexOf(' ') + 1);
        String LastName = getLastName.apply(employees.get(0));
        System.out.println(LastName);

        Function<Employee, String> getFirstName = (Employee employee) -> employee.getName().substring(0, employee.getName().indexOf(' '));

        Random random1 = new Random();
        for (Employee employee : employees) {
            if (random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));

            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(1, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(1)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> name.concat(" " + employee.getAge());

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        IntUnaryOperator incby5 = i -> i + 5;
        System.out.println(incby5.applyAsInt(10));

//
        printEmployeesByAge(employees, "\nEmployes 30 and over", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee -> employee.getAge() <= 30);
        printEmployeesByAge(employees, "\nEmployees younger than 25", employee -> employee.getAge() < 25);


        IntPredicate intP = i -> i > 15;
        System.out.println(intP.test(10));
        int a = 20;
        System.out.println(intP.test(a + 5));
        IntPredicate greaterthan15 = i -> i > 15;
        IntPredicate lesserthan100 = i -> i < 100;
        System.out.println(greaterthan15.test(10));
        System.out.println(greaterthan15.test(a + 5));
        System.out.println(greaterthan15.and(lesserthan100).test(50));
        System.out.println(greaterthan15.and(lesserthan100).test(15));

        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }
        Collections.sort(employees, Comparator.comparing(Employee::getName));

        for (Employee employee : employees) {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
            //new Thread(()-> System.out.println(employee.getAge())).start();
        }

        //print employee last name
        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
            System.out.println("Last Name is " + lastName);
        });

        System.out.println("\nEmployees over 30: ");
        System.out.println("==========================");
        employees.forEach(employee -> {
            if (employee.getAge() > 50) {
                System.out.println(employee.getName() + " " + employee.getAge());
            }
        });
//
        System.out.println("\nEmployees Lower 30: ");
        System.out.println("==========================");
        employees.forEach(employee -> {
            if (employee.getAge() < 29.99) {
                System.out.println(employee.getName() + " " + employee.getAge());
            }
        });

        //Using predicates


        System.out.println("*************************88");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }

        String sillyString = doStringStuff((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(),
            employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);
    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    //Using predicates
    private static void printEmployeesByAge(
        List<Employee> employees,
        String ageText,
        Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        System.out.println("==========================");
        for (Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName() + " " + employee.getAge());
            }
        };
    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }

    UpperConcat uc = (s1, s2) -> {
        String result = s1.toUpperCase() + s2.toUpperCase();
        return result;
    };
}


