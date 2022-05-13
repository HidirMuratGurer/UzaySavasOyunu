package UzaySavas;

public class Mermiler extends Bilgiler {

    
    private final int MISSILE_SPEED = 10;
    double theta;
   	double cos;
   	double sin;
   	

    public Mermiler(int x, int y,double theta) {
        super(x, y);

        MermiYap();
        this.theta=theta;
        
    }

    private void MermiYap() {

        loadImage("images/star.png");
        getImageDimensions();
    }

    public void move() {
    	
    	sin = Math.sin(theta - Math.PI / 2);
		cos = Math.cos(theta - Math.PI / 2);
      
        if (x < -10 || x > 810
				|| y < -10 || y > 800)
			visible = false;
		else {
			y = y+ (int) (MISSILE_SPEED * sin);

			x = x+ (int) (MISSILE_SPEED * cos);
		}
    }
}