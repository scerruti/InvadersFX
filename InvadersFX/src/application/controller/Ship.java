package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ship extends ImageView {
	application.model.Ship shipModel;
	BulletManager bulletManager;
	private Pane game;

	public Ship() {
		this.shipModel = new application.model.Ship(-50, -50);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/Ship.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		this.xProperty().bindBidirectional(shipModel.xProperty());
		this.yProperty().bindBidirectional(shipModel.yProperty());
	}
	
	public void setGame(Pane gameArea) {
		this.game = gameArea;
		
		this.xProperty().setValue(game.getWidth()/2.0 - this.getLayoutBounds().getWidth()/2.0);
		this.yProperty().setValue(game.getHeight() - this.getLayoutBounds().getHeight());
	}

	public void setBulletManager(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}
	
	public void moveUp() {
		shipModel.moveUp();
		if (this.yProperty().getValue() < 0) {
			this.yProperty().setValue(0);
		}		
	}

	public void moveDown() {
		shipModel.moveDown();		
		double height = this.getBoundsInLocal().getHeight();
		if (this.yProperty().getValue() > (game.getHeight() - height)) {
			this.yProperty().setValue(game.getHeight() - height);
		}		
	}

	public void moveLeft() {
		shipModel.moveLeft();
		if (this.xProperty().getValue() < 0) {
			this.xProperty().setValue(0);
		}		
	}

	public void moveRight() {
		shipModel.moveRight();
		double width = this.getBoundsInLocal().getWidth();
		if (this.xProperty().getValue() > (game.getWidth() - width)) {
			this.xProperty().setValue(game.getWidth() - width);
		}		
	}

	public void fire() {
		if (shipModel.isArmed()) {
			shipModel.disarm();
			bulletManager.fireFrom(this);
		} else {
			shipModel.rearm();
		}
	}
}
