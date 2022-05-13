package UzaySavas;

public class Dusman1 extends Bilgiler {

    private final int INITIAL_X = 820;
    public int hiz;

    public Dusman1(int x, int y,int hiz) {
        super(x, y);

        DusmanYap();
        this.hiz=hiz;
    }

    private void DusmanYap() {

    	loadImage("images/enemy1.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= hiz;
    }
}