import processing.core.PApplet;

public class Bomb {
    private Position position;
    private Image img;
    int bombState = 0;
    private int power;
    private long makeTime;

    Bomb(Position position, int power, Image img) {
        this.position = position;
        this.img = img;
        if (power > 3) power = 3;
        this.power = power;

        this.makeTime = System.currentTimeMillis();

        System.out.println("생성 : " + makeTime);
        explode();
    }

    public boolean explode() {

        while (System.currentTimeMillis() - makeTime < 2000) {
            //기다리는 시간 2초
            bombState++;
        }
        System.out.println("폭발!");
        return true;
    }

    public void draw(PApplet applet) {
        applet.image(img.bombs[0],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+0);
    }
}
