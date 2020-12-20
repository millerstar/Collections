package com.coursera.solvingproblems;

import com.coursera.edu.duke.Shape;
import com.coursera.edu.duke.Point;

public class PerimeterRunner {

    public static double getPerimeter(Shape shape) {
        double totalPerimeter = 0;
        Point prevPt = shape.getLastPoint();
        for (Point curPoint:shape.getPoints()) {
            double curDistance = prevPt.distance(curPoint);
            totalPerimeter += curDistance;
            prevPt = curPoint;
        }
        return totalPerimeter;
    }

    public static void main(String[] args) {
        Shape myShape = new Shape();
        Point p1 = new Point(-3, 9);
        Point p2 = new Point(-8, 7);
        Point p3 = new Point(-12, 4);
        Point p4 = new Point(-6, -2);
        Point p5 = new Point(-4, -6);
        Point p6 = new Point(2, -8);
        Point p7 = new Point(6, -5);
        Point p8 = new Point(10, -3);
        Point p9 = new Point(8, 5);
        Point p10 = new Point(4, 8);
        myShape.addPoint(p1);
        myShape.addPoint(p2);
        myShape.addPoint(p3);
        myShape.addPoint(p4);
        myShape.addPoint(p5);
        myShape.addPoint(p6);
        myShape.addPoint(p7);
        myShape.addPoint(p8);
        myShape.addPoint(p9);
        myShape.addPoint(p10);

        double perimeter = PerimeterRunner.getPerimeter(myShape);
        System.out.println(perimeter);
    }
}
