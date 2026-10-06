package A001expressions;

/**
* @author Uriel Neves Silva (https://gitlab.com/a253119un)
* This is a Java program that answers some logic exercises.
*/
class Relacionals {

//*=====================================================================================================================
// CONSTANTS
//*=====================================================================================================================

    private static final int X = 3;
    private static final int Y = 2;

    public static void main(String[] args) {

//*======================================================================================================================
// MAIN CODE
//*=====================================================================================================================
    
    System.out.println(""); // Line break
    
/* 
1) ----------------------------------------------------------------------------------------------------------------------

QUESTION:
int x = 3;
int y = 2;
int z;
boolean b = false;
boolean c = false;

z = ( x + 4 * y ) % 3;
z = ( 3 + 4 * 2 ) % 3;
z = ( 3 + 8 ) % 3;
z = 11 % 3;
z = 2;

c = ( x > y && !c) || (x==y)
c = (3 > 2 && !false) || (3==2)
c = ( true && true)   || (false)
c = true || false
c = true

ANSWER:
z = 2
c = true
*/
    //CODE FOR QUESTION 1
    int result1 = (X + 4 * Y) % 3;
    @SuppressWarnings("unused")
    boolean result2 = (X > Y && !false) || (X == Y); // OBS: (X == Y) is dead code, is a code that is unreachable.

    System.out.println("Question 1 answers: ");
    System.out.println("First z = " + result1);
    System.out.println("First c = " + result2 + "\n");

/*
2) ----------------------------------------------------------------------------------------------------------------------

QUESTION:
int x = 3;
int y = 2;
int z;
boolean b = true;
boolean c = false;

z = (( y + x / 2) % 2 ) * 5;
z = (( 2 + int(3 / 2)) % 2 ) * 5;
z = (( 2 + 1) % 2 ) * 5;
z = (3 % 2 ) * 5;
z = 1 * 5;
z = 5;

c = b && ( x > 0 || !( y < 5 ));
c = true && ( 3 > 0 || !( 2 < 5 ));
c = true && ( true || !( true ));
c = true && ( true || false );
c = true && (true);
c = true;

ANSWER:
z = 5
c = True
*/

    //CODE FOR QUESTION 2
    int result3 = ((Y + X / 2) % 2) * 5;
    @SuppressWarnings("unused")
    boolean result4 = true && (X > 0 || !(Y < 5)); // OBS: !(Y < 5) is dead code. 

    System.out.println("Question 2 answers: ");
    System.out.println("Second z = " + result3);
    System.out.println("Second c = " + result4 + "\n");

/* 

3) ----------------------------------------------------------------------------------------------------------------------

QUESTION:
Replace "?" with an expression that satisfies the following:
We want to know if a product's price is between €15 and €100.

int price = 20;
System.out.println(?);

ANSWER: 
Math: 15<price<100
Math: 15<price and price<100
System.out.println(15<price && price<100);

OUTPUT = true, because 20 is between 15 and 100
*/

    //CODE FOR QUESTION 3
    int price = 20;
    System.out.println("Question 3 answer: ");
    System.out.println((15<price && price<100) + "\n");
    }
}
// END