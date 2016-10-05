package application.controller;

import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Collider extends GameObject {

	public Collider(Pane game, application.model.GameObject model) {
		super(game, model);

		this.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				InvadersFX.checkForCollisions(Collider.this);
			}
		});
	}

	public boolean collidesWith(Node otherNode) {
		if (canCollide(otherNode) && otherNode.getBoundsInParent().intersects(getBoundsInParent())) {
			return true;
		} else {
			return false;
		}
	}

	public void impact() {
		model.impact();
	}
	
	public abstract boolean canCollide(Node node);
}
