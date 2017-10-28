import processing.core.PApplet;

public class Block {
    private Position position;

    enum Types {
        BREAKABLE,
        UNBREAKABLE,
        ABSENCE
    }
    Block.Types type;

    Block(int x, int y, Block.Types type) {
        if(x<0 || x>= 20 ||y<0 || y>=15) {
            System.out.println("위치 에러");
            return;
        }
        position = new Position(x,y);
        this.type = type;
    }

    public Item brake(){
        if(this.type != Types.UNBREAKABLE) {

        }
        this.type = Types.ABSENCE;
        return dropItem();
    }

    private Item dropItem() {
        double random = Math.random();

        if(random<0.4) return null;
        else if(random<0.6) return new Item(position, Item.Types.SPEED);
        else if(random<0.8) return new Item(position,Item.Types.POWER);
        else return new Item(position,Item.Types.NUMBER);
    }

    public void draw(PApplet applet, Types type){
        if(type == Types.ABSENCE){
            applet.fill(255);
        }else if(type == Types.UNBREAKABLE){
            applet.fill(0);
        }else if(type == Types.BREAKABLE){
            applet.fill(150);
        }
        applet.rect(position.getX()*40,position.getY()*40,Constants.BLOCK_WIDTH,Constants.BLOCK_HEIGHT);
    }
}
