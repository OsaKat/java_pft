package ru.stqa.pft.sandbox;

/**Created by Kate on 06.05.2018
 */

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args){
        Point p1 = new Point(3, 2);
        Point p2 = new Point(7, 8);

        System.out.println("Расстояние между точками = " + distance(p1, p2));
}


    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
        }
}