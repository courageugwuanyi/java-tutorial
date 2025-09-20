package classes.objects.methods.example.package2;

public class OtherClass {
    public void method1() {
        ClassB classB = new ClassB();
        // the difference between these two methods is called subclassing
        // visibility of methods of parent classes to subclasses
        classB.protectedMethod();
        classB.packageProtected();
    }
}
