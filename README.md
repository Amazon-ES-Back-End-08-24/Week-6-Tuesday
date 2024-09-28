# Week 6

# Tabla de Contenidos

- [Introducción a Spring y Spring Boot](#introducción-a-spring-y-spring-boot)
    - [¿Por qué utilizar estos conceptos?](#por-qué-utilizar-estos-conceptos)
    - [Componentes de Spring](#componentes-de-spring)
        - [Spring JPA y Spring Data JPA](#spring-jpa-y-spring-data-jpa)
        - [Spring Web](#spring-web)
        - [MySQL Driver](#mysql-driver)
    - [Arquitectura en Capas](#arquitectura-en-capas)
    - [Entidades en JPA](#entidades-entities-en-jpa)
        - [Conceptos clave](#conceptos-clave)
        - [Ventajas de las Entidades en JPA](#ventajas-de-las-entidades-en-jpa)
    - [Repositorio y JPA Repository](#repositorio-y-jpa-repository)
    - [Consultas Derivadas en Spring Data JPA](#consultas-derivadas-en-spring-data-jpa)
        - [Cómo funciona](#cómo-funciona)
        - [Convenciones de nombres de métodos](#convenciones-de-nombres-de-métodos)
        - [Ejemplos de Métodos Derivados](#ejemplos-de-métodos-derivados)
        - [Ventajas de las Consultas Derivadas](#ventajas-de-las-consultas-derivadas)
    - [Notas Importantes](#notas-importantes)
    - [Ejercicio Práctico¡](#ejercicio-práctico-gestión-de-inventario-de-vehículos-con-spring-boot-y-jpa)

---

# Introducción a Spring y Spring Boot

## ¿Por qué utilizar estos conceptos?

- **Spring** es un framework potente y flexible que ayuda a desarrollar aplicaciones en Java de manera eficiente,
  manejando la infraestructura subyacente y permitiendo que los desarrolladores se enfoquen en la lógica de negocio.

- **Spring Boot** simplifica el desarrollo al proporcionar una configuración mínima para crear aplicaciones listas para
  producción. Su integración con **Maven** facilita la gestión de dependencias y la automatización de tareas.

---

## Componentes de Spring

### Spring JPA y Spring Data JPA

- **Definición**: Spring Data JPA proporciona una capa de abstracción sobre JPA (Java Persistence API) para facilitar
  las operaciones relacionadas con la base de datos, permitiendo la creación de repositorios con mínimas líneas de
  código.

- **Uso**: Simplifica las operaciones CRUD y permite realizar consultas complejas con facilidad, a través de métodos
  proporcionados por interfaces como `JpaRepository`.

### Spring Web

- **Definición**: Parte del framework Spring que facilita el desarrollo de aplicaciones web, incluyendo soporte para
  APIs RESTful.

- **Uso**: Con **Spring Web** puedes crear controladores que manejen solicitudes HTTP y devuelvan respuestas,
  facilitando el desarrollo de servicios web.

### MySQL Driver

- **Definición**: Componente esencial para que las aplicaciones Java puedan interactuar con bases de datos MySQL.

- **Configuración** en el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/database
spring.datasource.username=user
spring.datasource.password=pass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

- **spring.jpa.hibernate.ddl-auto**: Especifica cómo se debe manejar el esquema de la base de datos. Algunas opciones
  comunes incluyen:
    - `validate`: Valida el esquema sin realizar cambios.
    - `update`: Actualiza el esquema existente.
    - `create`: Crea el esquema destruyendo los datos previos.
    - `create-drop`: Elimina el esquema al cerrar la aplicación.

---

## Arquitectura en Capas

1. **Capa de Controlador**: Gestiona las solicitudes HTTP, delega operaciones a los servicios y devuelve las respuestas.

2. **Capa de Servicio**: Contiene la lógica de negocio, ejecuta cálculos, transformaciones de datos y valida la
   coherencia entre registros.

3. **Capa de Datos**: Responsable de la persistencia de datos, interactúa directamente con los repositorios para
   realizar operaciones CRUD en la base de datos.

---

## Entidades (Entities) en JPA

En JPA (Java Persistence API), una **entidad** es una clase que representa una tabla en una base de datos relacional.
Las entidades están mapeadas a tablas mediante anotaciones y se utilizan para modelar los datos que se almacenan en la
base de datos.

### Conceptos Clave:

1. **`@Entity`**:
    - La anotación `@Entity` se utiliza para declarar que una clase es una entidad JPA. Esto significa que la clase se
      mapeará a una tabla de base de datos, y sus atributos se mapearán a columnas.
    - Ejemplo:
      ```java
      @Entity
      public class Book {
      }
      ```

2. **`@Table(name = "nombre_tabla")`**:
    - La anotación `@Table` se utiliza para especificar el nombre de la tabla en la base de datos a la que se va a
      mapear la entidad. Si no se especifica, el nombre de la tabla será el mismo que el nombre de la clase.
    - Ejemplo:
      ```java
      @Table(name = "books")
      public class Book {
      }
      ```

3. **`@Id`**:
    - La anotación `@Id` se utiliza para marcar el atributo que será la clave primaria de la tabla. Esta clave primaria
      identifica de manera única a cada fila en la tabla.
    - Ejemplo:
      ```java
      @Id
      private Integer bookId;
      ```

4. **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**:
    - La anotación `@GeneratedValue` se usa para especificar que el valor de la clave primaria será generado
      automáticamente por la base de datos. La estrategia `IDENTITY` indica que la base de datos utilizará una columna
      con auto-incremento para generar los valores de la clave primaria.
    - Ejemplo:
      ```java
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer bookId;
      ```

5. **`@Column(name = "nombre_columna")`**:
    - La anotación `@Column` se utiliza para especificar el nombre de la columna en la base de datos que se mapeará a un
      atributo de la entidad. Si no se especifica, se utilizará el nombre del atributo de la clase como nombre de la
      columna.
    - Ejemplo:
      ```java
      @Column(name = "title")
      private String bookTitle;
      ```

### Ventajas de las Entidades en JPA:

- **Mapeo claro**: Cada entidad se mapea directamente a una tabla en la base de datos, lo que facilita la manipulación
  de los datos.
- **Relaciones**: JPA permite definir relaciones entre entidades para representar asociaciones entre tablas.
- **Abstracción**: Permite trabajar con objetos Java en lugar de escribir consultas SQL directas, lo que facilita el
  desarrollo y el mantenimiento.

---

## Repositorio y JPA Repository

- **Definición**: Los repositorios son componentes que manejan la interacción con la base de datos de manera abstracta,
  permitiendo realizar operaciones CRUD y consultas personalizadas sin escribir código SQL explícito.

- **`JpaRepository`**: Proporciona operaciones CRUD y consultas por defecto sobre entidades gestionadas.

### `BookRepository`

```java
import org.ironhack.week6tuesday.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
```

---

## Consultas Derivadas en Spring Data JPA

**Spring Data JPA** permite crear consultas dinámicas utilizando los nombres de los métodos en los repositorios. En
lugar de escribir consultas manualmente (ya sea en JPQL o SQL nativo), Spring puede generar automáticamente las
consultas a partir de los nombres de los métodos definidos en los repositorios. Este enfoque simplifica el desarrollo,
ya que no es necesario definir explícitamente las consultas para operaciones comunes.

### Cómo funciona:

El nombre del método debe seguir una convención que Spring interprete para generar la consulta automáticamente. El
framework analiza el nombre del método, reconoce las entidades y los atributos involucrados, y crea la consulta
adecuada.

### Convenciones de nombres de métodos:

1. **findBy**: Utilizado para recuperar datos basados en condiciones.
    - Ejemplo: `findByBookTitle(String title)`
    - Genera una consulta para buscar todos los libros cuyo título coincida con el valor de `titulo`.

2. **findBy + Atributo + Condición**: Se puede agregar una condición específica al nombre del atributo para realizar
   consultas más complejas.
    - Ejemplo: `findByBookPriceGreaterThan(Integer price)`
    - Genera una consulta para buscar todos los libros cuyo precio sea mayor que `precio`.

3. **And / Or**: Puedes combinar varias condiciones utilizando `And` y `Or`.
    - Ejemplo: `findByBookTitleAndBookPrice(String title, Integer price)`
    - Genera una consulta que busca todos los libros cuyo título coincida con `titulo` **y** cuyo precio coincida
      con `precio`.

4. **OrderBy**: Permite ordenar los resultados de la consulta según un atributo.
    - Ejemplo: `findByBookTitleOrderByBookPriceDesc(String title)`
    - Genera una consulta que busca todos los libros cuyo título coincida con `titulo` y los ordena por `libroPrecio` en
      orden descendente.

### Ejemplos de Métodos Derivados:

```java
// Encuentra libros por título exacto
List<Libro> findByBookTitle(String title);

// Encuentra libros cuyo precio sea mayor o igual a un valor
List<Libro> findByBookPriceGreaterThan(Integer price);

// Encuentra libros cuyo título coincida con un valor y cuyo precio sea menor a un valor
List<Libro> findByLibroTituloAndLibroPrecioLessThan(String titulo, Integer precio);

// Encuentra libros cuyo precio esté entre dos valores
List<Libro> findByBookTitleAndBookPrice(String title, Integer price);

// Encuentra libros por título y los ordena por precio en orden ascendente
List<Libro> findByBookTitleOrderByBookPriceDesc(String title);
```

### Ventajas de las Consultas Derivadas:

1. **Simplicidad**: No necesitas escribir consultas manualmente, solo defines métodos con nombres claros.
2. **Automatización**: Spring genera automáticamente la consulta correcta según el nombre del método.
3. **Legibilidad**: Los nombres de los métodos son descriptivos y fáciles de entender, lo que mejora la claridad del
   código.

---

## Notas Importantes

- **Spring Boot** simplifica la configuración inicial de proyectos, especialmente en lo que respecta a la gestión de
  dependencias y configuración.

- **JPA** y **Spring Data JPA** ofrecen una forma fácil de interactuar con bases de datos sin la necesidad de escribir
  consultas SQL explícitas, utilizando **JpaRepository**.

- Las **queries derivadas de Spring Data JPA** permiten reducir el código necesario y facilita la creación de consultas
  estándar de manera rápida y eficiente.

- La arquitectura en capas es clave para separar responsabilidades y mejorar la mantenibilidad del código.

---

## Ejercicio Práctico: Gestión de Inventario de Vehículos con Spring Boot y JPA

Vas a crear una aplicación web sencilla para gestionar el inventario de vehículos en una concesionaria utilizando Spring
Boot con Spring Data JPA y una base de datos MySQL. El objetivo de este ejercicio es que practiques la creación de
entidades, repositorios y consultas con JPA queries, junto con el uso de Spring Initializr para configurar tu proyecto.

### Tareas:

1. Configurar el Proyecto con Spring Initializr:
    - Crea un proyecto con Spring Initializr seleccionando las dependencias Spring Web, Spring Data JPA, y MySQL Driver.
    - Configura el archivo application.properties para la conexión con MySQL.

2. Creación de Entidades:
    - Crea una entidad llamada `Car` con los siguientes atributos:
        - id (Long, clave primaria, autogenerada)
        - brand (String)
        - model (String)
        - year (Integer)
        - price (Decimal)
        - stock (Integer)

    - Crea una entidad llamada `Sale` para registrar las ventas de vehículos:
        - id (Long, clave primaria, autogenerada)
        - carId
        - quantity (Integer)
        - salesDate (LocalDate)

3. Crear Repositorios:
    - Crea un repositorio para la entidad `Car` utilizando la interfaz JpaRepository
    - Crea un repositorio para la entidad `Sale` utilizando la interfaz JpaRepository

4. Consultas JPA Básicas:
    - Implementa consultas en el repositorio de `Car` para realizar las siguientes tareas:
        - Encontrar todos los vehículos disponibles.
        - Buscar vehículos por brand.
        - Buscar vehículos cuyo precio esté dentro de un rango especificado (por ejemplo, entre 10,000 y 50,000).
        - Ordenar los vehículos por price de mayor a menor.

    - En el repositorio de `Sale`, implementa una consulta que:
        - Encuentre todas las ventas realizadas en una fecha específica.
        - Agrupe las ventas por carId y calcule el número total de ventas por carId.

### Ejercicio Práctico: Gestión de Inventario de Vehículos con Spring Boot y JPA

En este ejercicio, vamos a desarrollar una aplicación para gestionar el inventario y las ventas de vehículos en una concesionaria. El objetivo es practicar la creación de entidades, consultas con **JPQL** y **SQL nativo** utilizando **Spring Boot** y **Spring Data JPA**, sin relaciones explícitas en las entidades. La base de datos utilizada será **MySQL**.

---

### Tareas:

1. **Configuración del Proyecto con Spring Initializr**:
    - Crea un proyecto con **Spring Initializr** y selecciona las siguientes dependencias:
        - Spring Web
        - Spring Data JPA
        - MySQL Driver
    - Configura la conexión a la base de datos en el archivo `application.properties` para conectar con **MySQL**.

2. **Creación de Entidades**:
    - Define la entidad **`Car`** con los siguientes atributos:
        - `id`: Long, clave primaria autogenerada.
        - `brand`: String, marca del vehículo.
        - `model`: String, modelo del vehículo.
        - `year`: Integer, año de fabricación.
        - `price`: Decimal, precio del vehículo.
        - `stock`: Integer, cantidad disponible en el inventario.
    - Define la entidad **`Sale`** para registrar las ventas de vehículos con los siguientes atributos:
        - `id`: Long, clave primaria autogenerada.
        - `carId`: Long, hace referencia al vehículo vendido.
        - `quantity`: Integer, cantidad vendida.
        - `salesDate`: LocalDate, fecha de la venta.

3. **Creación de Repositorios**:
    - Crea un repositorio para la entidad `Car` utilizando la interfaz `JpaRepository`.
    - Crea un repositorio para la entidad `Sale` utilizando la interfaz `JpaRepository`.

4. **Consultas JPQL Básicas**:
    - Implementa las siguientes consultas en el repositorio de `Car` utilizando **JPQL**:
        - Encuentra todos los vehículos disponibles.
        - Busca vehículos por la marca (`brand`).
        - Busca vehículos cuyo precio esté dentro de un rango (por ejemplo, entre 10,000 y 50,000).
        - Ordena los vehículos por precio, de mayor a menor.
    - En el repositorio de `Sale`, implementa las siguientes consultas utilizando **JPQL**:
        - Encuentra todas las ventas realizadas en una fecha específica.
        - Agrupa las ventas por `carId` y calcula el número total de ventas por `carId`.


5. **Consultas con JPQL**:

- Crea una consulta en JPQL que busque vehículos por `brand` y los ordene por `price` de menor a mayor.
- Implementa una consulta que encuentre vehículos cuyo precio esté dentro de un rango especificado.
- Crea una consulta para buscar vehículos cuyo `model` contenga un término específico.
- Implementa una consulta en JPQL que encuentre todas las ventas realizadas después de una fecha específica.
- Implementa una consulta que agrupe las ventas por `carId` y cuente el número total de ventas por cada vehículo.
- Crea una consulta que encuentre el vehículo más caro en el inventario.
- Implementa una consulta que encuentre todas las ventas para un vehículo específico usando su `carId`.

6. **Consultas con SQL Nativo**:

- Crea una consulta en SQL nativo para encontrar todos los vehículos cuyo precio sea mayor a un valor específico.
- Implementa una consulta en SQL nativo que busque vehículos por `brand`.
- Crea una consulta que encuentre vehículos cuyo `model` comience con una letra específica usando `LIKE`.
- Crea una consulta en SQL nativo que sume todas las ventas realizadas para un vehículo específico.
- Implementa una consulta en SQL nativo que encuentre el vehículo más barato.
- Crea una consulta que busque todas las ventas realizadas entre dos fechas específicas.
- Implementa una consulta en SQL nativo que realice un `JOIN` entre las tablas `Car` y `Sale` para obtener las ventas junto con el precio del vehículo.
- Implementa una consulta en SQL nativo que calcule el total de ingresos (suma de precios) de las ventas de vehículos en un rango de fechas.

---

## JPQL vs SQL

### JPQL

- **Definición**: JPQL (Java Persistence Query Language) es un lenguaje de consultas orientado a objetos que trabaja con entidades JPA. Se basa en la sintaxis SQL pero opera a un nivel más alto de abstracción, permitiendo realizar consultas sobre clases y objetos en lugar de tablas y columnas.

- **Uso**: JPQL se usa cuando se desea una portabilidad entre distintas bases de datos y consultas más orientadas a objetos.

- Consultas agregadas: JPQL permite realizar consultas agregadas (SUM, AVG, MIN, MAX) de forma sencilla cuando trabajamos sobre entidades JPA.

```java
// Consulta personalizada usando JPQL en Book repository

// Named Bind Parameter (Parámetro de enlace con nombre)
@Query("SELECT l FROM Libro l WHERE l.libroTitulo LIKE %:title%")
List<Libro> findByLibroTituloLike(String title);

// Funciones agregadas con JPQL
@Query("SELECT SUM(l.libroPrecio) FROM Libro l")
Integer sumLibroPrecio();

@Query("SELECT AVG(l.libroPrecio) FROM Libro l")
Double averageLibroPrecio();

@Query("SELECT MAX(l.libroPrecio) FROM Libro l")
Integer maxLibroPrecio();

@Query("SELECT MIN(l.libroPrecio) FROM Libro l")
Integer minLibroPrecio();

// En Sale repository

@Query("SELECT s.clientName, s.saleDate FROM Sale s WHERE s.bookId = :bookId")
List<Object[]> findClientNamesAndSaleDatesByBookId(@Param("bookId") Integer bookId);

@Query("SELECT s.clientName, s.saleDate, b.bookTitle FROM Sale s JOIN Book b ON s.bookId = b.bookId WHERE s.bookId = :bookId")
List<Object[]> findClientNamesAndSaleDatesAndBookTitleByBookId(@Param("bookId") Integer bookId);

// Positional Bind Parameter (Parámetro de enlace posicional)
@Query("SELECT s FROM Sale s WHERE s.bookId = ?1 AND s.saleDate = ?2")
List<Sale> findSalesByBookIdAndSaleDate(Integer bookId, String saleDate);

```

### SQL

- **Definición**: SQL (Structured Query Language) es un lenguaje estándar utilizado para interactuar directamente con las bases de datos relacionales. Las consultas SQL trabajan directamente con tablas y columnas, lo que lo hace más potente en ciertas optimizaciones específicas de la base de datos.

- **Uso**: SQL nativo es preferido cuando se requiere un control total sobre las consultas y optimizaciones específicas de la base de datos.

#### Ejemplo:
Esta consulta compara el precio de los libros con el promedio calculado en una subconsulta. Aunque JPQL permite subconsultas, cuando la lógica es más compleja o dependiente de SQL específico, es mejor usar SQL nativo.
```sql
@Query(value = "SELECT * FROM books b WHERE b.price > (SELECT AVG(price) FROM books)", nativeQuery = true)
List<Book> findBooksAboveAveragePrice();
```

#### Más ejemplos:
```sql
-- Consultas agregadas en SQL
SELECT SUM(price) FROM books;
SELECT AVG(price) FROM books;
SELECT MAX(price) FROM books;
SELECT MIN(price) FROM books;
```

### Comparación

- **Nivel de Abstracción**: JPQL opera a un nivel más alto, trabajando con objetos y clases, mientras que SQL trabaja con tablas y columnas.
- **Portabilidad**: JPQL es más portable entre diferentes bases de datos porque no depende de características específicas de un sistema, mientras que SQL puede ser optimizado para una base de datos particular.
- **Complejidad de Consultas**: SQL puede ser más potente para operaciones complejas y optimizaciones específicas de una base de datos, mientras que JPQL ofrece una solución generalizada y orientada a objetos.

### Resumen:

- **JPQL** permite:
    - Consultas orientadas a entidades JPA.
    - Uso de agregaciones estándar (`SUM`, `AVG`, etc.).
    - Consultas que involucran relaciones entre entidades.
    - Paginación y ordenación directamente con entidades.
    - Subconsultas en el `WHERE`.

- **SQL Nativo** permite:
    - Uso de funciones específicas de la base de datos (JSON, arrays, etc.).
    - Optimización avanzada con índices.
    - Funciones de ventana (`RANK()`, `ROW_NUMBER()`, etc.).
    - Actualizaciones/borrados que afectan múltiples tablas con `JOINs`.
    - Subconsultas en el `FROM`.
    - Consultas recursivas con `WITH` (CTE).

Ambos enfoques son útiles dependiendo de la complejidad y los requerimientos específicos de la consulta.

---
