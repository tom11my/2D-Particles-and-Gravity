//Tommy Joseph
public class Vec {
    private double x;
    private double y;
    public Vec () {
        x = 0.0f;
        y = 0.0f;
        
    }
    public Vec (double xcomp, double ycomp) {
        x = xcomp;
        y = ycomp;
    }
    public Vec (Vec vec) {
        x = vec.getX();
        y = vec.getY();
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getMag() {
        return (double) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }
    public double getMagSqrd () {
        return (double) (Math.pow(x, 2) + Math.pow(y, 2));
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public Vec getUnitVec () {
        double mag = this.getMag();
        return new Vec (x/mag, y/mag);
        
    }
    public Vec scaledBy (double num) {
        return new Vec (x*num, y*num);
    }
    public Vec subtract (Vec v) {
        return new Vec (x - v.getX(), y - v.getY());
    }
    public Vec add (Vec v) {
        return new Vec (x + v.getX(), y + v.getY());  
   
    }
    public void setNegative() {
        this.x = -x;
        this.y = -y;
        
    }
    public String toString() {
        return ("x-component: " + getX()+"\n" + "y-component: " + getY());
    }
    
}