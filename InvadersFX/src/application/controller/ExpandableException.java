package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ExpandableException {
	@FXML
	private TextArea stackTrace;

	public void setStackTrace(String stackTrace) {
		this.stackTrace.setText(stackTrace);;
	}
}
