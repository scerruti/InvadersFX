package application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import application.InvadersFX;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameObject extends ImageView {
	final application.model.GameObject model;
	final Pane game;

	public GameObject(Pane game, application.model.GameObject model) {
		this.game = game;
		this.model = model;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(model.getView()));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			Alert alert = new Alert(AlertType.ERROR, "Error Loading View", ButtonType.CLOSE);
			alert.setTitle("Game Error");
			alert.setHeaderText("Error Loading View");
			alert.setContentText(exception.getLocalizedMessage());

			try {
				FXMLLoader loader = new FXMLLoader();
				Pane exceptionPane = (GridPane) loader
						.load(getClass().getResource("/application/view/ExpandableException.fxml").openStream());

				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				exception.printStackTrace(pw);
				((ExpandableException) loader.getController()).setStackTrace(sw.toString());

				alert.getDialogPane().setExpandableContent(exceptionPane);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			alert.showAndWait();
			Platform.exit();
		}

		this.xProperty().bindBidirectional(model.xProperty());
		this.yProperty().bindBidirectional(model.yProperty());

		model.getAlive().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue.booleanValue()) {
					InvadersFX.removeGameNode(GameObject.this);
					model.getAlive().removeListener(this);
				}
			}
		});
	}
}
