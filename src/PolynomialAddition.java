
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* **********************************************************
* Programmer:       Erika Tvaskis
* Class:            CS40S
* Assignment 3.2:   Polynomial Addition
* Description:      Reading file and adding polynomials
* *************************************************************
*/

public class PolynomialAddition { //Begin class

static String[] tokens;            //Array for splitting strings
static String delim = "[ ]+";      //Delimiter string for splitting input string   
    
public static void main(String[] args) throws IOException { //Begin main
    
//VARIABLES
BufferedReader fin = null;  //Declaring fin as BufferedReader
String strin1;              
String strin2;
//Strings that read file

//GETTING BANNER AND PRINTING IT
ProgramInfo programInfo = new ProgramInfo(); 
System.out.println(programInfo.getBanner("Polynomial Addition"));

//READING DATA FILE
try {
    fin = new BufferedReader(new FileReader("PolynomialVersionTwo.txt"));
} //End try statement 
catch (FileNotFoundException e) {
    System.out.println("file not found");
} //End catch statement
  
//WHILE LOOP
strin1 = fin.readLine();   
strin2 = fin.readLine();
while (strin1 != null && strin2 != null) {
    Term First = readPolynomial(strin1);
    Term Second = readPolynomial(strin2);
    Term Addition = addPolynomials(First, Second);
    printPolynomials(First, Second, Addition);
    System.out.println("\n");
    strin1 = fin.readLine();
    strin2 = fin.readLine();
} //End while loop

} //End main

/******************************************************
Purpose: To read polynomials
In: str (String)
Out: head (Term)
/******************************************************/ 
private static Term readPolynomial(String str) {
    tokens = str.split(delim);
    Term head = null, previous = null;
    
    for (int i = 0; i < tokens.length; i++) {
        Term current = new Term();
        String term = tokens[i];
        
        if (term.startsWith("+"))
            term = term.substring(1);
        
        current.Coefficient = Integer.parseInt(term.substring(0, term.indexOf(",")));
        current.Exponent = Integer.parseInt(term.substring(term.indexOf(",") + 1));

        if (previous == null) {
            head = current;
            previous = head;
        } //End if
        else {
            previous.Next = current;
            previous = current;
        } //End else
    
    } //End of for loop
    return head;
} //End of readPolynomial method

/******************************************************
Purpose: Adding polynomials
In: first (Term) and second (Term)
Out: head (Term)
/******************************************************/ 
private static Term addPolynomials(Term first, Term second) {
    Term head = null, current = null;
    while (first != null || second != null) {
        boolean pickfirst = false;
        boolean haveBoth = (first != null && second != null);

        Term term;
        if (haveBoth && first.Exponent == second.Exponent) {
            term = new Term(first.Coefficient + second.Coefficient, first.Exponent, null);
            first = first.Next;
            second = second.Next;
        } //End if
        else {
            pickfirst = null != first && ((second == null) || first.Exponent > second.Exponent);
            if (pickfirst) {
                term = new Term(first.Coefficient, first.Exponent, null);
                first = first.Next;
            } //End if
            else {
                term = new Term(second.Coefficient, second.Exponent, null);
                second = second.Next;
            } //End else
        } //End else

        if (current == null) {
            head = term;
            current = head;
        } //End if
        else {
            current.Next = term;
            current = term;
        } //End else
    } //End while loop
    return head;
} //End of addPolynomials method

/******************************************************
Purpose: Printing polynomials in a nice way
In: first (Term), second (Term), addition (term)
Out: None
/******************************************************/ 
private static void printPolynomials(Term first, Term second, Term addition) {
    String line1 = "", line2 = "", barline = "", line3 = "";
    while (addition != null) {
        String 
            part1 = "", 
            part2 = "", 
            part3 = "";
        
//        System.out.println(first.Exponent);
////            part1 = ("0x^" + first.Exponent); 
////            part2 = ("0x^" + second.Exponent);

        if (first != null && first.Exponent == addition.Exponent) {
            part1 = first.toString();
            first = first.Next;
        } //End if
        if (second != null && second.Exponent == addition.Exponent) {
            part2 = second.toString();
            second = second.Next;
        } //End if
       
        part3 = addition.toString();
        addition = addition.Next;
        line1 += part1;
        line2 += part2;
        barline += "-----------";
        line3 += part3;    
    } //End while loop
    System.out.println(line1);
    System.out.println(line2);
    System.out.println(barline);
    System.out.println(line3);
} //End printPolynomials

} //End class
