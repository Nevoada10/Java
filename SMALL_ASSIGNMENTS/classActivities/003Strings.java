package classActivities;

class Strings1 {
    public static void main(String[] args) {
    
    // String creation
    String st1  = "Joan";
    String st2 = "This is a sentence with some words.";
    String st3 = "This is a sentence with" + " more tha one line." + "THE END";
    
    System.out.println("\nCreation of strings:");
    System.out.println("st1:" + st1 );
    System.out.println("st2:" + st2 );
    System.out.println("st3:" + st3 + "\n");
    
    // Text in blocks
    System.out.println("Text in blocks:");
    String text = """
    This is the first line.
        This is the second line.
    This is the last line. THE END!
    """;
    System.out.println(text);

    // string.length()
    System.out.println("String.length():");
    String text2 = "This string has the lenght of: ";
    int length = text2.length();

    System.out.println(text2 + length + "\n");
  
//*======================================================================================================================
// STRING METHODS
//*======================================================================================================================

    // charAt(): Tells us the character in a specific position
    System.out.println("String methods:");  
    String text3 = "In the position 1 we have the letter: ";
    System.out.println("charAt(): " + text3.charAt(1));
    
    // substring(): Returns a part of the string
    String text4 = "Aixo és un text molt llarg.";
    String text5 = text4.substring(11,20);
    System.out.println("substring(): " + text5);

    // equals(): Returns true if the strings are the same, case sensitive
    String text6 = "Aixo és un text molt llarg.";
    System.out.println( "equals(): "  + text4.equals(text6));

    // equalsIgnoreCase()
    String text7 = "AIXO ÉS UN TEXT MOLT LLARG.";
    System.out.println( "equalsIgnoreCase(): "  + text4.equalsIgnoreCase(text7));

    // concat(): "Adds" two strings
    String text8 = "First sentence. ";
    String text9 = "Second sentence";
    System.out.println( "concat(): "  + text8.concat(text9));

    // contains(): Returns true if the string contains the substring (boolean)
    String text10 = "A sentence with a sentence inside, to check if 12345 is inside the string.";
    String text11 = "12345";
    System.out.println( "contains(): "  + text10.contains(text11));

    //replace(): Replaces one character for another
    String password = "America5682";
    System.out.println("replace(): " + password.replace('a','e').replace('A', 'E').replace('5','0'));

    //repeat()
    String text12 = "*";
    String result = text12.repeat(12);
    System.out.println("repeat(): " + result);

//*======================================================================================================================
// ARRAYS
//*======================================================================================================================

    //toCharArray()
    char[] chars = text6.toCharArray(); 
    System.out.print("toCharArray(): " + chars + " this is the hexadecimal value of the memory address of the array -->\n");

    // A loop to print the chars in the array separated by a space
    for (int i = 0; i < chars.length; i++) {
        System.out.print(chars[i] + ",");
    }

    // Create a String from the array
    String charsString = new String(chars);
    System.out.println("\n" + charsString);

//*======================================================================================================================
// STRING METHODS PART 2
//*======================================================================================================================

    // toLowerCase()
    String text13 = "THIS IS LOWERCASE";
    System.out.println("toLowerCase(): " + text13.toLowerCase());

    // toUpperCase()
    String text14 = "this is uppercase";
    System.out.println("toUpperCase(): " + text14.toUpperCase());
     
    // compareTo(), case sensitive
    String text15 = "This is a string";
    String text16 = "This is a string with more chars";
    
    if (text15.compareTo(text16) > 0) {
        System.out.println("compareTo(): " + text15.compareTo(text16)+ " - The first string has more characters than the second");
    } 
    else if (text15.compareTo(text16) < 0) {
        System.out.println("compareTo(): " + text15.compareTo(text16) + " - The first string has less characters than the second");
    }
    else {
        System.out.println("compareTo(): " + text15.compareTo(text16) + " - The strings are equal");
    }

    // compareToIgnoreCase()
    String text21 = "This is a string";
    String text22 = "this is a STRING";

    if (text21.compareToIgnoreCase(text22) > 0) {
        System.out.println("compareToIgnoreCase(): " + text21.compareToIgnoreCase(text22)+ " - The first string has more characters than the second");
    }
    else if (text21.compareToIgnoreCase(text22) < 0) {
        System.out.println("compareToIgnoreCase(): " + text21.compareToIgnoreCase(text22) + " - The first string has less characters than the second");
    }
    else {
        System.out.println("compareToIgnoreCase(): " + text21.compareToIgnoreCase(text22) + " - The strings are equal");
    }

    // trim()
    String text17 = " abcde ";
    System.out.println("trim():" + text17.trim());

    // indexOf(): returns the index of the first occurrence of the specified string
    String text18 = "this is a string";
    String text19 = "is";
    System.out.println("indexOf(): " + text18.indexOf(text19));

    // String.valueOf(): returns a string representation of the specified value
    int n1 = 100;
    String text20 = String.valueOf(n1);
    System.out.println("valueOf(): " + text20);  
    }
}
// END