public class Bomb {
    private Position position;
    private int power;
    private long makeTime;

    Bomb(Position position, int power) {
        this.position = position;

        if (power > 3) power = 3;
        this.power = power;

        this.makeTime = System.currentTimeMillis();

        System.out.println("생성 : " + makeTime);
        explode();
    }

    public boolean explode() {
        while (System.currentTimeMillis() - makeTime < 2000) {
            //기다리는 시간 2초
        }
        System.out.println("폭발!");
        return true;
    }
}
