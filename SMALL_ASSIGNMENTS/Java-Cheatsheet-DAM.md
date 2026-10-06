# CHEATSHEET JAVA - DAM (Técnico Superior en Desarrollo de Aplicaciones Multiplataforma)

## ÍNDICE
1. [Tipos de Datos](#tipos-de-datos)
2. [Variables y Constantes](#variables-y-constantes)
3. [Operadores](#operadores)
4. [Entrada de Datos (Scanner)](#entrada-de-datos-scanner)
5. [Salida de Datos (Print/Printf)](#salida-de-datos-printprintf)
6. [Condicionales (if/else/switch)](#condicionales-ifelseswitchswitch)
7. [Bucles (for/while/do-while)](#bucles-forwhiledo-while)
8. [Arrays](#arrays)
9. [Métodos/Funciones](#métodosfunciones)
10. [Strings (Cadenas de texto)](#strings-cadenas-de-texto)
11. [Conversión de Tipos](#conversión-de-tipos)
12. [Clases y Objetos](#clases-y-objetos)
13. [Try/Catch (Manejo de excepciones)](#trycatch-manejo-de-excepciones)

---

## TIPOS DE DATOS

### Tipos Primitivos (Valores simples)

```java
// NÚMEROS ENTEROS (sin decimales)
byte numero1 = 127;           // De -128 a 127
short numero2 = 32767;        // De -32,768 a 32,767
int numero3 = 2147483647;     // De -2,147,483,648 a 2,147,483,647 (USAR ESTE POR DEFECTO)
long numero4 = 9223372036854775807L;  // Números muy grandes (agregar L al final)

// NÚMEROS CON DECIMALES
float numero5 = 3.14f;        // De precisión simple (6-7 decimales) (agregar f al final)
double numero6 = 3.14159265358979;  // De precisión doble (15+ decimales) (USAR ESTE POR DEFECTO)

// BOOLEANO (verdadero/falso)
boolean esVerdad = true;      // true o false
boolean esFalso = false;

// CARÁCTER (un solo carácter)
char letra = 'A';             // Entre comillas simples
char numero = '5';
char simbolo = '@';
```

**¿Cuál usar?**
- Para números enteros normales: **int**
- Para números muy grandes: **long**
- Para números decimales: **double**
- Para verdadero/falso: **boolean**
- Para un solo carácter: **char**

---

## VARIABLES Y CONSTANTES

### Declarar variables

```java
// SINTAXIS: tipo nombre = valor;
int edad = 25;
double precio = 19.99;
String nombre = "Juan";

// Sin inicializar (se asigna valor después)
int contador;
contador = 10;

// Declarar varias del mismo tipo
int x = 1, y = 2, z = 3;

// Reasignar valor (cambiar)
edad = 26;  // Ahora edad vale 26, no 25
```

### Constantes (valores que NO cambian)

```java
// Usar final static para constantes de clase
// Por convención, en MAYÚSCULAS
public static final double PI = 3.14159265358979;
public static final int MAX_USUARIOS = 100;
public static final String NOMBRE_EMPRESA = "TechCorp";

// Una vez asignadas, NO pueden cambiar
// Si intentas: PI = 3.14; → ERROR

// Usar en código
double area = 2 * PI * radio;
if (usuarios > MAX_USUARIOS) {
    System.out.println("Máximo alcanzado");
}
```

---

## OPERADORES

### Operadores Aritméticos

```java
int a = 10, b = 3;

int suma = a + b;              // 13
int resta = a - b;             // 7
int multiplicacion = a * b;    // 30
int division = a / b;          // 3 (división entera, sin decimales)
double divisionExacta = (double) a / b;  // 3.333... (convertir a double)
int modulo = a % b;            // 1 (resto de la división)
int potencia = (int) Math.pow(a, b);  // 1000 (10 elevado a 3)
```

### Operadores de Incremento/Decremento

```java
int contador = 5;

contador++;      // contador = contador + 1; (ahora contador = 6)
contador--;      // contador = contador - 1; (ahora contador = 5)
contador += 3;   // contador = contador + 3; (ahora contador = 8)
contador -= 2;   // contador = contador - 2; (ahora contador = 6)
contador *= 2;   // contador = contador * 2; (ahora contador = 12)
contador /= 3;   // contador = contador / 3; (ahora contador = 4)
```

### Operadores de Comparación (Devuelven true/false)

```java
int x = 10, y = 20;

boolean iguales = (x == y);        // false (¿son iguales?)
boolean noIguales = (x != y);      // true  (¿NO son iguales?)
boolean menorQue = (x < y);        // true  (¿x menor que y?)
boolean menorOIgual = (x <= y);    // true  (¿x menor o igual?)
boolean mayorQue = (x > y);        // false (¿x mayor que y?)
boolean mayorOIgual = (x >= y);    // false (¿x mayor o igual?)
```

### Operadores Lógicos (Para combinar condiciones)

```java
boolean a = true, b = false;

// AND (y) - devuelve true si AMBAS son true
boolean resultado1 = (a && b);     // false (true Y false = false)
boolean resultado2 = (a && a);     // true  (true Y true = true)

// OR (o) - devuelve true si AL MENOS UNA es true
boolean resultado3 = (a || b);     // true  (true O false = true)
boolean resultado4 = (b || b);     // false (false O false = false)

// NOT (no) - invierte el valor
boolean resultado5 = !a;           // false (no true = false)
boolean resultado6 = !b;           // true  (no false = true)

// EJEMPLO COMPLETO
int edad = 25;
boolean esAdulto = (edad >= 18);
boolean tieneCarnet = true;
if (esAdulto && tieneCarnet) {
    System.out.println("Puede conducir");
}
```

---

## ENTRADA DE DATOS (SCANNER)

### Leer datos del usuario

```java
import java.util.Scanner;

// CREAR SCANNER
Scanner input = new Scanner(System.in);

// LEER DIFERENTES TIPOS
String nombre = input.nextLine();      // Lee una línea completa de texto
int numero = input.nextInt();          // Lee un número entero
double decimal = input.nextDouble();   // Lee un número con decimales
boolean verdadero = input.nextBoolean();  // Lee true o false

// EJEMPLO COMPLETO
Scanner sc = new Scanner(System.in);
System.out.println("¿Cuál es tu nombre?");
String nombre = sc.nextLine();

System.out.println("¿Cuántos años tienes?");
int edad = sc.nextInt();

System.out.println("Tu nombre es " + nombre + " y tienes " + edad + " años");

// IMPORTANTE: Limpiar buffer
// Después de nextInt/nextDouble/nextBoolean, queda un salto de línea en el buffer
// Por eso, si vas a leer con nextLine() después, debes limpiar:
int numero = sc.nextInt();
sc.nextLine();  // Limpia el buffer
String texto = sc.nextLine();  // Ahora funciona correctamente
```

---

## SALIDA DE DATOS (PRINT/PRINTF)

### Imprimir en consola

```java
// print - imprime sin salto de línea al final
System.out.print("Hola");
System.out.print(" mundo");
// Resultado: Hola mundo

// println - imprime con salto de línea al final
System.out.println("Primera línea");
System.out.println("Segunda línea");
// Resultado:
// Primera línea
// Segunda línea

// printf - imprime con formato
double precio = 19.99;
int cantidad = 5;
System.out.printf("Precio: %.2f euros, Cantidad: %d\n", precio, cantidad);
// Resultado: Precio: 19.99 euros, Cantidad: 5

// Especificadores de formato para printf:
// %d - número entero (int, long)
// %f - número decimal (float, double)
// %.2f - número decimal con 2 decimales
// %s - cadena de texto (String)
// %c - carácter (char)
// %b - booleano (true/false)
// %% - imprime un %

// EJEMPLOS
String nombre = "Ana";
int edad = 30;
double altura = 1.75;
System.out.printf("Nombre: %s, Edad: %d, Altura: %.2f m\n", nombre, edad, altura);
// Resultado: Nombre: Ana, Edad: 30, Altura: 1.75 m
```

---

## CONDICIONALES (IF/ELSE/SWITCH)

### If/Else

```java
int edad = 18;

// IF simple (si)
if (edad >= 18) {
    System.out.println("Eres adulto");
}

// IF-ELSE (si...sino)
if (edad >= 18) {
    System.out.println("Eres adulto");
} else {
    System.out.println("Eres menor");
}

// IF-ELSE IF-ELSE (varias condiciones)
int nota = 7;
if (nota >= 9) {
    System.out.println("Excelente");
} else if (nota >= 7) {
    System.out.println("Bien");
} else if (nota >= 5) {
    System.out.println("Aprobado");
} else {
    System.out.println("Suspenso");
}

// Operador ternario (if simplificado)
String estado = (edad >= 18) ? "Adulto" : "Menor";
System.out.println(estado);  // Imprime: Adulto

// Ejemplo más completo
double promedio = 8.5;
boolean estudia = true;
if (promedio >= 8 && estudia) {
    System.out.println("Buen estudiante");
} else {
    System.out.println("Necesita mejorar");
}
```

### Switch (múltiples opciones)

```java
int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;  // IMPORTANTE: break para no pasar al siguiente case
    case 2:
        System.out.println("Martes");
        break;
    case 3:
        System.out.println("Miércoles");
        break;
    case 4:
    case 5:  // Puede haber múltiples cases para el mismo resultado
        System.out.println("Entre semana");
        break;
    default:  // Si ningún case coincide
        System.out.println("Desconocido");
}

// Switch con Strings
String color = "rojo";
switch (color) {
    case "rojo":
        System.out.println("Es color rojo");
        break;
    case "azul":
        System.out.println("Es color azul");
        break;
    default:
        System.out.println("Otro color");
}
```

---

## BUCLES (FOR/WHILE/DO-WHILE)

### For (cuando sabes cuántas veces repetir)

```java
// FOR simple - repetir 5 veces
for (int i = 0; i < 5; i++) {
    System.out.println("Iteración " + i);
}
// Resultado:
// Iteración 0
// Iteración 1
// ... hasta Iteración 4

// FOR con array
int[] numeros = {10, 20, 30, 40, 50};
for (int i = 0; i < numeros.length; i++) {
    System.out.println("Elemento " + i + ": " + numeros[i]);
}

// FOR-EACH (más simple para recorrer)
int[] numeros = {10, 20, 30, 40, 50};
for (int num : numeros) {  // "num" toma cada valor del array
    System.out.println(num);
}

// FOR anidados (bucles dentro de bucles)
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.print(i + "," + j + " ");
    }
    System.out.println();
}
// Resultado:
// 1,1 1,2 1,3
// 2,1 2,2 2,3
// 3,1 3,2 3,3
```

### While (mientras se cumpla la condición)

```java
int contador = 0;
while (contador < 5) {
    System.out.println("Contador: " + contador);
    contador++;  // IMPORTANTE: incrementar, si no es bucle infinito
}
// Resultado:
// Contador: 0
// Contador: 1
// ... hasta Contador: 4

// Leer hasta que el usuario escriba "salir"
Scanner input = new Scanner(System.in);
String comando = "";
while (!comando.equals("salir")) {  // Mientras NO sea igual a "salir"
    System.out.println("Escribe comando (o 'salir' para terminar):");
    comando = input.nextLine();
}
System.out.println("Programa terminado");
```

### Do-While (ejecuta una vez, luego pregunta)

```java
int contador = 0;
do {
    System.out.println("Contador: " + contador);
    contador++;
} while (contador < 5);  // Pregunta al final
// Resultado: igual que while, pero se ejecuta al menos una vez

// Diferencia importante:
// Si contador fuera 10:
// while: NO entra al bucle
// do-while: entra una vez, imprime, pregunta y sale
```

### Break y Continue

```java
// BREAK - sale del bucle
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Sale del bucle cuando i = 5
    }
    System.out.println(i);
}
// Resultado: 0 1 2 3 4

// CONTINUE - salta a la siguiente iteración
for (int i = 0; i < 5; i++) {
    if (i == 2) {
        continue;  // Salta el 2, no lo imprime
    }
    System.out.println(i);
}
// Resultado: 0 1 3 4
```

---

## ARRAYS

### Crear y usar arrays

```java
// CREAR array de tamaño 5
int[] numeros = new int[5];

// CREAR array con valores iniciales
int[] numeros = {10, 20, 30, 40, 50};

// ACCEDER a elementos (índices empiezan en 0)
int primerElemento = numeros[0];   // 10
int tercerElemento = numeros[2];   // 30

// MODIFICAR elementos
numeros[1] = 25;  // Cambiar el segundo elemento

// SABER el tamaño del array
int tamaño = numeros.length;  // 5

// RECORRER array con for
for (int i = 0; i < numeros.length; i++) {
    System.out.println(numeros[i]);
}

// RECORRER array con for-each
for (int num : numeros) {
    System.out.println(num);
}

// Array de Strings
String[] nombres = {"Ana", "Juan", "María"};
System.out.println(nombres[0]);  // Ana

// Array de double
double[] precios = new double[3];
precios[0] = 19.99;
precios[1] = 29.99;
precios[2] = 39.99;

// EJEMPLO: Suma de todos los elementos
int[] numeros = {10, 20, 30, 40, 50};
int suma = 0;
for (int num : numeros) {
    suma += num;
}
System.out.println("Suma: " + suma);  // Suma: 150
```

### Arrays multidimensionales (matrices)

```java
// Array 2D (filas x columnas)
int[][] matriz = new int[3][3];

// Inicializar con valores
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Acceder a elementos
int elemento = matriz[1][2];  // 6 (fila 1, columna 2)

// Recorrer matriz
for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println();
}
// Resultado:
// 1 2 3
// 4 5 6
// 7 8 9
```

---

## MÉTODOS/FUNCIONES

### Definir y llamar métodos

```java
// MÉTODO sin parámetros y sin retorno
public static void saludar() {
    System.out.println("¡Hola!");
}

// Llamar el método
saludar();  // Imprime: ¡Hola!

// MÉTODO con parámetros y sin retorno
public static void saludarPersona(String nombre) {
    System.out.println("¡Hola " + nombre + "!");
}

saludarPersona("Juan");  // Imprime: ¡Hola Juan!

// MÉTODO con parámetros y CON retorno
public static int sumar(int a, int b) {
    return a + b;  // Devuelve el resultado
}

int resultado = sumar(5, 3);  // resultado = 8
System.out.println(resultado);

// MÉTODO con múltiples parámetros
public static double calcularPromedio(double[] calificaciones) {
    double suma = 0;
    for (double cal : calificaciones) {
        suma += cal;
    }
    return suma / calificaciones.length;
}

double[] notas = {8.5, 9.0, 7.5};
double promedio = calcularPromedio(notas);
System.out.println("Promedio: " + promedio);  // 8.33...

// MÉTODO static vs método de instancia
public class Calculadora {
    public static int multiplicar(int x, int y) {
        return x * y;
    }
}

// Llamar método static (sin crear objeto)
int resultado = Calculadora.multiplicar(4, 5);  // 20

// IMPORTANTE para DAM:
// En el main y en métodos auxiliares, SIEMPRE usar "static"
public class MiPrograma {
    public static void main(String[] args) {
        metodoAuxiliar();  // Puede llamar a otros métodos static
    }
    
    public static void metodoAuxiliar() {
        System.out.println("Método auxiliar");
    }
}
```

---

## STRINGS (CADENAS DE TEXTO)

### Crear y manipular Strings

```java
// CREAR Strings
String nombre = "Juan";
String saludo = "Hola " + nombre;  // Concatenación
String completo = "Hola " + nombre + ", bienvenido";

// MÉTODOS comunes de String
String texto = "Hola Mundo";

int longitud = texto.length();           // 11 (número de caracteres)
String minusculas = texto.toLowerCase(); // "hola mundo"
String mayusculas = texto.toUpperCase(); // "HOLA MUNDO"
String parte = texto.substring(0, 4);   // "Hola" (desde 0 hasta 4)
boolean contiene = texto.contains("Mundo");  // true

// DIVIDIR String
String tiempo = "05:30";
String[] partes = tiempo.split(":");  // Divide por ":"
// partes[0] = "05"
// partes[1] = "30"

// REEMPLAZAR
String frase = "Hola Juan, Hola Ana";
String nueva = frase.replace("Hola", "Adiós");  // "Adiós Juan, Adiós Ana"

// BUSCAR posición
int posicion = frase.indexOf("Juan");  // 5 (posición donde está "Juan")

// COMPARAR Strings
String a = "Hola";
String b = "Hola";
if (a.equals(b)) {  // USAR equals, NO ==
    System.out.println("Son iguales");
}
if (a.equalsIgnoreCase(b)) {  // Sin importar mayúsculas/minúsculas
    System.out.println("Son iguales (ignorando case)");
}

// CONVERTIR String a número
String numeroStr = "123";
int numero = Integer.parseInt(numeroStr);  // 123
double numeroDecimal = Double.parseDouble("3.14");  // 3.14

// EJEMPLO PRÁCTICO: Convertir "mm:ss" a segundos
String tiempo = "05:30";
String[] partes = tiempo.split(":");
int minutos = Integer.parseInt(partes[0]);
int segundos = Integer.parseInt(partes[1]);
int totalSegundos = minutos * 60 + segundos;  // 330
```

---

## CONVERSIÓN DE TIPOS

### Casting (cambiar tipo de dato)

```java
// Conversión automática (pequeño a grande)
int numeroEntero = 10;
double numeroDecimal = numeroEntero;  // Automático (10.0)

// Conversión manual (grande a pequeño) - puede perder información
double precio = 19.99;
int precioRedondeado = (int) precio;  // 19 (pierde los decimales)

// String a número
String numeroStr = "42";
int numero = Integer.parseInt(numeroStr);  // 42
double decimal = Double.parseDouble("3.14");  // 3.14

// Número a String
int numero = 42;
String numeroStr = String.valueOf(numero);  // "42"
String numeroStr2 = numero + "";  // También funciona: "42"

// EJEMPLO completo
String entrada = "25";
int edad = Integer.parseInt(entrada);  // Convertir a int
if (edad >= 18) {
    System.out.println("Eres adulto");
}
```

---

## CLASES Y OBJETOS

### Estructura básica

```java
// DEFINIR una clase
public class Persona {
    // Atributos (propiedades)
    String nombre;
    int edad;
    
    // Constructor (se ejecuta al crear el objeto)
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    // Método
    public void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}

// USAR la clase
Persona juan = new Persona("Juan", 25);  // Crear objeto
juan.saludar();  // Llamar método: "Hola, soy Juan"
System.out.println(juan.nombre);  // Acceder atributo: "Juan"
```

### Clases para DAM (Ciclos de vida)

```java
// Ejemplo: Clase para manejar datos de un producto
public class Producto {
    String nombre;
    double precio;
    int stock;
    
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    public void mostrarInfo() {
        System.out.printf("Producto: %s, Precio: %.2f, Stock: %d\n", 
                          nombre, precio, stock);
    }
    
    public void vender(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            System.out.println("Venta realizada");
        } else {
            System.out.println("No hay stock suficiente");
        }
    }
}

// Usar la clase
Producto p = new Producto("Laptop", 899.99, 10);
p.mostrarInfo();  // Mostrar info
p.vender(2);  // Vender 2 unidades
p.mostrarInfo();  // Stock ahora es 8
```

---

## TRY/CATCH (MANEJO DE EXCEPCIONES)

### Capturar errores

```java
// SIN manejo (si hay error, el programa se detiene)
String numeroStr = "abc";
int numero = Integer.parseInt(numeroStr);  // ERROR: NumberFormatException

// CON manejo
try {
    String numeroStr = "abc";
    int numero = Integer.parseInt(numeroStr);
} catch (NumberFormatException e) {
    System.out.println("Error: No es un número válido");
}

// MÚLTIPLES catch
try {
    int[] numeros = {1, 2, 3};
    System.out.println(numeros[10]);  // Error: índice fuera de rango
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Error: índice fuera de rango");
} catch (Exception e) {  // Catch genérico (cualquier error)
    System.out.println("Error desconocido");
}

// TRY-CATCH-FINALLY (finally siempre se ejecuta)
try {
    String numero = "abc";
    Integer.parseInt(numero);
} catch (NumberFormatException e) {
    System.out.println("Formato inválido");
} finally {
    System.out.println("Fin del bloque try-catch");  // SIEMPRE se ejecuta
}

// EJEMPLO práctico con Scanner
Scanner input = new Scanner(System.in);
boolean valido = false;
while (!valido) {
    try {
        System.out.println("Introduce un número:");
        int numero = input.nextInt();
        valido = true;
    } catch (Exception e) {
        System.out.println("Error: Debes introducir un número");
        input.nextLine();  // Limpiar buffer
    }
}
```

---

## TIPS Y BUENAS PRÁCTICAS

### Nombres de variables (Convención camelCase)

```java
// CORRECTO
int edad = 25;
String nombreCompleto = "Juan Pérez";
double precioPorUnidad = 19.99;
boolean esEstudiante = true;

// INCORRECTO (evitar)
int e = 25;
String NombreCompleto = "Juan";  // Empezar con mayúscula
double precio_por_unidad = 19.99;  // Usar guiones
```

### Nombres de constantes (MAYÚSCULAS)

```java
public static final int EDAD_MINIMA = 18;
public static final double PI = 3.14159265358979;
public static final String EMPRESA = "TechCorp";
```

### Indentación y formato

```java
// BIEN INDENTADO (fácil de leer)
if (edad >= 18) {
    if (tieneCarnet) {
        System.out.println("Puede conducir");
    }
}

// MAL INDENTADO (difícil de leer)
if (edad >= 18) {
if (tieneCarnet) {
System.out.println("Puede conducir");
}
}
```

### Comentarios útiles

```java
// Comentario de una línea
int edad = 25;  // Esta es la edad del usuario

/* Comentario de múltiples líneas
   Explicación más detallada
   de lo que hace este código */

/** JavaDoc - comentario especial para documentación
 * @param nombre El nombre de la persona
 * @return Devuelve un saludo personalizado
 */
public static String saludarPersona(String nombre) {
    return "Hola " + nombre;
}
```

---

## EJERCICIO COMPLETO: SUMA DE NÚMEROS EN ARRAY

```java
import java.util.Scanner;

public class SumaArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Pedir cantidad de números
        System.out.println("¿Cuántos números deseas sumar?");
        int cantidad = input.nextInt();
        
        // Crear array
        double[] numeros = new double[cantidad];
        
        // Leer los números
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduce número " + (i + 1) + ":");
            numeros[i] = input.nextDouble();
        }
        
        // Calcular suma
        double suma = calcularSuma(numeros);
        
        // Mostrar resultado
        System.out.printf("La suma total es: %.2f\n", suma);
    }
    
    public static double calcularSuma(double[] numeros) {
        double total = 0;
        for (double num : numeros) {
            total += num;
        }
        return total;
    }
}
```

---

## EJERCICIO COMPLETO: CONVERTIR MM:SS A SEGUNDOS (COMO TU PROYECTO BICI)

```java
import java.util.Scanner;

public class ConvertidorTiempo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Leer tiempo en formato mm:ss
        System.out.println("Introduce tiempo en formato mm:ss:");
        String tiempo = input.nextLine();
        
        // Convertir a segundos
        double segundos = convertirATotalSegundos(tiempo);
        
        // Mostrar resultado
        System.out.printf("Total: %.0f segundos\n", segundos);
    }
    
    // Convierte "mm:ss" a segundos totales
    public static double convertirATotalSegundos(String tiempo) {
        String[] partes = tiempo.split(":");
        int minutos = Integer.parseInt(partes[0]);
        int seg = Integer.parseInt(partes[1]);
        return minutos * 60 + seg;
    }
}
```

---

## ERRORES COMUNES Y CÓMO EVITARLOS

```java
// ERROR 1: Olvidar break en switch
switch (opcion) {
    case 1:
        System.out.println("Opción 1");
        // FALTA break, ejecuta también case 2
    case 2:
        System.out.println("Opción 2");
        break;
}
// CORRECTO:
switch (opcion) {
    case 1:
        System.out.println("Opción 1");
        break;  // Agregar esto
    case 2:
        System.out.println("Opción 2");
        break;
}

// ERROR 2: Bucle infinito
int i = 0;
while (i < 10) {
    System.out.println(i);
    // FALTA i++, bucle infinito
}
// CORRECTO:
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;  // Incrementar
}

// ERROR 3: Usar == para comparar Strings
String a = "Hola";
if (a == "Hola") {  // INCORRECTO
    System.out.println("Son iguales");
}
// CORRECTO:
if (a.equals("Hola")) {
    System.out.println("Son iguales");
}

// ERROR 4: No limpiar buffer después de nextInt/nextDouble
int numero = input.nextInt();
String texto = input.nextLine();  // PROBLEMA: lee vacío
// CORRECTO:
int numero = input.nextInt();
input.nextLine();  // Limpiar
String texto = input.nextLine();

// ERROR 5: Acceder a índice inválido de array
int[] numeros = {1, 2, 3};
System.out.println(numeros[5]);  // ERROR: fuera de rango
// CORRECTO:
if (5 < numeros.length) {
    System.out.println(numeros[5]);
}

// ERROR 6: No inicializar variable antes de usar
int resultado;
System.out.println(resultado);  // ERROR: no inicializada
// CORRECTO:
int resultado = 0;
System.out.println(resultado);
```

---

## ATAJOS DE TECLADO EN ECLIPSE

```
Ctrl + Shift + F  → Formatear código (indentación automática)
Ctrl + /          → Comentar/Descomentar línea
Ctrl + 1          → Sugerencias rápidas (Quick Fix)
Alt + /           → Autocompletar
F11              → Ejecutar (Run)
Ctrl + S         → Guardar
Ctrl + Z         → Deshacer
Ctrl + Y         → Rehacer
```

---

## ESTRUCTURA BÁSICA DE UN PROGRAMA JAVA

```java
import java.util.Scanner;  // Importar clases si es necesario

public class MiPrograma {  // Nombre del archivo .java debe coincidir
    
    // CONSTANTES (si necesitas)
    public static final double IVA = 0.21;
    
    public static void main(String[] args) {
        // Aquí va el programa principal
        Scanner input = new Scanner(System.in);
        
        System.out.println("¿Cuál es tu nombre?");
        String nombre = input.nextLine();
        
        saludar(nombre);  // Llamar método auxiliar
        
        input.close();  // Cerrar Scanner (buena práctica)
    }
    
    // MÉTODOS AUXILIARES
    public static void saludar(String nombre) {
        System.out.println("Hola " + nombre + ", bienvenido");
    }
}
```

---

## RESUMEN RÁPIDO DE ESTRUCTURAS

| Estructura | Uso | Ejemplo |
|-----------|-----|---------|
| **if/else** | Tomar decisiones | `if (edad >= 18) { ... }` |
| **switch** | Múltiples opciones | `switch (opcion) { case 1: ... }` |
| **for** | Repetir número de veces conocido | `for (int i = 0; i < 5; i++) { ... }` |
| **while** | Repetir mientras se cumple condición | `while (x < 10) { ... }` |
| **for-each** | Recorrer arrays | `for (int num : array) { ... }` |
| **array** | Guardar múltiples valores | `int[] numeros = new int[5];` |
| **método** | Reutilizar código | `public static void método() { ... }` |
| **try-catch** | Manejar errores | `try { ... } catch (Exception e) { ... }` |

---

**FIN DE LA CHEATSHEET**

Este documento cubre todos los conceptos principales de Java que necesitarás para el examen. 
Llévalo impreso o en PDF y consulta según lo necesites. ¡Mucho éxito!