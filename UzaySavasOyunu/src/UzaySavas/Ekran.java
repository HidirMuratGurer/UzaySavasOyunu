package UzaySavas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ekran extends JPanel implements ActionListener {
	private final Font smallFont = new Font("Helvetica", Font.BOLD, 14);
    private Timer timer;
    private SavasGemisi savasgemisi;
    private List<Dusman1> dusman1;
    private List<Dusman2> dusman2;
    private boolean OyunAktif=false;;
    private boolean oyunudurdur=false;
    private boolean bitis=false;
    public int skor;
    public int level=1;
    private final int Sabit_X = 400;
    private final int Sabit_Y = 600;
    private final int Ekran_Genisligi = 800;
    private final int Ekran_Buyuklugu = 650;
    private final int DELAY = 15;
    private double theta;
    private MouseKontrol mouse;
    AffineTransform at = new AffineTransform();
    public int can=3;
    public int hiz=1;
    public Random rand;
    public int rastgele1,rastgele2,rastgele3,rastgele4;
    
    private Image canresim=new ImageIcon("images/heart1.png").getImage();
    
  
    public Ekran() {
    	mouse = new MouseKontrol(this);
    	EkraniOlustur();
    }

    private void EkraniOlustur() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        
      

        setPreferredSize(new Dimension(Ekran_Genisligi, Ekran_Buyuklugu));
        savasgemisi = new SavasGemisi(Sabit_X, Sabit_Y,theta);

        DusmanlariOlustur();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void DusmanlariOlustur() {
    	dusman1 = new ArrayList<>();

        for (int i=0;i<20;i++) {
            dusman1.add(new Dusman1(RandomYap1(), RandomYap2(),hiz));
        }
        
        dusman2 = new ArrayList<>();

        for  (int i=0;i<20;i++) {
            dusman2.add(new Dusman2(RandomYap3(), RandomYap4(),hiz));
        }
       
    }
    public int RandomYap1() {
		rand =new Random();
		rastgele1=rand.nextInt(1050)+800;;
		return rastgele1;
		
	}
    public int RandomYap2() {
		rand =new Random();
		rastgele2=rand.nextInt(600)+20;
		return rastgele2;
	}
	public int RandomYap3() {
		rand =new Random();
		rastgele3=rand.nextInt(750)+20;
		return rastgele3;
		
	}
	public int RandomYap4() {
		rand =new Random();
		rastgele4=rand.nextInt(0+550)-550;
		return rastgele4;
		
	}
	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        //PuanCiz(g2d);
        
        if (OyunAktif) {

            NesneleriCiz(g);

        }else  {
        	
        	girisEkranGoster(g);
        	
        	timer.stop();
        	
        }
        
        if (oyunudurdur) {
        	oyunaAraVer(g);
        	 timer.stop();
        }  
        
        
       
        Toolkit.getDefaultToolkit().sync();
    }
   
    private void NesneleriCiz(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D)g;
    	
    	
        if (savasgemisi.isVisible()) {
        	theta = Math.atan2(mouse.getY() - (savasgemisi.getY() + 40), mouse.getX()
    				- (savasgemisi.getX() + 50))
    				+ Math.PI / 2;
        	at.setToIdentity();
    		at.rotate(theta, savasgemisi.getX() + 50, savasgemisi.getY() + 40);
    		at.translate(savasgemisi.getX(), savasgemisi.getY());
    		g2d.drawImage(savasgemisi.getImage(), at, null);
           // g.drawImage(savasgemisi.getImage(), savasgemisi.getX(), savasgemisi.getY(),this);
        }

        List<Mermiler> ms = savasgemisi.getmermi();

        for (Mermiler missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(),
                        missile.getY(), this);
            }
        }

        for (Dusman1 alien : dusman1) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }
        for (Dusman2 alien2 : dusman2) {
            if (alien2.isVisible()) {
                g.drawImage(alien2.getImage(), alien2.getX(), alien2.getY(), this);
            }
        }
        g.setFont(smallFont);
        g.setColor(Color.WHITE);
        g.drawString("Kalan Dusman Sayisi : " + (dusman1.size()+dusman2.size()), 200, 630);
        
        g.setColor(Color.WHITE);
        g.drawString("Skorunuz: " + skor, 400, 630);
        
        for (int i = 0; i < can; i++) {
            g.drawImage(canresim, i * 40 + 16, Ekran_Buyuklugu -30, this);
        }
    }
    private void girisEkranGoster(Graphics g) {

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Ekran_Genisligi / 4 - 30, Ekran_Genisligi - 100, 200);
        g.setColor(Color.white);
        g.drawRect(50, Ekran_Genisligi / 4 - 30, Ekran_Genisligi - 100, 200);

        String s = ""
        		+ "  Oyuna Baþlamak için B tuþuna basýnýz  ";
        String s2 = ""
        		+ "  Gemi Hareketi Ýçin Yön Tuþlarý Ve W,A,S,D Tuþlarýný Kullanýnýz  ";
        String s3 = ""
        		+ "  Ateþ Edebilmek Ýçin SPACE Tuþunu Kullanýnýz.  ";
        String s4 = ""
        		+ "  Oyunu Durdurmak Ýçin P Tuþunu Kullanabilirsiniz.  ";
        String s5 = ""
        		+ "  Skorunuz: "+skor;;
        String s6 = ""
        		+ "  Leveliniz: "+level;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(s, (Ekran_Genisligi - metr.stringWidth(s)) / 2, Ekran_Genisligi / 4);
        g.drawString(s2, (Ekran_Genisligi - metr.stringWidth(s2)) / 2, Ekran_Genisligi / 4+30);
        g.drawString(s3, (Ekran_Genisligi - metr.stringWidth(s3)) / 2, Ekran_Genisligi / 4+60);
        g.drawString(s4, (Ekran_Genisligi - metr.stringWidth(s4)) / 2, Ekran_Genisligi / 4+90);
        g.drawString(s5, (Ekran_Genisligi - metr.stringWidth(s5)) / 2, Ekran_Genisligi / 4+120);
        g.drawString(s6, (Ekran_Genisligi - metr.stringWidth(s6)) / 2, Ekran_Genisligi / 4+150);
     }
   
    private void oyunaAraVer(Graphics g) {

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Ekran_Buyuklugu / 2 - 30, Ekran_Genisligi - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Ekran_Buyuklugu / 2 - 30, Ekran_Genisligi - 100, 50);

        String s = ""
        		+ "  Oyun Durduruldu Devam Etmek Ýçin P Tuþuna Basýnýz  ";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(s, (Ekran_Genisligi - metr.stringWidth(s)) / 2, Ekran_Buyuklugu / 2);
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {

        OyunAktif();
       
        
        GemiyiGuncelle();
        MermiyiGuncelle();
        updatedusman1();
        
        CarpýsmaKontrol();

        repaint();
    }

    private void OyunAktif() {

        if (!OyunAktif) {
            timer.stop();
        }
    }
    
  

    private void GemiyiGuncelle() {

        if (savasgemisi.isVisible()) {

            savasgemisi.move();
            savasgemisi.theta=theta;
        }
        if(savasgemisi.getX()>780) {
        	savasgemisi.x-=100;
        }
        if(savasgemisi.getY()>600) {
        	savasgemisi.y-=100;
        }
    }

    private void MermiyiGuncelle() {

        List<Mermiler> ms = savasgemisi.getmermi();

        for (int i = 0; i < ms.size(); i++) {

            Mermiler m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }
  
   
    
    private void updatedusman1() {

        if (dusman1.isEmpty() && dusman2.isEmpty()) {
        	if(can<3)
        		can++;
        	DusmanlariOlustur();
        	savasgemisi.x=Sabit_X;
        	savasgemisi.y=Sabit_Y;
        	if(OyunAktif)
        		hiz++;
        	level++;
        	
            //ongame = false;
            //return;
        }

        for (int i = 0; i < dusman1.size(); i++) {

            Dusman1 a = dusman1.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                dusman1.remove(i);
            }
        }
        for (int i = 0; i < dusman2.size(); i++) {

            Dusman2 a = dusman2.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                dusman2.remove(i);
            }
        }
    }

    public void CarpýsmaKontrol() {

        Rectangle r3 = savasgemisi.getBounds();

        for (Dusman1 alien : dusman1) {

            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
            	/*
                savasgemisi.setVisible(false);
                
                OyunAktif = false;
                */
            	alien.setVisible(false);
            	can--;
            }
        }

        List<Mermiler> ms = savasgemisi.getmermi();

        for (Mermiler m : ms) {

            Rectangle r1 = m.getBounds();

            for (Dusman1 alien : dusman1) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);
                    alien.setVisible(false);
                    skor+=100;
                }
            }
        }
        Rectangle r32 = savasgemisi.getBounds();

        for (Dusman2 alien : dusman2) {

            Rectangle r2 = alien.getBounds();

            if (r32.intersects(r2)) {
            	/*
                savasgemisi.setVisible(false);
                
                OyunAktif = false;
                */
            	alien.setVisible(false);
            	can--;
                
            }
        }

        List<Mermiler> ms2 = savasgemisi.getmermi();

        for (Mermiler m : ms2) {

            Rectangle r1 = m.getBounds();

            for (Dusman2 alien : dusman2) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);
                    alien.setVisible(false);
                    skor+=100;
                }
            }
        }
        if(can<=0) {
        	savasgemisi.setVisible(false);
            OyunAktif = false;
            hiz=1;
            
            
        }
    }

    private class TAdapter extends KeyAdapter {
    	
        @Override
        public void keyReleased(KeyEvent e) {
            savasgemisi.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            savasgemisi.keyPressed(e);
            int key = e.getKeyCode();

          
            if (key == KeyEvent.VK_PAUSE || key == 'p' || key == 'P') {
            	if (timer.isRunning()) {
            		oyunudurdur=true;
            	}
            	else {
            		timer.start();
            		oyunudurdur=false;
            	}
               
           }
           
           if (key == 'b' || key == 'B') {
                OyunAktif = true;
                hiz=1;
                can=3;
                skor=0;
                level=1;
                savasgemisi.x=Sabit_X;
            	savasgemisi.y=Sabit_Y;
                savasgemisi.setVisible(true);
                DusmanlariOlustur();
                OyunAktif();
                
                timer.start();
            }
           
        }
    }
}
