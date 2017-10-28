import processing.core.PApplet;

public class Main extends PApplet {
    Block[][] map = new Block[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
    BomberMan p1;
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
        makeMap();
        makeBomberMan();
    }

    @Override
    public void draw() {
        drawMap();
        p1.draw(this);
    }

    @Override
    public void keyPressed() {
        if(keyCode==RIGHT) p1.goRight(map);
        if(keyCode==LEFT) p1.goLeft(map);
        if(keyCode==DOWN) p1.goDown(map);
        if(keyCode==UP) p1.goUP(map);
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
                map[x][y] = new Block(x, y, type);

            }
        }
        map[1][1] = new Block(1, 1, Block.Types.ABSENCE);
        map[1][2] = new Block(1, 2, Block.Types.ABSENCE);
        map[2][1] = new Block(2, 1, Block.Types.ABSENCE);
        map[Constants.MAP_WIDTH - 2][Constants.MAP_HEIGHT - 2] = new Block(Constants.MAP_WIDTH - 2, Constants.MAP_HEIGHT - 2, Block.Types.ABSENCE);
        map[Constants.MAP_WIDTH - 2][Constants.MAP_HEIGHT - 3] = new Block(Constants.MAP_WIDTH - 2, Constants.MAP_HEIGHT - 3, Block.Types.ABSENCE);
        map[Constants.MAP_WIDTH - 3][Constants.MAP_HEIGHT - 2] = new Block(Constants.MAP_WIDTH - 3, Constants.MAP_HEIGHT - 1, Block.Types.ABSENCE);

        drawMap();
    }

    public void drawMap() {
        for (int y = 0; y < Constants.MAP_HEIGHT; y++) {
            for (int x = 0; x < Constants.MAP_WIDTH; x++) {
                map[x][y].draw(this, map[x][y].type);
            }
        }
    }

    public void makeBomberMan() {
        p1 = new BomberMan(1,1);
    }
}
