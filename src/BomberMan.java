import processing.core.PApplet;

public class BomberMan {
    Position position;
    private float speed = 0.25f;
    private int power = 1;
    private int numberOfBomb = 1;

    BomberMan(int x, int y) {
        this.position = new Position(x,y);
    }

    public void goLeft(Block[][] map) {
        if(detectCollision(map)) return;
        position.setX(position.getX() - speed);
    }

    public void goRight(Block[][] map) {
        if(detectCollision(map)) return;
        position.setX(position.getX() + speed);
    }

    public void goUP(Block[][] map) {
        if(detectCollision(map)) return;
        position.setY(position.getY() - speed);
    }

    public void goDown(Block[][] map) {
        if(detectCollision(map)) return;
        position.setY(position.getY() + speed);
    }

    public boolean detectCollision(Block[][] map) {
        return false;
    }
    public void speedUp() {
        if (speed > 0.8) return;
        speed += 0.25f;
    }

    public void powerUp() {
        if (power > 3) return;
        power += 1;
    }

    public void numberOfBombUp() {
        if (numberOfBomb > 3) return;
        numberOfBomb += 1;
    }

    public void draw(PApplet applet){
        applet.fill(200,0,0);
        applet.ellipse(position.getX()*Constants.BLOCK_WIDTH+Constants.BLOCK_WIDTH/2, position.getY()*Constants.BLOCK_HEIGHT+Constants.BLOCK_HEIGHT/2,Constants.BLOCK_WIDTH,Constants.BLOCK_HEIGHT);
    }


}
