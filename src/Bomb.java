import processing.core.PApplet;

public class Bomb {
    private Position position;
    private Image img;
    int tick = 0;
    int bombState = 0;
    private int power;
    private long makeTime;
    private boolean exploded;

    Bomb(Position position, int power, Image img) {

        this.position = new Position((int) position.getX(), (int) position.getY());
        this.img = img;
        if (power > 3) power = 3;
        this.power = power;
        this.makeTime = System.currentTimeMillis();
        this.exploded = false;
        System.out.println("생성 : " + makeTime);
    }

    public Position getPosition() {
        return position;
    }

    public int getPower() {
        return power;
    }

    public boolean isExploded() {
        return exploded;
    }


    public void explode() {
        System.out.println("폭발!");
        this.exploded = true;
    }

    public void draw(PApplet applet) {
        bombState++;
        if (System.currentTimeMillis() - makeTime > 2000) {
            explode();
        }

        tick++;
        applet.image(img.bomb[tick / 10 % 3], position.getX() * Constants.BLOCK_WIDTH, position.getY() * Constants.BLOCK_HEIGHT);
    }

}
