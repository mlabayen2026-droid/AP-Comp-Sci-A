/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmain;

/**
 *
 * @author MLabayen2026
 */

/*
I supply a very basic “skeleton code” for a Student Class
Often in jobs in IT you will be given the “high level” Class to work on
You need to make it work properly - add features and so on
I want you to alter this base Class as follows:
Each student object should have 3 courses
Each course will have a test
Provide constructors (overloaded) for student/course objects
Each test score will be initialized to zero
Provide methods to  setTestScore and getTestScore for each course
Provide a method called getAverage that computes this
Alter toString to provide the student details
Modify the driver Class to allow adding students and getting their scores
Provide a planning doc to show your logic in how you design the 
classes/relationships
Provide a simple UML Class Diagram to show the relationships, 
and where the public/private methods are
*/


// Class that does the main things
public class StudentMain {
    public static void main(String[] args) {
        // first student with course and score
        Student st1 = new Student("Alice", "Brown", "Math", "History", "Physics");
        st1.setTestScore(0, 95); // statrt with zero becaseu that is how lists work, Math
        st1.setTestScore(1, 88); // History
        st1.setTestScore(2, 91); // Physics

        // Make the second student
        Student st2 = new Student("Charlie", "Davis", "Art", "Biology", "English");
        st2.setTestScore(0, 75); // Art
        st2.setTestScore(1, 82); // Biology
        st2.setTestScore(2, 98); // English

        // Print out the studne and their courses
        System.out.println("--- Student Records ---");
        System.out.println(st1);
        System.out.println("-----------------------");
        System.out.println(st2);
    }
}

// class for the course
class Course {
    private String courseName;
    private int testScore;

    public Course(String name) {
        this.courseName = name;
        this.testScore = 0; // start the scores at zero so we can add it later
    }

    public void setScore(int score) {
        this.testScore = score; //set the score
    }

    public int getScore() {
        return testScore; //get the score
    }

    @Override
    public String toString() {
        return courseName + ": " + testScore + "%"; //pritn out the course with the score formated with percent
    }
}

// Class for the student
class Student {
    private String firstName;
    private String lastName;
    private Course[] courses;

    // Constuctor to name the courses
    public Student(String first, String last, String c1, String c2, String c3) {
        this.firstName = first;
        this.lastName = last;
        this.courses = new Course[3];
        this.courses[0] = new Course(c1);
        this.courses[1] = new Course(c2);
        this.courses[2] = new Course(c3);
    }

    public void setTestScore(int courseIndex, int score) {
        if (courseIndex >= 0 && courseIndex < courses.length) {
            courses[courseIndex].setScore(score);
        }
    }

    public double getAverage() {
        double total = 0;
        for (Course c : courses) {
            total += c.getScore();
        }
        return total / courses.length;
    }

    @Override
    public String toString() {
        String result = "Student: " + firstName + " " + lastName + "\n";
        result += "Courses:\n";
        for (Course c : courses) {
            result += "  - " + c.toString() + "\n";
        }
        result += "Average Score: " + String.format("%.2f", getAverage()) + "%";
        return result;
    }
}