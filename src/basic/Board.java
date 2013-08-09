package basic;

import basic.controls.MouseControl;
import basic.controls.WindowControl;
import basic.objects.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable {
	
	private Ship character;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private Thread animator;
	private MouseControl mouseControl = new MouseControl();
	private WindowControl windowControl = new WindowControl();
	private final int DELAY = 30;
	private Component parent;
	
	private JLabel fpsLabel = new JLabel();
	private JPanel statBar = new JPanel();
	private JLabel mouseCoordLabel = new JLabel();
	private JLabel charCoordLabel = new JLabel();
	
	public Board(int width, int height) {
		setLayout(new BorderLayout());
		add(fpsLabel, BorderLayout.NORTH);
		add(statBar, BorderLayout.SOUTH);
		statBar.add(mouseCoordLabel);
		statBar.add(charCoordLabel);
		setSize(width, height);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addMouseListener(mouseControl);
		addMouseMotionListener(mouseControl);
		addComponentListener(windowControl);
	}
	
	public void initializeGame() {
		parent = getParent();
		character = new Ship(10, parent.getWidth()/2, parent.getHeight()/2);	
		character.setMaxRotation(12);
		
		Ship otherShip = new Ship(10, parent.getWidth()/2 + 50, parent.getHeight()/2 + 50);	
		otherShip.setMaxRotation(45);
		gameObjects.add(otherShip);
	}
	
	public void addNotify() {
		super.addNotify();
		animator = new Thread(this);
		animator.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		character.draw(g2d);
		for(GameObject gob:gameObjects) {
			gob.draw(g2d);
		}
		
		int mouseX = mouseControl.getX();
		int mouseY = mouseControl.getY();		
		
		int charX = (int) character.getX();
		int charY = (int) character.getY();
		
		float angle = (float) -Math.toDegrees(Math.atan2(charX - mouseX, charY - mouseY));
		character.setRotation(angle);
		
		charCoordLabel.setText("Ship: [" + charX  + "," + charY + "] Angle:" + character.getRotation());
		mouseCoordLabel.setText("Mouse: [" + mouseX  + "," + mouseY + "] Angle:" + angle);
		
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	public void cycle() {
		for(GameObject gob:gameObjects) {
			gob.setX(gob.getX()+1);	
			gob.setY(gob.getY()+1);	
			gob.setRotation(gob.getRotation()+5);
		}
	}
	
	public void run() {
        long beforeTime, timeDiff, sleep, avgSleep = 0;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            avgSleep = (avgSleep + sleep)/2;
            fpsLabel.setText(Long.toString(avgSleep));

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }		
	}


}
