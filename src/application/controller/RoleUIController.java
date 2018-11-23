package application.controller;

import java.io.InputStream;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.EmployeeDAO;
import application.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoleUIController {
	Handler handler;

	@FXML
	private ImageView image_manager;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnManager;

	@FXML
	private ImageView image_employee;

	@FXML
	private ImageView image_admin;

	@FXML
	private Button btnEmployeeUI;

	@FXML
	private Button btnAdminUI;

	private Employee emp;

	@FXML
	void roleAction(ActionEvent event) {

		Object btn = event.getSource();

		if (btn == btnEmployeeUI) {
			Main.changeLayout("EmployeeUI");
		}
		if (btn == btnManager) {
			Main.changeLayout("ManagementTask");
		}
		if (btn == btnLogout) {
			Main.newWindow("Login", "Đăng Nhập");
			Main.disableMainwindow();
		}
		if (btn == btnAdminUI) {
			Main.changeLayout("AdminPanel");
		}
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		EmployeeDAO emdao = new EmployeeDAO();
		emp = emdao.findByAc(handler.getAccount_using());
		InputStream url1 = getClass().getResourceAsStream("/application/style/employee-icon.png");
		image_employee.setImage(new Image(url1, 80, 90, false, true));
		InputStream url2 = getClass().getResourceAsStream("/application/style/manager-icon.png");
		image_manager.setImage(new Image(url2, 80, 90, false, true));
		InputStream url3 = getClass().getResourceAsStream("/application/style/admin-icon.png");
		image_admin.setImage(new Image(url3, 80, 90, false, true));
		if (emp == null) {
			btnEmployeeUI.setDisable(true);
			btnManager.setDisable(true);
			btnAdminUI.setDisable(true);
			ErrorAlert error = new ErrorAlert("Lỗi",
					"Tài khoản không có chức năng trong hệ thống, hãy liên hệ người quản lý hệ thống");
			handler.setError(error);
			Main.newWindow("AlertMessage", "Thông báo");
		} else {
			String pos = emp.getPosition();
			if (pos.equalsIgnoreCase("NV Bán hàng")) {
				Main.changeLayout("EmployeeUI");
			}
			if (pos.equalsIgnoreCase("NV Quản lý")) {
				btnAdminUI.setDisable(true);
			}
		}

	}
}
