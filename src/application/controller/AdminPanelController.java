package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminPanelController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnCAccount;
	@FXML
	private Button btnAddEmployee;

	@FXML
	void AdAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnBack) {
			Main.changeLayout("RoleUI");
		}
		if (btn == btnCAccount) {
			Main.newWindow("ConfirmPassword", "Xác nhận tạo tài khoản");
		}
		if (btn == btnAddEmployee) {
			Main.newWindow("EmployeeInfoInput", "Tạo nhân viên mới");
		}
	}

}
