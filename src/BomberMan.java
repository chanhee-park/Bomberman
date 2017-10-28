public class BomberMan {
    Position position;
    private int speed = 1;
    private int power = 1;
    private int numberOfBomb = 1;

    BomberMan(Position position){
        this.position = position;
    }


    public void goLeft(){
        position.setX(position.getX()-1);
    }
    public void goRight(){
        position.setX(position.getX()+1);

    }
    public void goUP(){
        position.setY(position.getY()-1);
    }
    public void goDown(){
        position.setY(position.getY()+1);
    }

}
