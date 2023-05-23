package MainPackage;

import MainPackage.Windows.SimulationWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Simulation {
    boolean gameOver;
    SimulationWindow sw;
    Main graphicHandler;
    int w, h;
    World world;

    public Simulation(int wid, int hei, World newWorld){
        gameOver = false;
        w = wid;
        h = hei;
        world = newWorld;
        sw = SimulationWindow.getInstance(world);
        graphicHandler = Main.getInstance();
    }

    public void startSimulation(){
        world.spawnDefAnimals();
        graphicHandler.createPanel(sw.getWin());
        JFrame f = Main.getInstance().getFrame();
        f.requestFocusInWindow();
        f.pack();
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               world.handleKeys(e.getKeyCode());
            }
        });
    }
}
