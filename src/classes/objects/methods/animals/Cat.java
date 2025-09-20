package classes.objects.methods.animals;

import classes.objects.methods.model.Pet;

import java.time.LocalDate;

public class Cat extends Pet {


    public Cat(String name, LocalDate dob) {
        super(name, dob);
    }

    public void meow() {
        System.out.println("Meow meow");
        // super lets us access what methods that are available on the parent class
        // If a method is package protected, it cannot be accessed by class outside the package of the parent class
        super.protectedMethod();
    }

    @Override
    public String toString() {
        // super means "the class that above the class we currently at". This is a class hierarchy pattern.
        return "Cat - name: " + this.getName();
    }

    public static void main(String[] args) {
        Cat cat1 = new Cat("dojo", LocalDate.of(1990, 10, 8));
        System.out.println(cat1);
        cat1.meow();
    }

    // for every single class in Java, there is a default parent class called Object.
    // if any class is not explicitly extending any class, then it is extending an ancestor class of type Object.
    // All class implicitly extends a standard class called Object.
    // Every other class in JAVA extends from the original class called Object.
    //

    // Class Member Visibility.

}
