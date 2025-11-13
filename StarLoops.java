/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.starloops;

/**
 *
 * @author MLabayen2026
 */
/*
Create modified versions of the Stars program to print the fol-
lowing patterns. Hint: Parts b, c, and d require several loops, some of which
print a specific number of spaces.
*/

public class StarLoops {
    
public static void main(String[] args){
final int MAX_ROWS = 10;
System.out.println("a.");
//for each row
for (int row = MAX_ROWS; row >= 1; row--)
{
//print out the appropriate number of stars for that row
for (int star = 1; star <= row; star++){
System.out.print("*");
}
//newline for a new row
System.out.println("");
}



System.out.println("\nb.");
for (int row = MAX_ROWS; row >= 1; row--) {
//print out the appropriate number of spaces for that row
for (int space = 2; space <= row; space++){
System.out.print(" ");
}
for (int star = MAX_ROWS; star >=row; star--){
    System.out.print("*");
}
//newline for a new row
System.out.println("");
}

System.out.println("\nc.");

for (int row = MAX_ROWS; row>=1; row--){    

for (int space = 9; space >= row; space--){
    System.out.print(" ");
}
for (int star = 1; star<= row; star++){
    System.out.print("*");
}
System.out.println();

}

System.out.println("\nd.");

for (int row = 1; row<=MAX_ROWS/2; row++){
    
    for (int space = 1; space <=5 - row; space++){
    System.out.print(" ");
    
}
    
    for (int star = 1; star<= 1 +(2 * (row-1)); star++){
        System.out.print("*");
    }
    for (int space = 1; space <= 5 - row; space++) {
        System.out.print(" ");
    }
    System.out.println();
}

for (int row = 1; row <= MAX_ROWS/2; row++)
{ for (int space = 1; space <= row - 1; space++){
                System.out.print(" ");  
            }
            
            //print out the appropriate number of stars for that row
            for (int star = 1; star <= 9 - (2 * (row - 1)); star++){
                System.out.print("*");
            }
            
            for (int space = 1; space <= row - 1; space++){
                System.out.print(" ");  
            }    
            
            //newline for a new row
            System.out.println();






}






}
}