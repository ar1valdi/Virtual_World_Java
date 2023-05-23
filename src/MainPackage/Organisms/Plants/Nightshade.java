package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.Organisms.Organism;
import MainPackage.World;

public class Nightshade extends Plant{
    public Nightshade(int x, int y, World w) {
        super("Nightshade", x, y, 99, w);
    }

    @Override
    public void draw()  {
        world.addToDrawMap(this, Consts.NIGHTSHADE_COLOR);
    }

    @Override
    public void collision(Organism withWho) {
        killByAnim();
        withWho.killByPlant();
    }
}
