package MainPackage.Organisms.Animals;

import MainPackage.Organisms.Organism;
import MainPackage.World;
import MainPackage.Consts;

import java.util.Random;

public class Turtle extends Animal {
    public Turtle(int x, int y, World w){
        super("Turtle",x, y, 2, 1,w);
    }

    @Override
    public void draw(){
        world.addToDrawMap(this, Consts.TURTLE_COLOR);
    }

    @Override
    public void action() {
        int chance = new Random().nextInt(100);
        if (chance <= 75)
            return;

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
    public short reflected(Organism attacker) {
        if (attacker.getStrengh() < 5)
            return Consts.REFLECTED_MOVE;

        if (this.strength > attacker.getStrengh())
            return Consts.REFLECTED_KILL;

        return Consts.NOT_REFLECTED;
    }

}
