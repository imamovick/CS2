public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector() {
        this.x = 0.0;
        this.y = 0.0;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    /* returns a string for the positions */
    public String toString() {
        return "<" + this.x + ", " + this.y + ">";
    }
    /* checks whether the value of x is equal in-between classes */
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Vector that = (Vector) x;
        return (this.x == that.x) && (this.y == that.y);
    }
    /* adds vectors */
    public Vector plus(Vector u) {
        Vector v = this;
        double x = v.getX() + u.getX();
        double y = v.getY() + u.getY();
        return new Vector(x, y);
    }
    /* subtracts vectors */
    public Vector minus(Vector u) {
        Vector v = this;
        double x = v.getX() - u.getX();
        double y = v.getY() - u.getY();
        return new Vector(x, y);
    }
    /* returns the dot product */
    public double dot(Vector u) {
        Vector v = this;
        return v.getX()* u.getX() + v.getY()* u.getY();
    }
    /* multiplies vectors */
    public Vector times(double u) {
        Vector v = this;
        double x = v.getX()*u;
        double y = v.getY()*u;
        return new Vector(x, y);
    }
    /* returns the distance between vectors */
    public double distanceTo(Vector u) {
        Vector v = this;
        return Math.sqrt((v.getX()- u.getX())*(v.getX()- u.getX())+(v.getY()- u.getY())*(v.getY()- u.getY()));
    }
}
