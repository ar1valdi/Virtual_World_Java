package MainPackage.Organisms.Animals;

import MainPackage.Consts;
import MainPackage.World;

public class Sheep extends Animal {
    public Sheep(int x, int y, World w){
        super("Sheep",x, y, 4, 4,w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.SHEEP_COLOR);
    }
}
