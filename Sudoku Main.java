
/** Chiana G & Kenan I **/

/** Sudoku solver. */
public class Sudoku {

    /** Prints the solution to the puzzle in the specified directory. */
    public static void main(String[] args) {

        String puzzle = new In("sudoku1.txt").readAll();
        Square[][] grid = createSquares(puzzle);
        solve(grid);
        StdOut.println(toString(grid));
    }

    /** Returns a new Location object with the specified row and column.
     *  Takes the value of r to row and of c to column. */
    static Location createLocation(int r, int c) {

        Location l = new Location(); // object l of Location
        l.row = r;
        l.column = c;
        return l;
    }

    /** Returns an array of the eight Locations in the same row as here.
     *  */
    static Location[] findRow(Location here) {

        Location[] array = new Location[8];
        for (int i = 0; i < 9; i++) {
            if (i < here.column) array[i] = createLocation(here.row, i);
            if (i > here.column) array[i-1] = createLocation(here.row, i);
        }
        return array;
    }

    /** Returns an array of the eight Locations in the same column as here. */
    static Location[] findColumn(Location here) {

        Location[] array = new Location[8];
        for (int i = 0; i < 9; i++) {
            if (i < here.row) array[i] = createLocation(i, here.column);
            if (i > here.row) array[i-1] = createLocation(i, here.column);
        }
        return array;
    }

    /** Returns an array of the eight Locations in the same 3x3 block as here. */
    static Location[] findBlock(Location here) {

        Location[] array = new Location[8];
        int i = 0;
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                if ((x != here.row % 3) || (y != here.column % 3)) { // % 3 translates grid coordinates to block coordinates
                    array[i] = createLocation((here.row - here.row % 3) + x, (here.column - here.column % 3) + y);
                    i += 1;
                }
            }
        }
        return array;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block.
     */
    static Square[][] createSquares() {

        Square[][] grid = new Square[9][9]; // create grid of squares
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) { // create the Square, and embedded values for value, row, column, and block
                grid[row][col] = new Square();
                grid[row][col].value = 0;
                grid[row][col].row = new Square[8];
                grid[row][col].column = new Square[8];
                grid[row][col].block = new Square[8];
            }
        }
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                for (int i = 0; i < 8; ++i) { // fill in row, column, and block arrays
                    grid[row][col].row[i] = grid[findRow(createLocation(row, col))[i].row][findRow(createLocation(row, col))[i].column];
                    grid[row][col].column[i] = grid[findColumn(createLocation(row, col))[i].row][findColumn(createLocation(row, col))[i].column];
                    grid[row][col].block[i] = grid[findBlock(createLocation(row, col))[i].row][findBlock(createLocation(row, col))[i].column];
                }
            }
        }
        return grid;
    }

    /** Returns a String representing grid, showing the numbers (or . for squares with value 0). */
    static String toString(Square[][] grid) {

        String diagram = "";
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                if (grid[row][col].value == 0)
                    diagram += ".";
                else
                    diagram += grid[row][col].value;
            }
            diagram += "\n";
        }
        return diagram;

    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block. Any numbers in diagram are filled in to the grid; empty squares should be given as
     * periods.
     */
    static Square[][] createSquares(String diagram) {

        Square[][] grid = createSquares();

        int i = 0;
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {

                if (diagram.charAt(i) == '\n') ++i;
                char c = diagram.charAt(i++);
                if (c == '.') grid[row][col].value = 0; // replace .'s with 0's
                else          grid[row][col].value = c - '0'; // translate to the corresponding number
            }
        }
        return grid;

    }

    /**
     * Returns a boolean array of length 10. For each digit, the corresponding entry in the array is true
     * if that number does not appear elsewhere in s's row, column, or block.
     */
    static boolean[] findValidNumbers(Square s) {

        boolean[] validNumsArray = new boolean[10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                if (s.row[j].value == i
                        || s.column[j].value == i
                        || s.block[j].value == i) { // if the value appears anywhere in row, col, or blk
                    validNumsArray[i] = false;
                    break;
                }
                validNumsArray[i] = true;
            }
        }

        return validNumsArray;
    }

    /**
     * Returns true if grid can be solved. If so, grid is modified to fill in that solution.
     */
    static boolean solve(Square[][] grid) {

        // iterates through grid: row, col
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {

                if (grid[i][j].value == 0) { // if spaces needs to be filled

                    boolean[] validNumsArray = findValidNumbers(grid[i][j]);
                    for (int k = 1; k < 10; k++) { // for correlating values of 1-9 in validNumsArray
                        if (validNumsArray[k]) { // if the value is valid
                            // edit grid
                            grid[i][j].value = k; // should correlate to validNumbersArray
                            if (solve(grid)) return true; // ultimate base case
                        }
                    }
                    grid[i][j].value = 0;
                    return false;
                }
            }
        }
        return true;
    }

}
