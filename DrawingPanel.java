//Tommy Joseph
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener{
    private int width = 800; 
    private int height = 600;
    private Color clearColor;
    private ArrayList<Particle> particles;
    private Gravity g;
    private boolean collisionOn;
    private int myStartX;
    private int myStartY;
    private int myEndX;
    private int myEndY;
    private boolean mouseDown = false;
    public DrawingPanel(){
       addMouseListener(this);
       addMouseMotionListener(this);
        Dimension d = new Dimension(width, height);
       setPreferredSize(d);
       int radius = 10;
       double mass = 50.0f;
       Vec center = new Vec (300, 200);
       Vec velocity = new Vec (1.1f, 1.1f);
       Vec center2 = new Vec (500, 400);
       Vec velocity2 = new Vec (-1.1f, -1.1f);
       Vec center3 = new Vec (100, 200);
       Vec velocity3 = new Vec (0.5f, .2f);
       
       particles = new ArrayList<Particle>();
       int num = 30;

       //getParticles(num);
       //getRealOrbit();
       //getOrbit();
       g = new Gravity (particles);
       clearColor = new Color (0, 0, 0, 5);
       collisionOn = true;
    }
    public void getClickedParticles () {
        Vec center = new Vec (myStartX, myStartY);
        Vec velocity = new Vec();
        

        //if(mouseDown)
        particles.add( new Particle(5, 50.0f, center, velocity));
        
    }
    public void mouseClicked(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}
    
    public void mousePressed(MouseEvent e)
    {
        //double start = System.nanoTime()/1_000_000_000;
        mouseDown = true;
        myStartX = e.getX();
        myStartY = e.getY();
        getClickedParticles();
    }
    
    public void mouseReleased(MouseEvent e){
        
        myEndX = e.getX();
        myEndY = e.getY();
        particles.get(particles.size()-1).setVelocity(modifyVel());
        System.out.println(particles.get(particles.size()-1).getVelocity());
        mouseDown = false;
        
    }
    public Vec modifyVel () {
        Vec velocity = new Vec (myEndX - myStartX, myEndY - myStartY);
        velocity = velocity.scaledBy(20);
        return velocity;
    }
    public void mouseMoved(MouseEvent e){
        
    }
    public void mouseDragged(MouseEvent e){
        
    }
    public void getParticles (int num) {
        for(int rep = 0; rep < num; rep++)
            particles.add(getParticle());
        
    }
    public void getOrbit () {
        Vec center = new Vec (400, 300);
        Vec velocity = new Vec (0, 0);
        
        Vec center2 = new Vec (400, 200);
        Vec velocity2 = new Vec(7000.0f, 0);
        
        Vec center3 = new Vec (400, 500);
        Vec velocity3 = new Vec (4700.0f, 0);
        
        particles.add( new Particle(20, 5000.0f, center, velocity));
        
        particles.add( new Particle(10, 10.0f, center2, velocity2));
        
        particles.add( new Particle(5, 10.0f, center3, velocity3));
    }
    public void getRealOrbit() {
         Vec center = new Vec (400, 300);
        Vec velocity = new Vec (0, 0);
        
        Vec center2 = new Vec (400, 200);
        Vec velocity2 = new Vec(1.0f, 0);
        
        Vec center3 = new Vec (400, 170);
        Vec velocity3 = new Vec (1.0f, 0);
        
        particles.add( new Particle(20, 1000.0f, center, velocity));
        
        particles.add( new Particle(10, 5.0f, center2, velocity2));
        
        particles.add( new Particle(5, 1.0f, center3, velocity3));
        getParticles(10);
        
        
    }
    public Particle getParticle () {
        return new Particle (5, 50.0f, new Vec((double)(Math.random()*590), (double)(Math.random()*590)), /*new Vec((double)(Math.random()*1.5), (double)(Math.random()*1.5))*/ new Vec (0, 0)); 
    }
    public void update (double dt) {
        move(dt);
        repaint();
    
    
    
    
    }
    public void paintComponent(Graphics g){
        //System.out.println("xcomp" + myStartX + "ycomp" + myStartY);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(clearColor);
        g2d.fillRect(0, 0, width, height);
        for(Particle p: particles) {
            p.draw(g2d);
            //System.out.println(p.getOrigColor());
            //p.setColor(Color.red);
        }
    }
    public void move (double dt) {
               //getClickedParticles();
        if(!collisionOn)
        for(int rep = 0; rep < particles.size(); rep++) {
            Particle p = particles.get(rep);
            Vec force = getTforce(rep);
            Vec acc = new Vec (force.getX()/p.getMass(), force.getY()/p.getMass());
            p.getVf(dt, acc);
            Vec dx = p.getDistanceVec(dt);
            p.setCenter(new Vec((double)(p.getCenter().getX() + dx.getX()), (double)(p.getCenter().getY() + dx.getY())));
            
            
            
        }
        else
        for (int rep = 0; rep < particles.size(); rep++) {
            Particle p = particles.get(rep);
            Particle colliding = new Particle();
            boolean b = false;
            for(int n = 0; n < particles.size(); n++) {
                if(rep != n) 
                    b = p.checkCol(particles.get(n));
                if(b) {
                    colliding = particles.get(n);
                    break;  
                }
            }
            p.setIsColliding(b);
            
            //if(rep < 1 && !p.getIsColliding())
            if(p.getIsColliding())
                p.bounce(colliding);
            else if (!p.getIsColliding()) {
                //p.changeCenter(p.getVelocity().getX(), p.getVelocity().getY());
                p.setColor(p.getOrigColor());
            }
            Vec force = getTforce(rep);
            Vec acc = new Vec (force.getX()/p.getMass(), force.getY()/p.getMass());
            p.getVf(dt, acc);
            Vec dx = p.getDistanceVec(dt);
            p.setCenter(new Vec((double)(p.getCenter().getX() + dx.getX()), (double)(p.getCenter().getY() + dx.getY())));
            p.checkBounds();
            //else if (!p.getIsColliding())
            //p.changeCenter(-1, 0);
        }
    }
    public Vec getTforce(int index) {
        Vec tforce = new Vec (0, 0);
        for(int rep = 0; rep < particles.size(); rep++) {
            if(index != rep){
                tforce = tforce.add(g.getGravForce(index, rep));
            }
        }
        return tforce;
    }
}


