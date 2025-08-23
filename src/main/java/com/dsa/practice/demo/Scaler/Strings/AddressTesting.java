package com.dsa.practice.demo.Scaler.Strings;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class AddressTesting {
    public static void main(String[] args) {
        String str ="Hello";
        String sample = new String("Hello");
        String str1 ="Hello";

        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        String str3 = new String(charArray);
        if(sample.equals(str)){
            System.out.println("Equal");
        }
        System.out.println(str.hashCode()+"  "+sample.hashCode()+"   "+str1.hashCode()+" "+str3.hashCode());

        Integer[] arr = new Integer[3];
        // Java Program to demonstrate memory leaks

                Vector a = new Vector(21312312);
                Vector b = new Vector(2147412344);
                Vector c = new Vector(219944);
                System.out.println("Memory Leak in Java");
    }
}
