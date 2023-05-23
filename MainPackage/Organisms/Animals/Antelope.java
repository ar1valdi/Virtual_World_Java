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

import static MainPackage.Consts.EMPTY_SPACE;

public class Antelope extends Animal{
    private Coords ogPos;

   public Antelope(int x, int y, World w) {
       super("Antelope",x, y, 4,4,w);
   }

   @Override
    public void draw() {
        world.addToDrawMap(this, Consts.ANTELOPE_COLOR);
    }

    @Override
    public void action() {
        ogPos = new Coords(pos.x, pos.y);
        world.setField(pos.x, pos.y, Consts.EMPTY_SPACE);
        updatePrevPos();
        moveRandomly();

        Organism collided = world.checkCollision(this);

        if (collided instanceof Animal) {
            collision(collided);
        }
        else {
            int deltaX = pos.x - prevPos.x;
            int deltaY = pos.y - prevPos.y;
            updatePrevPos();

            if (deltaY == 1 && pos.y + 1 < world.getHeight())
                moveTo(pos.x, pos.y + 1);
            else if (deltaY == -1 && pos.y - 1 >= 0)
                moveTo(pos.x, pos.y - 1);
            else if (deltaX == 1 && pos.x + 1 < world.getWidth())
                moveTo(pos.x + 1, pos.y);
            else if (deltaX == -1 && pos.x - 1 >= 0)
                moveTo(pos.x - 1, pos.y);

            collided = world.checkCollision(this);
            if (collided != null)
                collision(collided);
        }

        if (pos.x != -1)
            world.setField(pos.x, pos.y, this);
    }

    @Override
    public void collision(Organism withWho) {
        if (getName().equals(withWho.getName())) {
            moveBack();

            // antelope can 'jump over' flowers so
            // when it goes back onto it, it has to be eaten
            Organism collided = world.checkCollision(this);

            if(collided != null)
                collision(collided);

            if(pos.x != -1)
                //moveTo(ogPos.x, ogPos.y);
                world.setField(pos.x, pos.y, this);

            try {
                breed(withWho);
            } catch (InvalidType e) {
                throw new RuntimeException(e);
            }

            withWho.blockTurn();
        }
        else if (withWho instanceof Animal) {
            int chance = new Random().nextInt(100);
            if (chance > 50) {
                attack(withWho);
            }
            else {
                if (!moveToSafePlace())
                    attack(withWho);
            }
        }
	    else if (withWho instanceof Plant) {
            withWho.collision(this);
        }
    }

    @Override
    public void attack(Organism who) {
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
                moveTo(ogPos.x, ogPos.y);
                return;
            }
            loser.killByAnim();
        }
    }

    private boolean moveToSafePlace() {
        boolean escaped = false;
        if (pos.y + 1 < world.getHeight() && world.getField(pos.x, pos.y + 1) == EMPTY_SPACE) {
            updatePrevPos();
            escaped = true;
            moveTo(pos.x, pos.y + 1);
        }
        else if (pos.y - 1 >= 0 && world.getField(pos.x, pos.y - 1) == EMPTY_SPACE) {
            updatePrevPos();
            escaped = true;
            moveTo(pos.x, pos.y - 1);
        }
        else if (pos.x + 1 < world.getWidth() && world.getField(pos.x + 1, pos.y) == EMPTY_SPACE) {
            updatePrevPos();
            escaped = true;
            moveTo(pos.x + 1, pos.y);
        }
        else if (pos.x - 1 >= 0 && world.getField(pos.x - 1, pos.y) == EMPTY_SPACE) {
            updatePrevPos();
            escaped = true;
            moveTo(pos.x - 1, pos.y);
        }

        if (escaped) {
            DialogWindow.getInstance().addMsg("Antelope has moved to safe place!", Color.YELLOW);
            world.setField(pos.x, pos.y, this);
            return true;
        }

        DialogWindow.getInstance().addMsg("Antelope couldn't find safe place!", Color.YELLOW);
        return false;
    }

    @Override
    protected short reflected(Organism attacker) {
        int chance = new Random().nextInt(100);

        if (chance > 50) {
            if (moveToSafePlace()) {
                return Consts.RUNNED_AWAY;
            }
        }

        if (this.strength > attacker.getStrengh())
            return Consts.REFLECTED_KILL;

        return Consts.NOT_REFLECTED;
    }
}
