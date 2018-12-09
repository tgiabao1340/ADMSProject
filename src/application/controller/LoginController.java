package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class LoginController {
	Handler handler;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnAbout;

	@FXML
	private Button btnHelp;

	@FXML
	private TextField passwordText;

	@FXML
	private TextField usernameText;

	@FXML
	void LoginAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnLogin) {
			doLogin();
		}
		if (btn == btnAbout) {
			Main.newWindow("About", "About");
		}
		if (btn == btnHelp) {
			Main.newWindow("Tutorial", "Hướng dẫn");
		}
	}

	void doLogin() {

		String username = usernameText.getText().trim();
		String password = passwordText.getText().trim();
		if (usernameText.getText().trim().equals("") || passwordText.getText().trim().equals("")) {
			ErrorAlert alert = new ErrorAlert("Lỗi đăng nhập", "Tên tài khoản và mật khẩu không được để trống!");
			handler.setError(alert);
			Main.newWindow("AlertMessage", "Thông báo");
			return;
		}
		if (!handler.validateLogin(username, password)) {
			ErrorAlert error = new ErrorAlert("LỖI ĐĂNG NHẬP", "Tài khoản hoặc mật khẩu không đúng");
			handler.setError(error);
			Main.newWindow("AlertMessage", "Thông báo");
			return;
		}
		if(handler.getAccount_using().getLastDate()!=null) {
			ErrorAlert error = new ErrorAlert("LỖI ĐĂNG NHẬP", "Truy cập bị từ chối.");
			handler.setError(error);
			Main.newWindow("AlertMessage", "Thông báo");
			return;
		}
		Main.changeLayout("RoleUI");
		Main.closeWindow(btnLogin);
		Main.enableMainwindow();

	}
	@FXML
	void initialize() {
		handler = Main.getHandler();
		passwordText.setOnKeyReleased((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				doLogin();
			}
		});
	}
}