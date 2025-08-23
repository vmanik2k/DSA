package com.dsa.practice.demo.DesignPatterns.Creational;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class SingletonBreak{
    public static void main(String[] args) throws Exception {
        Singleton s1 = Singleton.getInstance();
        Singleton s2;

        // 1. Break using Reflection
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        s2 = constructor.newInstance();
        System.out.println("Reflection: " + (s1 == s2)); // false

        // 2. Break using Serialization
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        oos.writeObject(s1);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
        Singleton s3 = (Singleton) ois.readObject();
        ois.close();
        System.out.println("Serialization: " + (s1 == s3)); // false

        // 3. Break using Cloning
        Singleton s4 = (Singleton) s1.clone();
        System.out.println("Cloning: " + (s1 == s4)); // false
    }
}
