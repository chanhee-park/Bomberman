import processing.core.PApplet;

public class BomberMan {
    Position position;
    Image img = new Image();
    private float speed = 0.25f;
    private int power = 1;
    private int numberOfBomb = 1;

    BomberMan(int x, int y) {
        this.position = new Position(x, y);

    }

    public void goLeft(Block[][] map) {
        position.setX(position.getX() - speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() + speed);

    }

    public void goRight(Block[][] map) {
        position.setX(position.getX() + speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() - speed);

    }

    public void goUP(Block[][] map) {
        position.setY(position.getY() - speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() + speed);
    }

    public void goDown(Block[][] map) {
        position.setY(position.getY() + speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() - speed);
    }

    public boolean detectCollisionWithBlock(Block[][] map) {
        int x1 = (int) position.getX();
        int x2 = (int) (position.getX() + 0.99); // 사이즈 맨 밑에
        int y1 = (int) position.getY();
        int y2 = (int) (position.getY() + 0.99);

        System.out.println(x1 + " " + x2 + " " + y1 + " " + y2 + " ");

        Block.Types brk = Block.Types.BREAKABLE;
        Block.Types unbrk = Block.Types.UNBREAKABLE;
        if (map[x1][y1].type == brk || map[x1][y1].type == unbrk) return true;
        if (map[x1][y2].type == brk || map[x1][y2].type == unbrk) return true;
        if (map[x2][y1].type == brk || map[x2][y1].type == unbrk) return true;
        if (map[x2][y2].type == brk || map[x2][y2].type == unbrk) return true;

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

    public void draw(PApplet applet) {
        applet.image(img.getCharacterImg(applet)[2],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
    }


}
