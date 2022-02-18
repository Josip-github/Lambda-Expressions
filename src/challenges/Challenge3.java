package challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Challenge3 {

    /*Challenge:
    * Write code to print the items in the list in sorted order, and with the first letter
    * in each name upper-cased. The name "harry" should be printed as "Harry" and
    * should be printed after "Emily" and before "Isla".
    * Use lambda expressions wherever possible.
    * */

    public static void main(String[] args) {
        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Olivia",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        topNames2015.
                stream()
                .map(s -> capitalize(s))
                .sorted()
                .forEach(System.out::println);

        // another solution:
        System.out.println("\nAnother solution:");
        List<String> list2 = new ArrayList<>();
        topNames2015.forEach(name -> list2.add(capitalize(name)));
        // list2.sort((s1, s2) -> s1.compareTo(s2));
        // list2.forEach(s -> System.out.println(s));
        list2.sort(String::compareTo);
        list2.forEach(System.out::println);


    }

    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
