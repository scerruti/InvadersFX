package application.controller;

import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Bullet extends Collider {

	public Bullet(Pane game, application.model.Bullet bullet) {
		super(game, bullet);

		// Center and advance bullet based on size
		this.xProperty().set(this.xProperty().doubleValue() - this.getBoundsInLocal().getWidth() / 2.0);
		this.yProperty().set(this.yProperty().doubleValue() - this.getBoundsInLocal().getHeight() / 2.0);

		// Remove bullets that exit the screen
		this.yProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() <= game.getBoundsInLocal().getMinY()) {
					model.kill();
					observable.removeListener(this);
					InvadersFX.removeGameNode(Bullet.this);
				}
			}
		});
	}

	@Override
	public boolean canCollide(Node node) {
		return node instanceof Enemy;
	}
}
