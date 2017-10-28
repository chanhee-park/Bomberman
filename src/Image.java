import processing.core.PApplet;
import processing.core.PImage;

class Image {
    static PImage characterMovement;
    static PImage[] characterMovements;
    static PImage characterStay;
    static PImage[] characterStays;
    static PImage block;
    static PImage unBreakableBlock;
    static PImage[] breakableBlock;
    static PImage effect;
    static PImage[] bomb;
    static PImage[] explosion;
    static PImage item;
    static PImage[] items;

    Image(PApplet applet) {
        characterMovement = applet.loadImage("./img/bomberman-movement.png");
        characterMovements = new PImage[20];
        for(int i = 0 ; i < 5; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                characterMovements[j * 5  + i] = characterMovement.get(20 * i, 32 * j, 20, 32);
                characterMovements[j * 5  + i].resize(25,40);
            }
        }

        characterStay = applet.loadImage("./img/bomberman-stay.png");
        characterStays = new PImage[12];
        for(int i = 0 ; i < 3; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                characterStays[j * 3 + i] = characterStay.get(20 * i, 32 * j, 20, 32);
                characterStays[j * 3 + i].resize(25,40);
            }
        }

        block = applet.loadImage("./img/bomberman-block.png");
        unBreakableBlock = block.get(40 * 0, 40 * 3, 40, 40);


        breakableBlock = new PImage[3];
        for(int i = 0 ; i < 3 ; i++) {
            breakableBlock[i] = block.get(40 * i, 0, 40, 40);
        }

        effect = applet.loadImage("./img/bomberman-effect.png");

        bomb = new PImage[4];
        for(int i = 0 ; i < 4 ; i++) {
            bomb[i] = effect.get(24 * i, 0, 24, 24);
            bomb[i].resize(40,40);
        }

        explosion = new PImage[45];
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 5 ; j++) {
                explosion[9 * j + i] =  effect.get(25 * i, 25 * j, 25, 25);
                explosion[9 * j + i].resize(40,40);
            }
        }

        item = applet.loadImage("./img/bomberman-items.png");
        items = new PImage[3];
        for(int i = 0 ; i < 3 ; i++) {
            items[i] = item.get( 24 * i *2 , 0, 24, 24);
            items[i].resize(40,40);
        }

    }

}
