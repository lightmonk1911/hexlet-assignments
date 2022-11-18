package exercise;

// BEGIN
public class Segment {
    private final Point beginPoint;
    private final Point endPoint;

    public Segment(Point start, Point endPoint) {
        this.beginPoint = start;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        return new Point(average(endPoint.getX(), beginPoint.getX()), average(beginPoint.getY(), endPoint.getY()));
    }

    public int average(int a, int b) {
        return (a + b) / 2;
    }
}
// END
