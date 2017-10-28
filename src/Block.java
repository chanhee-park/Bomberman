public class Block {
    private Position position;

    enum Types {
        BREAKABLE {
            @Override
            void draw() {

            }
        }, UNBREAKABLE {
            @Override
            void draw() {

            }
        }, ABSENCE {
            @Override
            void draw() {

            }
        };

        abstract void draw();
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

}
