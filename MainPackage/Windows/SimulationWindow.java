package MainPackage.Windows;

import MainPackage.Consts;
import MainPackage.Main;
import MainPackage.Simulation;
import MainPackage.World;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationWindow {
    private final Button nextTurn, save, load;
    private JPanel win;
    private static SimulationWindow instance;
    World world;

    public static SimulationWindow getInstance(){
        if (instance == null)
            throw new RuntimeException("instance is null, use other override of this function");
        return instance;
    }

    public static SimulationWindow getInstance(World w){
        if (instance == null)
            instance = new SimulationWindow(w);
        return instance;
    }

    private SimulationWindow(World w){
        world = w;
        nextTurn = new Button("Next Turn");
        save = new Button("Save");
        load = new Button("Load");

        rerenderWindow();

        nextTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.nextTurn();
                Main.getInstance().getFrame().requestFocusInWindow();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.save();
                Main.getInstance().getFrame().requestFocusInWindow();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.load();
                Main.getInstance().getFrame().requestFocusInWindow();
            }
        });
    }

    public void rerenderWindow(){
        win = new JPanel();
        win.setLayout(new BoxLayout(win, BoxLayout.X_AXIS));
        win.setBackground(Consts.BACKGROUND_COL);

        JPanel col1 = new JPanel();
        JPanel col2 = new JPanel();
        JPanel buttons = new JPanel();


        // set columns properties
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));
        col1.setBackground(Consts.BACKGROUND_COL);

        col2.setLayout(new BorderLayout());
        col2.setBackground(Consts.BACKGROUND_COL);


        // set buttons properties
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(nextTurn);
        buttons.add(save);
        buttons.add(load);


        // add components to columns
        col1.add(world.getMapPanel());
        col1.add(buttons);

        col2.add(DialogWindow.getInstance().getWin(), BorderLayout.NORTH);
        col2.add(Legend.getInstance().getWin(), BorderLayout.SOUTH);


        // set sizes of components
        int firstColWidth;
        if (world.getWidth() * Consts.CELL_SIZE > Consts.MIN_BUTTONS_SIZE)
            firstColWidth = world.getWidth() * Consts.CELL_SIZE;
        else
            firstColWidth = Consts.MIN_BUTTONS_SIZE;

        Dimension prefSizeLogs = new Dimension(Consts.LOGS_LEGEND_WIDTH, (int)col2.getPreferredSize().getHeight());
        Dimension prefSizeBts = new Dimension(firstColWidth, Consts.BUTTON_HEIGHT);
        Dimension prefSizeMapSide = new Dimension(firstColWidth, (int)col1.getPreferredSize().getHeight() + Consts.BUTTON_HEIGHT);
        buttons.setPreferredSize(prefSizeBts);
        buttons.setMaximumSize(prefSizeBts);
        col2.setPreferredSize(prefSizeLogs);
        col2.setMinimumSize(prefSizeLogs);
        col1.setPreferredSize(prefSizeMapSide);
        col1.setMinimumSize(prefSizeMapSide);
        col1.setMaximumSize(prefSizeMapSide);


        // add columns to window
        win.add(col1);
        win.add(col2);


        // add borders
        Border border = BorderFactory.createLineBorder(Consts.BORDER_COLOR);
        for (Component c : win.getComponents()){
            ((JPanel)c).setBorder(border);
        }
    }

    public JPanel getWin(){
        return win;
    }
}
