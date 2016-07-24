/*
    Save Beta Rabbit
    ================

    Oh no! The mad Professor Boolean has trapped Beta Rabbit in an NxN grid of rooms. In the center of each room
    (except for the top left room) is a hungry zombie. In order to be freed, and to avoid being eaten, Beta Rabbit
    must move through this grid and feed the zombies.

    Beta Rabbit starts at the top left room of the grid. For each room in the grid, there is a door to the room above,
    below, left, and right. There is no door in cases where there is no room in that direction. However, the doors are
    locked in such a way that Beta Rabbit can only ever move to the room below or to the right. Once Beta Rabbit
    enters a room, the zombie immediately starts crawling towards him, and he must feed the zombie until it is full
    to ward it off. Thankfully, Beta Rabbit took a class about zombies and knows how many units of food each zombie
    needs be full.

    To be freed, Beta Rabbit needs to make his way to the bottom right room (which also has a hungry zombie) and have
    used most of the limited food he has. He decides to take the path through the grid such that he ends up with as
    little food as possible at the end.

    Write a function answer(food, grid) that returns the number of units of food Beta Rabbit will have at the end,
    given that he takes a route using up as much food as possible without him being eaten, and ends at the bottom
    right room. If there does not exist a route in which Beta Rabbit will not be eaten, then return -1.

    food is the amount of food Beta Rabbit starts with, and will be a positive integer no larger than 200.

    grid will be a list of N elements. Each element of grid will itself be a list of N integers each, denoting a
    single row of N rooms. The first element of grid will be the list denoting the top row, the second element will be
    the list denoting second row from the top, and so on until the last element, which is the list denoting the bottom
    row. In the list denoting a single row, the first element will be the amount of food the zombie in the left-most
    room in that row needs, the second element will be the amount the zombie in the room to its immediate right needs
    and so on. The top left room will always contain the integer 0, to indicate that there is no zombie there.

    The number of rows N will not exceed 20, and the amount of food each zombie requires will be a positive
    integer not exceeding 10.
 */

package save_beta_rabbit;

public class Answer {
    private static final int right = 0;
    private static final int down  = 1;

    private static int step(int food, int[][] grid, int x, int y) {
        // If outside boundaries, then this route does not exist
        if (x < 0 || y < 0 || x >= grid.length || y >= grid.length || food < 0) {
            System.out.println();
            return -1; // non-existent route
        }

        // After entering current square, grid will take the food a bit
        food = food - grid[x][y];
        if (x == grid.length-1 && y == grid.length-1) {
            System.out.println("Returning one of the answers: " + food);
            return food;
        }

        System.out.println("x, y = " + x +  "," +  y + ", food = " + food + ", zombie needs: " + grid[x][y]);

        // If not at the end and food is lower than required, then foodLeft = -1
        int minFoodNeeded = 2*(grid.length-1) - x - y;
        if (food < minFoodNeeded) {
            return -1;
        }

        // Keep values of food left
        int[] foodSteps = {0, 0};

        // Find how much food is left if choose step #right or #down
        System.out.print("Right > ");
        foodSteps[right] = step(food, grid, x, y+1);

        System.out.print("Down > ");
        foodSteps[down] = step(food, grid, x+1, y);

        if (foodSteps[down] == 0) {
            System.out.println("footsteps down is 0" + ", x, y = " + x +  "," +  y);
            System.out.println("footsteps right is " + foodSteps[right]);
        }
        // Food left is the least posiitve
        int foodLeft = -1;
        if ((foodSteps[down] < foodSteps[right] && foodSteps[down] >= 0)
                || (foodSteps[right] < 0 && foodSteps[down] >= 0)) {
            foodLeft = foodSteps[down];
        } else if ((foodSteps[right] < foodSteps[down] && foodSteps[right] >= 0)
                || (foodSteps[down] < 0 && foodSteps[right] >= 0)) {
            foodLeft = foodSteps[right];
        }

        // Return the amount of food left after making the step (if last step, then the final answer)
        return foodLeft;
    }

    public static int answer(int food, int[][] grid) {
        return step(food, grid, 0, 0);
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0, 2, 5},
                {1, 1, 3},
                {2, 1, 1}
        };

        int food = 7;
        System.out.println("answer(food, grid) = " + answer(food, grid));
    }
}
