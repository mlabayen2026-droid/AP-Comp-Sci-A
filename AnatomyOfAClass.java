/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anatomyofaclass;

/**
 *
 * @author MLabayen2026
 */
public class AnatomyOfAClass {

    public static void main(String[] args) {

//Cars!
//MakeModel, HP, TopMPH, Cost
Car Cmax = new Car("Ford C-max", 188, 115, 25000); 
System.out.println(Cmax);
        }
}

class Car {
    private String MakeModel;
    private int hp;
    private double TopMPH;
    private double Cost;
    
    //set up constructor
  public Car(String MakeModel, int hp, double TopMPH, double Cost) {
        this.MakeModel = MakeModel;
        this.hp = hp;
        this.TopMPH = TopMPH;
        this.Cost = Cost;










/*
class Car{
private String carBrand = "";
private int horsePower = 0;

public Car(String inBrand){
carBrand = inBrand;
}

Car my2ndCar = new Car("BruceCar");


public Car(String inBrand, int inHp){
carBrand = inBrand;
}
public void setHp(int hp){
    horsePower = hp;
}
public int getHp(){
    return horsePower;
}
public String toString(){
String myString = "The Car Brand Is: " + carBrand;
System.out.println(my2ndCar);
return myString;

*/
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
        
        
    }
}
