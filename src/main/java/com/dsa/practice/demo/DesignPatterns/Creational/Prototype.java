package com.dsa.practice.demo.DesignPatterns.Creational;

/*
🔹 Prototype Design Pattern
📌 Description

The Prototype Pattern is a creational design pattern that lets you create new objects by copying existing ones (prototypes) instead of instantiating them from scratch.

        👉 Instead of using the new keyword, you clone an existing object.
👉 Useful when object creation is expensive or complex (e.g., requires database calls, network operations, or heavy computations).

        📌 Key Idea

Define a prototype interface with a clone() method.

Concrete classes implement cloning.

Client calls clone() to get new objects.

📌 Pros

✅ Reduces the cost of creating complex objects
✅ Avoids subclassing (can just copy objects)
✅ Provides a simpler way to create object copies at runtime
✅ Useful when creating multiple similar objects

📌 Cons

❌ Cloning can be tricky if the object has nested objects (deep vs shallow copy)
❌ Requires extra care to implement clone() correctly
❌ Circular references can complicate cloning

📌 Applications

Document editing software (cloning shapes, text blocks)

Game development (copying characters, enemies, weapons)

Prototyping objects where initial setup is heavy

Java itself: Object.clone() is the base for Prototype pattern*
*/


//Step 1: Prototype Interface
interface IPrototype extends Cloneable {
    IPrototype clone();
}

//Step 2: Concrete Prototype
class Car implements IPrototype {
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    public IPrototype clone() {
        return new Car(this.brand, this.model); // Shallow copy
    }

    @Override
    public String toString() {
        return "Car [brand=" + brand + ", model=" + model + "]";
    }
}


/*Output
Car [brand=Tesla, model=Model S]
Car [brand=Tesla, model=Model S]
Same object? false

        📌 Shallow vs Deep Copy

Shallow Copy: Copies only primitive fields, references point to the same objects.

Deep Copy: Duplicates everything, including nested objects.

Deep Copy Example*/
class Engine {
    String type;
    Engine(String type) { this.type = type; }
}

class Vehicle implements IPrototype {
    String brand;
    Engine engine;

    Vehicle(String brand, Engine engine) {
        this.brand = brand;
        this.engine = engine;
    }

    @Override
    public IPrototype clone() {
        // Deep copy → clone engine too
        return new Vehicle(this.brand, new Engine(this.engine.type));
    }

    @Override
    public String toString() {
        return "Vehicle [brand=" + brand + ", engine=" + engine.type + "]";
    }
}

public class Prototype {
    public static void main(String[] args) {
        //Deep Copy
        Vehicle v1 = new Vehicle("BMW", new Engine("V8"));
        Vehicle v2 = (Vehicle) v1.clone();

        System.out.println(v1);
        System.out.println(v2);

        v2.engine.type = "V6"; // Changing cloned object’s engine
        System.out.println("After modification:");
        System.out.println(v1);
        System.out.println(v2);

//Shallow Copy
        Car car1 = new Car("Tesla", "Model S");
        Car car2 = (Car) car1.clone();

        System.out.println(car1);
        System.out.println(car2);
        System.out.println("Same object? " + (car1 == car2));
    }
}
/*

Output
Vehicle [brand=BMW, engine=V8]
Vehicle [brand=BMW, engine=V8]
After modification:
Vehicle [brand=BMW, engine=V8]
Vehicle [brand=BMW, engine=V6]*/
