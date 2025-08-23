package com.dsa.practice.demo.DesignPatterns.Creational;

import java.io.*;

public class SafeSingleton implements Serializable, Cloneable {
    private static volatile SafeSingleton instance;

    private SafeSingleton() {
        // Protect against reflection
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method");
        }
    }

    public static SafeSingleton getInstance() {
        if (instance == null) {
            synchronized (SafeSingleton.class) {
                if (instance == null) {
                    instance = new SafeSingleton();
                }
            }
        }
        return instance;
    }

    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }

    // Prevent deserialization
    protected Object readResolve() {
        return getInstance();
    }
}

