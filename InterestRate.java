/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interestrate;
import java.util.*;
/**
 *
 * @author 760ma
 * Write a public main method that uses a different class called 
 * InterestRateCalculator

The main should call for a user to input a financial amount (say $1,000,000)

The main should then call a method in the Class that adds an interest rate 
* amount to the amount input and passes it back to the main.

Then ask for the number of months for repayment.

The Class should have a public constant called RATE, which has a rate value 
* (3.5% or 10% or whatever you choose). All other variables should be private.

The Class should have a public method which takes the amount as input. 
* The Class will then calculate the compound interest payments. 

The Class should divide the amount by the number of months.

The first month, the borrower will have to pay the monthly amout plus interest. 

The remaining amount should also be increased by the interest rate.

Next payment should divide the remaining amount by the number of months left, 
* and present that monthly amount plus the interest.

Output should be the month, that month's payment, as well as the total amount 
* paid so far.

Remember to show the amounts in currency format, interest in percentage format 
* and so on.

Ensure all Classes, Variables, methods etc. have only the correct required 
* visibility.

Only the variables/methods that should be accessed from outside should be 
* available from outside - everything else private to the Class.

The InterestRateCalculator should be a "black box". Remember to plan your 
* project, and comment your code throughout.
 */
public class InterestRate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the financial amount?");
        double amt = scanner.nextDouble();
        System.out.println("How many months?");
        int months = scanner.nextInt();
 
        InterestRateCalculator calc = new InterestRateCalculator();
        calc.calculatePayments(amt, months);
                
    }
}

class InterestRateCalculator {
    public static final double RATE = 0.10;
    /* public means its public
    
    Static means it can be referenced anywhere in the class and is not
    attached to any specific object
    
    Final means it cannot be changed
    
    It is in all CAPS becasue that is the naming convention of a constant 
    like this
    */
    
    public void calculatePayments(double amt, int months){ //create the method
   System.out.println("The current intrest rate is: " + RATE*100 + "%" +
            "\nThe financial amount is: $" + amt + 
            "\nThe amount of months is: " + months );
   double totalPaid = 0.0; //keep track of how much has been paid
   
   for (int month = 1; month <= months; month++) { /*as long as the condition of
month (it goes up every loop because of month++) stays less than months 
(expressed in month <= months) then the loop will continue until untrue*/
       
       double monthlyInterest = amt * RATE; //calculate the intrest
       double monthlyPayment = (amt / months) + monthlyInterest;
       totalPaid += monthlyPayment; //the += means add to the existing value
       amt = (amt - (amt / months)) * (1 + RATE); /*
       amt - (amt / months) gets you the amt remaining
       * (1 + RATE) adds the intrest remain
      */
       
       //print months info
       System.out.printf("Month %d: Payment = $%.2f | Total Paid = $%.2f%n", month, monthlyPayment, totalPaid); //formatted print
       /*  %d just means an integer
       %.2 means  a floating point nubmer with 2 decimal places
$ just prints the dollar sign
%n prints new line (its preffered to \n in formated print) */
   }
    
    
}
}