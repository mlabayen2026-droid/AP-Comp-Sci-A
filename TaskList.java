/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tasklist;

import java.util.*;
/**
 *
 * @author 760ma
 */

/*
In this project you will set up a to-do list of tasks with different
complexities and priorities, and compare these.

Set up an interface called Priority, with methods setPriority and getPriority.
This intrrface should allow defining a way to establish numeric priority between
different object instances.

Design a Class called Task and implement it (instantiate objects) in the main
method, that instantiates one or more of these to-do tasks. Task should
implement Priority.

Have Task also implement the Complexity interface shown earlier (in MiniQuiz).
Also have task implement the Comparable Interface (it is in the standard Class
library, no need to import anything). 

the driver class (containing the main method) should rank the tasks in order of
piority, then complexity.
*/
public class TaskList {

    
    public static void main(String[] args) {
        // start by making the arrylist
          ArrayList<Task> tasks = new ArrayList<>();

        //make the tasks for the list and give them priority and also complexity values 
        tasks.add(new Task("Lift", 2, 1));
        tasks.add(new Task("Make dinner", 1, 3));
        tasks.add(new Task("Read book", 2, 2));
        tasks.add(new Task("Watch movie", 3, 1));

        System.out.println("List of Tasks\n");
        System.out.println("Tasks:\n");
        //show the list of tasks that are presented in the code 
        for (Task t : tasks) {
            System.out.println(t);
        }
        //Task must be organized by priority and complexity
        Collections.sort(tasks);

        System.out.println("\nTasks organized by the priotrity, and the complexity:");
        //print the organized list
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
}


//Complexity interface
///This allows defining a way to establish numeric complexity between different object instances 
interface Complexity {
    public void setComplexity(int complexity);
    public int getComplexity();
}
//Set and get the priority
//give numeric value to the priorities
interface Priority {
    public void setPriority(int p);
    public int getPriority();
}
//Task class puts in the the stats of the tasks 
class Task implements Priority, Complexity, Comparable <Task>{

    //task name
    private final String name;
    //#1 is the highest possible priority value 
    private int priority;
    //#1 means super easy tasks
    private int complexity;

    //Constructor gets and sets all the values for the task to be organized by 
    public Task(String name, int priority, int complexity) {
        this.name = name;
        this.priority = priority;
        this.complexity = complexity;
    }
    // Complexity methods
    @Override
    public void setComplexity(int c) {
        complexity = c;
    }
    @Override
    public int getComplexity() {
        return complexity;
    }
        // Priority methods
    @Override
    public void setPriority(int p) {
        priority = p;
    }
    @Override
    public int getPriority() {
        return priority;
    }
    @Override
    //compare the tasks stats/values
    public int compareTo(Task other) {
        //compare priority
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }
        // shoudl the priority be the same look to complexity
        return this.complexity - other.complexity;
    }
    
    @Override
    public String toString() {
        return "- " + name + " \n    (Priority: " + priority + ", Complexity: " + complexity + ")";
    }
}