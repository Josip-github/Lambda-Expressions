package challenges;

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


    }

    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
