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

import java.util.*;

public class Answer {
//    http://codereview.stackexchange.com/questions/91317/google-foobar-challenge-save-beta-rabbit-in-python
    public static int answer(int food, int[][] grid) {
        int N = grid.length;

        List<Integer>[][] ans_grid = new ArrayList[N][N];

        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                ans_grid[row][col] = new ArrayList<Integer>();
                int val = grid[row][col];
                if (row == 0 && col == 0) {
                    ans_grid[0][0].add(grid[0][0]);
                }

                if (row != 0) {
                    for (Integer x : ans_grid[row-1][col]) {
                        if (val + x <= food && !ans_grid[row][col].contains(val + x)) {
                            ans_grid[row][col].add(val + x);
                        }
                    }
                }
                if (col != 0) {
                    for (Integer x : ans_grid[row][col-1]) {
                        if (val + x <= food && !ans_grid[row][col].contains(val + x)) {
                            ans_grid[row][col].add(val + x);
                        }
                    }
                }
            }
        }

        List<Integer> all_ans = ans_grid[N-1][N-1];
        Collections.sort(all_ans);
        Collections.reverse(all_ans);

        for (Integer el : all_ans) {
            if (el <= food) {
                return food - el;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[20][20];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = (int)(Math.random()*10);
            }
        }

        int food = grid.length*15;
        System.out.println("answer(food, grid) = " + answer(food, grid));
    }
}
