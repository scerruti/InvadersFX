package application.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Ship extends Collider {

	public Ship(Pane game, application.model.Ship modelShip) {
		super(game, modelShip);

		// Initial position
		this.xProperty().setValue(game.getWidth() / 2.0 - this.getLayoutBounds().getWidth() / 2.0);
		this.yProperty().setValue(game.getHeight() - this.getLayoutBounds().getHeight());

		// Compute canon location
		this.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				((application.model.Ship) Ship.this.model).setCannonX(newValue.getWidth() / 2.0);
			}
		});
		((application.model.Ship) this.model).setCannonX(this.getBoundsInLocal().getWidth() / 2.0);
		((application.model.Ship) this.model).setCannonY(0.0);

	}

	public void moveUp() {
		((application.model.Ship) this.model).moveUp();
		if (model.getY() < game.getBoundsInLocal().getMinY()) {
			model.setY(game.getBoundsInParent().getMinY());
		}
	}

	public void moveDown() {
		((application.model.Ship) this.model).moveDown();
		if ((model.getY() + this.getBoundsInParent().getHeight()) > game.getBoundsInLocal().getMaxY()) {
			model.setY(game.getBoundsInLocal().getMaxY() - Ship.this.getBoundsInParent().getHeight());

		}
	}

	public void moveLeft() {
		((application.model.Ship) this.model).moveLeft();
		if (model.getX() < game.getBoundsInLocal().getMinX()) {
			model.setX(game.getBoundsInParent().getMinX());
		}
	}

	public void moveRight() {
		((application.model.Ship) this.model).moveRight();
		if ((model.getX() + this.getBoundsInParent().getWidth()) > game.getBoundsInLocal().getMaxX()) {
			model.setX(game.getBoundsInLocal().getMaxX() - Ship.this.getBoundsInParent().getWidth());

		}
	}

	public void fire() {
		if (((application.model.Ship) this.model).isArmed()) {
			((application.model.Ship) this.model).fire();
		} else {
			((application.model.Ship) this.model).rearm();
		}
	}

	@Override
	public boolean canCollide(Node node) {
		return node instanceof Enemy;
	}
}
