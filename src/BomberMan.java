import processing.core.PApplet;

public class BomberMan {
    private Position position;
    Image img;
    private int state = 0;
    private int dir = 0;
    private float speed = 0.05f;
    private int power = 1;
    private int numberOfBomb = 1;
    private boolean dead = false;

    BomberMan(int x, int y, Image img) {
        this.position = new Position(x, y);
        this.img = img;
    }

    public Position getPosition() {
        return position;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        if (speed >= 0.2f) return;
        this.speed = speed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power >= 3) return;
        this.power = power;
    }

    public int getNumberOfBomb() {
        return numberOfBomb;
    }

    public void setNumberOfBomb(int numberOfBomb) {
        if (numberOfBomb >= 3) return;
        this.numberOfBomb = numberOfBomb;
    }

    public void goLeft(Block[][] map) {
        position.setX(position.getX() - speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() + speed);
        dir = 15;
        this.setState(state + 1);
    }

    public void goRight(Block[][] map) {
        position.setX(position.getX() + speed);
        if (detectCollisionWithBlock(map)) position.setX(position.getX() - speed);
        dir = 5;
        this.setState(state + 1);
    }

    public void goUP(Block[][] map) {
        position.setY(position.getY() - speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() + speed);
        dir = 10;
        this.setState(state + 1);
    }

    public void goDown(Block[][] map) {
        position.setY(position.getY() + speed);
        if (detectCollisionWithBlock(map)) position.setY(position.getY() - speed);
        dir = 0;
        this.setState(state + 1);
    }

    public boolean isDead() {
        return dead;
    }

    public void isDead(boolean dead) {
        this.dead = dead;
    }

    public boolean detectCollisionWithBlock(Block[][] map) {
        int x1 = (int) (position.getX() + 0.1);
        int x2 = (int) (position.getX() + 0.6); // 사이즈 맨 밑에
        int y1 = (int) (position.getY());
        int y2 = (int) (position.getY() + 0.7);

        Block.Types brk = Block.Types.BREAKABLE;
        Block.Types unbrk = Block.Types.UNBREAKABLE;
        if (map[x1][y1].getType() == brk || map[x1][y1].getType() == unbrk) return true;
        if (map[x1][y2].getType() == brk || map[x1][y2].getType() == unbrk) return true;
        if (map[x2][y1].getType() == brk || map[x2][y1].getType() == unbrk) return true;
        if (map[x2][y2].getType() == brk || map[x2][y2].getType() == unbrk) return true;

        return false;
    }

    public void speedUp() {
        if (speed > 0.2) return;
        speed += 0.05f;
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
        if (state > 20) {
            this.state = 0;
        }
    }

    public void draw(PApplet applet) {
        applet.image(img.characterMovements[dir + (state / 5)], position.getX() * Constants.BLOCK_WIDTH, position.getY() * Constants.BLOCK_HEIGHT);
    }

    public void getItem(Item item) {
        Item.Types itemType = item.getType();

        if (itemType == Item.Types.SPEED) speedUp();
        if (itemType == Item.Types.POWER) powerUp();
        if (itemType == Item.Types.NUMBER) numberOfBombUp();
    }
}
