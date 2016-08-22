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

import java.util.Arrays;

public class Answer {
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int square : row) {
                System.out.print(square + " ");
            }
            System.out.println();
        }
    }


    /*
        Theory: http://math.stackexchange.com/questions/441571/lights-out-variant-flipping-the-whole-row-and-column
     */
    private static boolean isSolvable(int[][] matrix) {
        // If n is even, there is always a solution given any starting configuration
        if ((matrix.length & 1) == 0) {
            return true;
        }

        // If n is odd, there is a solution iff the 'on' lights parities for each row and column are the same
        int requiredParity = 0;
        for (int column = 0; column < matrix.length; ++column) {
            requiredParity += matrix[0][column];
        }
        requiredParity &= 1;

        // Check parity for each row
        for (int row = 0; row < matrix[0].length; ++row) {
            int parity = 0;
            for (int column = 0; column < matrix.length; ++column) {
                parity += matrix[row][column];
            }
            parity &= 1;
            if (requiredParity != parity) {
                return false;
            }
        }

        // Check parity for each column
        for (int column = 0; column < matrix[0].length; ++column) {
            int parity = 0;
            for (int row = 0; row < matrix.length; ++row) {
                parity += matrix[row][column];
            }
            parity &= 1;
            if (requiredParity != parity) {
                return false;
            }
        }

        return true;
    }

    private static int[][] getRowColumnSums(int[][] matrix) {
        int[][] rowColSums = new int[2][];

        int[] rowSum = new int[matrix.length];
        int[] columnSum = new int[matrix.length];

        for (int row = 0; row < rowSum.length; ++row) {
            for (int column = 0; column < columnSum.length; ++column) {
                rowSum[row] += matrix[row][column];
                columnSum[column] += matrix[row][column];
            }
        }

        rowColSums[0] = rowSum;
        rowColSums[1] = columnSum;

        return rowColSums;
    }

    private static int findParitySum(int[][] matrix, int[] rowSum, int[] columnSum) {
        int paritySum = 0;
        for (int row = 0; row < rowSum.length; ++row) {
            for (int column = 0; column < columnSum.length; ++column) {
                paritySum += (rowSum[row] + columnSum[column] - matrix[row][column]) & 1;
            }
        }
        return paritySum;
    }

    // Returns the minimum number of flips to turn of all lights
    public static int answer(int[][] matrix) {
        // If not solvable, return -1
        if (!isSolvable(matrix)) {
            return -1;
        }

        int matrixSize = matrix.length;

        // Find sums and parity
        int[][] rowColumnSum = getRowColumnSums(matrix);
        int[] rowSum = rowColumnSum[0];
        int[] columnSum = rowColumnSum[1];

        // If even matrix
        if ((matrixSize & 1) == 0) {
            return findParitySum(matrix, rowSum, columnSum);
        }

        // Else if odd matrix
        // Add a new column to make it an even matrix
        for (int row = 0; row < matrixSize; ++row) {
            matrix[row] = Arrays.copyOf(matrix[row], matrixSize + 1);
        }

        // Add a new row to make it completely even
        matrix = Arrays.copyOf(matrix, matrixSize + 1);
        matrix[matrixSize] = new int[matrixSize+1];
        columnSum = Arrays.copyOf(columnSum, columnSum.length + 1);
        rowSum = Arrays.copyOf(rowSum, rowSum.length + 1);

        int result = Integer.MAX_VALUE;
        for (int permutation = 0; permutation < Math.pow(2, matrixSize); ++permutation) {
            int[] combination = new int[matrixSize + 1];
            int combinationSum = 0;

            // Generate a combination using current permutation cycle
            for (int row = 0; row < matrixSize; ++row) {
//                combination[row] = (permutation >> row) & 1;
                combination[row] = (permutation >> (matrixSize - 1 - row)) & 1;
                combinationSum += combination[row];
            }


            // If parity does not match, then it does not lead to the solution
            if ((combinationSum & 1) != (rowSum[0] & 1)) {
                continue;
            }

            // Amend column sums with the new matrix column's values
            for (int column = 0; column < matrixSize; ++column) {
                columnSum[column] += combination[column];
            }

            // Values of column padding
            for (int row = 0; row < matrixSize; ++row) {
                int F = 0;
                for (int column = 0; column < matrixSize; ++column) {
                    F += (rowSum[row] + columnSum[column] - matrix[row][column]) & 1;
                }

                if (F > matrixSize >> 1) {
                    matrix[row][matrixSize] = 1;
                    ++rowSum[row];
                    ++columnSum[matrixSize];
                } else {
                    matrix[row][matrixSize] = 0;
                }
            }

            // Add the row candidate
            matrix[matrixSize] = Arrays.copyOf(combination, matrixSize+1);

            rowSum[matrixSize] = 0;
            for (int column = 0; column < matrixSize; ++column) {
                rowSum[matrixSize] += combination[column];
            }

            int candidate = findParitySum(matrix, rowSum, columnSum);

            if (candidate < result) {
                result = candidate;
            }

            // Return column sums back for next permutation
            for (int index = 0; index < matrixSize; ++index) {
                rowSum[index] -= matrix[index][matrixSize];
                columnSum[index] -= matrix[matrixSize][index];
            }

            // Reset last row of matrix
            for (int column = 0; column < matrix.length; ++column) {
                matrix[matrixSize][column] = 0;
            }

            // Sums reset to zero
            rowSum[matrixSize] = 0;
            columnSum[matrixSize] = 0;
        }

        return result;
    }

//    private static int[][] toggleSwitch(int[][] matrix, int row, int column) {
//        for (int otherColumn = 0; otherColumn < matrix.length; ++otherColumn) {
//            matrix[row][otherColumn] ^= 1;
//        }
//
//        for (int otherRow = 0; otherRow < matrix[0].length; ++otherRow) {
//            matrix[otherRow][column] ^= 1;
//        }
//
//        matrix[row][column] ^= 1;
//
//        return matrix;
//    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1}
        };

//        printMatrix(matrix);
//        System.out.println("Solving: ");
//        System.out.println("isSolvable(matrix) = " + isSolvable(matrix));
        System.out.println("-1 ?= answer(matrix) = " + answer(matrix));
    }
}