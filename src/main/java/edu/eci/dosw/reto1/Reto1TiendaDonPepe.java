package edu.eci.dosw.reto1;

/**
 * Runner class for Challenge 1 — Don Pepe's Store.
 */
public final class Reto1TiendaDonPepe {

    private Reto1TiendaDonPepe() {
    }

    public static void run() {
        System.out.println("Running Challenge 1 — Don Pepe's Store...");

        // 1. Define available products
        Product tshirt = new Product("Camiseta", 20000);
        Product pants = new Product("Pantalón", 50000);
        Product cookies = new Product("Galletas", 500);
        Product juice = new Product("Jugo Natural", 3000);

        // 2. Create customer (Frequent customer with 10% discount)
        Customer customer = new Customer("Carlos Gómez", CustomerType.FREQUENT);

        // 3. Create shopping cart and add products according to example scenario
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(tshirt, 2);  // 2 * 20,000 = 40,000
        cart.addItem(cookies, 3); // 3 * 500 = 1,500
        cart.addItem(juice, 5);   // 5 * 3,000 = 15,000

        // 4. Generate and display receipt
        Receipt receipt = new Receipt(cart);
        receipt.printReceipt();
    }
}
