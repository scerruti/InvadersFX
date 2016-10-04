package application.controller;

import java.util.HashSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class BulletManager {
	Pane game;
	HashSet<Bullet> bullets = new HashSet<>();

	public void fireFrom(Ship ship) {
		// Fire from center top of ship
		double x = ship.xProperty().doubleValue() + ship.getLayoutBounds().getWidth()/2.0;
		double y = ship.yProperty().doubleValue();

		final Bullet bullet = new Bullet(x, y);
		game.getChildren().add(bullet);
		bullets.add(bullet);

		bullet.yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() == 0.0) {
					game.getChildren().remove(bullet);
					bullets.remove(bullet);
				}
			}
		});
	}

	public void setGame(Pane gameArea) {
		this.game = gameArea;
	}
}
