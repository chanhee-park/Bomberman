import processing.core.PApplet;

public class Item {
    Position position;

    enum Types {
        SPEED, POWER, NUMBER
    }

    private Types type;

    Item(Position p, Types t) {
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
        if(this.type == Types.SPEED) applet.fill(155,0,0);
        if(this.type == Types.POWER) applet.fill(0,155,0);
        if(this.type == Types.NUMBER) applet.fill(0,0,155);
        applet.ellipse(position.getX()*40+10,position.getY()*40+10,25,25);
    }
}

