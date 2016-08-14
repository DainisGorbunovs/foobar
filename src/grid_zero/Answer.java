/*
    Grid Zero
    =========

    You are almost there. The only thing between you and foiling Professor Boolean's plans for good is a square grid
    of lights, only some of which seem to be lit up. The grid seems to be a lock of some kind. That's interesting.
    Touching a light toggles the light, as well as all of the other lights in the same row and column as that light.

    Wait! The minions are coming - better hide.

    Yes! By observing the minions, you see that the light grid is, indeed, a lock. The key is to turn off all the
    lights by touching some of them. The minions are gone now, but the grid of lights is now lit up differently.
    Better unlock it fast, before you get caught.

    The grid is always a square. You can think of the grid as an NxN matrix of zeroes and ones, where one denotes
    that the light is on, and zero means that the light is off.

    For example, if the matrix was

    1 1
    0 0

    Touching the bottom left light results in

    0 1
    1 1

    Now touching the bottom right light results in

    0 0
    0 0

    ...which unlocks the door.

    Write a function answer(matrix) which returns the minimum number of lights that need to be touched to unlock
    the lock, by turning off all the lights. If it is not possible to do so, return -1.

    The given matrix will be a list of N lists, each with N elements. Element matrix[i][j] represents the element
    in row i, column j of the matrix. Each element will be either 0 or 1, 0 representing a light that is off,
    and 1 representing a light that is on.

    N will be a positive integer, at least 2 and no more than 15.
 */
package grid_zero;

public class Answer {
//    private static int[][] flipRow(int[][] matrix, int row) {
//        for (int column = 0; column < matrix.length; ++column) {
//            matrix[row][column] ^= 1;
//        }
//
//        return matrix;
//    }
//
//    private static int[][] flipColumn(int[][] matrix, int column) {
//        for (int row = 0; row < matrix[0].length; ++row) {
//            matrix[row][column] ^= 1;
//        }
//
//        return matrix;
//    }

    private static int[][] toggleSwitch(int[][] matrix, int row, int column) {
        for (int otherColumn = 0; otherColumn < matrix.length; ++otherColumn) {
            matrix[row][otherColumn] ^= 1;
        }

        for (int otherRow = 0; otherRow < matrix[0].length; ++otherRow) {
            matrix[otherRow][column] ^= 1;
        }

        matrix[row][column] ^= 1;

        return matrix;
    }

    private static boolean isSolved(int[][] matrix) {
        for (int[] row : matrix) {
            for (int square : row) {
                if (square == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // Returns the minimum number of flips to turn of all lights
    public static int answer(int[][] matrix) {
        // Can flip 15 columns
        // Can flip 15 rows
        // Total of 15*15=225 combinations

        int minFlips = -1;
//        for (int column = 0; column < matrix.length; ++column) {
//            for (int row = 0; row < matrix[0].length; ++row) {
//
//            }
//        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1},
                {0, 0}
        };

        System.out.println("2 ?= answer(matrix) = " + answer(matrix));

        matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 1}
        };

        System.out.println("-1 ?= answer(matrix) = " + answer(matrix));
    }
}

/*
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 1}

                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
 */