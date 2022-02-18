package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*new Thread(()-> {
            System.out.println("Printing from the Runnable");
            System.out.println("Line 2");
            System.out.format("This is line %d\n", 3);
        }).start();*/


        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee ante = new Employee("Jaime Lannister", 25);
        Employee zizi = new Employee("Robb Stark", 50);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(ante);
        employees.add(zizi);

//        Collections.sort(employees, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee employee1, Employee employee2) {
//                return employee1.getName().compareTo(employee2.getName());
//            }
//        });

        Collections.sort(employees, (e1, e2) ->
                e1.getName().compareTo(e2.getName())); // instead of Comparator lambda is passed as the second parameter

        employees.forEach(e -> {
            System.out.println(e.getName());
            System.out.println(e.getAge());
        });

        /*for(Employee employee : employees) {
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();

            This loop just prooves that everything is going to work, even though employee changes,
            and it's already been constated that variables declared outside of lambda scope and used
            within the lambda scope should be final or effectively not changed. But here,
            it is about a loop, during each iteration a new object is being created and it doesn't change.

        }*/

        /*String sillyString = doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        },
        employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);*/

        // The simplest example:
        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        String sillyString = doStringStuff(uc, employees.get(0).getName(), employees.get(1).getName());
        System.out.println(sillyString);

        // Another way
        UpperConcat uc1 = (a1, a2) -> {
            String result = a1.toUpperCase() + a2.toUpperCase();
            return result;
        };
        String sillyString2 = doStringStuff(uc1, employees.get(2).getName(), employees.get(3).getName());
        System.out.println(sillyString2);

        System.out.println("Another class:");
        AnotherClass anotherClass = new AnotherClass();
        String str = anotherClass.doSomething();
        System.out.println(str);

    }

    public final static String doStringStuff(UpperConcat uc, String s1, String s2){
        return uc.upperAndConcat(s1, s2);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {

    /*
     * Bilješka za ovu klasu: u prvom zakomentiranom snippetu anonymous class je nepostojeći
     * a u lambda izrazu ispod ovog snippeta getClass().getSimpleName() je AnotherClass
     *To  drugim riječima znači: lambda izraz nije klasa; kad je kod pokrenut, anonimna instanca
     * se ne stvara, umjesto toga lambda se tretira kao ugniježđeni blok koda i ima isti scope
     * kao ugniježđeni blok
     * */

    public String doSomething(){

        // S anonimnom klasom
        /*System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(new UpperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
                return s1.toUpperCase() + s2.toUpperCase();
            }
        }, "String1", "String2");*/

        // S lambda izrazom

        int i = 0;
        // i++;  >>>  this doesn't work

        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is: " + getClass().getSimpleName());
            System.out.println("i in the lambda expression: " + i);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };
        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
        return Main.doStringStuff(uc, "String1", "String2");


        // Tim Buchalka, Lection 295., anonymous classes and final variables

    }

    public void printValue(){

        int number = 25;

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
}


