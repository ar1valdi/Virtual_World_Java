package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.Exceptions.InvalidType;
import MainPackage.World;

public class Dandelion extends Plant{
    public Dandelion(int x, int y, World w) {
        super("Dandelion",x, y, 0,w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.DANDELION_COLOR);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++) {
            try {
                expand();
            } catch (InvalidType e) {
                throw new RuntimeException(e);
            }
        }
    }
}
