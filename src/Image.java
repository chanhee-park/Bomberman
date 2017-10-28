import processing.core.PApplet;
import processing.core.PImage;

public class Image {
    public PImage character;

    PImage enemy;
    PImage[] enemyAct;


    public PImage[] getCharacterImg(PApplet applet) {
        PImage character = applet.loadImage("/img/bomberman-movement.png");

        PImage[] characterAct = new PImage[20];

        for(int i = 0 ; i < 5; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                characterAct[j * 5  + i] = character.get(20 * i, 32 * j, 20, 32);
            }
        }
        return characterAct;
    }

    public PImage setUnBreakableBlock(PApplet applet) {
        PImage block = applet.loadImage("/img/bomberman-block.png");
        block = block.get(40 * 0, 40 * 3, 40, 40);

        return block;
    }

    public PImage[] setBreakableBlock(PApplet applet) {
        PImage block = applet.loadImage("/img/bomberman-block.png");
        PImage[] blocks = new PImage[3];

        for(int i = 0 ; i < 3 ; i++) {
            blocks[i] = block.get(40 * i, 0, 40, 40);
        }

        return blocks;
    }

}
