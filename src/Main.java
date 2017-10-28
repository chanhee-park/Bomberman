import processing.core.PApplet;

public class Main extends PApplet {
    Block[][] map = new Block[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];

    public static void main(String[] args) {
//        PApplet.main("Main");
        Main m = new Main();
        m.makeMap();

    }

    @Override
    public void settings() {
        this.size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {
        makeMap();
    }

    @Override
    public void draw() {

    }

    @Override
    public void keyPressed() {

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
                Block.Types type = map[x][y].type;
                if (type == Block.Types.UNBREAKABLE) {
                    System.out.print("m");
                } else if (type == Block.Types.BREAKABLE) {
                    System.out.print(".");
                } else if (type == Block.Types.ABSENCE) {
                    System.out.print(" ");
                } else {
                    System.out.print("~~~~");
                }
            }
            System.out.print("\n");
        }
    }
}
