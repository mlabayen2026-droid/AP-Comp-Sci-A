/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recursionproject;
import java.util.Arrays;
import java.util.Scanner;

public class RecursionProject {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the row number (N): ");
        int n = input.nextInt();

        int[] row = getNthLine(n);
        
        System.out.println("Line " + n + " of Pascal's Triangle is:");
        System.out.println(Arrays.toString(row));
    }

    /**
     * Recursive method to determine the Nth line of Pascal's Triangle
     */
    public static int[] getNthLine(int n) {
        // Base Case: The first row is always just [1]
        if (n <= 1) {
            return new int[]{1};
        }

        // Recursive Call: Get the previous line (N-1)
        int[] prevLine = getNthLine(n - 1);

        // Create the current line (which has N elements)
        int[] currentLine = new int[n];

        // The first and last elements of any row are always 1
        currentLine[0] = 1;
        currentLine[n - 1] = 1;

        // Calculate interior values based on the previous line
        // Each value is the sum of the two values above it
        for (int i = 1; i < n - 1; i++) {
            currentLine[i] = prevLine[i - 1] + prevLine[i];
        }

        return currentLine;
    }
}