package MainPackage.Organisms.Animals;

import MainPackage.Consts;
import MainPackage.Organisms.Organism;
import MainPackage.World;
import javafx.util.Pair;

import java.util.ArrayList;

public class Fox extends Animal {
    public Fox(int x, int y, World w) {
        super("Fox",x, y, 3, 7,w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.FOX_COLOR);
    }

    @Override
    public void moveRandomly(){

        int walls = 0;
        if (pos.x == 0)
            walls++;
        if (pos.y == 0)
            walls++;
        if (pos.x == world.getWidth() - 1)
            walls++;
        if (pos.y == world.getHeight() - 1)
            walls++;

        super.moveRandomly();
        Organism a = world.checkCollision(this);

        ArrayList<Pair<Integer, Integer>> alreadyTried = new ArrayList<>();
        short tries = 1;
        Pair<Integer, Integer> helper;

        while (a instanceof Animal && a.getStrengh() <= this.getStrengh()) {

            if (tries >= 4 - walls) {
                moveBack();
                break;
            }

            alreadyTried.add(new Pair<>(pos.x, pos.y));

            do {
                moveBack();
                super.moveRandomly();
                helper = new Pair<>(pos.x, pos.y);
            } while (isInArray(helper, alreadyTried));
            a = world.checkCollision(this);

            tries++;
        }
    }

    private boolean isInArray(Pair<Integer, Integer> targetPair, ArrayList<Pair<Integer, Integer>> list){
        for (Pair<Integer, Integer> pair : list) {
            if (pair.equals(targetPair)) {
                return true;
            }
        }
        return false;
    }
}
