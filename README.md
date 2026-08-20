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

# Reto 6 — Talk to Technical Support

## Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Behavioral (patrón de comportamiento) |
| **Pattern Used** | Chain of Responsibility |
| **Justification** | El enunciado pide explícitamente que, si un técnico no puede resolver un ticket, este se pase al siguiente técnico. Eso es la definición del patrón: una cadena de manejadores donde cada uno decide procesar la solicitud o delegarla, sin que quien la envía sepa de antemano quién la resolverá. |
| **How It Was Applied** | Cada `Tecnico` es un eslabón con una especialidad (`Dificultad`) y una prioridad máxima. Se enlazan con `setSiguiente(...)`. Al llegar un ticket, `atender(ticket)` evalúa si puede resolverlo (`puedeResolver`); si no, llama a `siguiente.atender(ticket)`. Si ningún eslabón puede resolverlo, el ticket se marca como pendiente con `marcarPendienteEscalacion()`. |


### Ejemplo real

```
Ticket: "Instalacion de software con licencia especial" (BASICO / ALTA)
   Ana no puede atender ... -> escalando...
   Luis no puede atender ... -> escalando...
   alfonso resolvio el ticket "Instalacion de software con licencia especial".
```

Este ticket pasó por 3 técnicos (Ana → Luis → alfonso) antes de resolverse, cumpliendo el requisito de mostrar el recorrido por la cadena.

### Encapsulation and Immutability
- **Encapsulation:** Attributes across all classes (`Product`, `CartItem`, `Customer`, `ShoppingCart`, `Receipt`) are declared `private final`, exposing data strictly through getter methods. Additionally, `ShoppingCart` protects its internal state by returning an unmodifiable list via `Collections.unmodifiableList(items)`, preventing unauthorized external modification of cart items.
- **Immutability:** Domain entities (`Product`, `CartItem`, `Customer`) are immutable. Once a product is created, attributes such as its unit price (`unitPrice`) cannot be modified, guaranteeing pricing data integrity and consistency throughout the entire transaction lifecycle.

---
