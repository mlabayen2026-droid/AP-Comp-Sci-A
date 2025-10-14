/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anatomyofaclass;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author MLabayen2026
 */
public class AnatomyOfAClass {

      /*Write a class called Car that has 1 string variable called carBrand
An int variable called enginePower (in Horse Power)
A double variable called maxSpeed (in MPH)
A double variable called price (in $)

I suggest you start a control main method in the Main class, 
and use this to construct a single car object from the 
Car class as you go along, to help debug.

Write a constructor to produce a base car.

Write methods to set and get the brand, enginePower, as well as 
carSpeed and price

Write a toString method to print out these details.

Write a driver method in main to create car types VW Bug, 
Toyota Prius, BMW i8, Tesla Model 3, 
give them the enginePower and maxSpeed as you wish, 
and print these details out.*/
    
    public static void main(String[] args) {

//Cars!
//MakeModel, HP, TopMPH, Cost
Car Bug = new Car("vwBug", 188, 115, 25000); 
Car Prius = new Car("Toyota Prius", 191, 110, 28000);
Car i8 = new Car("Bmw i8", 450, 155, 48000);
Car Model3 = new Car("Tesla Model 3", 168, 115, 42000);

//sepcific constructor

Car Rivian = new Car("Rivian");
Rivian.setTopMPH(135);
Rivian.setCost(80000);
Rivian.setMakeModel("Rivian");
Rivian.sethp(200);


System.out.println(Bug);
System.out.println(Prius);
System.out.println(i8);
System.out.println(Model3);
System.out.println(Rivian);
        }
}

class Car {
    private String MakeModel;
    private int hp;
    private double TopMPH;
    private double Cost;
    
    //set up constructor
  public Car(String inModel, int inhp, double inMPH, double inCost) {
      MakeModel = inModel;
      hp = inhp;
      TopMPH = inMPH;
      Cost = inCost;
              
  }
  //for rivian
  public Car(String inModel) {
      MakeModel = inModel;
  }

    //The returns
    //BRAND

    public String getMakeModel() {
        return MakeModel;
    }
    public void setMakeModel (String inModel){
        MakeModel = inModel;
    }


    //ENGINE
    public int gethp() {
        return hp;
    }
    public void sethp(int inhp) {
        hp = inhp;
    }

    //SPEED
    public double getTopMPH() {
        return TopMPH;
    }
    public void setTopMPH(double inMPH) {
         TopMPH = inMPH;
    }

    //PRICE
    public double getCost() {
        return Cost;
    }
    public void setCost(double inCost) {
        Cost = inCost;
    }
    
     //toString method to print all car details 
    @Override
    public String toString(){
        return "Make and Model: " + MakeModel + "\nHorsepower: " + hp + " HP" + "\nMax Speed : " + TopMPH + " MPH" + "\nCost: $" + Cost + "\n";
        
    }

}


