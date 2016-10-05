package application.model;

import application.InvadersFX;
import application.controller.Game;
import javafx.application.Platform;
import javafx.scene.Node;

public class Enemy extends TimeMotionObject {
	private long cumulativeDuration = 0;
	private boolean rotation;

	public Enemy(int x, int y) {
		super(x, y);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				InvadersFX.addGameObject(Enemy.this);
			}
		});
	}

	@Override
	public void move(long duration) {
		cumulativeDuration += duration;
		long frames = 10 * cumulativeDuration / 1000000000;
				
		double distance = (duration / 10000000);
		double secondaryYMotion = 5 * Math.cos(frames);
		double secondaryXMotion = 5 * Math.sin(frames);
		double newY = y.getValue() + distance + secondaryYMotion;
		double newX = x.getValue() + (rotation?secondaryXMotion:-secondaryXMotion);
		x.setValue(newX);
		y.setValue(newY);
	}

	public void setRotation(boolean rotation) {
		this.rotation = rotation;
	}

	@Override
	public void addToGameController(Game game) {
		game.addEnemy(this);
	}

	@Override
	public Node getControllerObject() {
		throw new UnsupportedOperationException();
	}
}
