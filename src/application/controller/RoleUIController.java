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
		String pos = emp.getPosition();
		Object btn = event.getSource();
		if (pos.equalsIgnoreCase("NV Bán hàng")) {
			if (btn == btnEmployeeUI) {
				Main.changeLayout("EmployeeUI");
			}
			if (btn == btnManager) {
				Main.changeLayout("ManagementTask");
			}
			if (btn == btnAdminUI) {
				ErrorAlert alert = new ErrorAlert("Giới hạn truy cập", "Bạn không thể sử dụng chức năng này!");
				handler.setError(alert);
				Main.newWindow("AlertMessage", "Thông báo");
			}
			if (btn == btnLogout) {
				Main.newWindow("Login", "Đăng Nhập");
				Main.disableMainwindow();
			}
		} else if (pos.equalsIgnoreCase("NV Kỹ thuật")) {
			if (btn == btnEmployeeUI) {
				Main.changeLayout("EmployeeUI");
			}
			if (btn == btnManager) {
				ErrorAlert alert = new ErrorAlert("Giới hạn truy cập", "Bạn không thể sử dụng chức năng này!");
				handler.setError(alert);
				Main.newWindow("AlertMessage", "Thông báo");
			}
			if (btn == btnAdminUI) {
				ErrorAlert alert = new ErrorAlert("Giới hạn truy cập", "Bạn không thể sử dụng chức năng này!");
				handler.setError(alert);
				Main.newWindow("AlertMessage", "Thông báo");
			}
			if (btn == btnLogout) {
				Main.newWindow("Login", "Đăng Nhập");
				Main.disableMainwindow();
			}
		} else {
			if (btn == btnAdminUI) {
				Main.changeLayout("AdminPanel");
			}

		}
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		EmployeeDAO emdao = new EmployeeDAO();
		emp = emdao.getByAc(handler.getAccount_using());
		InputStream url1 = getClass().getResourceAsStream("/application/style/employee-icon.png");
		image_employee.setImage(new Image(url1, 80, 90, false, true));
		InputStream url2 = getClass().getResourceAsStream("/application/style/manager-icon.png");
		image_manager.setImage(new Image(url2, 80, 90, false, true));
		InputStream url3 = getClass().getResourceAsStream("/application/style/admin-icon.png");
		image_admin.setImage(new Image(url3, 80, 90, false, true));
	}
}
