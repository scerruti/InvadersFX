package application.controller;

import java.io.IOException;

import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Enemy extends ImageView implements Collider {
	final application.model.Enemy enemyModel;
	final Pane game;

	public Enemy(Pane gameArea, application.model.Enemy enemyModel) {
		this.game = gameArea;
		this.enemyModel = enemyModel;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/Enemy.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		this.xProperty().bindBidirectional(enemyModel.xProperty());
		this.yProperty().bindBidirectional(enemyModel.yProperty());
		
		yProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() >= gameArea.getHeight()) {
					enemyModel.kill();
					// GAME OVER
				}
				
			}
		});
		
		enemyModel.getAlive().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue.booleanValue()) {
					InvadersFX.removeGameNode(Enemy.this);
					enemyModel.getAlive().removeListener(this);
				}

			}
			
		});
		
		this.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				InvadersFX.checkForCollisions(Enemy.this);
			}
		});
				
	}

	@Override
	public boolean collidesWith(Node otherNode) {
		if (otherNode instanceof Bullet &&
				otherNode.getBoundsInParent().intersects(getBoundsInParent())) {
				return true;
			} else {
				return false;
			}
	}

	@Override
	public void impact() {
		enemyModel.impact();
	}

}
