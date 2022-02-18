package challenges;

import java.util.function.Function;
import java.util.function.Supplier;

public class Challenge2 {

    // Write the following method as a lambda expression

    public static String everxSecondChar(String source){
        StringBuilder returnVal = new StringBuilder();
        for(int i = 0; i < source.length(); i++){
            if(i % 2 == 1){
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();

    }





    public static void main(String[] args) {

        Function<String, String> lambdaFunction = s -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                if(i % 2 == 1){
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        System.out.println(lambdaFunction.apply("1234567890"));
        System.out.println("\n");
        System.out.println(everySecondCharacter(lambdaFunction, "1234567890"));

        // Challenge 5:
        // Write a lambda expression that maps to the java.util.Supplier interface
        //This lambda should return the string "I love Java!" Assign it to a variable called iLoveJava

        //Supplier<String> iLoveJava = () -> "I love Java!";
        Supplier<String> iLoveJava = () -> { return "I love Java!"; };

        //Challenge 6:
        // the Supplier won't do anything until we use it. use this supplier
        // to assign the string "I love Java" to a variable called supplierResult

        String supplierResult = iLoveJava.get();
        System.out.println("Ma samo da vidim: " + supplierResult);
    }

    // Challenge 4:
    //Write a method called everySecondCharacter that accepts the function as a parameter and executes it
    // wiith the argument "1234567890"; it should return the result of the function

    public static String everySecondCharacter(Function<String, String> lambdaFunction, String str){
        return lambdaFunction.apply(str);
    }


}
