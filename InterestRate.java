/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interestrate;
import static com.mycompany.interestrate.InterestRateCalculator.RATE;
import java.util.*;
/**
 *
 * @author 760ma
 * Write a public main method that uses a different class called 
 * InterestRateCalculator

Write a public main method that uses a different class called 
* InterestRateCalculator

The main should call for a user to input a financial amount (say $1,000,000)

Then ask for the number of months for repayment.

The Class should have a public constant called RATE, which has a rate value 
* (3.5% or 10% or whatever you choose). All other variables should be private.

The Class should have a public method which takes the amount as input. 
* The Class will then calculate the monthly payments. 

The method should divide the amount by the number of months.

The each month, the borrower will have to pay the monthly amout plus interest. 

Output should be the monthly payment amount, as well as that amount times the 
* number of months (total amount repaid)

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
        Scanner scanner = new Scanner(System.in); //define a new scanner
        System.out.println("What is the financial amount?"); // ask for amount
        double amt = scanner.nextDouble();//scan for the amount
        System.out.println("How many months?");//ask for months
        int months = scanner.nextInt(); //scan for months
 
        InterestRateCalculator calc = new InterestRateCalculator (0,0,0) ; 
//reference the method in the class
        calc.calculatePayments(amt, months);
 
           System.out.println("The current intrest rate is: " + RATE*100 + "%" +
            "\nThe financial amount is: $" + amt + 
            "\nThe amount of months is: " + months + "\n");
        
               //print months info
       System.out.printf("Number of Months %d | Monthly Payment = $%.2f | Total Payment = $%.2f%n"
               ,calc.getNumMonths(), calc.getMonthly(), calc.getTotal()); //formatted print
       /*  %d just means an integer
       %.2 means  a floating point nubmer with 2 decimal places
$ just prints the dollar sign
%n prints new line (its preffered to \n in formated print) */
    }
   
    
}

class InterestRateCalculator {
    public static final double RATE = 0.10;
    private int numMonths;
    private double total;
    private double monthly;
    
    public InterestRateCalculator (int inMonths, double inTotal, double inMonthly) {

        numMonths = inMonths;
        total = inTotal;
        monthly = inMonthly;
    }
    public int getNumMonths() {
        return numMonths;
    }
    
    public void setNumMonths (int numMonths){
        this.numMonths = numMonths;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal (double total){
        this.total = total;
    }
    
    public double getMonthly() {
        return monthly;
    }
    
    public void setMonthly (double monthly) {
        this.monthly = monthly;
    }
       
    /* public means its public
    
    
    Static means it can be referenced anywhere in the class and is not
    attached to any specific object
    
    Final means it cannot be changed
    
    It is in all CAPS becasue that is the naming convention of a constant 
    like this
    */
    
    public void calculatePayments(double amt, int months){ //create the method
        double payment = (amt/months) * (1+RATE);
        double totalPaid = payment * months;
        numMonths = months;
        monthly = payment;
        total = totalPaid;

 
   }
    
    
}
