package UzaySavas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class MouseKontrol extends MouseAdapter implements MouseMotionListener {

	int x = 0;
	int y = 0;
	Point pMoved = new Point(0, 0);
	Ekran game;

	public MouseKontrol(Ekran game) {
		game.addMouseMotionListener(this);
		game.addMouseListener(this);
		this.game = game;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pMoved = e.getPoint();
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	/*
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	*/
}