
/* **********************************************************
* Programmer:       Erika Tvaskis
* Class:            CS40S
* Assignment 3.2:   Polynomial Addition
* Description:      Term class
* *************************************************************
*/

public class Term 
{ //Begin class
    
int Coefficient;
int Exponent;
Term Next;

//CONSTRUCTORS   

/******************************************************
Purpose: Default constructor
In: None
Out: None
/******************************************************/ 
public Term() {
    Coefficient = 0;
    Exponent = 0;
    Next = null;
} //End default constructor

/******************************************************
Purpose: Initialized constructor
In: coefficient (int), exponent (int), next (Term)
Out: None
/******************************************************/ 
public Term(int coefficient, int exponent, Term next) {
    this.Coefficient = coefficient;
    this.Exponent = exponent;
    this.Next = next;
} //End initialized constructor

/******************************************************
Purpose: toString method
In: None
Out: Formatted string
/******************************************************/ 
public String toString() {
    return String.format("%+4dx^%d    ", new Integer[] { new Integer(Coefficient), new Integer(Exponent) }); 
} //End toString() method

} //End of class



