package exercise;

// BEGIN
public class Circle {
    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Error! Radius is negative");
        }

        return (Math.pow(radius, 2) * Math.PI);
    }

    private Point center;
    private int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }
}
// END
