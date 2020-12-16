package board;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Draw extends Canvas{
	protected Server server;
	protected JLabel edit;
	public Draw(Server s, JLabel e) {
		this.server = s;
		this.edit = e;
	}
	public void paint(Graphics g) {
		super.paint(g);
		try {
			ArrayList<shape> graphlist = server.getShapeList();
			for(shape s:graphlist) {
				draw(s);
					
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void draw(shape s) {


		edit.setText(s.getUser() + " is drawing"); 
		Graphics2D g2d = (Graphics2D)this.getGraphics();
		if(s.getType().equals("line")) {
			g2d.setColor(s.getColor());
			g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			
		}else if(s.getType().equals("rect")) {
			g2d.setColor(s.getColor());
			g2d.drawRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			
		}else if(s.getType().equals("oval")) {
			g2d.setColor(s.getColor());
			g2d.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			
		}else if(s.getType().equals("circle")) {
			g2d.setColor(s.getColor());
			g2d.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			
		}else if(s.getType().equals("pencil")) {
			g2d.setColor(s.getColor());
			g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			
		}else if(s.getType().equals("easer")) {
			g2d.setColor(Color.white);
			g2d.setStroke(new BasicStroke(8));
			g2d.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
			g2d.setStroke(new BasicStroke(1));
			
			
		}else if(s.getType().equals("text")) {
			g2d.setColor(s.getColor());
			g2d.drawString(s.getText(), s.getX1(),s.getY1());
			
		}
	}
}
