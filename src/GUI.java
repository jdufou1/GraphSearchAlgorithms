
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel{

	/**
	 * 
	 */
	static final boolean SHOW_POSITION = true; /* Pour activer l'affichage rouge des positions*/
	
	
	private static final long serialVersionUID = 1L;
	public static final int WINDOWS_HEIGHT = 800;
	public static final int WINDOWS_WIDTH = 800;
	
	private JFrame view;
	
	private MapState map;
	private MapState finalMap;
	private ArrayList<Point> positions;
	
	
	public GUI(String nameWindows) {
		view = new JFrame(nameWindows);
		view.setPreferredSize(new Dimension(WINDOWS_WIDTH,WINDOWS_HEIGHT));
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.add(this);
	}
	
	public void update(MapState map,ArrayList<Point> positions) {
		this.map = map;
		this.positions = positions;
	}
	public void setFinalMap(MapState finalMap) {
		this.finalMap = finalMap;
	}
	
	public void show() {
		view.pack();
		view.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle zone = new Rectangle(0,0,getWidth()/map.getSizeC(),getHeight()/map.getSizeL());
		for(int i = 0; i < map.getSizeL(); i++) {
			for(int j = 0; j < map.getSizeC(); j++) {
				if(SHOW_POSITION) {
					for(Point position : positions) {
						if(position.x == i && position.y == j) {
							g2.setColor(Color.red);
							g2.fill(zone);
						}
					}
				}
				if(map.getMap()[i][j].contains("X")) {
					g2.setColor(Color.gray);
					g2.fill(zone);
				}
				if(finalMap.getMap()[i][j].contains("P")) {
					g2.setColor(Color.blue);
					g2.fill(zone);
				}
				if(map.getMap()[i][j].contains("P")) {
					g2.setColor(Color.green);
					g2.fill(zone);
				}
				
				g2.setColor(Color.black);
				g2.draw(zone);
				
				zone.x += zone.width;
			}
			zone.x = 0;
			zone.y += getHeight()/map.getSizeL();
		}
	}
}
