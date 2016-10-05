package application.model;

import application.InvadersFX;
import application.controller.Game;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Ship extends GameObject {

	private boolean armed;
	private double speed;

	private double cannonX;
	private double cannonY;

	public Ship(double x, double y) {
		super(x, y);
		view = "/application/view/Ship.fxml";

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
		double x = getX() + getCannonX();
		double y = getY() + getCannonY();
		InvadersFX.addGameObject(new Bullet(x, y));
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
	public Node getControllerObject(Pane game) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void impact() {
		super.impact();
		InvadersFX.gameOver();
	}

	

}
