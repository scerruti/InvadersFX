package application.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import application.model.GameObject;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Game {

	@FXML
	Pane gameArea;

	private final LongProperty timeProperty = new SimpleLongProperty();
	private Ship ship;
	private Random rng = new Random();

	@FXML
	public void initialize() {

		// Keep objects drawn off board from expanding board
		Rectangle clipRectangle = new Rectangle();
		gameArea.setClip(clipRectangle);
		gameArea.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			clipRectangle.setWidth(newValue.getWidth());
			clipRectangle.setHeight(newValue.getHeight());
		});

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				timeProperty.set(now);
			}
		}.start();

	}

	public void registerForTimeUpdates(ChangeListener<? super Number> listener) {
		timeProperty.addListener(listener);
	}

	public void unregisterForTimeUpdates(ChangeListener<Number> listener) {
		timeProperty.removeListener(listener);
	}

	public void registerBoundsChanged(ChangeListener<Bounds> changeListener) {
		gameArea.boundsInLocalProperty().addListener(changeListener);
	}

	public void unregisterBoundsChanged(ChangeListener<Bounds> changeListener) {
		gameArea.boundsInLocalProperty().removeListener(changeListener);
	}

	@FXML
	public void keyHandler(KeyEvent event) {
		switch (event.getCode()) {
		case DOWN:
			ship.moveDown();
			break;
		case ESCAPE:
			Platform.exit();
			break;
		case LEFT:
			ship.moveLeft();
			break;
		case RIGHT:
			ship.moveRight();
			break;
		case SPACE:
			ship.fire();
			break;
		case UP:
			ship.moveUp();
			break;
		default:
			break;
		}
	}

	@FXML
	public void mouseHandler(MouseEvent event) {
	}

	public void add(GameObject object) {
		object.addToGameController(this);
	}

	public void addGenericObject(GameObject gameObject) {
		gameArea.getChildren().add(gameObject.getControllerObject(gameArea));
	}

	public void remove(Node node) {
		gameArea.getChildren().remove(node);
	}

	public void addEnemy(application.model.Enemy enemyModel) {
		double position = rng.nextDouble() * gameArea.getWidth() * 0.8 + 
				gameArea.getWidth() / 10.0;
		enemyModel.setX(position);
		enemyModel.setRotation(rng.nextBoolean());

		gameArea.getChildren().add(new Enemy(gameArea, enemyModel));
	}

	public void addShip(application.model.Ship shipModel) {
		this.ship = new Ship(gameArea, shipModel);
		gameArea.getChildren().add(this.ship);
	}

	public void collision(Collider node) {
		boolean impact = false;
		List<Node> hitNodes = new ArrayList<Node>();
		List<Node> nodes = gameArea.getChildren();
		for (Node otherNode : nodes) {
			if ((otherNode instanceof Collider) && node.collidesWith(otherNode)) {
				hitNodes.add(otherNode);
				impact = true;
			}
		}

		if (impact) {
			Iterator<Node> i = hitNodes.iterator();
			while (i.hasNext()) {
				((Collider) i.next()).impact();
			}
			node.impact();
		}

	}
}
