package MainPackage;

import MainPackage.Windows.ReadParamsWindow;

import javax.swing.*;

public class Main {
    private static Main instance;
    private final JFrame frame;

    private Main(){
        frame = new JFrame("Virtual World Jan Kaczerski");
    }

    public static Main getInstance(){
        if (instance == null)
            instance = new Main();
        return instance;
    }

    public JFrame getFrame(){
        return frame;
    }

    public void createPanel(JPanel newPanel){
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(newPanel);
            frame.pack();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main m = Main.getInstance();
            JFrame f = m.getFrame();

            World world = null;

            if(Consts.SKIP_READING){
                world = new World(Consts.DEBUG_W, Consts.DEBUG_H);
                Simulation sim1 = new Simulation(Consts.DEBUG_W, Consts.DEBUG_H, world);
                sim1.startSimulation();
            }
            else {
                ReadParamsWindow rpm = new ReadParamsWindow(world);
                f.setContentPane(rpm.getWin());
            }
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.pack();
            f.setVisible(true);
            f.requestFocusInWindow();
        });
    }
}
