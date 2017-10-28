import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    Block[][] map = new Block[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
    Image img;
    BomberMan p1, p2;
    Bomb[] bombs = new Bomb[6];
    ArrayList<Item> items = new ArrayList<Item>();


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
        if (keyPressed && p1 != null && p2 != null) {
            System.out.println(keyCode);
            if (keyCode == RIGHT) p1.goRight(map);
            else if (keyCode == LEFT) p1.goLeft(map);
            else if (keyCode == DOWN) p1.goDown(map);
            else if (keyCode == UP) p1.goUP(map);

            if (keyCode == 68) p2.goRight(map);
            else if (keyCode == 65) p2.goLeft(map);
            else if (keyCode == 83) p2.goDown(map);
            else if (keyCode == 87) p2.goUP(map);

            detectCollisionPlayerAndItem();
        }

        drawBomb();
        drawItem();
    }

    private void detectCollisionPlayerAndItem() {
        for (Item item : items) {
            int p1x = (int) p1.getPosition().getX();
            int p1y = (int) p1.getPosition().getY();
            int p2x = (int) p1.getPosition().getX();
            int p2y = (int) p1.getPosition().getY();
            int x = (int) item.getPosition().getX();
            int y = (int) item.getPosition().getY();
            if (p1x == x && p1y == y) {
                System.out.println("ㅇㅁㄹㅇㄹㄴ");
                p1.getItem(item);
                items.remove(item);
                break;
            } else if (p2x == x && p2y == y) {
                p2.getItem(item);
                items.remove(item);
                break;
            }
        }
    }


    @Override
    public void keyPressed() {
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


    private void explodeBomb(Bomb bomb) {
        int x = (int) bomb.getPosition().getX();
        int y = (int) bomb.getPosition().getY();
        int power = bomb.getPower();


        for (int i = 0; i <= power; i++) {
            CollideWithPlayer(p1, x - i, y);
            CollideWithPlayer(p1, x + i, y);
            CollideWithPlayer(p1, x, y - i);
            CollideWithPlayer(p1, x, y + i);
        }

        for (int i = 0; i < 4; i++) {
            image(img.explosion[9 * (4 - i)], x * Constants.BLOCK_WIDTH, y * Constants.BLOCK_HEIGHT); // 가운데

            for (int j = 1; j < power + 1; j++) { // 왼
                if (collideWithBlock(map[x - j][y]) == 1) {
                    break;
                } else if (collideWithBlock(map[x - j][y]) == 2) {
                    image(img.explosion[9 * (4 - i) + 2], (x - j) * Constants.BLOCK_WIDTH, y * Constants.BLOCK_HEIGHT);
                    break;
                }
                image(img.explosion[9 * (4 - i) + 1], (x - j) * Constants.BLOCK_WIDTH, y * Constants.BLOCK_HEIGHT);
            }

            for (int j = 1; j < power + 1; j++) { // 오
                if (collideWithBlock(map[x + j][y]) == 1) {
                    break;
                } else if (collideWithBlock(map[x + j][y]) == 2) {
                    image(img.explosion[9 * (4 - i) + 4], (x + j) * Constants.BLOCK_WIDTH, y * Constants.BLOCK_HEIGHT);
                    break;
                }
                image(img.explosion[9 * (4 - i) + 3], (x + j) * Constants.BLOCK_WIDTH, y * Constants.BLOCK_HEIGHT);
            }

            for (int j = 1; j < power + 1; j++) {  // 위
                if (collideWithBlock(map[x][y - j]) == 1) {
                    break;
                } else if (collideWithBlock(map[x][y - j]) == 2) {
                    image(img.explosion[9 * (4 - i) + 8], x * Constants.BLOCK_WIDTH, (y - j) * Constants.BLOCK_HEIGHT);
                    break;
                }
                image(img.explosion[9 * (4 - i) + 7], x * Constants.BLOCK_WIDTH, (y - j) * Constants.BLOCK_HEIGHT);
            }

            for (int j = 1; j < power + 1; j++) {  // 아래
                if (collideWithBlock(map[x][y + j]) == 1) {
                    break;
                } else if (collideWithBlock(map[x][y + j]) == 2) {
                    image(img.explosion[9 * (4 - i) + 6], x * Constants.BLOCK_WIDTH, (y + j) * Constants.BLOCK_HEIGHT);
                    break;
                }
                image(img.explosion[9 * (4 - i) + 5], x * Constants.BLOCK_WIDTH, (y + j) * Constants.BLOCK_HEIGHT);
            }

        }

    }

    private int collideWithBlock(Block block) {
        if (block.getType() == Block.Types.BREAKABLE) {
            float randomNumber = (float) Math.random();
            Position position = block.getPosition().clone();
            if (randomNumber < 0.4) {
                //nothing to do
            } else if (randomNumber < 0.6) {
                items.add(new Item(position, Item.Types.SPEED));
            } else if (randomNumber < 0.8) {
                items.add(new Item(position, Item.Types.POWER));
            } else {
                items.add(new Item(position, Item.Types.NUMBER));
            }
            block.setType(Block.Types.ABSENCE);
            return Constants.BREAKABLE;
        } else if (block.getType() == Block.Types.UNBREAKABLE) {
            return Constants.UNBREAKABLE;
        }
        return Constants.ABSENCE;
    }

    private void CollideWithPlayer(BomberMan player, int x, int y) {
        int px = (int) player.getPosition().getX();
        int py = (int) player.getPosition().getY();

        if (px == x && py == y) {
            player.isDead(true);
        }
    }

    private void drawPlayers() {
        if (p1 != null && p1.isDead()) {
            p1 = null;
        }
        if (p2 != null && p2.isDead()) p2 = null;
        if (p1 != null) p1.draw(this);
        if (p2 != null) p2.draw(this);
    }

    private void drawItem() {
        for (Item item : items) {
            item.draw(this);
        }
    }
}
