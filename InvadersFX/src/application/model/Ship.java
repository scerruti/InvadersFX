package application.model;

import application.InvadersFX;
import application.controller.Game;
import javafx.scene.Node;

public class Ship extends GameObject {

	private boolean armed;
	private double speed;

	private double cannonX;
	private double cannonY;

	public Ship(double x, double y) {
		super(x, y);

		speed = 5.0;
		armed = true;
	}

	public void moveUp() {
		y.setValue(y.getValue() - speed);
	}

	public void moveDown() {
		y.setValue(y.getValue() + speed);
	}

	public void moveLeft() {
		x.setValue(x.getValue() - speed);
	}

	public void moveRight() {
		x.setValue(x.getValue() + speed);
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

	public void fire() {
		InvadersFX.fire(this);
	}

	public double getCannonX() {
		return cannonX;
	}

	public void setCannonX(double cannonX) {
		this.cannonX = cannonX;
	}

	public double getCannonY() {
		return cannonY;
	}

	public void setCannonY(double cannonY) {
		this.cannonY = cannonY;
	}

	@Override
	public void addToGameController(Game game) {
		game.addShip(this);
	}

	@Override
	public Node getControllerObject() {
		throw new UnsupportedOperationException();
	}

	public void impact() {
		// TODO game over
		kill();
	}

}
