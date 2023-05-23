package MainPackage.Organisms.Animals;

import MainPackage.DataStructs.Coords;
import MainPackage.Organisms.Organism;
import MainPackage.Windows.DialogWindow;
import MainPackage.World;
import MainPackage.Consts;

import java.util.Random;

public class Human extends Animal {
    private Coords direction;
    private short spellCD;

    private void updateSpellCD(){
        if (spellCD != 0)
            spellCD--;
    }

    private Organism moveAndCollCheck(int x, int y){
        moveTo(x,y);
        return world.checkCollision(this);
    }

    public Human(int x, int y, World w) {
        super("Human", x, y, 5, 4, w);
        direction = new Coords(1,0);
        spellCD = 0;
    }

    public void setSpellCD(short n){
        spellCD = n;
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.HUMAN_COLOR);
    }

    public void useSpell() {
        if (spellCD == 0) {
            DialogWindow.getInstance().addMsg("Spell activated!", Consts.COMMANDS_COLOR);
            DialogWindow.getInstance().reloadLogs();
            spellCD = 10;
        }
    }

    @Override
    public void action() {
        world.setField(pos.x, pos.y, Consts.EMPTY_SPACE);
        if (pos.y + direction.y >= 0 && pos.y + direction.y < world.getHeight() &&
                pos.x + direction.x >= 0 && pos.x + direction.x < world.getWidth()) {

            updatePrevPos();
            Organism collided = moveAndCollCheck(pos.x + direction.x, pos.y + direction.y);

            if (collided != null && (collided instanceof Animal || spellCD <= 5))
                collision(collided);

		    else if (spellCD > 7) {
                if (pos.y + direction.y >= 0 && pos.y + direction.y < world.getHeight() &&
                        pos.x + direction.x >= 0 && pos.x + direction.x < world.getWidth()) {

                    updatePrevPos();
                    moveTo(pos.x + direction.x, pos.y + direction.y);
                }
                collided = world.checkCollision(this);

                if (collided != null)
                    collision(collided);
            }

            else if (spellCD > 5) {
                int chance = new Random().nextInt(100);
                if (chance <= 50) {
                    if (pos.y + direction.y >= 0 && pos.y + direction.y < world.getHeight() &&
                            pos.x + direction.x >= 0 && pos.x + direction.x < world.getWidth()) {

                        updatePrevPos();
                        moveTo(pos.x + direction.x, pos.y + direction.y);
                    }
                }
                collided = world.checkCollision(this);

                if (collided != null)
                    collision(collided);
            }
        }

        updateSpellCD();
        if (pos.x != -1)
            world.setField(pos.x, pos.y, this);
    }

    public void handleKeys(int in) {
        if (in == Consts.UP) {
            direction = new Coords(0, -1);
            System.out.println("UP");
        }
        else if (in == Consts.DOWN) {
            direction = new Coords(0,1);
            System.out.println("DOWN");
        }
        else if (in == Consts.RIGHT) {
            direction = new Coords(1,0);
        }
        else if (in == Consts.LEFT) {
            direction = new Coords(-1, 0);
        }
        else if (in == Consts.SPELL){
            useSpell();
        }
    }

    public short getSpellCD() {
        return spellCD;
    }

    @Override
    public void onKilled() {
        world.setHumanKilled();
    }
}
