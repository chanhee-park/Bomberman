import processing.core.PApplet;
import processing.core.PImage;

class Image {
    static PImage characterMovement;
    static PImage[] characterMovements;
    static PImage characterStay;
    static PImage[] characterStays;
    static PImage block;
    static PImage unBreakableBlock;
    static PImage[] breakableBlocks;
    static PImage bomb;
    static PImage[] bombs;

    Image(PApplet applet) {
        characterMovement = applet.loadImage("./img/bomberman-movement.png");
        characterMovements = new PImage[20];
        for(int i = 0 ; i < 5; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                characterMovements[j * 5  + i] = characterMovement.get(20 * i, 32 * j, 20, 32);
            }
        }

        characterStay = applet.loadImage("./img/bomberman-stay.png");
        characterStays = new PImage[12];
        for(int i = 0 ; i < 3; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                characterStay.get(20 * i, 32 * j, 20, 32);
            }
        }

        block = applet.loadImage("./img/bomberman-block.png");
        unBreakableBlock = block.get(40 * 0, 40 * 3, 40, 40);


        breakableBlocks = new PImage[3];
        for(int i = 0 ; i < 3 ; i++) {
            breakableBlocks[i] = block.get(40 * i, 0, 40, 40);
        }

        bomb = applet.loadImage("./img/bomberman-effect.png");
        bombs = new PImage[4];
        for(int i = 0 ; i < 4 ; i++) {
            bombs[i] = bomb.get(24 * i, 0, 24, 24);
        }
    }

}
