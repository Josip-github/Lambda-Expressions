package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /*
    * Oracle defines stream as a sequence of elements supporting sequential and parallel aggregate
    * operations.
    * In practice, a stream is a set of object references. The stream() method,
    *  which was added to the Collection class in Java 8, creates a stream from a collection.
    * Now each object reference in the stream corresponds to an object in the collection
    * and the ordering of the object reference matches the ordering of the collection.
    * Now, when we want to use a stream that uses a collection as a source, the stream() method
    * will always be the first call we make.
    *
    * When we work with streams, in fact any stream operations that we use have to meet two
    * requirements. First they must be non-interfering which means that they don't change the
    * stream source in any way. Secondly, they must be stateless so the result of an operation
    * can't depend on any state outside of the operation. Example of that would be
    * that it can't depend on variable values in a previous step. So, each operation should be seen as
    * an independent step that's operating on a stream argument.
    * */

    public static void main(String[] args) {

        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "G23", "g64",
                "I26", "I17", "I29",
                "O71");

        List<String> gNumbers = new ArrayList<>();

        /*someBingoNumbers.forEach(number -> {
            if(number.toUpperCase().startsWith("G")){
                gNumbers.add(number);
                //System.out.println(number);
            }
        });

        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        gNumbers.forEach((String s) -> System.out.println(s));
        Ideja je sad cijeli ovaj zakomentirani blok napisati u jednom statementu
        */
        someBingoNumbers
                .stream()
                .map(String::toUpperCase) // .map(s -> s.toUpperCase())
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("\n-----------------------------");
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());

        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        System.out.println("\n===============");
        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                //.collect(Collectors.toList());  >>> this would work as well
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for(String s: sortedGNumbers){
            System.out.println(s);
        }

        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        System.out.println("Find the youngest employee:");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        System.out.println("\n");
        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();




    }
}
