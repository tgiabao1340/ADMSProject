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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<String> comboPlace;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextArea txtAddress;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnCancel;
	void load64city() {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("An Giang");
		list.add("Bà Rịa - Vũng Tàu");
		list.add("Bắc Giang");
		list.add("Bắc Kạn");
		list.add("Bạc Liêu");
		list.add("Bắc Ninh");
		list.add("Bến Tre");
		list.add("Bình Định");
		list.add("Bình Dương");
		list.add("Bình Phước");
		list.add("Bình Thuận");
		list.add("Cà Mau");
		list.add("Cao Bằng");
		list.add("Đắk Lắk");
		list.add("Đắk Nông");
		list.add("Điện Biên");
		list.add("Đồng Nai");
		list.add("Đồng Tháp");
		list.add("Gia Lai");
		list.add("Hà Giang ");
		list.add("Hà Nam");
		list.add("Hà Tĩnh");
		list.add("Hải Dương");
		list.add("Hậu Giang");
		list.add("Hòa Bình");
		list.add("Hưng Yên");
		list.add("Khánh Hòa");
		list.add("Kiên Giang");
		list.add("Kon Tum");
		list.add("Lai Châu");
		list.add("Lâm Đồng");
		list.add("Lạng Sơn");
		list.add("Lào Cai");
		list.add("Long An");
		list.add("Nam Định");
		list.add("Nghệ An");
		list.add("Ninh Bình");
		list.add("Ninh Thuận");
		list.add("Phú Thọ");
		list.add("Quảng Bình");
		list.add("Quảng Nam");
		list.add("Quảng Ngãi");
		list.add("Quảng Ninh");
		list.add("Quảng Trị");
		list.add("Sóc Trăng");
		list.add("Sơn La");
		list.add("Tây Ninh");
		list.add("Thái Bình");
		list.add("Thái Nguyên");
		list.add("Thanh Hóa");
		list.add("Thừa Thiên Huế");
		list.add("Tiền Giang");
		list.add("Trà Vinh");
		list.add("Tuyên Quang");
		list.add("Vĩnh Long");
		list.add("Vĩnh Phúc");
		list.add("Yên Bái");
		list.add("Phú Yên");
		list.add("Cần Thơ");
		list.add("Đà Nẵng");
		list.add("Hải Phòng");
		list.add("Hà Nội");
		list.add("TP HCM");
		comboPlace.getItems().addAll(list);
	}
	@FXML
	void initialize() {
		handler = Main.getHandler();
		String phone = handler.getNew_phone();
		load64city();
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
				customer.setPermanentAddress(comboPlace.getSelectionModel().getSelectedItem().trim());
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
