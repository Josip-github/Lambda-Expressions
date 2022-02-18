package challenges;

public class Challenge1 {
     // Write the following anonymous class as a lambda expression:

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for(String part : parts){
                    System.out.println(part);
                }
            }
        };


    }




}
