package MainPackage.Organisms;

import MainPackage.DataStructs.Coords;
import MainPackage.Windows.DialogWindow;
import MainPackage.World;

import java.awt.*;

import static MainPackage.Consts.EMPTY_SPACE;

abstract public class Organism {
    protected boolean turnBlock;
    protected String name;
    protected int strength, initiative;
    protected Coords pos;
    protected World world;

    public Organism(String n, int x, int y, int strength, int init, World w){
        name = n;
        pos = new Coords(x,y);
        this.strength = strength;
        initiative = init;
        world = w;
        turnBlock = false;
    }

    public abstract void collision(Organism withWho);
    public abstract void action();
    public abstract void draw();
    public abstract void onKilled();

    public int getStrengh(){
        return strength;
    }
    public int getInit(){
        return initiative;
    }
    public String getName(){
        return name;
    }
    public Coords getPos(){
        return pos;
    }
    public void blockTurn(){
        turnBlock = true;
    }
    public void unblockTurn(){
        turnBlock = false;
    }
    public boolean canMakeTurn(){
        return !turnBlock;
    }
    public void modifyStrength(int modifyBy){
        strength += modifyBy;
    }
    public void killByAnim(){
        blockTurn();
        pos.x = -1;
        pos.y = -1;

        DialogWindow.getInstance().addMsg(this.name + " killed!", Color.RED);
    }
    public  void killByPlant(){
        blockTurn();
        Coords tempPos = new Coords(pos.x, pos.y);
        pos.x = -1;
        pos.y = -1;

        world.setField(tempPos.x, tempPos.y, EMPTY_SPACE);

        DialogWindow.getInstance().addMsg(this.name + " killed!", Color.RED);
    }
    public void actionDoneAlways(){}
}
