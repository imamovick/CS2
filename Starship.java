public class Starship {
    String name;
    Vector position;
    Vector velocity;

    /* giving values to variables in the class */
    public Starship(String name, Vector position, Vector velocity) {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
    }
    /* adds a new value to the vector of velocity */
    public Starship(String name, Vector position) {
        this.name = name;
        this.position = position;
        velocity = new Vector();
    }
    /* getter method for the name */
    public String getName() {
        return this.name;
    }
    /* getter method for the velocity */
    public Vector getVelocity() {
        return this.velocity;
    }
    /* getter method for the position */
    public Vector getPosition(){
        return this.position;
    }
    /* setter method for the velocity */
    public void setVelocity(Vector u) {
        this.velocity = u;
    }
    /* setter method for the position */
    public void setPosition(Vector u){
        this.position = u;
    }
    /* assings values p & v from vector class and returns an answer in a textual form */
    public String toString(){
        Vector p = this.position;
        Vector v = this.velocity;
        return name + " at " + p.toString() + " moving " + v.toString();
    }
    /* sets the accelerated velocity of the starship */
    public void accelerate(Vector v) {
        Starship ship = this;
        ship.setVelocity(ship.getVelocity().plus(v));
    }
    /* changes the location of the starship, "drifts" it */
    public void drift() {
        Starship ship = this;
        ship.setPosition(ship.getPosition().plus(ship.getVelocity()));
    }
}
