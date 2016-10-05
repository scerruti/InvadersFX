package application.controller;

import java.io.IOException;
import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView implements Collider {
	application.model.Bullet bulletModel;

	public Bullet(application.model.Bullet bullet) {
		this.bulletModel = bullet;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/Bullet.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		this.xProperty().bindBidirectional(bulletModel.xProperty());
		this.yProperty().bindBidirectional(bulletModel.yProperty());

		// Center and advance bullet based on size
		this.xProperty().set(this.xProperty().doubleValue() - this.getBoundsInLocal().getWidth() / 2.0);
		this.yProperty().set(this.yProperty().doubleValue() - this.getBoundsInLocal().getHeight() / 2.0);

		this.yProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() <= 0) {
					bulletModel.kill();
					observable.removeListener(this);
					InvadersFX.removeGameNode(Bullet.this);
				}
			}
		});

		this.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				InvadersFX.checkForCollisions(Bullet.this);
			}
		});

		bulletModel.getAlive().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue.booleanValue()) {
					InvadersFX.removeGameNode(Bullet.this);
					bulletModel.getAlive().removeListener(this);
				}

			}

		});
	}

	@Override
	public boolean collidesWith(Node otherNode) {
		if (otherNode instanceof Enemy && otherNode.getBoundsInParent().intersects(getBoundsInParent())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void impact() {
		bulletModel.kill();
	}
}
