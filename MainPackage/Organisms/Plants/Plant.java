package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.DataStructs.Coords;
import MainPackage.Organisms.Organism;
import MainPackage.Windows.DialogWindow;
import MainPackage.Exceptions.InvalidType;
import MainPackage.World;

import java.util.Random;

public abstract class Plant extends Organism {
    protected Plant(String name, int x, int y, int s, World w) {
        super(name, x, y, s,0,w);
    }

    public void expand() throws InvalidType {
        if (new Random().nextInt(100) > Consts.EXPAND_CHANCE)
            return;

        boolean[] checked = {false,false,false,false};
        Coords[] sides = { new Coords(0,1), new Coords(0,-1), new Coords(-1, 0), new Coords(1,0) };
        Coords newPos = null;

        // find accurate place for new plant
        boolean foundValid = false;
        int side;

        for (int i = 0; i < 4; i++) {
            while (true) {
                side = new Random().nextInt(4);
                if (!checked[side]) {
                    newPos = new Coords( pos.x + sides[side].x, pos.y + sides[side].y );
                    if (newPos.x >= 0 && newPos.x < world.getWidth() && newPos.y >= 0 && newPos.y < world.getHeight() && world.getField(newPos.x, newPos.y) == null) {
                        foundValid = true;
                        i = 10;
                        break;
                    }
                    checked[side] = true;
                    break;
                }
            }
        }


        if (!foundValid) {
            return;
        }

        // add new plant
        Organism newOrg;
        if (this instanceof Grass)
            newOrg = new Grass(newPos.x, newPos.y, world);
        else if (this instanceof Dandelion)
            newOrg = new Dandelion(newPos.x, newPos.y, world);
        else if (this instanceof Guarana)
            newOrg = new Guarana(newPos.x, newPos.y, world);
        else if (this instanceof Nightshade)
            newOrg = new Nightshade(newPos.x, newPos.y, world);
        else if (this instanceof PineBorsch)
            newOrg = new PineBorsch(newPos.x, newPos.y, world);
        else {
            throw new InvalidType("Invalid type in plant expansion: " + this.name);
        }

        world.addOrganism(newOrg);
        newOrg.blockTurn();
    }

    @Override
    public void action() {
        try {
            expand();
        } catch (InvalidType e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void collision(Organism withWho) {
        DialogWindow.getInstance().addMsg(this.name + " eaten!");
        killByAnim();
    }

    @Override
    public void onKilled(){}
}
