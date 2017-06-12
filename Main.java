//Tommy Joseph
import javax.swing.JFrame;
public class Main {
    public static void main(String [] args) throws InterruptedException{
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        DrawingPanel drawer = new DrawingPanel();
        frame.add(drawer);
        frame.pack();
        boolean run = true;
        double nano = 1_000_000_000.0;
        Thread.sleep(2000);
        /*double oldTime = System.nanoTime()/nano;
        double totalTime = 0.0;
        Thread.sleep(2000);
        double dt = 1/60;
        while (run) {
            double currentTime = System.nanoTime()/nano;
            double timePassed = currentTime - oldTime;
            totalTime+= timePassed;
            oldTime = currentTime;
            Thread.sleep(30);
            if (totalTime > 100) {
                run = false;
            }
            drawer.update(dt);
            
        }*/
        double t = 0.0;
        double dt = 1.0/1440.0;
        double currentTime = System.nanoTime()/nano;
        double accumulator = 0.0;
        boolean quit = false;
        double timeScale = 0.01;
        while (true) {
            double newTime = System.nanoTime()/nano;
            double frameTime = newTime - currentTime;
            currentTime = newTime;
            accumulator += frameTime*timeScale;
            while (accumulator >=dt) {
                drawer.move(dt);
                accumulator -=dt;
                t+= dt;
                
            }
            drawer.repaint();
           
        }
        /*double t = 0.0;
         * const double dt = 0.01;

         * double currentTime = hires_time_in_seconds();
         * double accumulator = 0.0;

         * while ( !quit )
         * {
    double newTime = hires_time_in_seconds();
    double frameTime = newTime - currentTime;
    currentTime = newTime;

    accumulator += frameTime;

    while ( accumulator >= dt )
    {
        integrate( state, t, dt );
        accumulator -= dt;
        t += dt;
    }

    render( state );
    }
    */
    }
    
    
    
}