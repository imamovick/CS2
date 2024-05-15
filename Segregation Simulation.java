// Chiana G & Kenan I

public class SegregationSimulation {

    /** initiates simulation,
     * establishes grid size, scale, values of agents and blank spaces, and t,
     * continuously updates visual by calling drawGrid()
     * and checks allSatisfied()
     *
     * @param args
     */
    public static void main(String[] args) {

        int[][] grid = new int[30][30];
        int cellNum = grid.length * grid[0].length;

        StdDraw.setScale(-0.5, grid.length - 0.5);

        double t = 0.3; //threshold %
        double p = 0.5; //proportion % of the different agents
        double e = 0.1; //% of empty cells

        int blue = (int) (((cellNum) - (e * cellNum)) / (1 / p)); // blue in grid
        int red = (int) (((cellNum) - (e * cellNum)) - blue); // red in grid
        int empty = (int) (e * cellNum); // empty in grid

        initGrid(grid, blue, red, empty, t);

        StdDraw.enableDoubleBuffering(); //faster rendering

        while (true) { //event loop, makes sure it repeats the program until the satisfactory combination is found

            drawGrid(grid, t);
            StdDraw.show();

            if (allSatisfied(grid, t)) {
                break;
            }

        }

    }

    /** initializes grid data by dispersing agents across matrix, grid
     * blue -> 1, red -> -1, empty -> 0
     *
     * @param grid
     * @param blue
     * @param red
     * @param empty
     * @param t
     */
    public static void initGrid(int[][] grid, int blue, int red, int empty, double t) {


        for (int i = 0; i < blue; i++) {

            int xCoord = StdRandom.uniform(grid.length);
            int yCoord = StdRandom.uniform(grid[0].length);

            if (grid[xCoord][yCoord] == 0) { // checks if the space is empty

                grid[xCoord][yCoord] = 1; //1's are blue

            } else {
                i--;
            }
        }

        for (int i = 0; i < red; i++) {

            int xCoord = StdRandom.uniform(grid.length);
            int yCoord = StdRandom.uniform(grid[0].length);

            if (grid[xCoord][yCoord] == 0) { // checks if the space is empty

                grid[xCoord][yCoord] = -1;//-1's are red

            } else {
                i--;
            }
        }

    }

    /** visually draws simulation to screen using data in grid
     *
     * @param grid
     * @param t
     */
    public static void drawGrid(int[][] grid, double t) {


        StdDraw.clear();
        for (int hor = 0; hor < grid[0].length; hor++) {
            for (int ver = 0; ver < grid.length; ver++) {

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(hor, ver, 0.5);

                if (grid[ver][hor] == 1) {
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledSquare(hor, ver, 0.5);
                } else if (grid[ver][hor] == -1) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledSquare(hor, ver, 0.5);
                }

            }
        }

        StdDraw.show();
        StdDraw.pause(1000);

    }

    /** checks if all agents are satisfied
     * all agents satisfied -> return true; stop flow of simulation
     * not all agents satisfied -> return false; reverts to while loop in main()
     *
     * @param grid
     * @param t
     * @return
     */
    public static boolean allSatisfied(int[][] grid, double t) {

        //checks with the countNeighboringFields function if the threshold is satisfied and moves on

        boolean satisfactory = true;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] != 0) {
                    if (!countNeighboringFields(grid, x, y, t)) {
                        moveAgent(grid, x, y);
                        satisfactory = false;

                    }
                }
            }
        }
        return satisfactory;
    }

    /** counts similar neighbors / total neighbors
     * >= 0.3 -> returns true; doesn't prompt moving agent
     * < 0.3 -> returns false; prompts moving agent
     *
     * @param grid
     * @param x
     * @param y
     * @param t
     * @return
     */
    public static boolean countNeighboringFields(int[][] grid, int x, int y, double t) {

        //counts the neighbors of a selected random field and returns a boolean value if it is bigger or lower than the threshold percentage, allowing
        //the all satisfied function to work


        int countRed = 0;
        int countBlue = 0;
        int countAll = 0;
        double perc = 0;

        // iterate through 1 row/col before - 1 row/col after current square
        for (int x1 = x - 1; x1 <= x + 1; ++x1) {
            for (int y1 = y - 1; y1 <= y + 1; ++y1) {

                if (x1 >= 0 && x1 < grid.length &&
                        y1 >= 0 && y1 < grid.length &&
                        (x1 != x || y1 != y)) {
                    if (grid[x1][y1] == 1) ++countBlue;
                    else if (grid[x1][y1] == -1) ++countRed;
                }
                ++countAll;
            }
        }

        if (grid[x][y] == 1) {
            perc = (double) countBlue/countAll;
        }
        else if (grid[x][y] == -1) {
            perc = (double) countRed/countAll;
        }
        return perc >= t;
    }

    /** moves unsatisfied agent to random empty spot on grid
     *
     * @param grid
     * @param x
     * @param y
     */
    public static void moveAgent(int[][] grid, int x, int y) {

        //takes random locations, checks them if they're empty, relocates and so forth

        boolean isEmpty = false;

        while (!isEmpty) {
            int newRow = StdRandom.uniform(grid.length);
            int newCol = StdRandom.uniform(grid[0].length);

            if (grid[newRow][newCol] == 0) {
                grid[newRow][newCol] = grid[x][y];
                grid[x][y] = 0;
                isEmpty = true;

            }
        }
    }

}