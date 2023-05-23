package MainPackage.Windows;

import MainPackage.Consts;
import MainPackage.Main;
import MainPackage.Simulation;
import MainPackage.World;
import MainPackage.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadParamsWindow {
    Button submit;
    JPanel win;
    JTextField readW, readH;
    World world;

    public ReadParamsWindow(World worldRef){
        world = worldRef;
        submit = new Button("Submit");
        win = new JPanel(new GridLayout(2,1));
        readW = new JTextField(10);
        readH = new JTextField(10);

        JPanel row1 = new JPanel(new GridLayout(1,2));
        JPanel widthField = new JPanel(new GridLayout(2,1));
        JPanel heightField = new JPanel(new GridLayout(2,1));

        widthField.add(new JLabel("Width"));
        widthField.add(readW);

        heightField.add(new JLabel("Height"));
        heightField.add(readH);

        row1.add(widthField);
        row1.add(heightField);

        win.add(row1);
        win.add(submit);

        win.setPreferredSize(new Dimension(Consts.READ_PARAMS_WIN_W, Consts.READ_PARAMS_WIN_H));
        win.setMinimumSize(new Dimension(Consts.READ_PARAMS_WIN_W, Consts.READ_PARAMS_WIN_H));


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int w = Integer.parseInt(readW.getText());
                int h = Integer.parseInt(readH.getText());
                world = new World(w,h);

                Simulation sim1 = new Simulation(w, h, world);
                sim1.startSimulation();

                Main.getInstance().getFrame().requestFocusInWindow();
            }
        });
    }

    public JPanel getWin(){
        return win;
    }
}
