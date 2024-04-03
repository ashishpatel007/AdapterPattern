package jdp2e.adapter.modified.demo;


import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

interface RectInterface {
    void aboutRectangle();
    double calculateAreaOfRectangle();
}

class Rectangle implements RectInterface {
    public double length;
    public double width;
    public Rectangle (double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void aboutRectangle() {
        System.out.println("Rectangle object with length: " + this.length + " unit and width :" +
                this.width+" unit.");
    }

    @Override
    public double calculateAreaOfRectangle() {
        return length * width;
    }
}

interface TriInterface {
    void aboutTriangle();
    double calculateAreaOfTriangle();
}

class Triangle implements  TriInterface {

    public double base;//base
    public double height;//height
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void aboutTriangle() {
        System.out.println("Triangle object with base: " + this.base +" unit and height :" +
                this.height + "unit.");

    }

    @Override
    public double calculateAreaOfTriangle() {
        return 0.5 * base * height;
    }
}

/*
TriangleAdapter is implementing RectInterface.
So, it needs to implement all the methods defined
in the target interfac
 */

class TriangleAdapter implements RectInterface {

    Triangle triangle;

    public TriangleAdapter(Triangle t) {
        this.triangle = t;
    }

    @Override
    public void aboutRectangle() {
        triangle.aboutTriangle();
    }

    @Override
    public double calculateAreaOfRectangle() {
        return triangle.calculateAreaOfTriangle();
    }
}

public class ModifiedAdapterPatternExample {

    public static void main(String[] args) {
        System.out.println("***Adapter Pattern Modified Demo***\n");
        Rectangle rectangle = new Rectangle(20, 10);
        System.out.println("Area of Rectangle is : " + rectangle.calculateAreaOfRectangle() + " square unit.");
        Triangle triangle = new Triangle(10,5);
        System.out.println("Area of Triangle is " + triangle.calculateAreaOfTriangle() + " square unit.");
        RectInterface adapter = new TriangleAdapter(triangle);
        //Passing a Triangle instead of a Rectangle
        System.out.println("Area of Triangle using the triangle adapter is : " + getArea(adapter) + " square unit.");

        //Some additional code (Optional) to show the power of adapter
        //pattern
        List<RectInterface> rectangleObjects = new ArrayList<>();
        rectangleObjects.add(rectangle);
        //rectangleObjects.add(triangle);   // error
        rectangleObjects.add(adapter);
        System.out.println();
        System.out.println("*****Current objects in the system are:*****");

        for (RectInterface rectObjects:rectangleObjects)
        {
            rectObjects.aboutRectangle();
        }
    }

    /*
    getArea(RectInterface r) method does not know that through TriangleAdapter, it is
    getting a Triangle object instead of a Rectangle object
     */
    static double getArea(RectInterface r)
    {
        return r.calculateAreaOfRectangle();
    }

}
