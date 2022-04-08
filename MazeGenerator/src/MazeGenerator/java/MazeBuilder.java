/*
    Name: Jonathan Nguyen
    Date: 12/2/2021
    About: Randomly generates the maze and draws the walls.
 */

package MazeGenerator.java;

public class MazeBuilder {
    private final int n, m;
    private final boolean[][] marked, up, right, down, left;

    public MazeBuilder(int n, int m, boolean[][] marked,
                       boolean[][] up, boolean[][] right, boolean[][] down, boolean[][] left) {
        this.n = n;
        this.m = m;

        this.marked = marked;
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;

        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, m+2);

        buildMaze(1, 1);
    }

    //Creates the randomly generated maze walls
    public void buildMaze(int row, int col) {
        marked[row][col] = true;

        //Makes sure that the area is free
        while (marked[row][col+1] == false || marked[row+1][col] == false
            || marked[row][col-1] == false || marked[row-1][col] == false) {

            //Randomly selects a number from 0-3
            while (true) {
                double r = StdRandom.uniform(4);
                if (r == 0 && marked[row][col+1] == false) { //Constructs a wall from top and bottom
                    up[row][col] = false;
                    down[row][col+1] = false;
                    buildMaze(row, col + 1);
                    break;

                } else if (r == 1 && marked[row+1][col] == false) { //Constructs a wall from right and left
                    right[row][col] = false;
                    left[row+1][col] = false;
                    buildMaze(row+1, col);
                    break;

                } else if (r == 2 && marked[row][col-1] == false) { //Constructs a wall from bottom and top
                    down[row][col] = false;
                    up[row][col-1] = false;
                    buildMaze(row, col-1);
                    break;

                } else if (r == 3 && marked[row-1][col] == false) { //Constructs a wall from left and right
                    left[row][col] = false;
                    right[row-1][col] = false;
                    buildMaze(row-1, col);
                    break;

                }
            }
        }
    }

    //Draws out the walls of the base program
    public void draw() {
        //Start and end square
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledSquare(n + 0.5, m + 0.5, .50);
        StdDraw.filledSquare(1.5, 1.5, .50);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                //Draws the walls
                if (down[i][j])
                    StdDraw.line(i, j, i+1, j);
                if (up[i][j])
                    StdDraw.line(i, j+1, i+1, j+1);
                if (left[i][j])
                    StdDraw.line(i, j, i, j+1);
                if (right[i][j])
                    StdDraw.line(i+1, j, i+1, j+1);

            }
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }


}

