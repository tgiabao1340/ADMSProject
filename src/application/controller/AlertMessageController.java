package application.controller;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AlertMessageController {
	Handler handler;

	@FXML
	private Label messageLabel;

	@FXML
	private Label detailsLabel;

	@FXML
	private HBox actionParent;

	@FXML
	private Button actionButton;

	@FXML
	private Button cancelButton;

	@FXML
	private HBox okParent;

	@FXML
	private Button okButton;

	@FXML
	void AlertAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == okButton) {
			Main.closeWindow(btn);
		}
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		ErrorAlert error = handler.getError();
		messageLabel.setText(error.getMessage());
		detailsLabel.setText(error.getDetail());
	}
}
