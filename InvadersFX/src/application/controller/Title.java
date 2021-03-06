package application.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import application.InvadersFX;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Title {
	@FXML
	TextArea instructionArea;

	@FXML
	protected void handlePlayButtonAction(ActionEvent event) {
		InvadersFX game = InvadersFX.getInstance();
		if (game != null) {
			try {
				game.showGame();
			} catch (Exception ex) {
				Alert alert = new Alert(AlertType.ERROR, "Error Loading Game", ButtonType.CLOSE);
				alert.setTitle("Game Error");
				alert.setHeaderText("Error Loading Game");
				alert.setContentText(ex.getLocalizedMessage());

				try {
					FXMLLoader loader = new FXMLLoader();
					Pane exceptionPane = (GridPane) loader
							.load(getClass().getResource("/application/view/ExpandableException.fxml").openStream());

					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					ex.printStackTrace(pw);
					((ExpandableException) loader.getController()).setStackTrace(sw.toString());

					alert.getDialogPane().setExpandableContent(exceptionPane);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				alert.showAndWait();
				Platform.exit();
			}
		}
	}

	@FXML
	public void keyHandler(KeyEvent event) {
		switch (event.getCode()) {
		case ESCAPE:
			Platform.exit();
			break;
		case SPACE:
			instructionArea.setVisible(!instructionArea.isVisible());
			break;
		default:
			break;
		}
	}
}
