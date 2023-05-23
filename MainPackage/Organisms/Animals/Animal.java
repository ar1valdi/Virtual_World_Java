package MainPackage.Organisms.Animals;

import MainPackage.Consts;
import MainPackage.DataStructs.Coords;
import MainPackage.Exceptions.InvalidType;
import MainPackage.Organisms.Organism;
import MainPackage.Organisms.Plants.Plant;
import MainPackage.Windows.DialogWindow;
import MainPackage.World;

import java.awt.*;
import java.util.Random;

abstract public class Animal extends Organism {
    protected Coords prevPos;

    @Override
    public void action() {
        world.setField(pos.x, pos.y, Consts.EMPTY_SPACE);
        updatePrevPos();

        moveRandomly();

        Organism collided = world.checkCollision(this);
        if (collided != null)
            collision(collided);

        if (pos.x != -1)
            world.setField(pos.x, pos.y, this);
    }
    @Override
    public void collision(Organism withWho) {
        if (getName().equals(withWho.getName())) {
            moveBack();
            world.setField(pos.x, pos.y, this);

            try {
                breed(withWho);
            } catch (InvalidType e) {
                throw new RuntimeException(e);
            }

            withWho.blockTurn();
        }
        else if (withWho instanceof Animal) {
            attack(withWho);
        }
        else if (withWho instanceof Plant) {
            withWho.collision(this);
        }
    }
    @Override
    public void onKilled(){}
    protected Animal(String name, int x, int y, int strength, int initiative, World world) {
        super(name, x, y, strength, initiative, world);
        prevPos = new Coords(pos.x, pos.y);
    }
    protected void breed(Organism withWho) throws InvalidType {

        DialogWindow dialog = DialogWindow.getInstance();
        boolean foundSpace = false;
        Coords newPos = new Coords(0,0);
        Coords from = new Coords(pos.x, pos.y);

        Coords[] directions = new Coords[4];
        directions[0] = new Coords(-1, 0);
        directions[1] = new Coords(1, 0);
        directions[2] = new Coords(0, 1);
        directions[3] = new Coords(0, -1);

        for (Coords dir : directions) {
            int x = from.x + dir.x;
            int y = from.y + dir.y;
            if (x >= 0 && x < world.getWidth() && y >= 0 && y < world.getHeight() && world.getField(x,y) == Consts.EMPTY_SPACE) {
                newPos = new Coords(x,y);
                foundSpace = true;
                break;
            }
        }

        if (!foundSpace) {
            from = withWho.getPos();
            for (Coords dir : directions) {
                int x = from.x + dir.x;
                int y = from.y + dir.y;
                if (x >= 0 && x < world.getWidth() && y >= 0 && y < world.getHeight() && world.getField(x,y) == Consts.EMPTY_SPACE) {
                    newPos = new Coords(x,y);
                    foundSpace = true;
                    break;
                }
            }
        }

        if (!foundSpace)
            return;

        Organism newOrg;
        if (this instanceof Sheep)
            newOrg = new Sheep(newPos.x, newPos.y, world);
        else if (this instanceof Wolf)
            newOrg = new Wolf(newPos.x, newPos.y, world);
        else if (this instanceof Fox)
           newOrg = new Fox(newPos.x, newPos.y, world);
        else if (this instanceof Turtle)
            newOrg = new Turtle(newPos.x, newPos.y, world);
        else if (this instanceof Antelope)
            newOrg = new Antelope(newPos.x, newPos.y, world);
        else
            throw new InvalidType();

        newOrg.blockTurn();
        world.addOrganism(newOrg);
        dialog.addMsg(this.name + " breeded! (" + newPos.x + ", " + newPos.y + ")", Color.GREEN);
    }

    protected void moveRandomly() {
        Random random = new Random();
        boolean[] checked = new boolean[4];
        checked[0] = false;
        checked[1] = false;
        checked[2] = false;
        checked[3] = false;
        //0 - left, 1 - right, 2 - up, 3 - down

        boolean foundValid = false;
        while(!foundValid && !(checked[0] && checked[1] && checked[2] && checked[3])){
            int dir = random.nextInt(4);
            switch (dir) {
                case 0:
                    if (pos.x - 1 < 0) {
                        checked[0] = true;
                        break;
                    }
                    foundValid = true;
                    moveTo(pos.x - 1, pos.y);
                    break;
                case 1:
                    if (pos.x + 1 >= world.getWidth()) {
                        checked[1] = true;
                        break;
                    }
                    foundValid = true;
                    moveTo(pos.x + 1, pos.y);
                    break;
                case 2:
                    if (pos.y - 1 < 0) {
                        checked[2] = true;
                        break;
                    }
                    foundValid = true;
                    moveTo(pos.x, pos.y - 1);
                    break;
                case 3:
                    if (pos.y + 1 >= world.getHeight()) {
                        checked[3] = true;
                        break;
                    }
                    foundValid = true;
                    moveTo(pos.x, pos.y + 1);
                    break;
            }
        }
    }
    protected void updatePrevPos() {
        prevPos = new Coords(pos.x, pos.y);
    }
    protected void moveBack() {
        moveTo(prevPos.x, prevPos.y);
    }
    protected void moveTo(int x, int y) {
        pos = new Coords(x,y);
    }
    protected void attack(Organism who) {
        DialogWindow.getInstance().addMsg(this.name + " attacks " + who.getName() + "!", Color.YELLOW);
        if (who instanceof Animal) {
            Organism loser;
            short result = ((Animal) who).reflected(this);

            if (result == Consts.REFLECTED_KILL)
                loser = this;
            else if (result == Consts.NOT_REFLECTED)
                loser = who;
            else if (result == Consts.RUNNED_AWAY)
                return;
            else {	// REFLECTED_MOVE
                moveBack();
                return;
            }

            loser.killByAnim();
        }
    }
    protected short reflected(Organism attacker) {
        if (attacker.getStrengh() < this.getStrengh())
            return Consts.REFLECTED_KILL;

        return Consts.NOT_REFLECTED;
    }
    public Coords getPrevPos() {
        return prevPos;
    }
}
