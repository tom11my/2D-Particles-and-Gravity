import java.util.ArrayList;
public class Gravity {
    private ArrayList<Particle> list;
    public Gravity (ArrayList<Particle> list) {
        this.list = list;

        
    }
    public Vec getGravForce (int index1, int index2)
    {


        //double G = 6.67*Math.pow(10, -11);
        double G =1000000;
        int length = list.size();
        Particle s1 = list.get(index1);
        
        Particle s2 = list.get(index2);
        double mass1 = s1.getMass();
        double mass2 = s2.getMass();
        
        double distance = Math.sqrt(s1.getCenter().subtract(s2.getCenter()).getMagSqrd());
        //error will likely be in the line below
        Vec rightDirection = new Vec (s2.getCenter().getX() - s1.getCenter().getX(), s2.getCenter().getY() - s1.getCenter().getY()); 
        //System.out.println(mass1 + "  " + mass2);
        Vec unit = rightDirection.scaledBy(1.0/distance);
        Vec gravity = unit.scaledBy((G*mass1*mass2/distance/distance));
        Vec repulsion = unit.scaledBy(-(10*G*mass1*mass2/distance/distance/distance));
        
        return gravity.add(repulsion);
    }
    
}