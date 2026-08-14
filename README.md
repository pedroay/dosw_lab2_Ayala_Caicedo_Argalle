# Laboratorio 2 - DOSW (Reto 1: Tienda Don Pepe)

## Design Documentation - Reto 1

### Patrón de Diseño Utilizado
- **Patrón Strategy (Estrategia):** Se utilizó para el cálculo de descuentos a través de la interfaz `DiscountStrategy`. Este patrón encapsula los algoritmos de descuento (`CustomerType.NEW` con 5% y `CustomerType.FREQUENT` con 10%), permitiendo que la clase `ShoppingCart` y `Customer` deleguen el cálculo dinámicamente según el tipo de cliente sin acoplarse a una lógica condicional específica.

---

### SOLID Principles

| Principle | Application in the Solution |
| :--- | :--- |
| **Single Responsibility** | **Se implementó.** Cada clase tiene una única razón para cambiar: <br>- [Product](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Product.java): Modela la información básica del producto.<br>- [CartItem](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/CartItem.java): Maneja un ítem del carrito y su cálculo parcial.<br>- [Customer](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Customer.java): Gestiona los datos del cliente.<br>- [CustomerType](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/CustomerType.java): Define los tipos de clientes y sus porcentajes de descuento.<br>- [ShoppingCart](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/ShoppingCart.java): Gestiona el estado de la compra y totales.<br>- [Receipt](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/Receipt.java): Formatea y genera la factura/recibo en texto. |
| **Open/Closed** | **Se implementó.** El sistema está abierto a la extensión pero cerrado a la modificación gracias a la interfaz [DiscountStrategy](file:///c:/Users/samue/OneDrive/Documentos/dosw_lab2_Ayala_Caicedo_Argalle/src/main/java/edu/eci/dosw/reto1/DiscountStrategy.java). Se pueden agregar nuevas reglas o tipos de descuento implementando esta interfaz sin modificar el código de `ShoppingCart` ni de `Customer`. |
| **Liskov Substitution** | **Se implementó.** Cualquier clase o enum que implemente `DiscountStrategy` (como `CustomerType.NEW` o `CustomerType.FREQUENT`) se puede usar de manera intercambiable en cualquier lugar que espere un contrato de descuento, sin alterar el funcionamiento correcto ni lanzar excepciones inesperadas. |
| **Interface Segregation** | **No se implementó explícitamente.** No fue necesario aplicar segregación de interfaces ya que el sistema solo requirió una única interfaz simple de un solo método (`DiscountStrategy`). No existían interfaces densas o con métodos innecesarios que requirieran ser divididas. |
| **Dependency Inversion** | **Se implementó parcialmente.** El cálculo de descuentos depende de la abstracción `DiscountStrategy`. Sin embargo, a nivel global las clases de alto nivel (`ShoppingCart`) dependen directamente de clases concretas de dominio como `Customer` y `CartItem` en lugar de abstracciones de servicios o repositorios. |

---

### Polymorphism
El polimorfismo se aplica mediante el uso de la interfaz `DiscountStrategy`. Las distintas variantes de descuento (`CustomerType.NEW` y `CustomerType.FREQUENT`) implementan el contrato `calculateDiscount(double subtotal)` de forma particular. `ShoppingCart` y `Customer` invocan de manera polimórfica este método, ejecutando la estrategia correspondiente sin requerir estructuras de control como `if/else` o `switch` basadas en tipos de cliente.

---

### Encapsulation and Immutability
- **Encapsulación:** Los atributos de todas las clases (`Product`, `CartItem`, `Customer`, `ShoppingCart`, `Receipt`) están definidos como `private final`, exponiendo únicamente métodos getter. Además, `ShoppingCart` protege su estado interno devolviendo una lista inmodificable con `Collections.unmodifiableList(items)`, evitando la alteración externa de los ítems del carrito.
- **Inmutabilidad:** Las entidades del dominio (`Product`, `CartItem`, `Customer`) son inmutables. Una vez creado un producto, sus atributos como el precio unitario (`unitPrice`) no pueden ser modificados, garantizando la consistencia y seguridad del valor del producto a lo largo de toda la transacción.