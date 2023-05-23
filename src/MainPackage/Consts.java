package MainPackage;

import MainPackage.Organisms.Organism;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Consts {
    public static final boolean SKIP_READING=false;
    public static final int DEBUG_H=40;
    public static final int DEBUG_W=40;
    public static final int READ_PARAMS_WIN_H=500;
    public static final int READ_PARAMS_WIN_W=500;
    public static final int CELL_SIZE=20;
    public static final int BUTTON_HEIGHT=50;

    public static final int MAX_LOGS=30;
    public static final int LOGS_LEGEND_WIDTH=500;
    public static final int DIALOG_FONT_SIZE=20;
    public static final int BORDER_SIZE=20;
    public static final String FONT_NAME="Arial";
    public static final int LEGEND_FONT_SIZE=20;
    public static final int LEGEND_HEIGHT=(int)(CELL_SIZE * 6 * 1.5);
    public static final int LOGS_HEIGHT=(int)(CELL_SIZE * MAX_LOGS * 1.5);
    public static final int MIN_BUTTONS_SIZE = 200;

    public static final Color BACKGROUND_COL=Color.BLACK;
    public static final Color FOREGROUND_COL=Color.WHITE;
    public static final Organism EMPTY_SPACE=null;

    public static final int REFLECTED_KILL=1;
    public static final int NOT_REFLECTED=2;
    public static final int REFLECTED_MOVE=3;
    public static final int RUNNED_AWAY=4;

    public static final Color SHEEP_COLOR=Color.WHITE;
    public static final Color WOLF_COLOR=Color.DARK_GRAY;
    public static final Color FOX_COLOR=Color.ORANGE;
    public static final Color ANTELOPE_COLOR=Color.YELLOW;
    public static final Color TURTLE_COLOR=Color.CYAN;
    public static final Color HUMAN_COLOR=Color.BLUE;
    public static final Color GRASS_COLOR=Color.GREEN;
    public static final Color DANDELION_COLOR=Color.MAGENTA;
    public static final Color GUARANA_COLOR=Color.PINK;
    public static final Color NIGHTSHADE_COLOR=new Color(113, 35, 229);
    public static final Color PINE_BORSCH_COLOR=Color.RED;

    public static final Color COMMANDS_COLOR=Color.CYAN;
    public static final Color ERROR_COLOR=Color.CYAN;

    public static final boolean SPAWN_HUMAN=        true;
    public static final int SHEEP_NUMBER=           0;
    public static final int WOLF_NUMBER=            0;
    public static final int FOX_NUMBER=             0;
    public static final int ANTELOPE_NUMBER=        0;
    public static final int TURTLE_NUMBER=          0;
    public static final int GRASS_NUMBER=           0;
    public static final int DANDELION_NUMBER=       0;
    public static final int GUARANA_NUMBER=         0;
    public static final int NIGHTSHADE_NUMBER=      0;
    public static final int PINE_BORSCH_NUMBER=     0;

    public static final int EXPAND_CHANCE=50;

    public static final int UP=     KeyEvent.VK_UP;
    public static final int DOWN=   KeyEvent.VK_DOWN;
    public static final int LEFT=   KeyEvent.VK_LEFT;
    public static final int RIGHT=  KeyEvent.VK_RIGHT;
    public static final int SPELL=  KeyEvent.VK_SPACE;

    public static final Color BORDER_COLOR = Color.WHITE;

    public static final String[] ORGANISMS_NAMES = { "Sheep", "Wolf", "Fox",
            "Antelope", "Turtle", "Grass",
            "Dandelion", "Guarana", "Nightshade",
            "Pine Borsch" };
}
