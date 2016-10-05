package application.controller;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ship extends ImageView {
	final application.model.Ship shipModel;
	private Pane game;

	public Ship(Pane gameArea, application.model.Ship shipModel) {
		this.game = gameArea;
		this.shipModel = shipModel;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/Ship.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		this.xProperty().bindBidirectional(this.shipModel.xProperty());
		this.yProperty().bindBidirectional(this.shipModel.yProperty());
		
		this.xProperty().setValue(game.getWidth()/2.0 - this.getLayoutBounds().getWidth()/2.0);
		this.yProperty().setValue(game.getHeight() - this.getLayoutBounds().getHeight());
		
		this.boundsInLocalProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				// TODO Auto-generated method stub
				shipModel.setCannonX(newValue.getWidth()/2.0);
			}
		});
		this.shipModel.setCannonX(this.getBoundsInLocal().getWidth()/2.0);
		this.shipModel.setCannonY(0.0);
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
			shipModel.fire();
		} else {
			shipModel.rearm();
		}
	}
}
