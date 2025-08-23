package com.dsa.practice.demo.DesignPatterns.Creational;

/*
        ## 🔹 **Description**

The **Builder Pattern** is a **creational design pattern** used to construct complex objects step by step.
 Instead of creating an object in a single constructor call (which can become overloaded or messy),
  the builder pattern separates the construction process from the object’s representation.

        * It is useful when an object has many **optional parameters** or **complex construction logic**.
        * The builder provides a **fluent API** (method chaining) for readability and flexibility.

---

        ## 🔹 **Structure**

        1. **Product** – The complex object that is to be built.
        2. **Builder (Abstract Interface)** – Defines the steps required to build the product.
        3. **Concrete Builder** – Implements the steps to build the product.
4. **Director** – (Optional) Controls the construction order of the object.
5. **Client** – Uses the builder to construct the product.

---

        ## 🔹 **Pros**

✅ **Improves readability & maintainability** – Code becomes easier to read, especially when dealing with many constructor parameters.
✅ **Handles complex objects** – Can construct objects with many optional configurations without multiple constructors.
✅ **Encapsulation** – Hides the internal construction process from the client.
✅ **Immutability** – Often used with immutable objects (e.g., Java’s `StringBuilder` or Lombok’s `@Builder`).
✅ **Fluent Interface** – Supports method chaining for clean and elegant object creation.

---

        ## 🔹 **Cons**

❌ **Increased boilerplate code** – Requires multiple classes (Builder, Product, Director).
❌ **Overkill for simple objects** – Not needed if the object has few attributes.
❌ **Learning curve** – Developers unfamiliar with it may find the design complex at first.
❌ **Director adds rigidity** – If not designed properly, the `Director` may restrict flexibility.

---

        ## 🔹 **Applications**

The Builder Pattern is widely used in:

        1. **Java Standard Library**

        * `StringBuilder`, `StringBuffer`
        * `java.lang.StringBuilder.append()` methods
   * `java.sql.PreparedStatement` (step-by-step query building)

        2. **Frameworks / APIs**

        * **Lombok’s `@Builder` annotation** for immutables
   * **Jackson / Gson** object creation with custom deserialization
   * **Spring Boot** (`ResponseEntity` uses builder pattern: `ResponseEntity.ok().body(...)`)

        3. **Real-world examples**

        * **House Construction** (step by step: build walls, add roof, paint, etc.)
        * **Burger in Fast Food** (choose bread, meat, toppings, sauces).
        * **Document creation** (e.g., PDF builder libraries).

        ---
*/

class Computer{
    private String cpu;
    private String gpu;
    private int ram;
    private int storage;

    private Computer(Builder builder){
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

     public static class Builder{
         private String cpu;
         private String gpu;
         private int ram;
         private int storage;

         public Builder setCpu(String cpu){
             this.cpu= cpu;
             return this;
         }

         public Builder setGpu(String gpu){
             this.gpu= gpu;
             return this;
         }

         public Builder setRam(int ram){
             this.ram= ram;
             return this;
         }

         public Builder setStorage(int storage){
             this.storage= storage;
             return this;
         }

         public Computer build(){
             return new Computer(this);
         }
     }
    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", GPU=" + gpu + ", RAM=" + ram + "GB, Storage=" + storage + "GB]";
    }
}

// Client
public class Builder {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCpu("Intel i9")
                .setGpu("RTX 4090")
                .setRam(32)
                .setStorage(2000)
                .build();

        System.out.println(gamingPC);
    }
}
