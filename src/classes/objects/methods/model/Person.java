package classes.objects.methods.model;

import java.time.LocalDate;

public class Person {
    // A class member is any item( variable or method) that is directly underneath a class.
    // visibility means accessibility of that member: protected, public, private, or no keyword at all.
    // Variables inside a method are not class members
    // Variables inside a class are called instance variables or class fields
    // you want to limit the access of data inside a class from outside classes
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Address address;
    private Person spouse;
    private Pet pet;

    // What are constructors?
    // They do not declare a return type or return anything
    // They don't have a return type - void or anything
    // Helps to initialize certain properties of an object as it is being created
    // In General world, there is usually no person who doesn't have a name
    // In a prog that is modelling a person, it does not make sense to classes.objects.methods.business.model a Person without a name
    // In that case we must supply a name of that person when we create them
    // Constructors are methods that make sure these initial data are set before an object is created

    // Two types of Constructor, -No Args Default Constructor and Constructor that takes Args.
    // If you create a JAVA Class and you do not write a constructor at all, JAVA will give you a free No Args Constructor
    // However, as soon as you create a constructor that takes any args, JAVA will no longer create a free, default no arg constructor for you
    // Lots of framework in JAVA assume that there a default no args default constructor
    // To be sure you can use both no arg and constructor that takes args w/o running into issues it is better to create both themselves
    // There are frameworks that requires you to have a no-arg constructor, even if you don't want to have them, the bes thing to do is to make them private or protected.

    // No argument constructor. Sometimes, this can be private
    public Person() {

    }
    // 3-argument constructors
    public Person(String firstName, String lastName, LocalDate dob, Address address) {
        // the word "this" refers to the new instance or object that is being created
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
    }

    // Getters and Setters:
    // Convention in JAVA, when we have fields, we often want to make that information private to protect the data inside the class
    // Sometimes, we may need the data to be obtained or modified from outside of the class using getters and setters
    // By using getters and setters can control or do a level of protection to the data.
    // By putting getters and setters in front of the fields helps us to protect that data in the fields
    public String getFirstName() {

        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                ", spouse=" + spouse +
                ", pet=" + pet +
                '}';
    }
}
