package regex;

/*
 * @author Uriel Neves Silva (https://gitlab.com/a253119un)
 * This is a code that get strings of numbers and use regex expressions
 */

 public class RegexTest {
    public static void main(String[] args) {
        
        // ========== EJERCICIO A ==========
        // Números sin dos dígitos pares consecutivos
        System.out.println("EJERCICIO A: Números sin pares consecutivos");
        System.out.println("--------------------------------------------");
        
        // Esta es nuestra expresión regular
        String regexA = "([02468][13579])*([02468]|[13579])";
        System.out.println("Regex: " + regexA);
        System.out.println();
        
        // Casos que DEBEN aceptarse ✓
        System.out.println("Casos VÁLIDOS (✓):");
        testString(regexA, "1");        // un solo dígito impar
        testString(regexA, "2");        // un solo dígito par
        testString(regexA, "213");      // par-impar-impar (OK!)
        testString(regexA, "2183");     // par-impar-par-impar (OK!)
        testString(regexA, "13579");    // todos impares (OK!)
        
        System.out.println();
        
        // Casos que DEBEN rechazarse ✗
        System.out.println("Casos INVÁLIDOS (✗):");
        testString(regexA, "24");       // dos pares seguidos: 2 y 4
        testString(regexA, "2468");     // todos pares seguidos
        testString(regexA, "2283");     // tiene 22 (dos pares)
        testString(regexA, "80163");    // tiene 80 (dos pares)
        
        
        // ========== EJERCICIO B ==========
        System.out.println("\n\n");
        System.out.println("EJERCICIO B: Comentarios especiales");
        System.out.println("------------------------------------");
        
        // Esta es nuestra expresión regular
        String regexB = "(\\*1[^\\n]*\\n|\\*2[^\\n]*\\n[^\\n]*\\n)";
        System.out.println("Regex: " + regexB);
        System.out.println();
        
        // Tipo 1: *1 y termina en esa línea
        System.out.println("Comentarios tipo *1 (una línea):");
        testString(regexB, "*1 este es un comentario\n");
        testString(regexB, "*1 otro comentario más\n");
        
        System.out.println();
        
        // Tipo 2: *2 y termina en la línea siguiente
        System.out.println("Comentarios tipo *2 (dos líneas):");
        testString(regexB, "*2 primera línea\nsegunda línea\n");
        testString(regexB, "*2 empieza aquí\ny termina aquí\n");
        
        System.out.println();
        
        // Casos inválidos
        System.out.println("Comentarios INVÁLIDOS:");
        testString(regexB, "*3 no empieza con *1 o *2\n");
        testString(regexB, "*1 sin salto de línea");
    }
    
    // Método helper para hacer las pruebas más simples
    public static void testString(String regex, String text) {
        boolean matches = text.matches(regex);
        
        // Mostrar saltos de línea como \n para que se vean
        String displayText = text.replace("\n", "\\n");
        
        if (matches) {
            System.out.println("  ✓ ACEPTA: '" + displayText + "'");
        } else {
            System.out.println("  ✗ RECHAZA: '" + displayText + "'");
        }
    }
}