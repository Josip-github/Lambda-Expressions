package challenges;

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


    }


}
