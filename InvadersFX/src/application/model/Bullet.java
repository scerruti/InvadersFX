package application.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Bullet extends TimeMotionObject {
	
	public Bullet(double x, double y) {
		super(x, y);
		view = "/application/view/Bullet.fxml";
	}

	@Override
	public void move(long duration) {
		double distance = (duration / 1000000);
		y.setValue(y.getValue() - distance);
	}

	@Override
	public Node getControllerObject(Pane game) {
		return new application.controller.Bullet(game, this);
	}

}
