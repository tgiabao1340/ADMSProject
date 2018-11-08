package application.controller;

import java.util.List;

import application.Handler;
import application.Main;
import application.daos.EmployeeDAO;
import application.daos.OrderDAO;
import application.entities.Employee;
import application.entities.Order;
import application.entities.OrderDetail;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class EmployeeController {
	Handler handler;
	@FXML
	private Button btnLogout;

	@FXML
	private Button btnOrder;

	@FXML
	private Button btnMaintenanceReport;

	@FXML
	private Button btnback;

	@FXML
	private TableView<Order> tableOrder;

	@FXML
	private Text textName;

	@FXML
	private Text textID;

	@FXML
	void EAction(final ActionEvent event) {
		final Object btn = event.getSource();
		if (btn == btnLogout) {
			Main.disableMainwindow();
			Main.newWindow("Login", "Đăng Nhập");
		}
		if (btn == btnOrder) {
			Main.changeLayout("CreateOrder");
		}
		if (btn == btnMaintenanceReport) {
			Main.changeLayout("MaintenaceReport");
		}
		if (btn == btnback) {
			Main.changeLayout("RoleUI");
		}

	}

	public void LoadListOrder() {
		OrderDAO odDao = new OrderDAO();
		List<Order> listorder = odDao.getAll(Order.class);
		TableColumn<Order, Order> colNumbered = new TableColumn<>("STT");
		TableColumn<Order, String> colOrderID = new TableColumn<>("Mã Hóa đơn");
		TableColumn<Order, String> colDate = new TableColumn<>("Ngày lập");
		TableColumn<Order, String> colEmployeeID = new TableColumn<>("Mã Nhân viên");
		TableColumn<Order, String> colCustomerID = new TableColumn<>("Mã Khách hàng");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Order, Order>, ObservableValue<Order>>() {
					@Override
					public ObservableValue<Order> call(CellDataFeatures<Order, Order> param) {
						// TODO Auto-generated method stub
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Order, Order>, TableCell<Order, Order>>() {

			@Override
			public TableCell<Order, Order> call(TableColumn<Order, Order> param) {
				// TODO Auto-generated method stub
				return new TableCell<Order, Order>() {
					@Override
					protected void updateItem(Order arg0, boolean arg1) {
						// TODO Auto-generated method stub
						super.updateItem(arg0, arg1);
						if (this.getTableRow() != null && arg0 != null) {
							setText(this.getTableRow().getIndex() + 1 + "");
						} else {
							setText("");
						}
					}
				};
			}
		});
		colNumbered.setSortable(false);
		colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colEmployeeID.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getEmployee().getBussinessID()));
		colCustomerID.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getCustomer().getBussinessID()));
		ObservableList<Order> items = FXCollections.observableArrayList(listorder);
		tableOrder.setItems(items);
		tableOrder.getColumns().addAll(colNumbered, colOrderID, colDate, colEmployeeID, colCustomerID);
		tableOrder.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Order orderselected = tableOrder.getSelectionModel().getSelectedItem();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(orderselected.getOrderID());
					alert.setHeaderText("Tổng tiền : " + orderselected.getSubTotal() + " VND");
					String lines = "";
					List<OrderDetail> listdt = orderselected.getListItems();
					for (int i = 0; i < listdt.size(); i++) {
						OrderDetail odt = listdt.get(i);
						lines += "[" + odt.getOrderDetailID() + "]" + "\n" + odt.getMotorbike().getProductID()
								+ odt.getMotorbike().getProductName() + " * " + odt.getQuantity() + " = "
								+ String.format("%,d", odt.getTotalRaw()) + odt.getUnitPrice() + " VND" + "\n";

					}
					lines += "Người lập: " + orderselected.getEmployee().getLastName() + " "
							+ orderselected.getEmployee().getFirstName() + "\n" + "Người mua: "
							+ orderselected.getCustomer().getLastName() + " "
							+ orderselected.getCustomer().getFirstName() + "\n";
					alert.setContentText(lines);
					alert.showAndWait();

				}
			}
		});
	}

	public void LoadListMaintenanceReport() {

	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		EmployeeDAO empDAO = new EmployeeDAO();
		Employee emp = empDAO.getByAc(handler.getAccount_using());
		String name = emp.getLastName() + " " + emp.getFirstName();
		textName.setText(name);
		textID.setText(emp.getBussinessID());
		LoadListOrder();
	}

}
