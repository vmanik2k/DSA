package com.dsa.practice.demo.DesignPatterns.Behavioral;


import java.util.HashMap;
import java.util.Map;

/*🔹 Strategy Design Pattern
📌 Description

The Strategy Pattern is a behavioral design pattern that lets you define a family of algorithms,
put each of them in a separate class, and make them interchangeable at runtime.

        👉 Instead of hardcoding logic, the algorithm is chosen dynamically based on the situation.
        👉 Promotes the principle: “Encapsulate what varies.”

        📌 Real-World Analogy

Payment methods in an e-commerce site (CreditCard, PayPal, UPI) → user can choose dynamically.

Navigation apps (fastest route, cheapest toll route, scenic route).

Sorting algorithms (quick sort, bubble sort, merge sort) depending on data size.

📌 Pros

✅ Promotes open/closed principle → easily add new strategies without changing existing code
✅ Avoids long if-else or switch statements
✅ Allows changing behavior at runtime
✅ Improves testability → each strategy can be tested independently

📌 Cons

❌ Increases number of classes (one per strategy)
❌ Client must be aware of different strategies to choose the right one

📌 Applications

Payment gateways (CreditCard / UPI / PayPal)

Compression algorithms (ZIP / RAR / 7z)

Sorting/Searching algorithms

Authentication methods (OAuth / JWT / LDAP)

📌 Example (Java – Strategy Pattern)
Step 1: Strategy Interface*/
interface PaymentStrategy {
    void pay(int amount);
}

//Step 2: Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(){}
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(){}

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

class UpiPayment implements PaymentStrategy {
    private String upiId;

    public UpiPayment(){}

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using UPI: " + upiId);
    }
}

//Step 3: Context (uses a Strategy)
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    // Set strategy dynamically
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new RuntimeException("Payment method not selected!");
        }
        paymentStrategy.pay(amount);
    }
}

//Step 4: Strategy ManagerClient Code
class PaymentManager {
    private static final Map<String, PaymentStrategy> strategies = new HashMap<>();

    static {
        strategies.put("CREDITCARD", new CreditCardPayment());
        strategies.put("PAYPAL", new PayPalPayment());
        strategies.put("UPI", new UpiPayment());
    }

    public static PaymentStrategy getStrategy(String type) {
        return strategies.get(type.toUpperCase());
    }
}


public class Strategy{
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876"));
        cart.checkout(500);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(1000);

        cart.setPaymentStrategy(new UpiPayment("user@upi"));
        cart.checkout(750);

        PaymentStrategy strategy;

        strategy = PaymentManager.getStrategy("creditcard");
        strategy.pay(500);

        strategy = PaymentManager.getStrategy("paypal");
        strategy.pay(1000);

        strategy = PaymentManager.getStrategy("upi");
        strategy.pay(750);

        // Invalid input
        strategy = PaymentManager.getStrategy("bitcoin");
        if (strategy != null) {
            strategy.pay(2000);
        } else {
            System.out.println("Invalid payment type!");
        }
    }
}

/*
Output
Paid 500 using Credit Card: 1234-5678-9876
Paid 1000 using PayPal: user@example.com
Paid 750 using UPI: user@upi

📌 Difference from Other Patterns

Strategy vs State: Strategy chooses algorithm; State changes behavior based on internal state.

Strategy vs Template Method: Strategy allows runtime selection of algorithms; Template fixes algorithm structure at compile time.

✅ Summary

Strategy Pattern allows selecting different algorithms at runtime.

Promotes flexibility and maintainability.

Common in payments, navigation, sorting, authentication.

Example: Payment gateways in e-commerce.*/
