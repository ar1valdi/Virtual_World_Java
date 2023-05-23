package MainPackage.Windows;

import MainPackage.Consts;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Legend {
    JPanel win;
    private static Legend instance;

    private Legend(){
        win = new JPanel();
        win.setFont(new Font(Consts.FONT_NAME, Font.PLAIN, Consts.LEGEND_FONT_SIZE));
        win.setBackground(Consts.BACKGROUND_COL);
        win.setLayout(new GridLayout(6,2));

        createLegend();
    }

    private void createLegend(){
        Label mainLabel = new Label("Legend");
        mainLabel.setForeground(Consts.FOREGROUND_COL);
        mainLabel.setForeground(Consts.BACKGROUND_COL);
        win.add(mainLabel);
        win.setBorder(new LineBorder(Color.WHITE));

        Pair<String, Color>[] legendPoss = new Pair[11];
        legendPoss[0] = new Pair<>("    Sheep", Consts.SHEEP_COLOR);
        legendPoss[1] = new Pair<>("    Wolf", Consts.WOLF_COLOR);
        legendPoss[2] = new Pair<>("    Fox", Consts.FOX_COLOR);
        legendPoss[3] = new Pair<>("    Antelope", Consts.ANTELOPE_COLOR);
        legendPoss[4] = new Pair<>("    Turtle", Consts.TURTLE_COLOR);
        legendPoss[5] = new Pair<>("    Human", Consts.HUMAN_COLOR);
        legendPoss[6] = new Pair<>("    Grass", Consts.GRASS_COLOR);
        legendPoss[7] = new Pair<>("    Dandelion", Consts.DANDELION_COLOR);
        legendPoss[8] = new Pair<>("    Guarana", Consts.GUARANA_COLOR);
        legendPoss[9] = new Pair<>("    Nightshade", Consts.NIGHTSHADE_COLOR);
        legendPoss[10] = new Pair<>("    Pine Borsch", Consts.PINE_BORSCH_COLOR);

        for (Pair<String, Color> p : legendPoss){
            Dimension sqDim = new Dimension(Consts.CELL_SIZE, Consts.CELL_SIZE);
            JPanel panel = new JPanel();
            JPanel square = new JPanel();

            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setBackground(Consts.BACKGROUND_COL);

            square.setBackground(p.getValue());
            square.setPreferredSize(sqDim);
            square.setMaximumSize(sqDim);
            square.setPreferredSize(sqDim);

            Label l = new Label(p.getKey());
            l.setForeground(p.getValue());

            panel.add(square);
            panel.add(l);
            win.add(panel);

            Dimension winDim = new Dimension(Consts.LOGS_LEGEND_WIDTH, (int)Consts.LEGEND_HEIGHT);
            win.setPreferredSize(winDim);
            win.setMinimumSize(winDim);
            win.setMaximumSize(winDim);
        }
    }

    public static Legend getInstance(){
        if (instance == null)
            instance = new Legend();
        return instance;
    }

    public JPanel getWin(){
        return win;
    }
}
