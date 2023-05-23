package MainPackage.Organisms.Plants;

import MainPackage.Consts;
import MainPackage.DataStructs.Coords;
import MainPackage.Exceptions.InvalidType;
import MainPackage.Organisms.Animals.Animal;
import MainPackage.Organisms.Organism;
import MainPackage.World;

import java.util.ArrayList;

public class PineBorsch extends Plant {
    public PineBorsch(int x, int y, World w){
        super("Pine_Borsch", x, y, 10, w);
    }

    @Override
    public void draw() {
        world.addToDrawMap(this, Consts.PINE_BORSCH_COLOR);
    }

    @Override
    public void action() {
        try { expand(); }
        catch (InvalidType e) { throw new RuntimeException(e); }
        killInRadius();
    }

    @Override
    public void collision(Organism withWho) {
        killByAnim();
        withWho.killByPlant();
    }

    @Override
    public void actionDoneAlways() {
        killInRadius();
    }

    private void killInRadius() {
        ArrayList<Coords> sides = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if(i == 0 && j == 0)
                    continue;

                sides.add(new Coords(pos.x + i, pos.y + j));

            }
        }

        for (Coords side : sides) {
            if (side.x >= 0 && side.x < world.getWidth() && side.y >= 0 && side.y < world.getHeight()) {
                if (world.getField(side.x, side.y) != Consts.EMPTY_SPACE) {

                    Organism toKill = world.getField(side.x, side.y);
                    if (toKill instanceof Animal)
                        toKill.killByPlant();

                }
            }
        }
    }
}
