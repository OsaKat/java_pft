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

        System.out.println("Расстояние между точками = " + p1.distance(p2));
}


    public double distance(Point p2) {
        return Math.sqrt(Math.pow((p2.x - this.x),2) + Math.pow((p2.y - this.y),2));
        }
}