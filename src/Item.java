public class Item {
    Position position;
    enum Types {
        SPEED,
        POWER,
        NUMBER,
    }
    private Types type;
    Item(Position p, Types t){
        position = p;
        type = t;
    }
}

