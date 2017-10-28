import processing.core.PApplet;

public class Main extends PApplet {
    Block[][] map = new Block[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
    Image img;
    BomberMan p1, p2;
    Bomb[] bombs = new Bomb[6];

    public static void main(String[] args) {
        PApplet.main("Main");
        Main m = new Main();
    }

    @Override
    public void settings() {
        this.size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

    }

    @Override
    public void setup() {
        this.background(51, 102, 0);
        img = new Image(this);
        makeMap();
        makeBomberMan();
    }

    @Override
    public void draw() {
        this.background(51, 102, 0);
        drawMap();
        drawPlayers();
        if (keyPressed) {
            System.out.println(keyCode);
            if (keyCode == RIGHT) p1.goRight(map);
            else if (keyCode == LEFT) p1.goLeft(map);
            else if (keyCode == DOWN) p1.goDown(map);
            else if (keyCode == UP) p1.goUP(map);

            if (keyCode == 68) p2.goRight(map);
            else if (keyCode == 65) p2.goLeft(map);
            else if (keyCode == 83) p2.goDown(map);
            else if (keyCode == 87) p2.goUP(map);
        }

        drawBomb();
    }

    @Override
    public void keyPressed() {
        System.out.println(keyCode);
        if (keyCode == 77) { // m
            for (int i = 0; i < p1.getNumberOfBomb(); i++) {
                if (bombs[i] == null) {
                    bombs[0] = new Bomb(p1.getPosition().clone(), p1.getPower(), img);
                }
            }
        }
        if (keyCode == 86) { // v
            for (int i = 3; i < 3 + p1.getNumberOfBomb(); i++) {
                if (bombs[i] == null) {
                    bombs[0] = new Bomb(p2.getPosition().clone(), p2.getPower(), img);
                }
            }
        }
    }

    @Override
    public void keyReleased() {

    }

    public void makeMap() {
        for (int y = 0; y < Constants.MAP_HEIGHT; y++) {
            for (int x = 0; x < Constants.MAP_WIDTH; x++) {
                Block.Types type;
                if (x == 0 || y == 0 || x == Constants.MAP_WIDTH - 1 || y == Constants.MAP_HEIGHT - 1) {
                    type = Block.Types.UNBREAKABLE;
                } else if (x % 2 == 0 && y % 2 == 0) {
                    type = Block.Types.UNBREAKABLE;
                } else if (Math.random() < 0.3) {
                    type = Block.Types.BREAKABLE;
                } else {
                    type = Block.Types.ABSENCE;
                }
                map[x][y] = new Block(x, y, type, img);

            }
        }
        map[1][1] = new Block(1, 1, Block.Types.ABSENCE, img);
        map[1][2] = new Block(1, 2, Block.Types.ABSENCE, img);
        map[2][1] = new Block(2, 1, Block.Types.ABSENCE, img);
        map[Constants.MAP_WIDTH - 2][Constants.MAP_HEIGHT - 2] = new Block(Constants.MAP_WIDTH - 2, Constants.MAP_HEIGHT - 2, Block.Types.ABSENCE, img);
        map[Constants.MAP_WIDTH - 2][Constants.MAP_HEIGHT - 3] = new Block(Constants.MAP_WIDTH - 2, Constants.MAP_HEIGHT - 3, Block.Types.ABSENCE, img);
        map[Constants.MAP_WIDTH - 3][Constants.MAP_HEIGHT - 2] = new Block(Constants.MAP_WIDTH - 3, Constants.MAP_HEIGHT - 1, Block.Types.ABSENCE, img);

        drawMap();
    }

    public void drawMap() {
        for (int y = 0; y < Constants.MAP_HEIGHT; y++) {
            for (int x = 0; x < Constants.MAP_WIDTH; x++) {
                map[x][y].draw(this, map[x][y].getType());
            }
        }
    }

    public void makeBomberMan() {
        p1 = new BomberMan(1, 1, img);
        p2 = new BomberMan(Constants.MAP_WIDTH - 2, Constants.MAP_HEIGHT - 2, img);
    }

    public void drawBomb() {
        for (int i = 0; i < 6; i++) {
            if (bombs[i] == null) {
                continue;
            }
            if (bombs[i].isExploded()) {
                explodeBomb(bombs[i]);
                bombs[i] = null;
                continue;
            }
            bombs[i].draw(this);
        }
    }


    public void explodeBomb(Bomb bomb) {
        int x = (int) bomb.getPosition().getX();
        int y = (int) bomb.getPosition().getY();

        for (int i = 0; i <= bomb.getPower(); i++) {
            collideWithBlock(bomb, map[x - i][y]);
            collideWithBlock(bomb, map[x + i][y]);
            collideWithBlock(bomb, map[x][y - i]);
            collideWithBlock(bomb, map[x][y + i]);

            CollideWithPlayer(p1, x - 1, y);
            CollideWithPlayer(p1, x + 1, y);
            CollideWithPlayer(p1, x, y - 1);
            CollideWithPlayer(p1, x, y + 1);
        }


    }

    void collideWithBlock(Bomb bomb, Block block) {
        Block.Types breakable = Block.Types.BREAKABLE;
        Block.Types absence = Block.Types.ABSENCE;
        if (block.getType() == breakable) block.setType(absence);
    }

    void CollideWithPlayer(BomberMan player, int x, int y) {
        int px = (int) player.getPosition().getX();
        int py = (int) player.getPosition().getY();

        if (px == x && py == y) {
            System.out.println("ㅠㅠ 죽었다..");
            player.isDead(true);
        }
    }

    void drawPlayers() {
        if (p1 != null && p1.isDead()) {
            System.out.println("주거써서서서");

            p1 = null;
        }
        if (p2 != null && p2.isDead()) p2 = null;
        if (p1 != null) p1.draw(this);
        if (p2 != null) p2.draw(this);

    }
}
