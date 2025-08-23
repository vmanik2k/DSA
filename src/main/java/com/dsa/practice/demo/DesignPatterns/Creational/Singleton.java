package com.dsa.practice.demo.DesignPatterns.Creational;


/*
Description:
The Singleton Pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to it.
It is used when only one object is needed (for example: logging service, configuration manager, cache, thread pool, or database connection pool).
*/


import java.io.Serializable;

import java.io.*;
import java.lang.reflect.*;

public class Singleton implements Serializable, Cloneable {
    private static Singleton instance;

    private Singleton() {
        // No protection here → can be broken by reflection
    }
//Thread safe singleton
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton(); // lazy init
            }
        }

        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // allows cloning → break
    }
}


/*Ways Singleton Can Be Broken:
1. Reflection:- Reflection can access the private constructor and create a new instance.

2. Serialization/Deserialization:- When a singleton is serialized and deserialized, a new object is created.

3. Cloning:- If the class implements Cloneable, calling clone() can create another instance.

4. Multi-threading:- If multiple threads call getInstance() simultaneously (without synchronization), multiple objects can be created.

How to Prevent Breaking:

1. Prevent Reflection:- Add a check inside the constructor and throw an exception if the instance already exists.

2. Prevent Serialization:- Implement the readResolve() method to always return the same singleton instance.

3. Prevent Cloning:- Override the clone() method and throw CloneNotSupportedException.

4. Prevent Multi-threading Issues:- Use synchronized or double-checked locking in getInstance().

5. Best Solution: Enum Singleton:- Using enum ensures thread-safety, prevents serialization, reflection, and cloning issues.*/

enum SingletonEnum {
    INSTANCE;
    public void show() {
        System.out.println("Enum Singleton");
    }
}

/*

Pros:

* Guarantees a single instance
* Saves memory by reusing the same object
* Provides centralized access to resources

Cons:

* Can be misused and turn into a global variable
* Harder to unit test or mock
* Needs careful handling in multithreaded environments

Summary:
The Singleton Pattern ensures only one instance of a class exists, but it can be broken using reflection, serialization, cloning, and multi-threading. To prevent this, use defensive techniques like constructor guards, readResolve, clone prevention, synchronized methods, or the safest option: enum-based singleton.
*/

/**/


