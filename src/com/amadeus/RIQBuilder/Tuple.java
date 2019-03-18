package com.amadeus.RIQBuilder;

public class Tuple {
	private boolean x;
	private boolean y;
	
	public Tuple() {}
	
	public Tuple(boolean x, boolean y) {
		this.x=x;
		this.y=y;
	}
	
	public boolean getX() {return x;}
	public boolean getY() {return y;}
	
	public void setX(boolean x) {this.x=x;}
	public void setY(boolean y) {this.y=y;}
}
