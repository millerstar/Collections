package com.coursera.solvingproblems;

import com.coursera.edu.duke.Point;
import com.coursera.edu.duke.Shape;
import com.coursera.edu.duke.*;

import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        int pointCounter = 0;
        for (Point p : s.getPoints()) {
            ++pointCounter;
        }
        return pointCounter;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double averageLength = 0.0;
        averageLength = getPerimeter(s) / getNumPoints(s);
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point p : s.getPoints()) {
            double currentSide = prevPt.distance(p);
            if (currentSide >= largestSide) {
                largestSide = currentSide;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        for (Point p : s.getPoints()) {
            int currentX = p.getX();
            if (currentX > largestX) {
                largestX = currentX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            Double perimeter = getPerimeter(shape);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        String fileName = null;
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            Double perimeter = getPerimeter(shape);
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testPerimeter() {
        FileResource fr = new FileResource("datatest4.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = " + getNumPoints(s));
        System.out.println("Average length = " + getAverageLength(s));
        System.out.println("Largest side = " + getLargestSide(s));
        System.out.println("Largest X = " + getLargestX(s));
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest perimeter in multiple files = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("Test file with the largest perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter();
//        pr.printFileNames();
//        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();

       /* Shape myShape = new Shape();
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
        System.out.println(pr.getPerimeter(myShape));*/

    }
}
