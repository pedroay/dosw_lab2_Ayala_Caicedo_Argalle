# Laboratory 2 — DOSW (Software Design and SOLID Principles)

**Authors:** Ayala, Caicedo, Argalle  
**Course:** Software Development Workshop (DOSW) — Escuela Colombiana de Ingeniería Julio Garavito  
**Language:** Java 17 / Apache Maven  

---

## Challenge 1: Don Pepe's Store

### Design Pattern Used
- **Strategy Pattern:** Used for discount calculation through the `DiscountStrategy` interface. This pattern encapsulates discount algorithms (`CustomerType.NEW` with 5% and `CustomerType.FREQUENT` with 10%), allowing `ShoppingCart` and `Customer` to dynamically delegate calculation based on customer type without coupling to conditional logic.

### SOLID Principles

| Principle | Application in the Solution |
| :--- | :--- |
| **Single Responsibility** | **Implemented.** Each class has a single reason to change: <br>- `Product`: Models basic product information.<br>- `CartItem`: Handles a cart line item and its line total calculation.<br>- `Customer`: Manages customer information.<br>- `CustomerType`: Defines customer types and their discount percentages.<br>- `ShoppingCart`: Manages purchase state and aggregates totals.<br>- `Receipt`: Formats and generates the text sales receipt. |
| **Open/Closed** | **Implemented.** The system is open for extension but closed for modification thanks to the `DiscountStrategy` interface. New discount rules or customer types can be added by implementing this interface without altering existing `ShoppingCart` or `Customer` code. |
| **Liskov Substitution** | **Implemented.** Any class or enum implementing `DiscountStrategy` (such as `CustomerType.NEW` or `CustomerType.FREQUENT`) can be used interchangeably wherever a discount contract is expected, without altering correct system behavior or throwing unexpected exceptions. |
| **Interface Segregation** | **Not explicitly implemented.** Interface segregation was not required as the system only needed a single, concise single-method interface (`DiscountStrategy`). There were no monolithic or bloated interfaces with unnecessary methods requiring segregation. |
| **Dependency Inversion** | **Partially implemented.** Discount calculation depends on the `DiscountStrategy` abstraction. However, at a global level, high-level classes like `ShoppingCart` depend directly on concrete domain classes like `Customer` and `CartItem` rather than service or repository abstractions. |

### Encapsulation and Immutability
- **Encapsulation:** Attributes across all classes (`Product`, `CartItem`, `Customer`, `ShoppingCart`, `Receipt`) are declared `private final`, exposing data strictly through getter methods. Additionally, `ShoppingCart` protects its internal state by returning an unmodifiable list via `Collections.unmodifiableList(items)`, preventing unauthorized external modification of cart items.
- **Immutability:** Domain entities (`Product`, `CartItem`, `Customer`) are immutable. Once a product is created, attributes such as its unit price (`unitPrice`) cannot be modified, guaranteeing pricing data integrity and consistency throughout the entire transaction lifecycle.

---

## Challenge 2 — The Five-Star Chef

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Creational |
| **Pattern Used** | Builder Pattern |
| **Justification** | A customized hamburger is a complex object composed of various optional ingredients (bread, meat, cheese, tomato) in variable quantities. Using a single constructor with multiple parameters would cause telescoping constructor anti-patterns and inflexible code. The Builder pattern separates the incremental construction of the hamburger from its final representation, allowing the same construction workflow to produce diverse hamburger configurations. |
| **How It Was Applied** | The `BuilderFastFood` interface defines the construction steps (`setBread`, `setTomato`, `setMeat`, `setCheese`). The concrete builder `HamburguerBuilder` implements these methods, accumulating `Ingredient` objects in an internal collection and computing the total cost via `calculatePrice()`. The `Restaurant` class acts as the director/manager that guides the builder based on the quantities selected by the user. |

---

## Challenge 3 — The Kingdom of Vehicles

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Creational |
| **Pattern Used** | Abstract Factory Pattern |
| **Justification** | The application requires creating distinct families of vehicles across categories (Land, Water, Air) and commercial tiers (Economy, Luxury, Used) without coupling client code to specific concrete classes. The Abstract Factory pattern provides an interface for creating families of related or dependent objects, guaranteeing that vehicles of the same tier are created together consistently. |
| **How It Was Applied** | The `VehicleFactory` interface declares factory methods for each vehicle category (`createLandVehicle`, `createWaterVehicle`, `createAirVehicle`). Concrete factories (`EconomyFactory`, `LuxuryFactory`, `UsedFactory`) implement these methods to instantiate family-specific vehicles (e.g., `EconomyLandVehicule`, `LuxuryAirVehicule`, `UsedWaterVehicule`). The client `Reto3ReinoVehiculos` interacts exclusively through the `VehicleFactory` and `Vehicule` abstractions. |

---

## Challenge 4 — The Currency Exchange Scam

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Behavioral |
| **Pattern Used** | Strategy Pattern |
| **Justification** | Foreign exchange transactions involve dynamic conversion rates and specific conversion rules for distinct currency pairs (e.g., USD to EUR, USD to COP, EUR to JPY). Hardcoding these conversions into monolithic `if-else` or `switch` statements violates the Open/Closed Principle whenever new currency pairs are introduced. The Strategy pattern encapsulates each currency pair conversion algorithm into its own class, making them interchangeable at runtime. |
| **How It Was Applied** | The `ExchangeRateStrategy` interface defines the contract with `convert(double amount)`, `getSourceCurrency()`, `getTargetCurrency()`, and `getRate()`. Concrete strategy classes (`UsdToEurStrategy`, `CopToUsdStrategy`, `EurToJpyStrategy`, etc.) implement their specific exchange rates and conversion logic. `CurrencyConverter` acts as the context, registering strategies in a lookup map and dynamically invoking the appropriate strategy for each transaction. |

---

## Challenge 5 — The Coffee Shop

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Structural |
| **Pattern Used** | Decorator Pattern |
| **Justification** | A coffee shop needs to offer base beverages that customers can customize with an arbitrary combination of toppings and extra ingredients (milk, chocolate, caramel, whipped cream, mint). Creating static subclasses for every possible combination would cause a class explosion. The Decorator pattern allows responsibilities and extra costs to be dynamically attached to an individual coffee object at runtime without altering other objects. |
| **How It Was Applied** | The `Coffe` interface defines the core contract with `getDescription()` and `getPrice()`. `SimpleCoffee` provides the base coffee component. `ToppingDecorator` is an abstract decorator implementing `Coffe` and holding a reference to a wrapped `Coffe` instance. Concrete decorators (`MilkDecorator`, `ChocolateDecorator`, `CaramelDecorator`, `WhippedCreamDecorator`, `MintDecorator`, `CustomToppingDecorator`) override methods to recursively append their name and additional price to the wrapped beverage. |

---

## Challenge 6 — Talk to Technical Support

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Behavioral |
| **Pattern Used** | Chain of Responsibility |
| **Justification** | Support tickets have varying difficulty levels (`BASICO`, `INTERMEDIO`, `AVANZADO`) and priorities (`BAJA`, `MEDIA`, `ALTA`). The client submitting a ticket should not need to know which specific technician will handle it. The Chain of Responsibility pattern decouples the sender of the request from its receivers by giving multiple handlers a chance to process the request sequentially along an escalation chain. |
| **How It Was Applied** | The `Tecnico` class represents a link in the chain, holding a reference to the next technician via `setSiguiente(Tecnico)`. When `atender(ticket)` is called, the technician evaluates if they can resolve it based on difficulty and maximum priority (`puedeResolver`). If capable, they resolve it; otherwise, they automatically pass the ticket to `siguiente.atender(ticket)`. If no technician can resolve the ticket, it is marked as pending escalation. |

---

## Challenge 7 — The Magic Remote Control

### Design Pattern Documentation

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Behavioral |
| **Pattern Used** | Command Pattern |
| **Justification** | A smart home automation system must execute varied actions across heterogeneous devices (lights, doors, sound systems, blinds) while supporting user tracking, command execution history, and action undoing. Coupling the remote controller directly to device APIs would make it rigid and difficult to extend. The Command pattern encapsulates a request as a standalone object containing all information needed to execute and reverse the action. |
| **How It Was Applied** | The `Comando` interface declares `ejecutar()`, `deshacer()`, and `getDescripcion()`. Concrete command classes (`EncenderLuzComando`, `ApagarLuzComando`, `AbrirPuertaComando`, `CerrarPuertaComando`, `SetVolumenComando`, `AjustarPersianaComando`) encapsulate their receiver devices and state. `ControlRemoto` acts as the invoker, storing action history via `RegistroAccion` objects and enabling selective undoing of previously executed commands. |

---

## Challenge 8 — UML Design: ECIZoo Management System

### Main Classes and Responsibilities

| Class or Interface | Responsibility |
|---|---|
| **ECIZoo** | Central management controller for the zoo system. Registers visitors, caretakers, and animals, coordinating interactions across the zoo domain. |
| **Person** | Abstract base class representing all individuals in the zoo. Encapsulates shared attributes and base behaviors such as feeding animals (`FeedAnimals`). |
| **Visitor** | Extends `Person`. Models zoo visitors; allows selecting a favorite animal (`SelectFavouriteAnimal`), tipping staff (`GiveTipsTo`), and uploading photographs (`UploadPhotographs`). |
| **Caretaker** | Extends `Person`. Models zoo staff responsible for animal welfare; manages animal grooming (`BatheAnimal`), habitat maintenance (`CleanHabitat`), and specialized care. |
| **Animal** | Base abstract class modeling zoo animals. Encapsulates core biological and physical attributes (name, age, sound, diet, preferred food, weight, height, health status, habitat, species, color). |
| **Mammals** | Extends `Animal`. Represents mammalian species in the zoo with mammal-specific characteristics and behaviors. |
| **Birds** | Extends `Animal`. Represents avian species; tracks bird-specific attributes such as average egg production (`numberOfEggsProm`). |
| **Reptiles** | Extends `Animal`. Represents reptilian species in the zoo with reptile-specific traits. |

### Relationships

| Source | Relationship | Target | Multiplicity | Explanation |
|---|---|---|---|---|
| **ECIZoo** | Composition / Aggregation | **Animal** | `1` to `*` | `ECIZoo` contains and manages the collection of all registered animals in the zoo. |
| **ECIZoo** | Composition / Aggregation | **Person** | `1` to `*` | `ECIZoo` contains and manages the registered people (visitors and caretakers) in the zoo. |
| **Visitor** | Generalization (Inheritance) | **Person** | `*` to `1` | `Visitor` is a specialized subclass of `Person`, inheriting personal attributes and feeding behavior. |
| **Caretaker** | Generalization (Inheritance) | **Person** | `*` to `1` | `Caretaker` is a specialized subclass of `Person`, adding specialty and maintenance routines. |
| **Mammals** | Generalization (Inheritance) | **Animal** | `*` to `1` | `Mammals` is a specialized subclass of `Animal`. |
| **Birds** | Generalization (Inheritance) | **Animal** | `*` to `1` | `Birds` is a specialized subclass of `Animal`, adding avian attributes (`numberOfEggsProm`). |
| **Reptiles** | Generalization (Inheritance) | **Animal** | `*` to `1` | `Reptiles` is a specialized subclass of `Animal`. |
| **Visitor** | Association | **Animal** | `*` to `0..1` | A visitor can select and associate a specific animal as their favorite (`favouriteAnimal`). |
| **Person** | Association / Dependency | **Animal** | `*` to `*` | A person interacts with animals to feed them via `FeedAnimals(Animal)`. |
| **Caretaker** | Association / Dependency | **Animal** | `*` to `*` | A caretaker performs dedicated maintenance on animals (`BatheAnimal`, `CleanHabitat`). |

### SOLID Application

| Principle | Application in the UML Design |
|---|---|
| **Single Responsibility** | Each class has a single, well-defined role: `ECIZoo` coordinates registry and zoo management; `Visitor` handles guest-specific interactions; `Caretaker` encapsulates animal maintenance procedures; `Animal` (and its subcategories) encapsulates biological and habitat data. |
| **Open/Closed** | The design is open for extension and closed for modification. New animal categories (e.g., `Amphibians`, `Fish`) or new personnel roles (e.g., `Veterinarian`, `TourGuide`) can be added by subclassing `Animal` or `Person` without modifying existing zoo coordination logic. |
| **Liskov Substitution** | Subclasses (`Visitor`, `Caretaker` under `Person`; `Mammals`, `Birds`, `Reptiles` under `Animal`) can be substituted for their parent classes anywhere in the system without altering program correctness or breaking expectations. |
| **Interface Segregation** | Specialized operations are isolated to their specific roles (`BatheAnimal` and `CleanHabitat` belong exclusively to `Caretaker`, whereas `UploadPhotographs` belongs strictly to `Visitor`), preventing bloated interfaces. |
| **Dependency Inversion** | High-level management modules (`ECIZoo`) and interactions (`FeedAnimals`) depend on abstractions (`Person`, `Animal`) rather than concrete animal or personnel subclasses. |

### Design Patterns

| Item | Team Explanation |
|---|---|
| **Design Pattern Category** | Structural / Domain Modeling |
| **Pattern Used** | Object-Oriented Domain Hierarchy & Polymorphism |
| **Justification** | The zoo management system requires structured classification of diverse biological species and human roles while keeping management routines decoupled from specific concrete subtypes. Applying polymorphic domain modeling ensures modularity, extensibility, and maintainability. |
| **How It Was Applied** | Entity hierarchies are organized through abstract base classes (`Person` and `Animal`), while `ECIZoo` serves as the central domain controller interacting with these abstractions via polymorphic operations (`RegistrateAnimal`, `RegistrateVisitor`, `RegistrateCaretaker`). |
