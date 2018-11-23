package application.controller;

import java.time.LocalDate;

import application.Handler;
import application.Main;
import application.entities.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmployeeInfoInputController {
	private Handler handler;
	@FXML
	private TextField textEmployee_ID;

	@FXML
	private TextField textEmployee_LNAME;

	@FXML
	private TextField textEmployee_FNAME;

	@FXML
	private CheckBox checkEmployee_GENDER;

	@FXML
	private TextField textEmployee_PHONE;

	@FXML
	private DatePicker dpEmployee_DATE;

	@FXML
	private TextArea textEmployee_AD;

	@FXML
	private Button btnCreate_E;

	@FXML
	private Button btnCancel;

	@FXML
	void AAction(ActionEvent event) {
		if (event.getSource().equals(btnCancel)) {
			Main.closeWindow(btnCancel);
		}
		if (event.getSource().equals(btnCreate_E)) {

			// if (check()) {
			Employee employee = new Employee();
			setE(employee);
			System.out.println(employee);
			
			handler.setNew_Employee(employee);
			Main.closeWindow(btnCreate_E);
			// }
		}
	}

	Employee setE(Employee employee) {
		String lname = textEmployee_LNAME.getText();
		String fname = textEmployee_FNAME.getText();
		String address = textEmployee_AD.getText();
		String phone = textEmployee_PHONE.getText();
		LocalDate dob = dpEmployee_DATE.getValue();
		Boolean gender = checkEmployee_GENDER.isSelected();
		employee.setLastName(lname);
		employee.setFirstName(fname);
		employee.setAddress(address);
		employee.setPhoneNumber(phone);
		employee.setDateOfBirth(dob);
		employee.setGender(gender);
		return employee;
	}

	boolean check() {
		if (textEmployee_ID.getText().isEmpty()) {
			textEmployee_ID.getStyleClass().add("error");
			textEmployee_ID.requestFocus();
			return false;
		}
		if (textEmployee_FNAME.getText().isEmpty()) {
			textEmployee_FNAME.getStyleClass().add("error");
			textEmployee_FNAME.requestFocus();
			return false;
		}
		if (textEmployee_LNAME.getText().isEmpty()) {
			textEmployee_LNAME.getStyleClass().add("error");
			textEmployee_LNAME.requestFocus();
			return false;
		}
		if (textEmployee_AD.getText().isEmpty()) {
			textEmployee_AD.getStyleClass().add("error");
			textEmployee_AD.requestFocus();
			return false;
		}
		if (textEmployee_PHONE.getText().isEmpty()) {
			textEmployee_PHONE.getStyleClass().add("error");
			textEmployee_PHONE.requestFocus();
			return false;
		}
		textEmployee_ID.getStyleClass().remove("error");
		textEmployee_FNAME.getStyleClass().remove("error");
		textEmployee_LNAME.getStyleClass().remove("error");
		textEmployee_AD.getStyleClass().remove("error");
		textEmployee_PHONE.getStyleClass().remove("error");
		return true;
	}

	void initialize() {
		handler = Main.getHandler();
	}
}
