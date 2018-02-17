package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

import dadou.procedural.Monde;
import dadou.procedural.Piece;
import dadou.procedural.Separation;
import dadou.procedural.Zone;

class MondeCanvas extends JComponent implements KeyListener {
	public static int width = 512;
	public static int height = 512;
	Monde monde;
	List<Separation> seps;

	public Map<String, Color> colors = new HashMap<String, Color>();

	public MondeCanvas(Monde monde) {
	this.monde =monde;
		colors.put("0", Color.gray);
		colors.put("1", Color.black);
		colors.put("2", Color.green);
		colors.put("3", Color.pink);
		colors.put("4", Color.orange);
		colors.put("5", Color.magenta);
		colors.put("6", Color.darkGray);
		colors.put("7", Color.cyan);
		colors.put("8", Color.lightGray);
		colors.put("9", Color.yellow);
		colors.put("10", Color.WHITE);

		colors.put("porte", Color.red);

	}

	public void paint(Graphics g) {
		for (Zone zone : monde.zones.values()) {
			for (Piece p : zone.pieces) {
				if (!p.color.equals("porte")) {
					Color c = colors.get(p.color);

					g.setColor(c);
					g.fillRect(p.minX(), p.minY(), (p.maxX() - p.minX()),
							(p.maxY() - p.minY()));
					
			
					
				}

			}
		}
		for (Separation sep : monde.separations) {
			Piece p = sep.a;
			Color c = colors.get(p.color);

			g.setColor(c);
			g.fillRect(p.minX(), p.minY(), (p.maxX() - p.minX()),
					(p.maxY() - p.minY()));
		}
		for (Zone zone : monde.zones.values()) {
			for (Piece p : zone.pieces) {
				if (!p.color.equals("porte")) {
		
					
					g.setColor(Color.WHITE);
					g.drawString(""+p.h, p.cx(), p.cy());
					
				}

			}
		}

	}

	public static void main(String[] a) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		MondeCanvas mc = new MondeCanvas(null);
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

	}
	public static void open(Monde monde) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, width, height);
		MondeCanvas mc = new MondeCanvas(monde);
		window.getContentPane().add(mc);
		window.setVisible(true);
		window.addKeyListener(mc);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		monde = new Monde(width,height);

		monde.generate();

		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
