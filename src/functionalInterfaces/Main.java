package functionalInterfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 31);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee jaime = new Employee("Jaime Lannister", 25);
        Employee varys = new Employee("Lord Varys", 50);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(jaime);
        employees.add(varys);

        /*for (Employee e: employees) {
            if(e.getAge() > 30){
                System.out.println(e.getName());
            }
        }*/

        System.out.println("Employees over 30:");
        System.out.println("==================");
        employees.forEach(e -> {
            if(e.getAge() > 30){
                System.out.println(e.getName());
            }
        });

        System.out.println("\nEmployees 30 and younger:");
        System.out.println("==================");
        employees.forEach(e -> {
            if(e.getAge() <= 30){
                System.out.println(e.getName());
            }
        });

        System.out.println("Another way:");
        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "\nEmployees 30 and under", employee -> employee.getAge() <= 30);
        printEmployeesByAge(employees, "\nEmployees 25 and under", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() <= 25;
            }
        });

        System.out.println("\nIntPredicate:");
        IntPredicate intp = i -> i > 15;
        System.out.println(intp.test(10)); // this prints false
        int a = 20;
        System.out.println(intp.test(a + 5)); // this prints true
        System.out.println("Chaining predicates:");
        IntPredicate greaterThan20 = i -> i > 20;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan20.and(lessThan100).test(65)); // prints true
        System.out.println(greaterThan20.and(lessThan100).test(17)); // prints false

        System.out.println("\nRandom supplier:");
        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(100);
        for(int i = 0; i < 10; i++){
            System.out.println(randomSupplier.get());
        }

        employees.forEach(e -> {
            String lastName = e.getName().substring(e.getName().indexOf(' ') + 1);
            System.out.println("Last name is: " + lastName);
        });

        System.out.println("\nFunction<> and apply:");
        Function<Employee, String> getLastName = (Employee e) -> {
            return e.getName().substring(e.getName().indexOf(' ') + 1);
        };
        String lastName = getLastName.apply(employees.get(2));
        System.out.println(lastName);

        Function<Employee, String> getFirstName = (Employee e) -> {
            return e.getName().substring(0, e.getName().indexOf(' '));
        };

        Random random1 = new Random();
        for(Employee e: employees){
            if(random1.nextBoolean()){
                System.out.println(getAName(getFirstName, e));
            } else {
                System.out.println(getAName(getLastName, e));
            }
        }

        Function<Employee, String> upperCase = (e) -> e.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(1));
        System.out.println(concatAge.apply(upperName, employees.get(1)));

        System.out.println("IntUnaryOperator:");
        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10)); // prints 15

        System.out.println("Consumer:");
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello world!");
    }

    private static String getAName(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition){

        System.out.println(ageText);
        System.out.println("====================");
        for(Employee e: employees){
            if(ageCondition.test(e)){
                System.out.println(e.getName());
            }
        }

    }


}
