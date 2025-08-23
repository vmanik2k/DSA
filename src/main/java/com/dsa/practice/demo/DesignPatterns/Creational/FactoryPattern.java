package com.dsa.practice.demo.DesignPatterns.Creational;

/*
🔹 Factory Design Pattern
📌 Description

The Factory Pattern is a creational design pattern that provides an interface for creating objects
but allows subclasses or methods to decide which class to instantiate.

👉 Instead of calling a class’s constructor directly, you use a factory method to get the object.
👉 This promotes loose coupling because the client code does not depend on concrete classes.

📌 Types of Factory Patterns

Simple Factory – A single factory method that returns objects based on input.

Factory Method Pattern – Subclasses decide which object to create (uses inheritance).

Abstract Factory Pattern – Factory of factories, used to create families of related objects.

📌 Pros

✅ Encapsulates object creation logic
✅ Promotes loose coupling (client depends on abstraction, not concrete classes)
✅ Makes code more maintainable and flexible
✅ Helps in adhering to Open/Closed Principle (open for extension, closed for modification)

📌 Cons

❌ More classes and complexity (extra factory layer)
❌ Can be overkill for small/simple object creation

📌 Applications

Logging frameworks (e.g., Logger.getLogger() in Java)

JDBC (e.g., DriverManager.getConnection())

GUI toolkits (creating different UI components based on OS)

Payment gateway integration (creating objects for CreditCard, UPI, PayPal, etc.)
*/


//Step 1: Product Interface
interface Shape {
    void draw();
}

//Step 2: Concrete Products
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

//Step 3: Factory Class
class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;

        switch (shapeType.toLowerCase()) {
            case "circle": return new Circle();
            case "square": return new Square();
            case "rectangle": return new Rectangle();
            default: throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}

//Step 4: Client Code
public class FactoryPattern{
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.getShape("circle");
        shape1.draw();

        Shape shape2 = factory.getShape("square");
        shape2.draw();

        Shape shape3 = factory.getShape("rectangle");
        shape3.draw();
    }
}


//Instead of using a single if-else, we can use subclasses to decide object creation.

/*
abstract class Dialog {
    public void render() {
        Button okButton = createButton();
        okButton.render();
    }

    // Factory Method
    protected abstract Button createButton();
}

interface Button {
    void render();
}

class WindowsButton implements Button {
    public void render() { System.out.println("Windows Button"); }
}

class HTMLButton implements Button {
    public void render() { System.out.println("HTML Button"); }
}

class WindowsDialog extends Dialog {
    protected Button createButton() {
        return new WindowsButton();
    }
}

class WebDialog extends Dialog {
    protected Button createButton() {
        return new HTMLButton();
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        Dialog dialog = new WindowsDialog();
        dialog.render(); // Windows Button

        dialog = new WebDialog();
        dialog.render(); // HTML Button
    }
}
*/

