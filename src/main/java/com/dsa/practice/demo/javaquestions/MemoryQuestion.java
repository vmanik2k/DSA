package com.dsa.practice.demo.javaquestions;

//Determine the order of printing
public class MemoryQuestion {
    static{
        System.out.println("From Static Block");
    }

    {
        System.out.println("From Class Level Block");
    }

    MemoryQuestion(){
        System.out.println("From Constructor");
    }

    public static void main(String[] args) {
        System.out.println("From Main");
        MemoryQuestion memoryQuestion = new MemoryQuestion();
        System.out.println("From Main 2");
        MemoryQuestion memoryQuestion1 = new MemoryQuestion();
    }
}
