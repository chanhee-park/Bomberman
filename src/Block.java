import processing.core.PApplet;

public class Block {
    private Position position;
    Image img;

    enum Types {
        BREAKABLE,
        UNBREAKABLE,
        ABSENCE
    }
    private Block.Types type;

    Block(int x, int y, Block.Types type, Image img) {
        this.img = img;
        if(x<0 || x>= 20 ||y<0 || y>=15) {
            System.out.println("위치 에러");
            return;
        }
        position = new Position(x,y);
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
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
        else if(random<0.6) return new Item(position, Item.Types.SPEED, img);
        else if(random<0.8) return new Item(position,Item.Types.POWER, img);
        else return new Item(position,Item.Types.NUMBER, img);
    }

    public void draw(PApplet applet, Types type){
        if(type == Types.UNBREAKABLE){
            applet.image(img.unBreakableBlock,position.getX()*40,position.getY()*40);
        }else if(type == Types.BREAKABLE){
            applet.image(img.breakableBlock[0],position.getX()*40,position.getY()*40);
        }
    }
}
