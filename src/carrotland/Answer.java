/*
    Carrotland
    ==========

    The rabbits are free at last, free from that horrible zombie science experiment. They need a happy, safe home,
    where they can recover.

    You have a dream, a dream of carrots, lots of carrots, planted in neat rows and columns! But first, you need some
    land. And the only person who's selling land is Farmer Frida. Unfortunately, not only does she have only one plot
    of land, she also doesn't know how big it is - only that it is a triangle. However, she can tell you the location
    of the three vertices, which lie on the 2-D plane and have integer coordinates.

    Of course, you want to plant as many carrots as you can. But you also want to follow these guidelines: The carrots
    may only be planted at points with integer coordinates on the 2-D plane. They must lie within the plot of land and
    not on the boundaries. For example, if the vertices were (-1,-1), (1,0) and (0,1), then you can plant only one
    carrot at (0,0).

    Write a function answer(vertices), which, when given a list of three vertices, returns the maximum number of
    carrots you can plant.

    The vertices list will contain exactly three elements, and each element will be a list of two integers
    representing the x and y coordinates of a vertex. All coordinates will have absolute value no greater
    than 1000000000. The three vertices will not be collinear.
 */

package carrotland;

import java.math.BigDecimal;

public class Answer {
    static class Triangle {
        Point a;
        Point b;
        Point c;

        public Triangle(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Triangle(int[][] vertices) {
            this(new Point(vertices[0]), new Point(vertices[1]), new Point(vertices[2]));
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int[] vertex) {
            this(vertex[0], vertex[1]);
        }
    }

    /**
     * Calculates area of a triangle using shoelace formula
     * @param triangle
     * @return area
     */
    public static int getArea(Triangle triangle) {
        BigDecimal area = BigDecimal.ZERO;
        area = BigDecimal.valueOf(triangle.a.x).multiply(
                BigDecimal.valueOf(triangle.b.y).subtract(
                        BigDecimal.valueOf(triangle.c.y)
                )
        );

        area = area.add(
                BigDecimal.valueOf(triangle.b.x).multiply(
                        BigDecimal.valueOf(triangle.c.y).subtract(
                                BigDecimal.valueOf(triangle.a.y)
                        )
                )
        );

        area = area.add(
                BigDecimal.valueOf(triangle.c.x).multiply(
                        BigDecimal.valueOf(triangle.a.y).subtract(
                                BigDecimal.valueOf(triangle.b.y)
                        )
                )
        );

        area = area.divide(BigDecimal.valueOf(2.0));

        return area.abs().toBigInteger().intValue();

//        area = triangle.a.x * (triangle.b.y - triangle.c.y) / 2;
//        area += triangle.b.x * (triangle.c.y - triangle.a.y) / 2;
//        area += triangle.c.x * (triangle.a.y - triangle.b.y) / 2;
//        area = Math.abs(Math.round(area));
//        return (int) area;
    }

    /**
     * Counts the boundary points on triangle sides
     * @param triangle
     * @return boundary point count
     */
    public static int getBoundaryPointCount(Triangle triangle) {
        int count = getGreatestCommonDivisor(
                Math.abs(triangle.a.x - triangle.b.x),
                Math.abs(triangle.a.y - triangle.b.y)
        );

        count += getGreatestCommonDivisor(
                Math.abs(triangle.b.x - triangle.c.x),
                Math.abs(triangle.b.y - triangle.c.y)
        );

        count += getGreatestCommonDivisor(
                Math.abs(triangle.c.x - triangle.a.x),
                Math.abs(triangle.c.y - triangle.a.y)
        );

        return count;
    }

    /**
     * Euclidean algorithm to find the greatest common divisor
     * @param a number
     * @param b number
     * @return greatest common divisor
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        }

        return getGreatestCommonDivisor(b, a % b);
    }

    public static int answer(int[][] vertices) {
        // Pick's theorem: A = i + b/2 - 1
        // where A - area, i - points inside triangle, b - points on the border
        Triangle triangle = new Triangle(vertices);

        int area = getArea(triangle);

        // If area is not greater than 0, then it has 0 points
        if (area <= 0) {
            return 0;
        }

        // Reordered Pick's theorem: i = A - b/2 + 1
        return area - getBoundaryPointCount(triangle)/2 + 1;
    }

    public static void main(String[] args) {

    }
}
