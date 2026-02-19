/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classinheriance;

/**
 *
 * @author MLabayen2026
 */
/*
I want you to use the code I have supplied for Vehicle and Car, and expand on
this. Look up vehicle specs on the web.

I want each person to create 2 Class files that inherit from Car, as well as
2 Boat Classes, 2 Airplane classes, and 2 other vehicle Classes (impress me!).
I basicallty want to get you used to using inheritance.

You can override the toString to print out speial characteristics, use the
Speedometer methods as needed (should all speeds be in mph?). I may give extra
credit if I am impressed by someone's imaginitive coding. Please make sure to
comment everything well.
*/
public class ClassInheriance {

    public static void main(String[] args) {
    
        Vehicle[] vehicles = {
            new FordMustang(),
            new MysteryMachine(),
            new Boeing767(),
            new RedBaron(),
            new Titanic(),
            new BlackPearl(),
            new LittleTikes(),
            new ElectricSkateboard(),
        };
               
          for (Vehicle v : vehicles) {
              System.out.println(v);
              System.out.println();
          }     
    }
    
}

// The Base Vehicle Class, implements the Speedometer interface
class Vehicle implements Speedometer{
//base (Class wide) variables
protected String brandName = "";
protected double speed = 0.0;
protected int passengers = 0;
protected double cargoWeight = 0.0;
//Base default constructor
public Vehicle(){
brandName = "";
speed = 0.0;
passengers = 0;
cargoWeight = 0.0;
}
public Vehicle(String inBrand, double inSpeed, int inPassengers, double
inCargo){
brandName = inBrand;
speed = inSpeed;
passengers = inPassengers;
cargoWeight = inCargo;
}
//getters and setters
public Vehicle(String inBrand){
brandName = inBrand;
}
public String getBrand(){
return brandName;
}
public void setBrandName(String inBrand){
brandName = inBrand;
}
public double getSpeed(){
return speed;
}
public void setSpeed(double inSpeed){
speed = inSpeed;
}
public int getPassengers(){
return passengers;
}
public void setPassengers(int inPassengers){
speed = inPassengers;
}
public double getCargoWeight(){
return cargoWeight;
}
public void setCargoWeight(double inCargoWeight){
cargoWeight = inCargoWeight;
}
//Base toString
public String toString(){
String result = "";
result = "Brand: " + getBrand() + "\n" +
"Speed (mph): " + getSpeed() + "\n" +
"Passengers: " + getPassengers() + "\n" +
"Cargo (lbs): " + getCargoWeight() + "\n";
return result;
}
}
//the Speedometer interface to show the speed of any vehicle in the same way
interface Speedometer{
public void setSpeed(double inSpeed);
public double getSpeed();
}






// Car Class inherits from Vehicle Class
 class Car extends Vehicle{
    int wheels = 4;
    String color = "White";
    boolean spoiler = false;
    boolean stereo = false;
    double mpg = 0.0; //has an extra variable, mpg

    public Car(String inBrand, double inSpeed, int inPassengers, double inCargo,
            double inMPG){
        super(inBrand, inSpeed, inPassengers, inCargo); //uses the super constructor
        mpg = inMPG; //also include the extra variable in the Car constructor
    }
    //another additional variable
    public void setSpoiler(boolean inSpoiler){
        spoiler = inSpoiler;
    }

    public boolean getSpoiler(){
        return spoiler;
    }
    //another additional variable
    public void setStereo(boolean inStereo){
        stereo = inStereo;
    }

    public boolean getStereo(){
        return  stereo;
    }
    //overrides the super getSpeed method
    @Override
    public double getSpeed() {
        if (spoiler)
            return super.getSpeed() + 20;
        else
            return super.getSpeed();
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public double getMPG(){
        if(stereo)
            return mpg - (mpg / 10);
        else
            return mpg;
    }
    //usesd the super toString, as well as addingnthe new variable to it.
    public String toString(){
        String result = super.toString() +
                "MPG: " + this.getMPG();
        return  result ;
    }
}

class FordMustang extends Car {
    
    private double engineSize = 5.0;
    
    public FordMustang() {
        super("Ford Mustang", 160., 4, 200, 21);
        setSpoiler(true);
        setStereo(true);
    }   
    
    @Override
    public String toString() {
        return "Ford Mustang \n" + super.toString() + "\nEngine: " + engineSize +" Liter V8";
    }
    
}

class MysteryMachine extends Car{
    
    private int mystery = 2000;
    
    public MysteryMachine() {
        super("Mystery Machine", 67, 6, 500, 10);
        setStereo(true);
    }
    
    @Override
    public String toString() {
        return "Mystery Machine \n" + super.toString()+ "\nMystery: " + mystery; 
    }
    
}

class Titanic extends Vehicle{
    
    private int casualties= 1500;
    
    public Titanic(){
        super("The Titanic", 28, 2224, 1800000 );
    }
    
    @Override
    public String toString(){
        return "Titanic \n" + super.toString() + "Casualties: " + casualties;
    }
}

class BlackPearl extends Vehicle{
    
    private boolean fastestShip = true;
    
    public BlackPearl(){
        super("The Black Pearl", 50, 100, 10000);
    }
    
    @Override
    public String toString() {
        return "The Black pearl \n" + super.toString() + "The fastest ship in the Carribean: " + fastestShip;
    }
}

class Boeing767 extends Vehicle{
    
    private int Wings = 2;
    
    public Boeing767() {
        super("Boeing 767", 530, 200, 116200 );
    }
    
    @Override
    public String toString() {
        return "Boeing 767 \n" + super.toString() + "Wings: " + Wings;
    }
}

class RedBaron extends Vehicle{
    
    private int planesShotDown = 80;
    
    public RedBaron() {
        super("Fokker Dr.I Triplane (The Red Baron) ", 115, 1, 400);
    }
    
    @Override
    public String toString() {
        return "Red Baron \n" + super.toString() + "Planes Shot Down: " + planesShotDown;
    }
}

class LittleTikes extends Vehicle{
    
    private boolean fun = true;
    
    public LittleTikes() {
        super("Little Tikes Cozy Coupe", 3, 1, 50);
    }
    
    @Override
    public String toString() {
        return "Little Tike Cozy Coupe \n" + super.toString() + "Fun: " + fun;
    }
}

class ElectricSkateboard extends Vehicle {
    private int studentsNeededToMake = 2;
    
    public ElectricSkateboard() {
        super("Electic Skateboard", 15, 1, 20);
    }
    
    @Override
    public String toString() {
        return "Electic Skateboard \n" + super.toString() + "Students needed to build it: " + studentsNeededToMake;
        
    }
}