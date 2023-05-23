package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.Organisms.Organism;
import MainPackage.World;

public class Guarana extends Plant {
    public Guarana(int x, int y, World w) {
        super("Guarana", x, y, 0, w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.GUARANA_COLOR);
    }

    @Override
    public void collision(Organism withWho) {
        withWho.modifyStrength(3);
        killByAnim();
    }
}
