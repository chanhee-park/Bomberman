import processing.core.PApplet;

public class BomberMan {
    Position position;
    Image img;
    private int state = 0;
    private int dir=0;
    private float speed = 0.05f;
    private int power = 1;
    private int numberOfBomb = 1;

    BomberMan(int x, int y, PApplet applet) {
        this.position = new Position(x, y);
        img = new Image(applet);
    }

    public void goLeft(Block[][] map, PApplet applet) {
        position.setX(position.getX() - speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() + speed);
        dir =15;
        // applet.image(img.characterMovements[dir+(state/5)],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
        this.setState(state+1);
    }

    public void goRight(Block[][] map, PApplet applet) {
        position.setX(position.getX() + speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() - speed);
        dir = 5;
        // applet.image(img.characterMovements[dir+state/5],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
        this.setState(state+1);

    }

    public void goUP(Block[][] map, PApplet applet) {
        position.setY(position.getY() - speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() + speed);
        dir = 10;
        // applet.image(img.characterMovements[dir+state/5],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
        this.setState(state+1);
    }

    public void goDown(Block[][] map, PApplet applet) {
        position.setY(position.getY() + speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() - speed);
        dir = 0;
        // applet.image(img.characterMovements[state/5],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
        this.setState(state+1);
    }

    public boolean detectCollisionWithBlock(Block[][] map) {
        int x1 = (int) (position.getX() + 0.2);
        int x2 = (int) (position.getX() + 0.8); // 사이즈 맨 밑에
        int y1 = (int) (position.getY() + 0.2);
        int y2 = (int) (position.getY() + 0.8);

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
        speed += 0.5f;
    }

    public void powerUp() {
        if (power > 3) return;
        power += 1;
    }

    public void numberOfBombUp() {
        if (numberOfBomb > 3) return;
        numberOfBomb += 1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        if(state > 20) {
            this.state =0;
        }
    }

    public void draw(PApplet applet) {
        applet.image(img.characterMovements[dir + 2],position.getX()*Constants.BLOCK_WIDTH+10, position.getY()*Constants.BLOCK_HEIGHT+3);
    }


}
