package application.model;

import javafx.scene.Node;

public class Bullet extends TimeMotionObject {
	public Bullet(double x, double y) {
		super(x, y);
	}	
	
	@Override
	public void move(long duration) {
		double distance = (duration / 1000000);
		y.setValue(y.getValue() - distance);
	}

	@Override
	public Node getControllerObject() {
		return new application.controller.Bullet(this);
	}
}
