import java.awt.Graphics2D;
import java.awt.Color;
public class Particle {
    private int radius; 
    private double mass;
    private Vec center;
    private Vec velocity;
    private Vec vf;
    private boolean isColliding;
    private Vec TForce;
    private Color curColor;
    private Color origColor;
    public final double ELASTICITY = 1.0f;
    
    public Particle (int radius, double mass, Vec center, Vec velocity) {
        this.radius = radius;
        this.mass = mass;
        this.center = center;
        this.velocity = velocity;
        origColor = new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), 200);
        curColor = origColor;
    }
    public Particle () {
        this.radius = 10;
        this.mass = 20;
        this.center = new Vec (100, 100);
        this.velocity = new Vec (1, 0);
        
    }
    public Vec getTForce () {
        return this.TForce;
        
    }
    public int getRadius() {
        return this.radius;
        
    }
    public double getMass () {
        return this.mass;
    }
    public Vec getCenter() {
        return this.center;
    }
    public Vec getVelocity() {
        return this.velocity;
        
    }
    public boolean getIsColliding() {
        return this.isColliding;
        
    }
    public void setTForce(Vec force) {
        TForce = force;
        
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public void setCenter(Vec center) {
        this.center = center;
    }
    public void setVelocity(Vec velocity) {
        this.velocity = velocity;
    }
    public void setVf(Vec velocity) {
        this.vf = velocity;
        
    }
    public void setIsColliding(boolean b) {
        this.isColliding = b;
        
    }
    public boolean checkCol (Particle p) {
        int maxDistance = this.radius + p.getRadius();
        //System.out.println("max distance" +maxDistance);
        return this.getDistance(p) < maxDistance;
        
    }
    public double getDistance (Particle p) {
        Vec result = this.getCenter().subtract(p.getCenter());
        //System.out.println("actual distance" +(result.getMag()));
        return result.getMag();
        
    }
    public void changeCenter (double x, double y) {
        this.center.setX(center.getX() + x);
        this.center.setY(center.getY() + y);
        
    }
    public void bounce (Particle parCol) {
        double x = this.velocity.getX();
        double y = this.velocity.getY();
        this.velocity.setX(parCol.velocity.getX()*ELASTICITY);
        this.velocity.setY(parCol.velocity.getY()*ELASTICITY);
        parCol.velocity.setX(x*ELASTICITY);
        parCol.velocity.setY(y*ELASTICITY);
        this.isColliding = false;
        parCol.isColliding = true;
        curColor = Color.blue;
    }
    public void checkBounds () {
        if(this.center.getX()>800 - this.radius || this.center.getX() < 0 +this.radius)
            this.velocity.setX(-this.velocity.getX());
        if(this.center.getY()>600 - this.radius|| this.center.getY() < 0 + this.radius)
            this.velocity.setY(-this.velocity.getY());
    }
    public void setColor (Color col) {
        
        this.curColor = col;
    }
    public Vec getDistanceVec (double timePassed) {
        Vec d = getVelocity().add(vf).scaledBy((double)(0.5*timePassed));
        this.setVelocity(vf);
        return d;
        
    }
    public Vec getVf (double timePassed, Vec acc) {
        Vec a = acc;
        vf = getVelocity().add(a.scaledBy((double) timePassed));
        return vf;
        
    }
    public Color getOrigColor() {
        return origColor;
        
    }
    public void draw (Graphics2D g2d) {
        g2d.setColor(curColor);
        g2d.fillArc((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius*2, (int) radius*2, 0, 360);
        
    }
    
}