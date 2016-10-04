package application.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {
	application.model.Bullet bulletModel;

	public Bullet(double x, double y) {
		bulletModel = new application.model.Bullet(x, y);
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
		this.xProperty().set(this.xProperty().doubleValue() - this.getBoundsInLocal().getWidth()/2.0);
		this.yProperty().set(this.yProperty().doubleValue() - this.getBoundsInLocal().getHeight()/2.0);
	}

}
