package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerInputController {
	Handler handler;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtCustomerID;

	@FXML
	private TextField txtLName;

	@FXML
	private TextField txtFName;

	@FXML
	private CheckBox checkGender;

	@FXML
	private DatePicker datePickBirth;

	@FXML
	private TextField txtIDcard;

	@FXML
	private DatePicker datePickIdCard;

	@FXML
	private ComboBox<?> comboPlace;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextArea txtAddress;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;

	@FXML
	void initialize() {
		handler = Main.getHandler();
		String phone = handler.getNew_phone();
		btnSave.setOnAction(e -> {
			boolean gender;
			if (checkGender.isSelected()) {
				gender = false;
			} else
				gender = true;
			if (txtFName.getText().equals("") || txtLName.getText().equals("") || txtAddress.getText().equals("")
					|| datePickBirth.equals(null) || txtPhone.getText().equals("") || txtIDcard.getText().equals("")
					|| datePickIdCard.equals(null)) {
				ErrorAlert alert = new ErrorAlert("Lỗi!", "Tất cả các trường không được để trống!");
				handler.setError(alert);
				Main.newWindow("AlertMessage", "Thông báo");
			} else {

				Customer customer = new Customer(txtFName.getText().trim(), txtLName.getText().trim(),
						txtAddress.getText().trim(), gender, datePickBirth.getValue(), txtPhone.getText().trim(),
						LocalDate.now(), txtIDcard.getText().trim(), datePickIdCard.getValue(),"");
				System.out.println(customer);
				CustomerDAO customerDAO = new CustomerDAO();
				Boolean check = customerDAO.save(customer);
				if (check)
					Main.closeWindow(btnSave);
				else {
					///
				}

			}

		});

		btnCancel.setOnAction(e -> {
			Main.closeWindow(btnCancel);

		});
		handler = Main.getHandler();
		CustomerDAO cusDAO = new CustomerDAO();
		List<Customer> listcustomer = cusDAO.getAll(Customer.class);

		if (!listcustomer.isEmpty()) {
			String odlast = listcustomer.get(listcustomer.size() - 1).getBussinessID();
			String prefix = odlast.substring(0, 2);
			int numberOd = Integer.valueOf((odlast.substring(2, odlast.length())));
			String cusID = prefix + String.format("%04d", numberOd + 1);
			txtCustomerID.setText(cusID);
		} else {
			txtCustomerID.setText("KH0001");
		}
	}
}
