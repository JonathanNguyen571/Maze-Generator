/*
    Name: Jonathan Nguyen
    Date: 12/2/2021
    About: Maze generation and solving program that simulates a maze given dimensions and animates and constructs a path using recursion.
           Main method that calls all other programs.
 */

package MazeGenerator.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean[][] marked, up, right, down, left;
        Scanner scanner = new Scanner(System.in);

        //Gets the dimensions the user inputs
        System.out.print("Input the total rows(n) = ");
        int n = scanner.nextInt();

        System.out.print("Input the total columns(m) = ");
        int m = scanner.nextInt();

        //Marked is a 2d array that tracks every position the maze path has been to
        marked = new boolean[n+2][m+2];

        //Creates first marked 2d array
        for(int row = 0; row < n + 2; row++) {
            marked[row][0] = true;
            marked[row][m+1] = true;
        }

        for(int col = 0; col < m + 2; col++) {
            marked[0][col] = true;
            marked[n+1][col] = true;
        }

        //Creates 2d array for the walls going up, down, left, and right
        up = new boolean[n+2][m+2];
        right = new boolean[n+2][m+2];
        down = new boolean[n+2][m+2];
        left = new boolean[n+2][m+2];

        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < m+2; y++) {
                up[x][y] = true;
                right[x][y]  = true;
                down[x][y] = true;
                left[x][y]  = true;
            }
        }

        //Calls both MazeBuilder and MazeSolver
        MazeBuilder build = new MazeBuilder(n, m, marked, up, right, down, left);
        MazeSolver solver = new MazeSolver(n, m, marked, up, right, down, left);

        //Enables double buffer for graphics
        StdDraw.enableDoubleBuffering();
        build.draw();

        System.out.print("Type 'start' to begin the solution path: ");
        String start = scanner.next();

        if(start.equalsIgnoreCase("start") == true)
            solver.solve();

        System.out.println("Program complete");
        System.out.print("Type end to close the program: ");
        String end = scanner.next();

        if(end.equalsIgnoreCase("end") == true)
            System.exit(1);
    }
}
