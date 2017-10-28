import processing.core.PApplet;

public class Item {
    Image img;
    Position position;

    enum Types {
        SPEED, POWER, NUMBER
    }

    private Types type;

    Item(Position p, Types t, Image img) {
        this.img = img;
        position = p;
        type = t;
    }

    public Position getPosition() {
        return position;
    }

    public Types getType() {
        return type;
    }

    public void draw(PApplet applet){
        if(this.type == Types.SPEED) applet.image(img.items[2],position.getX() * Constants.BLOCK_WIDTH, position.getY() * Constants.BLOCK_HEIGHT);
        if(this.type == Types.POWER) applet.image(img.items[0],position.getX() * Constants.BLOCK_WIDTH, position.getY() * Constants.BLOCK_HEIGHT);
        if(this.type == Types.NUMBER) applet.image(img.items[1],position.getX() * Constants.BLOCK_WIDTH, position.getY() * Constants.BLOCK_HEIGHT);
        applet.ellipse(position.getX()*40+10,position.getY()*40+10,25,25);
    }
}

