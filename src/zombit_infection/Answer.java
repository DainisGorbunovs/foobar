/*
    Zombit infection
    ================

    Dr. Boolean continues to perform diabolical studies on your fellow rabbit kin, and not all of it is taking place
    in the lab. Reports say the mad doctor has his eye on infecting a rabbit in a local village with a virus that
    transforms rabbits into zombits (zombie-rabbits)!

    Professor Boolean is confident in the virus's ability to spread, and he will only infect a single rabbit.
    Unfortunately, you and your fellow resistance agents have no idea which rabbit will be targeted. You've been asked
    to predict how the infection would spread if uncontained, so you decide to create a simulation experiment. In this
    simulation, the rabbit that Dr. Boolean will initially infect will be called "Patient Z".

    So far, the lab experts have discovered that all rabbits contain a property they call "Resistance", which is
    capable of fighting against the infection. The virus has a particular "Strength" which Dr. Boolean needs to make
    at least as large as the rabbits' Resistance for it to infect them.

    You will be provided with the following information:
    population = A 2D non-empty array of positive integers of the form population[y][x], that is, row then column.
    (The dimensions of the array are not necessarily equal.) Each cell contains one rabbit, and the value of the cell
    represents that rabbit's Resistance.
    x = The X-Coordinate (column) of "Patient Z" in the population array.
    y = The Y-Coordinate (row) of "Patient Z" in the population array.
    strength = A constant integer value representing the Strength of the virus.

    Here are the rules of the simulation: First, the virus will attempt to infect Patient Z. Patient Z will only be
    infected if the infection's Strength equals or exceeds Patient Z's Resistance. From then on, any infected rabbits
    will attempt to infect any uninfected neighbors (cells that are directly - not diagonally - adjacent in the array).
    They will succeed in infecting any neighbors with a Resistance lower than or equal to the infection's Strength.
    This will continue until no further infections are possible (i.e., every uninfected rabbit adjacent to an infected
    rabbit has a Resistance greater than the infection's Strength.)

    You will write a function answer(population, x, y, strength), which outputs a copy of the input array
    representing the state of the population at the end of the simulation, in which any infected cells value has
    been replaced with -1.
    The Strength and Resistance values will be between 0 and 10000. The population grid will be at least 1x1
    and no larger than 25x25. The x and y values will be valid indices in the population arrays, with numbering
    beginning from 0.
 */

package zombit_infection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Answer {
    protected List<Point> infected;
    protected int[][] population;
    protected int strength;

    protected void infect(int x, int y) {
        // Check that coordinates are within the boundaries
        if (x < 0 || x + 1 >= population.length)
            return;
        if (y < 0 || y + 1 >= population[0].length)
            return;

        // If infection is stronger, infect the Patient Z
        Point coordinate = new Point(x, y);
        if (this.strength >= this.population[x][y] && !this.infected.contains(coordinate)) {
            // Add to the list of infected cells
            this.infected.add(coordinate);

            // Kill cell by setting its resistance to -1
            this.population[x][y] = -1;

            // Try to infect adjacent neighbours (not diagonal ones)
            infect(x - 1, y);
            infect(x, y - 1);
            infect(x + 1, y);
            infect(x, y + 1);
        }
    }

    public int[][] answer(int[][] population, int x, int y, int strength) {
        // Your code goes here.

        // 1. infect rabbit #Z
        // isInfected() {if (infection's strength >= patient's resistance) { infected }}
        // infectNeighbours();
        // infectNeighbours() { if (infected) { infectNeighbours(); }}

        this.population = population;
        this.strength = strength;
        this.infected = new ArrayList<Point>();

        infect(x, y);

        return population;
    }

    public static void main(String[] args) {
        Answer test = new Answer();
        test.answer(new int[][] {
            {1, 2, 3},
            {2, 3, 4},
            {3, 2, 1}
        }, 0, 0, 2);
        for (int[] column: test.population) {
            for (int row: column) {
                System.out.print(row + ", ");
            }
            System.out.println();
        }
    }
}
