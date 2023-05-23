package MainPackage.Windows;

import MainPackage.Consts;
import MainPackage.Main;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class DialogWindow {
    String[] logs;
    JPanel win;
    java.util.ArrayList<Pair<Color, Color>> colors;

    private static DialogWindow instance;

    private DialogWindow() {
        win = new JPanel();
        win.setLayout(new BoxLayout(win, BoxLayout.Y_AXIS));
        win.setFont(new Font(Consts.FONT_NAME, Font.PLAIN, Consts.DIALOG_FONT_SIZE));
        win.setBackground(Consts.BACKGROUND_COL);
        win.setBorder(new LineBorder(Color.WHITE));

        logs = new String[Consts.MAX_LOGS];
        colors = new ArrayList<>();
        Pair<Color, Color> refColors = new Pair<>(Color.WHITE, Color.WHITE);

        for (int i = 0; i < Consts.MAX_LOGS; i++){
            String s = " ";
            logs[i] = s;
            colors.add(refColors);
        }

        reloadLogs();
    }

    public static DialogWindow getInstance(){
        if (instance == null)
            instance = new DialogWindow();
        return instance;
    }

    public void addMsg(String msg){
        for(int i = Consts.MAX_LOGS-1; i > 0; i--)
            logs[i] = logs[i - 1];

        logs[0] = msg;
        colors.add(0, new Pair<>(Color.WHITE, Color.WHITE));
    }

    public void addMsg(String msg, Color fg){
        for(int i = Consts.MAX_LOGS-1; i > 0; i--)
            logs[i] = logs[i - 1];

        logs[0] = msg;
        colors.add(0, new Pair<>(fg, Consts.BACKGROUND_COL));
    }

    public void reloadLogs(){
        win.removeAll();
        JLabel label;
        int i = 0;

        for (String s : logs){
            label = new JLabel(s);
            label.setForeground(colors.get(i).getKey());
            label.setBackground(colors.get(i).getValue());
            win.add(label);
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, Consts.DIALOG_FONT_SIZE));
            i++;
        }
        win.repaint();
    }

    public void clearMsgs(){
        for (int i = 0; i < Consts.MAX_LOGS; i++){
            logs[i]=" ";
        }
        reloadLogs();
    }

    public JPanel getWin(){
        return win;
    }
}
