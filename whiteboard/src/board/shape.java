package board;

import java.awt.Color;
import java.io.Serializable;

public class shape implements Serializable{
	private String type;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color color;
	private String text;
	private String username;
	
	public shape(String type, int x1,int y1,int x2,int y2,Color color,String text,String user) {
		
		this.type = type;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
		this.text = text;
		this.username = user;
		
		
		
	}
	public String getType() {
		return type;
	}
	
	public String getUser() {
		return username;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setColor(Color g) {
		this.color = g;
	}
	
	public Color getColor() {
		return color;
	}


	public String getText() {
		return text;
	}

	public void setTxt(String text) {
		this.text = text;
	}
	
}
