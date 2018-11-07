package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MaintenaceReportController {

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnCheckout;

	@FXML
	void MRAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnCancel) {
			Main.changeLayout("EmployeeUI");
		}
	}
}
