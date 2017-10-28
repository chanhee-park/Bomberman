import processing.core.PApplet;

public class Main extends PApplet {
    Block[][] map = new Block[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
    Image img;
    BomberMan p1;
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


        if (keyPressed) {
            if (keyCode == RIGHT) p1.goRight(map);
            else if (keyCode == LEFT) p1.goLeft(map);
            else if (keyCode == DOWN) p1.goDown(map);
            else if (keyCode == UP) p1.goUP(map);
        }

        p1.draw(this);
        drawBomb();
    }

    @Override
    public void keyPressed() {
        System.out.println(keyCode);
        if (keyCode == 77) { // m
            for (int i = 0; i < p1.getNumberOfBomb(); i++) {
                if (bombs[i] == null) {
                    bombs[0] = new Bomb(p1.position.clone(), p1.getPower(), img);
                }
            }
        }
        if (keyCode == 86) { // v
            for (int i = 3; i < 3 + p1.getNumberOfBomb(); i++) {
                if (bombs[i] == null) {
                    bombs[0] = new Bomb(p1.position.clone(), p1.getPower(), img);
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
        System.out.println(x+",  "+y);
        Block.Types breakable = Block.Types.BREAKABLE;
        Block.Types absence = Block.Types.ABSENCE;

        for(int i=1; i<=bomb.getPower(); i++){

            if(map[x-i][y].getType() == breakable){
                map[x-i][y].setType(absence);
                System.out.println("없애야지");
            }

            System.out.println(map[x+i][y].getType());
            if(map[x+i][y].getType() == breakable) {
                map[x+i][y].setType(Block.Types.ABSENCE);
                System.out.println("없애야지");
                System.out.println(map[x+i][y].getType());
            }
            if(map[x][y-i].getType() == breakable) {
                map[x][y-i].setType(absence);
                System.out.println("없애야지");
            }
            if(map[x][y+i].getType() == breakable) {
                map[x][y+i].setType(absence);
                System.out.println("없애야지");
            }
        }
    }
}
