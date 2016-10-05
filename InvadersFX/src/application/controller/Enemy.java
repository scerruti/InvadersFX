package application.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Enemy extends Collider {

	public Enemy(Pane game, application.model.Enemy enemyModel) {
		super(game, enemyModel);
		
		// Enemy left screen
		yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() >= game.getBoundsInLocal().getMaxY()) {
					model.kill();
				}

			}
		});
	}

	@Override
	public boolean canCollide(Node node) {
		return node instanceof Bullet || node instanceof Ship;
	}

}
