# Laboratory 2 - DOSW (Challenge 1: Don Pepe's Store)

## Design Documentation - Challenge 1

### Design Pattern Used
- **Strategy Pattern:** Used for discount calculation through the `DiscountStrategy` interface. This pattern encapsulates discount algorithms (`CustomerType.NEW` with 5% and `CustomerType.FREQUENT` with 10%), allowing `ShoppingCart` and `Customer` to dynamically delegate calculation based on customer type without coupling to conditional logic.

---

### SOLID Principles

| Principle | Application in the Solution |
| :--- | :--- |
| **Single Responsibility** | **Implemented.** Each class has a single reason to change: <br>- [Product](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Product.java): Models basic product information.<br>- [CartItem](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/CartItem.java): Handles a cart line item and its line total calculation.<br>- [Customer](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Customer.java): Manages customer information.<br>- [CustomerType](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/CustomerType.java): Defines customer types and their discount percentages.<br>- [ShoppingCart](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/ShoppingCart.java): Manages purchase state and aggregates totals.<br>- [Receipt](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Receipt.java): Formats and generates the text sales receipt. |
| **Open/Closed** | **Implemented.** The system is open for extension but closed for modification thanks to the [DiscountStrategy](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/DiscountStrategy.java) interface. New discount rules or customer types can be added by implementing this interface without altering existing `ShoppingCart` or `Customer` code. |
| **Liskov Substitution** | **Implemented.** Any class or enum implementing `DiscountStrategy` (such as `CustomerType.NEW` or `CustomerType.FREQUENT`) can be used interchangeably wherever a discount contract is expected, without altering correct system behavior or throwing unexpected exceptions. |
| **Interface Segregation** | **Not explicitly implemented.** Interface segregation was not required as the system only needed a single, concise single-method interface (`DiscountStrategy`). There were no monolithic or bloated interfaces with unnecessary methods requiring segregation. |
| **Dependency Inversion** | **Partially implemented.** Discount calculation depends on the `DiscountStrategy` abstraction. However, at a global level, high-level classes like `ShoppingCart` depend directly on concrete domain classes like `Customer` and `CartItem` rather than service or repository abstractions. |

---

### Polymorphism
Polymorphism is demonstrated through the use of the `DiscountStrategy` interface. Different discount strategies (`CustomerType.NEW` and `CustomerType.FREQUENT`) implement the `calculateDiscount(double subtotal)` contract in their own specific way. `ShoppingCart` and `Customer` polymorphically invoke this method, executing the corresponding strategy without relying on rigid control structures like `if/else` or `switch` statements based on customer types.

---

### Encapsulation and Immutability
- **Encapsulation:** Attributes across all classes (`Product`, `CartItem`, `Customer`, `ShoppingCart`, `Receipt`) are declared `private final`, exposing data strictly through getter methods. Additionally, `ShoppingCart` protects its internal state by returning an unmodifiable list via `Collections.unmodifiableList(items)`, preventing unauthorized external modification of cart items.
- **Immutability:** Domain entities (`Product`, `CartItem`, `Customer`) are immutable. Once a product is created, attributes such as its unit price (`unitPrice`) cannot be modified, guaranteeing pricing data integrity and consistency throughout the entire transaction lifecycle.