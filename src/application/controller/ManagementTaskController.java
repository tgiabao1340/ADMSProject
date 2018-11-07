package application.controller;

import java.util.List;

import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.entities.Customer;
import application.entities.Order;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManagementTaskController {
	Handler handler;
	@FXML
	private Button btnBack;

	@FXML
	private TableView<Customer> tableCustomer;

	@FXML
	void MAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnBack) {
			Main.changeLayout("RoleUI");
		}
	}

	void loadCustomer() {
		List<Customer> listCustomer = new CustomerDAO().getAll(Customer.class);
		final TableColumn<Order, String> colSTT = new TableColumn<>("STT");
		final TableColumn<Order, String> colCustomerID = new TableColumn<>("Mã Khách hàng");
		final TableColumn<Order, String> colLastName = new TableColumn<>("Họ");
		final TableColumn<Order, String> colFirstName = new TableColumn<>("Tên");
		final TableColumn<Order, String> colEmployeeID = new TableColumn<>("Mã Nhân viên");
		// final TableColumn<Order, String> colCustomerID = new TableColumn<>("Mã Khách
		// hàng");
		// colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
		// colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colEmployeeID.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getEmployee().getBussinessID()));
		colCustomerID.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getCustomer().getBussinessID()));
		ObservableList<Customer> items = FXCollections.observableArrayList(listCustomer);
		tableCustomer.setItems(items);
		// tableCustomer.getColumns().addAll(colOrderID, colDate, colEmployeeID,
		// colCustomerID);

	}

	@FXML
	void initialize() {
		handler = Main.getHandler();

	}

}
