/*
    Name: Jonathan Nguyen
    Date: 12/2/2021
    About: Program that solves the maze through recursion and animates a magenta path towards the solution.
 */

package MazeGenerator.java;

public class MazeSolver {
    private boolean finished;
    private final int n, m;
    private final boolean[][] marked, up, right, down, left;

    public MazeSolver(int n, int m, boolean[][] marked,
                      boolean[][] up, boolean[][] right, boolean[][] down, boolean[][] left) {
        this.n = n;
        this.m = m;

        this.marked = marked;
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;

        finished = false;
    }

    //Method to recursively search through a program using depth first search
    public void solve() {

        //Declares marked 2d array as false initially
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= m; y++) {
                marked[x][y] = false;
            }
        }

        //Finished will become true when reaching the end of the program
        finished = false;
        solve(1, 1);
    }

    //Helper method to recursively search
    private void solve(int x, int y) {

        //Base Case
        if (x == 0 || x == n+1 || y == 0 || y == m+1)
            return;
        if (finished || marked[x][y])
            return;
        marked[x][y] = true;

        //Draws path using magenta blocks
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.filledSquare(x + .5, y + .5, .30);
        StdDraw.show();
        StdDraw.pause(50);

        //When the path reaches the end, it declares finished as true
        if (x == n && y == m)
            finished = true;

        //If there is no wall, move in that direction
        if (up[x][y] == false)
            solve(x, y + 1);

        if (right[x][y] == false)
            solve(x + 1, y);

        if (down[x][y] == false)
            solve(x, y - 1);

        if (left[x][y] == false)
            solve(x - 1, y);

        if (finished == true)
            return;

        //Draw the overlapping paths
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(x + .5, y + .5, .35);
        StdDraw.show();
        StdDraw.pause(50);
    }




}
