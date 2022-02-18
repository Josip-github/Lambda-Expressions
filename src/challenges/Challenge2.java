package challenges;

import java.util.function.Function;

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
    }

    // Challenge 4:
    //Write a method called everySecondCharacter that accepts the function as a parameter and executes it
    // wiith the argument "1234567890"; it should return the result of the function

    public static String everySecondCharacter(Function<String, String> lambdaFunction, String str){
        return lambdaFunction.apply(str);
    }


}
