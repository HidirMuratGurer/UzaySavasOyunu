package UzaySavas;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SavasGemisi extends Bilgiler {

    private int dx;
    private int dy;
    private List<Mermiler> mermi;
    private MouseKontrol mouse;
    double theta;
    
    public SavasGemisi(int x, int y,double theta) {
        super(x, y);

        GemiCiz();
        this.theta=theta;
    }

    private void GemiCiz() {

        mermi = new ArrayList<>();
        loadImage("images/player.png");
        getImageDimensions();

        
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        
    }

    public List<Mermiler> getmermi() {
        return mermi;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        	Ates();
            
        }

        if (key == KeyEvent.VK_LEFT || key==KeyEvent.VK_A) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP || key==KeyEvent.VK_W) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN || key==KeyEvent.VK_S) {
            dy = 2;
        }
    }

    public void Ates() {
    	
        mermi.add(new Mermiler(x + width-60, y + height / 2-35,theta));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
