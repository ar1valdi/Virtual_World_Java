package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.World;

public class Grass extends Plant {
    public Grass(int x, int y, World w) {
        super("Grass", x, y, 0, w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.GRASS_COLOR);
    }
}
