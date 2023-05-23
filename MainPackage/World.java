package MainPackage;

import MainPackage.DataStructs.Coords;
import MainPackage.DataStructs.FieldOnMap;
import MainPackage.Exceptions.InvalidType;
import MainPackage.Exceptions.PosOutOfMap;
import MainPackage.Exceptions.SpawnOnTakenSpace;
import MainPackage.Organisms.*;
import MainPackage.Organisms.Animals.*;
import MainPackage.Organisms.Plants.*;
import MainPackage.Windows.DialogWindow;
import MainPackage.Windows.SimulationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static MainPackage.Consts.EMPTY_SPACE;

public class World {
    private int worldH, worldW;
    private int numOfOrganisms;
    private Human human;
    private final ArrayList<Organism> org;
    FieldOnMap[][] orgOnMap;

    public World(int w, int h){
        this.worldH = h;
        this.worldW = w;
        human = null;
        numOfOrganisms = 0;
        org = new ArrayList<>();

        // creating board array
        orgOnMap = new FieldOnMap[w][h];
        for(int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                orgOnMap[i][j] = new FieldOnMap(i, j, this);
            }
        }
    }

    private Coords randomFreePosition() {
        Random random = new Random();

        if (numOfOrganisms == worldW * worldH)
            return new Coords(-1,-1);

        boolean foundPlace = false;
        Coords newPos = new Coords(0,0);
        while (!foundPlace) {
            newPos.x = random.nextInt(worldW);
            newPos.y = random.nextInt(worldH);
            if (orgOnMap[newPos.x][newPos.y].getOrg() == EMPTY_SPACE)
                foundPlace = true;
        }
        return newPos;
    }

    public void setField(int x, int y, Organism val) {
        if (x != -1 && y != -1)
            orgOnMap[x][y].setOrg(val);
    }

    public int getWidth() {
        return worldW;
    }

    public int getHeight() {
        return worldH;
    }

    public Organism getField(int x, int y) {
        return orgOnMap[x][y].getOrg();
    }

    public void addOrganism(Organism o) {
        boolean added = false;
        Coords p = new Coords(o.getPos().x, o.getPos().y);

        try {
            if (p.x < 0 || p.x >= worldW || p.y < 0 || p.y >= worldH)
                throw new PosOutOfMap();
            if (orgOnMap[p.x][p.y].getOrg() != Consts.EMPTY_SPACE)
                throw new SpawnOnTakenSpace();
        } catch (PosOutOfMap | SpawnOnTakenSpace exc){
            throw new RuntimeException(exc);
        }

        if (numOfOrganisms == 0) {
            org.add(o);
            numOfOrganisms++;
            orgOnMap[o.getPos().x][o.getPos().y].setOrg(o);
            return;
        }

        for (int i = 0; i < numOfOrganisms; i++){
            if (org.get(i).getInit() < o.getInit()) {
                org.add(i, o);
                added = true;
                break;
            }
        }

        // if the organism has the lowest initiative and has not been added
        if (!added) {
            org.add(o);
        }

        orgOnMap[o.getPos().x][o.getPos().y].setOrg(o);
        numOfOrganisms++;
    }

    public void nextTurn(){
        for (int i = 0; i < numOfOrganisms; i++) {
            if (org.get(i).canMakeTurn())
                org.get(i).action();
            else
                org.get(i).actionDoneAlways();
        }

        for (int i = 0; i < numOfOrganisms; i++)
            org.get(i).unblockTurn();

        deleteKilled();

        DialogWindow.getInstance().addMsg("Next Turn!", Consts.COMMANDS_COLOR);
        drawWorld();

        System.out.println(org.get(0).getPos().x + " " + org.get(0).getPos().y);
    }

    public void addToDrawMap(Organism o, Color color) {
        orgOnMap[o.getPos().x][o.getPos().y].getBtn().setBackground(color);
    }

    public void spawnDefAnimals() {
        Coords newPos;

        // Sheep
        for (int i = 0; i < Consts.SHEEP_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Sheep(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }
        // Wolf
        for (int i = 0; i < Consts.WOLF_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Wolf(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Fox
        for (int i = 0; i < Consts.FOX_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Fox(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Antelope
        for (int i = 0; i < Consts.ANTELOPE_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Antelope(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }
        // Turtle
        for (int i = 0; i < Consts.TURTLE_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Turtle(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Grass
        for (int i = 0; i < Consts.GRASS_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Grass(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Dandelion
        for (int i = 0; i < Consts.DANDELION_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Dandelion(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Guarana
        for (int i = 0; i < Consts.GUARANA_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Guarana(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Nightshade
        for (int i = 0; i < Consts.NIGHTSHADE_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new Nightshade(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Pine Borsch
        for (int i = 0; i < Consts.PINE_BORSCH_NUMBER; i++) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            Organism toAdd = new PineBorsch(newPos.x, newPos.y, this);
            addOrganism(toAdd);
            toAdd.draw();
        }

        // Human
        if (Consts.SPAWN_HUMAN) {
            newPos = randomFreePosition();
            if (newPos.x == -1) return;
            human = new Human(newPos.x, newPos.y, this);
            addOrganism(human);
            human.draw();
        }
    }

    public Organism checkCollision(Organism o) {
        return orgOnMap[o.getPos().x][o.getPos().y].getOrg();
    }

    private void deleteKilled() {
        for (int i = 0; i < org.size(); i++) {
            if (org.get(i).getPos().x == -1) {
                org.get(i).onKilled();
                org.remove(i);
                numOfOrganisms--;
                i--;
            }
        }
    }

    private void drawWorld() {
        for (int i = 0; i < worldH; i++){
            for (int j = 0; j < worldW; j++){
                if  (orgOnMap[j][i].getOrg() == EMPTY_SPACE)
                    orgOnMap[j][i].getBtn().setBackground(Color.BLACK);
                else
                    getField(j,i).draw();
            }
        }

        DialogWindow.getInstance().reloadLogs();
    }

    public void setHumanKilled() {
        human = null;
    }

    public JPanel getMapPanel(){
        Dimension prefSize = new Dimension(worldW * Consts.CELL_SIZE,worldH * Consts.CELL_SIZE);
        JPanel map = new JPanel();
        map.setPreferredSize(prefSize);
        map.setMinimumSize(prefSize);
        map.setMaximumSize(prefSize);
        map.setLayout(new GridLayout(worldH, worldW));

        for (int i = 0; i < worldH; i++){
            for (int j = 0; j < worldW; j++) {
                map.add(orgOnMap[j][i].getBtn());
            }
        }

        map.setBackground(Consts.BACKGROUND_COL);
        return map;
    }

    public void handleKeys(int keyCode){
        if (keyCode == KeyEvent.VK_ENTER)
            nextTurn();
        else if (human != null)
            human.handleKeys(keyCode);
    }

    public void save() {
        try {
            FileWriter saveFile = new FileWriter("save");
            deleteKilled();
            saveFile.write(worldW + " " + worldH + "\n");

            for (int i = 0; i < numOfOrganisms; i++) {
                saveFile.write(org.get(i).getName() + "\n");
                saveFile.write(org.get(i).getPos().x + " " + org.get(i).getPos().y + "\n");
                if(org.get(i).canMakeTurn()) {
                    saveFile.write("0\n");
                }
                else {
                    saveFile.write("1\n");
                }
                saveFile.write(org.get(i).getStrengh() + "\n");

                if (org.get(i) instanceof Animal) {
                    Animal anim = (Animal)org.get(i);
                    saveFile.write(anim.getPrevPos().x + " " + anim.getPrevPos().y + "\n");

                    if (anim instanceof Human) {
                        Human hum = (Human)anim;
                        saveFile.write(hum.getSpellCD() + "\n");
                    }
                }
            }

            saveFile.close();

            DialogWindow.getInstance().addMsg("Saved Successfully!", Consts.COMMANDS_COLOR);
            DialogWindow.getInstance().reloadLogs();
        } catch (IOException e) {
            DialogWindow.getInstance().addMsg("Error saving file: " + e.getMessage());
            DialogWindow.getInstance().reloadLogs();
        }
    }

    public void load() {
        String line;
        String[] tokens;

        try {
            FileReader saveFile = new FileReader("save");
            BufferedReader bufReader = new BufferedReader(saveFile);

            numOfOrganisms = 0;
            human = null;
            while (!org.isEmpty()) {
                org.remove(0);
            }

            line = bufReader.readLine();
            tokens = line.split(" ");
            worldW = Integer.parseInt(tokens[0]);
            worldH = Integer.parseInt(tokens[1]);

            orgOnMap = new FieldOnMap[worldW][worldH];
            for(int i = 0; i < worldW; i++){
                for (int j = 0; j < worldH; j++){
                    orgOnMap[i][j] = new FieldOnMap(i, j, this);
                }
            }

            String newName;
            while ((newName = bufReader.readLine()) != null) {

                line = bufReader.readLine();
                tokens = line.split(" ");

                Coords newPos = new Coords(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

                Organism newOrg;
                switch (newName) {
                    case "Sheep":
                        newOrg = new Sheep(newPos.x, newPos.y, this);
                        break;
                    case "Wolf":
                        newOrg = new Wolf(newPos.x, newPos.y, this);
                        break;
                    case "Fox":
                        newOrg = new Fox(newPos.x, newPos.y, this);
                        break;
                    case "Antelope":
                        newOrg = new Antelope(newPos.x, newPos.y, this);
                        break;
                    case "Turtle":
                        newOrg = new Turtle(newPos.x, newPos.y, this);
                        break;
                    case "Human":
                        newOrg = new Human(newPos.x, newPos.y, this);
                        break;
                    case "Grass":
                        newOrg = new Grass(newPos.x, newPos.y, this);
                        break;
                    case "Dandelion":
                        newOrg = new Dandelion(newPos.x, newPos.y, this);
                        break;
                    case "Guarana":
                        newOrg = new Guarana(newPos.x, newPos.y, this);
                        break;
                    case "Nightshade":
                        newOrg = new Nightshade(newPos.x, newPos.y, this);
                        break;
                    case "Pine_Borsch":
                        newOrg = new PineBorsch(newPos.x, newPos.y, this);
                        break;
                    default:
                        throw new InvalidType();
                }

                int blocking = Integer.parseInt(bufReader.readLine());
                if (blocking == 1)
                    newOrg.blockTurn();
                else
                    newOrg.unblockTurn();
                newOrg.modifyStrength(Integer.parseInt(bufReader.readLine()) - newOrg.getStrengh());

                if (newOrg instanceof Animal) {
                    line = bufReader.readLine();
                    tokens = line.split(" ");

                    Animal animRepr = (Animal) newOrg;
                    animRepr.getPrevPos().x = Integer.parseInt(tokens[0]);
                    animRepr.getPrevPos().y = Integer.parseInt(tokens[1]);

                    if (newOrg instanceof Human) {
                        Human humRepr = (Human) newOrg;
                        humRepr.setSpellCD((short)Integer.parseInt(bufReader.readLine()));
                        human = humRepr;
                    }
                }

                addOrganism(newOrg);
            }

            DialogWindow.getInstance().clearMsgs();
            DialogWindow.getInstance().addMsg("Game loaded!", Consts.COMMANDS_COLOR);
            DialogWindow.getInstance().reloadLogs();
            saveFile.close();

            SimulationWindow.getInstance().rerenderWindow();
            Main.getInstance().createPanel(SimulationWindow.getInstance().getWin());
            Main.getInstance().getFrame().requestFocusInWindow();

            for (Organism org1 : org){
                org1.draw();
            }

        } catch (FileNotFoundException e) {
            DialogWindow.getInstance().addMsg("Can't open file to load!", Consts.ERROR_COLOR);
            DialogWindow.getInstance().reloadLogs();
            DialogWindow.getInstance().reloadLogs();
        } catch (InvalidType | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
