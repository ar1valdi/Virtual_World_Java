package MainPackage.DataStructs;

import MainPackage.Consts;
import MainPackage.Exceptions.InvalidType;
import MainPackage.Main;
import MainPackage.Organisms.Animals.*;
import MainPackage.Organisms.Organism;
import MainPackage.Organisms.Plants.*;
import MainPackage.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FieldOnMap {
    private JButton button;
    private Organism org;
    Coords pos;
    World wrd;

    public FieldOnMap(int x, int y, World w){
        button = new JButton();
        org = Consts.EMPTY_SPACE;
        pos = new Coords(x, y);
        wrd = w;

        Dimension size = new Dimension(Consts.CELL_SIZE, Consts.CELL_SIZE);

        button.setBackground(Color.BLACK);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (org != Consts.EMPTY_SPACE)
                    return;

                Object selectedAnim = JOptionPane.showInputDialog(null,
                        "Choose organism: ", "Input",
                        JOptionPane.INFORMATION_MESSAGE, null,
                        Consts.ORGANISMS_NAMES, Consts.ORGANISMS_NAMES[0]);

                if (selectedAnim == null)
                    return;

                String newName = selectedAnim.toString();

                Organism newOrg;
                if (newName.equals("Sheep"))
                    newOrg = new Sheep(pos.x, pos.y, wrd);
                else if (newName.equals("Wolf"))
                    newOrg = new Wolf(pos.x, pos.y, wrd);
                else if (newName.equals("Fox"))
                    newOrg = new Fox(pos.x, pos.y, wrd);
                else if (newName.equals("Antelope"))
                    newOrg = new Antelope(pos.x, pos.y, wrd);
                else if (newName.equals("Turtle"))
                    newOrg = new Turtle(pos.x, pos.y, wrd);
                else if (newName.equals("Grass"))
                    newOrg = new Grass(pos.x, pos.y, wrd);
                else if (newName.equals("Dandelion"))
                    newOrg = new Dandelion(pos.x, pos.y, wrd);
                else if (newName.equals("Guarana"))
                    newOrg = new Guarana(pos.x, pos.y, wrd);
                else if (newName.equals("Nightshade"))
                    newOrg = new Nightshade(pos.x, pos.y, wrd);
                else if (newName.equals("Pine Borsch"))
                    newOrg = new PineBorsch(pos.x, pos.y, wrd);
                else {
                    try {throw new InvalidType();}
                    catch(InvalidType exc) {throw new RuntimeException(exc);}
                }
                wrd.addOrganism(newOrg);
                newOrg.draw();

                Main.getInstance().getFrame().requestFocusInWindow();
            }
        });
    }

    public Organism getOrg(){
        return org;
    }

    public void setOrg(Organism val){
        org = val;
    }

    public JButton getBtn(){
        return button;
    }
}
