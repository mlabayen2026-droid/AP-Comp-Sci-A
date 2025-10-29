/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.constructorsassignment;

/**
 *
 * @author 760ma
 */

/*
Write a class called Course that represents a course 
offered to students. 
It should contain instance data that 
represents the course title (e.g. Computer Science), 
course code (e,g, AP CS A), pointsTotal, and course Instructor’s name. 
Define one Course constructor to accept and initialize all instance data. 
Define a second constructor which accepts course name only.
Include Accessor (getter) and Mutator (setter) methods for all instance data. 
Include a toString method that returns a multi-line description of the course. 

Create a driver class called CourseDetails whose
main method instantiates and updates several Course objects (at least 3).
At least one course should be created using the full object constructor, and one 
that uses the name only creator (but has the rest of it's values set by the setter and 
retrieved by getter methods).
The main method should then print out each course from the toString.
*/

class Course{
    //create the course data
    private String crsTitle;
    private String crsCode;
    private int pntsTotal;
    private String Instructor;

 //Create the constuctor for all the course data
public Course(String inTitle, String inCode, int inPnts, String inInstructor){
    crsTitle = inTitle;
    crsCode = inCode;
    pntsTotal = inPnts;
    Instructor = inInstructor;
}

//constructor to accpet data

public Course(String inTitle){
    crsTitle = inTitle;
    crsCode = "";
    pntsTotal = 0;
    Instructor = "";
}

//geter for the title
public String getTitle(){
    return crsTitle;
}
public void setTitle(String crsTitle){
    this.crsTitle = crsTitle;
}
//getter for code
public String getCode(){
    return crsCode;
}
public void setCode(String crsCode){
    this.crsCode = crsCode;
}

//get points
public int getPntTotal(){
    return pntsTotal;
}

public void setPntTotal(int pntsTotal){
    this.pntsTotal = pntsTotal;
}

//get the teacher
public String getInstructor(){
    return Instructor;
}

public void setInstructor(String Instructor){
    this.Instructor = Instructor;
}

//toString method that will give the description of the course
@Override
public String toString(){
    return "Course Info: " +
            "\nTitle: " + crsTitle +
            "\nCode: " + crsCode +
            "\nTotal Points: " + pntsTotal +
            "\nInstructor: " + Instructor;
}

}

//The Driver class
public class ConstructorsAssignment {
    public static void main(String[] args) {
        //update the courses
        Course G4 = new Course("AP Calculus BC", "2936", 5, "Mr.Dahms");
        Course G5 = new Course("AP Computer Science A", "3645", 5, "Dr. Nelson");
        
        //use the name only
        Course G6 = new Course("Capstone");
        G6.setCode("1002");
        G6.setPntTotal(5);
        G6.setInstructor("Mr. Knoll");
        
        //Print the courses to the String printer
        System.out.println("\nCourse 1 (Gold 4): ");
        System.out.println(G4.toString());
        
        System.out.println("\nCourse 2 (Gold 5): ");
        System.out.println(G5.toString());
        
        System.out.println("\nCourse 3 (Gold 6): ");
        System.out.println(G6.toString());
    }
}