package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConfirmPasswordController {

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnOk;

	@FXML
	void CAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnOk) {
			Main.closeWindow(btn);

		}
		if (btn == btnCancel) {
			Main.closeWindow(btn);

		}
	}

}