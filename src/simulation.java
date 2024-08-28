import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class simulation {
    // frame parameters
    private JFrame frame;
    private CustomPanel panel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)(screenSize.width * 0.9);
    int height = (int)(screenSize.height * 0.9);
    Color backgroundColor = new Color(183, 233, 255);

    // buttons
    private JButton resetButton;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resetWallsButton;
    // lines
    private ArrayList<miniSimulation> miniSimulations;
    private int wallSize = 250;
    private int lineSize = 600;
    private int offset = 50;
    private CustomLine startLine = new CustomLine(offset,offset,offset,lineSize + offset);
    private CustomLine endLine = new CustomLine(width - offset,offset, width - offset, lineSize + offset);
    private Color wallColor = Color.black;
    // cars 
    private List<Car> cars;
    private int carHeight = lineSize/4;
    private int carWidth = 40;
    private Color carColor = new Color(93,207,140);
    private Color parkedCarColor = new Color(254,77,77);
    // simulation parameters
    private Timer timer;
    private int particlesNumber = 50;
    // evolution
    private JButton evolutionStartButton;
    private JButton evolutionPauseButton;
    private Timer evolutionTimer;
    private int generation = 1;
    private boolean evolving = false;
    private double bigMutation = 0.3;
    private double smallMutation = 0.3;
    private double reproduction = 0.4;
    private double smallMutationSize = 16;

    public simulation() {
        frame = new JFrame("Simulation évolution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // panels
        panel = new CustomPanel();
        frame.add(panel, BorderLayout.CENTER);
        JPanel evolutionPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel generationDisplayPanel = new JPanel();
        JLabel generationDisplayLabel = new JLabel();

        // lines
        miniSimulations = new ArrayList<>();
        miniSimulations.add(new miniSimulation(300, 200, wallSize, wallColor, parkedCarColor));

        // // add a wall by clicking
        // panel.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         int x = e.getX();
        //         int y = e.getY();
        //         int xmin = startLine.getStartX();
        //         int xmax = endLine.getStartX();
        //         int ymin = startLine.getStartY();
        //         int ymax = startLine.getEndY();
        //         System.out.println("x :" + x + ", y : " + y);
        //         System.out.println("xmin :" + xmin + ", xmax :" + xmax + ", ymin : " + ymin + ", ymax : " + ymax);
        //         if(xmin + offset <= x && x <= xmax - offset && ymin <= y && y <= ymax){
        //             walls.add(new CustomLine(x, y, x, y+wallSize));
        //             panel.repaint();
        //         }else{
        //             System.out.println("Please select a valid position!");
        //         }
        //     }
        // });

        // cars
        cars = new ArrayList<>();
        cars.add(new Car(500,500,carHeight,carWidth,carColor));

        // buttons
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset particles");
        resetWallsButton = new JButton("Reset walls");

        startButton.setBackground(Color.GREEN);
        pauseButton.setBackground(new Color(255,60,60));
        resetButton.setBackground(new Color(110,110,255));
        resetWallsButton.setBackground(new Color(110,110,255));
        /* 
        timer = new Timer(5, e -> {
            for (Particule p : particles) {
                if(!p.isArrived(endLine) && !p.isBlocked(walls)){
                    p.update();
                }
            }
            panel.repaint();
            if(checkSimulationEnd()){
                System.out.println("Simulation ended!");
                timer.stop();
                if(evolving){
                    System.out.println("evolving...");
                    evolutionTimer.start();
                }
            }
        });
        
        startButton.addActionListener(e -> {
            System.out.println("Simulation started!");
            for(Particule p : particles){
                p.atStart = false;
            }
            timer.start();
        });

        pauseButton.addActionListener(e -> {
            System.out.println("Simulation paused!");
            timer.stop();
        });

        resetButton.addActionListener(e -> {
            System.out.println("Reset!");
            timer.stop();
            particles = newParticlesSet(particlesNumber);
            panel.repaint();
        });
        */
        // resetWallsButton.addActionListener(e -> {
        //     if(!timer.isRunning() && !evolutionTimer.isRunning()){
        //         System.out.println("Reset walls!");
        //         walls = new ArrayList<>();
        //         panel.repaint();
        //     }else{
        //         System.out.println("Please stop the simulation first!");
        //     }
        // });

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(resetWallsButton);

        // // evolution
        // // timer
        // evolutionTimer = new Timer(600, e -> {
        //     evolutionTimer.stop();
        //     System.out.println("New particules, new simulation!");
        //     // particles = evolveParticles(particles);
        //     generation+=1;
        //     generationDisplayLabel.setText("  Generation : " + generation + "  ");
        //     panel.repaint();
        //     timer.start();
        // });
        // // button
        // evolutionStartButton = new JButton("Start Evolution");
        // evolutionPauseButton = new JButton("Pause Evolution");
        // evolutionStartButton.setBackground(new Color(110,110,255));
        // evolutionPauseButton.setBackground(new Color(110,110,255));
        // /*
        // evolutionStartButton.addActionListener(e -> {
        //     if(particlesAtStart()){
        //         System.out.println("Evolution started!");
        //         for(Particule p : particles){
        //             p.atStart = false;
        //         }

        //         evolving = true;
        //         timer.start();
        //     }else{
        //         System.out.println("Please reset the simulation before starting evolution.");
        //     }
            
        // });
        // */
        // evolutionPauseButton.addActionListener(e -> {
        //     System.out.println("Evolution Paused!");
        //     evolving = false;
        //     timer.stop();
        //     evolutionTimer.stop();
        // });
        // // display
        // generationDisplayLabel.setText("  Generation : " + generation + "  ");
        // generationDisplayPanel.add(generationDisplayLabel);
        // generationDisplayPanel.setBackground(new Color(110,110,255));
        // // evolution panel
        // evolutionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // evolutionPanel.setBackground(new Color(140, 220, 255));
        // evolutionPanel.add(evolutionStartButton);
        // evolutionPanel.add(evolutionPauseButton);
        // evolutionPanel.add(generationDisplayPanel);

        // container
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.add(buttonPanel);
        containerPanel.add(evolutionPanel);
        frame.add(containerPanel, BorderLayout.SOUTH);

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(backgroundColor);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(new BasicStroke(2));

            g2d.setColor(Color.BLACK);
            g2d.drawLine(startLine.getStartX(),startLine.getStartY(),startLine.getEndX(),startLine.getEndY());
            g2d.drawLine(endLine.getStartX(),endLine.getStartY(),endLine.getEndX(),endLine.getEndY());
            g2d.setColor(new Color(100,100,0));
            for(miniSimulation s : miniSimulations) {
                s.draw(g2d);
            }
            
            g2d.setStroke(new BasicStroke(1));
            for (Car p : cars) {
                p.draw(g);
            }
        }
    }

    // private List<Car> newParticlesSet(int particleNb){
    //     cars = new ArrayList<>();
    //     for(int i = 0 ; i < particleNb ; i++){
    //         int minY = startLine.getStartY();
    //         int maxY = startLine.getEndY();
    //         int randomY = (int) (Math.random() * (maxY - minY + 1)) + minY;
    //         cars.add(new Car(startLine.getEndX(), randomY, particleSpeed, 0, particleSize, Color.blue));
    //     }
    //     return cars;
    // }
    /*
    private boolean checkSimulationEnd(){
        boolean simulationEnded = true;
        for(Particule p : particles){
            if(!p.blocked && !p.arrived){
                simulationEnded = false;
            }
        }
        return simulationEnded;
    }
    
    private boolean particlesAtStart(){
        boolean simulationAtStart = true;
        for(Particule p : particles){
            if(!p.atStart){
                simulationAtStart = false;
            }
        }
        return simulationAtStart;
    }
     
    private List<Particule> evolveParticles(List<Particule> particles){
        List<Particule> arrivedParticules = new ArrayList<>(); // they're going to stay the same
        List<Particule> notArrivedParticules = new ArrayList<>(); // they're going to evolve
        // filter particles
        for(Particule p : particles){
            if(p.arrived){
                arrivedParticules.add(p);
            } else {
                notArrivedParticules.add(p);
            }
        }
        if(arrivedParticules.size() == particles.size()){
            System.out.println("Evolution is over :)");
        } else {
             // order by distance the particles have travelled
            sortParticlesByX(notArrivedParticules);

            // get the number of particles to modify for each method
            int totalParticles = notArrivedParticules.size();
            int nbBigMutation = (int) Math.round(totalParticles * bigMutation);
            int nbSmallMutation = (int) Math.round(totalParticles * smallMutation);
            int nbReproduction = (int) Math.round(totalParticles * reproduction);
            // adjust to make sure the number of modified particles is correct
            int totalCalculated = nbBigMutation + nbSmallMutation + nbReproduction;
            if (totalCalculated != totalParticles) {
                nbReproduction += (totalParticles - totalCalculated);
            }

            // evolve ! 
            int minY = startLine.getStartY();
            int maxY = startLine.getEndY();
            // big mutation
            for(int i = 0 ; i < nbBigMutation ; i++){
                Particule particleToEvolve = notArrivedParticules.get(i);
                // random big mutation
                int randomY = (int) (Math.random() * (maxY - minY + 1)) + minY;
                particleToEvolve.setY(randomY);
            }
            // small mutation
            for(int i = nbBigMutation ; i < nbSmallMutation ; i++){
                Particule particleToEvolve = notArrivedParticules.get(i);
                // random small mutation
                int newY = (int) (particleToEvolve.getY() + (Math.random() * smallMutationSize) - Math.round(smallMutationSize/2)); // centered on old Y
                // correct the mutation if needed
                if(newY > maxY){ // to stay on the start line
                    newY = maxY;
                } else if(newY < minY){
                    newY = minY;
                }
                particleToEvolve.setY(newY);
            }
            // reproduction
            for(int i = nbSmallMutation ; i < totalParticles ; i++){
                Particule particleToEvolve = notArrivedParticules.get(i);
                // random perfect particle
                int randint = (int) Math.random()*totalParticles;
                Particule randomPerfecParticule = arrivedParticules.get(randint);
                // reproduction
                int newY = (int) (particleToEvolve.getY() + randomPerfecParticule.getY()) / 2 ; // average Y
                particleToEvolve.setY(newY);
            }

            // concatenate the new list
            particles = new ArrayList<>(arrivedParticules);
            particles.addAll(notArrivedParticules);
        }
        // reset parameters
        for(Particule p : particles){
            p.setX(startLine.getStartX());
            p.setVx(particleSpeed);
            p.arrived = false;
            p.atStart = true;
            p.blocked = false;
        }
        return particles;
    }
    
    public void sortParticlesByX(List<Particule> particles) {
        Collections.sort(particles, new Comparator<Particule>() {
            @Override
            public int compare(Particule p1, Particule p2) {
                return Integer.compare((int) p1.getX(), (int) p2.getX());
            }
        });
    }
*/
    public static void main(String[] args) {
        new simulation();
    }
}
