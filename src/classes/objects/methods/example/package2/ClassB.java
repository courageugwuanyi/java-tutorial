package classes.objects.methods.example.package2;

public class ClassB {
    public void publicMethod() {
        System.out.println("This is a public the method of ClassB");
        privateMethod();
    }

    private void privateMethod() {
        // This class can only be accessed from a method within this class
        System.out.println("This is the private method of ClassB");
    }

    protected void protectedMethod() {
        // This method is only accessible to other classes within the 'package2' package
        System.out.println("This is the protected method of ClassB");
    }

    void packageProtected() {
        System.out.println("This is the package protected method of ClassB");
    }
}
