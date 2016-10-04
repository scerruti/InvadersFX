package application.model;

public class Ship extends GameObject {
	
	private boolean armed;
	private double speed;
		
	public Ship(int x, int y){
		super(x, y);
		
		speed = 5.0;
		armed = true;
	}
	
	public void moveUp() {
		y.setValue(y.getValue()-speed);
	}
	
	public void moveDown() {
		y.setValue(y.getValue()+speed);
	}

	public void moveLeft() {
		x.setValue(x.getValue()-speed);
	}
	
	public void moveRight() {
		x.setValue(x.getValue()+speed);
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public void rearm() {
		armed = true;
	}

	public void disarm() {
		armed = false;		
	}

}
