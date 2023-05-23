package MainPackage.Organisms.Animals;

import MainPackage.Consts;
import MainPackage.World;

public class Wolf extends Animal {
    public Wolf(int x, int y, World w){
        super("Wolf",x, y, 9, 5,w);
    }
    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.WOLF_COLOR);
    }
}
