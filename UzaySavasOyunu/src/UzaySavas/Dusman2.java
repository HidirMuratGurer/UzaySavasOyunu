package UzaySavas;

public class Dusman2 extends Bilgiler {

    private final int INITIAL_Y = -10;
    public int hiz=1;

    public Dusman2(int x, int y,int hiz) {
        super(x, y);

        DusmanYap();
        this.hiz=hiz;
    }

    private void DusmanYap() {

        loadImage("images/enemy.png");
        getImageDimensions();
    }

    public void move() {

        if (y >800) {
            y = INITIAL_Y;
        }

        y += hiz;
    }
}