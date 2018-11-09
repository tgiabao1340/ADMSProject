package application.controller;

import java.util.List;

import application.Handler;
import application.Main;
import application.daos.EmployeeDAO;
import application.daos.OrderDAO;
import application.entities.Employee;
import application.entities.Order;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class EmployeeController {
	Handler handler;
	OrderDAO odDao = new OrderDAO();
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
	private RadioButton raIDO;

	@FXML
	private RadioButton raIDC;

	@FXML
	private RadioButton raIDN;

	@FXML
	private TextField textSearch;

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

	@SuppressWarnings("unchecked")
	public void LoadListOrder(List<Order> listorder) {
		TableColumn<Order, Order> colNumbered = new TableColumn<>("STT");
		TableColumn<Order, String> colOrderID = new TableColumn<>("Mã Hóa đơn");
		TableColumn<Order, String> colDate = new TableColumn<>("Ngày lập");
		TableColumn<Order, String> colEmployeeID = new TableColumn<>("Mã Nhân viên");
		TableColumn<Order, String> colCustomerID = new TableColumn<>("Mã Khách hàng");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Order, Order>, ObservableValue<Order>>() {
					@SuppressWarnings("rawtypes")
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
					handler.setOrder_selected(tableOrder.getSelectionModel().getSelectedItem());
					Main.newWindow("OrderView", "Chi tiết hóa đơn");
				}
			}
		});
	}

	public void LoadListMaintenanceReport() {

	}

	List<Order> listorder;

	@FXML
	void clear() {
		textSearch.clear();
		listorder = odDao.getANumberofrecordsDescbyDate(50);
		reloadTable(listorder);
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();

		ToggleGroup gr = new ToggleGroup();
		raIDC.setToggleGroup(gr);
		raIDO.setToggleGroup(gr);
		raIDN.setToggleGroup(gr);

		textSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (raIDO.isSelected()) {

					if (!(textSearch.getText().isEmpty() && textSearch.getText().equals(""))) {

						listorder = odDao.getListOrderbyOrderID(textSearch.getText().trim());
						reloadTable(listorder);
					}
				}
				if (raIDN.isSelected()) {
					System.out.println(textSearch.getText());
					if (!(textSearch.getText().isEmpty() && textSearch.getText().equals(""))) {

						listorder = odDao.getListOrderbyEmployeeID(textSearch.getText().trim());
						reloadTable(listorder);
					}
				}
				if (raIDC.isSelected()) {
					System.out.println(textSearch.getText());
					if (!(textSearch.getText().isEmpty() && textSearch.getText().equals(""))) {

						listorder = odDao.getListOrderbyCustomerID(textSearch.getText().trim());
						reloadTable(listorder);
					}
				}

			}

		});
		EmployeeDAO empDAO = new EmployeeDAO();
		Employee emp = empDAO.getByAc(handler.getAccount_using());
		String name = emp.getLastName() + " " + emp.getFirstName();
		textName.setText(name);
		textID.setText(emp.getBussinessID());
		listorder = odDao.getANumberofrecordsDescbyDate(50);

		LoadListOrder(listorder);
	}

	void reloadTable(List<Order> list) {
		tableOrder.getColumns().clear();
		LoadListOrder(list);
	}
}
