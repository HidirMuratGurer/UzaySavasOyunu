package UzaySavas;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class AnaClass extends JFrame {

    public AnaClass() {

        initUI();
    }

    private void initUI() {

        add(new Ekran());

        setResizable(false);
        pack();

        setTitle("Uzay Savas Oyunu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {

            AnaClass ex = new AnaClass();
            ex.setVisible(true);

    }
}