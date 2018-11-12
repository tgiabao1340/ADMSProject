package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.daos.EmployeeDAO;
import application.daos.MotorbikeDAO;
import application.daos.OrderDAO;
import application.daos.SupplierDAO;
import application.entities.Customer;
import application.entities.Employee;
import application.entities.Motorbike;
import application.entities.Order;
import application.entities.OrderDetail;
import application.entities.Supplier;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CreateOrderController {
	Handler handler;
	Order od;
	@FXML
	private ResourceBundle resources;

	@FXML
	private CheckBox checkReg;

	@FXML
	private CheckBox checkVAT;

	@FXML
	private URL location;

	@FXML
	private TextField textOrderID;

	@FXML
	private DatePicker textDate;

	@FXML
	private TextField textStoreName;

	@FXML
	private TextField textEmployeeID;

	@FXML
	private TextField textCustomer;

	@FXML
	private Button btnAddCustomer;

	@FXML
	private Label textCustomerName;

	@FXML
	private Label textCustomerPhone;

	@FXML
	private Label textCustomerIDCard;

	@FXML
	private Label textCustomerAdress;

	@FXML
	private ChoiceBox<String> choiceSupplier;

	@FXML
	private ChoiceBox<String> choiceType;

	@FXML
	private ChoiceBox<String> choiceMotorbikeName;

	@FXML
	private ChoiceBox<String> choiceColor;

	@FXML
	private Button btnMinus;

	@FXML
	private Button btnPlus;

	@FXML
	private Button btnAddMotorbike;

	@FXML
	private Button btnRemoveMotobike;

	@FXML
	private TableView<OrderDetail> tableOrderDetail;

	@FXML
	private Text textTax;

	@FXML
	private Text textTotal;

	@FXML
	private TextField textChassisp1;

	@FXML
	private TextField textChassisp2;

	@FXML
	private TextArea textComment;

	@FXML
	private Button btnCheckout;

	@FXML
	private Button btnCancel;
	
	@FXML
	private Button btnBack;

	@FXML
	void createOrderAction(final ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnCancel) {
			Main.changeLayout("EmployeeUI");
		}
		if (btn == btnBack) {
			Main.changeLayout("EmployeeUI");
		}
	}

	@FXML
	void initialize() {

		od = new Order();
		// System.out.println(od.toString());
		handler = Main.getHandler();
		OrderDAO odDAO = new OrderDAO();
		List<Order> listorder = odDAO.getAll(Order.class);
		///
		if (!listorder.isEmpty()) {
			String odlast = listorder.get(listorder.size() - 1).getOrderID();
			String prefix = odlast.substring(0, 2);
			int numberOd = Integer.valueOf((odlast.substring(2, odlast.length())));
			String odID = prefix + String.format("%04d", numberOd + 1);
			textOrderID.setText(odID);
		} else {
			textOrderID.setText("OR0000");
		}

		///
		List<OrderDetail> listod = new ArrayList<>();
		///
		LocalDate date = LocalDate.now();
		textDate.setValue(date);
		od.setDate(date);
		textDate.setDisable(true);
		//
		String storename = "YAMAHA HOANGCAU";
		textStoreName.setText(storename);
		// Employee
		EmployeeDAO emdao = new EmployeeDAO();
		Employee emp = emdao.getByAc(handler.getAccount_using());
		od.setEmployee(emp);
		//
		textEmployeeID.setText(emp.getLastName() + " " + emp.getFirstName());
		LoadType();
		LoadTable(listod);
		// Customer
		btnAddCustomer.setOnAction(e -> {
			handler.setNew_phone(textCustomer.getText());
			Main.newWindow("CustomerInfoInput", "Tạo khách hàng mới");
		});

		textCustomer.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					String cust_phone = textCustomer.getText();
					CustomerDAO custDAO = new CustomerDAO();
					Customer customer = custDAO.findbyPhone(cust_phone);
					if (customer != null) {
						od.setCustomer(customer);
						textCustomerName.setText(customer.getLastName() + " " + customer.getFirstName());
						textCustomerAdress.setText(customer.getAddress());
						textCustomerIDCard.setText(customer.getIdCard());
						textCustomerPhone.setText(customer.getPhoneNumber());
						textCustomer.getStyleClass().remove("error");
					} else {
						textCustomerName.setText("-unknown");
						textCustomerAdress.setText("-unknown");
						textCustomerIDCard.setText("-unknown");
						textCustomerPhone.setText("-unknown");
						textCustomer.getStyleClass().add("error");
					}
				}
			}
		});
		textChassisp1.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (textChassisp1.getText().equals("")) {
						textChassisp1.getStyleClass().add("error");

					} else {
						textChassisp1.getStyleClass().remove("error");
					}
				}
			}
		});
		textChassisp2.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					if (textChassisp2.getText().equals("")) {
						textChassisp2.getStyleClass().add("error");
					} else {
						textChassisp2.getStyleClass().remove("error");

					}
				}
			}
		});
		//
		//
		//
		btnCheckout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (checkOrder()) {
					OrderDAO odDao = new OrderDAO();
					od.setOrderComment(textComment.getText());
					odDao.save(od);
					// reload_Stock(od.getListItems());
					Main.changeLayout("EmployeeUI");
				}
			}

			private boolean checkOrder() {
				if (od.getCustomer() == null) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa điền thông tin khách hàng");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;

				}
				if (od.getListItems() == null || od.getListItems().size() == 0) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa có xe trong danh sách mua");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;
				}
				return true;
			}
		});
//		tableOrderDetail.setOnMouseClicked(event -> {
//			if (event.getClickCount() == 2) {
//				System.out.println(tableOrderDetail.getSelectionModel().getSelectedItem());
//			}
//		});
	}

	void LoadType() {
		MotorbikeDAO mDAO = new MotorbikeDAO();
		List<OrderDetail> list_detail = new ArrayList<>();
		List<Supplier> listSupplier = new SupplierDAO().getAll(Supplier.class);
		List<String> listSuplierName = new ArrayList<>();
		List<String> listType = new ArrayList<String>();
		for (int i = 0; i < listSupplier.size(); i++) {
			Supplier sup = listSupplier.get(i);
			if (!listSuplierName.contains(sup.getSupplierName()))
				listSuplierName.add(sup.getSupplierName());
		}
		choiceSupplier.getItems().addAll(listSuplierName);
		AtomicReference<String> sp = new AtomicReference<String>();
		choiceSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue != null) {
					sp.set(newValue);
					choiceMotorbikeName.getItems().clear();
				}
			}

		});

		listType.add("Tay ga");
		listType.add("Xe số");
		choiceType.getItems().addAll(listType);
		choiceType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					choiceMotorbikeName.getItems().clear();
					try {
						List<Motorbike> listMotorbike = mDAO.findbyTypeWSup(newValue, sp.get());
						List<String> listMotorbikeName = new ArrayList<>();

						for (int i = 0; i < listMotorbike.size(); i++) {
							String mb_name = listMotorbike.get(i).getProductName();
							if (!listMotorbikeName.contains(mb_name)) {
								listMotorbikeName.add(mb_name);
							}
						}
						choiceMotorbikeName.getItems().addAll(listMotorbikeName);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
		});
		AtomicReference<Motorbike> motorbike = new AtomicReference<Motorbike>();
		choiceMotorbikeName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					choiceColor.getItems().clear();
					List<String> colors = new ArrayList<>();
					Motorbike mb = mDAO.findbyName(newValue).get(0);
					motorbike.set(mb);
					String stringcolors = mb.getColor();
					String[] splitcolor = stringcolors.split(",");
					for (int i = 0; i < splitcolor.length; i++) {
						colors.add(splitcolor[i].trim());
					}
					choiceColor.getItems().addAll(colors);
				}
			}

		});
		AtomicReference<String> color_name = new AtomicReference<String>();
		choiceColor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					color_name.set(newValue);
				}
			}

		});
		AtomicInteger quantity = new AtomicInteger(1);

		/**
		 * Add motorbike to list
		 */
		btnAddMotorbike.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (motorbike.get() != null && validate()) {
					OrderDetail od_detail = new OrderDetail();
					od_detail.setOrder(od);
					od_detail.setMotorbike(motorbike.get());
					od_detail.setVAT(0.1);
					od_detail.setUnitPrice(motorbike.get().getUnitPrice());
					od_detail.setQuantity(quantity.get());
					od_detail.setChassisNo(textChassisp1.getText() + "/" + textChassisp2.getText());
					od_detail.setColor(color_name.get());
					list_detail.add(od_detail);
					od.setListItems(list_detail);
					loadTotal(list_detail);
					for (int i = 0; i < list_detail.size(); i++) {
						OrderDetail d = list_detail.get(i);
						d.setOrderDetailID(String.valueOf(i));
					}
					clearSection();
					reloadTable(list_detail);

				} else {
					// Messomething
				}
			}

			private boolean validate() {
				if (choiceSupplier.getSelectionModel().isEmpty() || choiceType.getSelectionModel().isEmpty()
						|| choiceMotorbikeName.getSelectionModel().isEmpty()
						|| choiceColor.getSelectionModel().isEmpty()) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa chọn thông tin xe");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;
				}
				if (textChassisp1.getText().isEmpty() || textChassisp1.getText().equals("")) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa điền thông tin số khung");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;
				}
				if (textChassisp2.getText().isEmpty() || textChassisp2.getText().equals("")) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa điền thông tin số sường");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;
				}
				return true;
			}

			private void clearSection() {
				choiceColor.getSelectionModel().clearSelection();
				choiceSupplier.getSelectionModel().clearSelection();
				choiceMotorbikeName.getSelectionModel().clearSelection();
				choiceType.getSelectionModel().clearSelection();
				textChassisp1.clear();
				textChassisp2.clear();

			}

		});
		btnRemoveMotobike.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				OrderDetail od_selected = tableOrderDetail.getSelectionModel().getSelectedItem();
				list_detail.remove(od_selected);
				loadTotal(list_detail);
				reloadTable(list_detail);
			}
		});

	}

	void reload_Stock(List<OrderDetail> list) {
		MotorbikeDAO mDAO = new MotorbikeDAO();
		for (int i = 0; i < list.size(); i++) {
			OrderDetail odd = list.get(i);
			int instock = odd.getMotorbike().getQuantity();
			System.out.println(instock);
			int new_instock = instock - odd.getQuantity();
			Motorbike mb = mDAO.getById(Motorbike.class, odd.getMotorbike().getProductID());
			mb.setQuantity(new_instock);
			mDAO.update(mb);
		}

	}

	void loadTotal(List<OrderDetail> list) {
		textTax.setText("");
		double total = 0;
		double tax = 0;
		total += od.getSubTotal();
		tax += od.getTotalVAT();
		textTax.setText(String.format("%,12.0f VND", tax));
		textTotal.setText(String.format("%,12.0f VND", total));
	}

	@SuppressWarnings("unchecked")
	void LoadTable(List<OrderDetail> list) {
		tableOrderDetail.setEditable(true);
		Callback<TableColumn<OrderDetail, String>, TableCell<OrderDetail, String>> cellFactory = col -> new TableCell<OrderDetail, String>() {
			{
				setEditable(true);
			}

			@Override
			public void startEdit() {
				super.startEdit();
				TextInputDialog dialog = new TextInputDialog(getItem());
				dialog.setGraphic(null);
				dialog.setHeaderText("Chỉnh sửa " + col.getText() + ".");
				dialog.setTitle("Chỉnh sửa " + col.getText());
				Optional<String> opt = dialog.showAndWait();
				if (opt.isPresent()) {
					commitEdit(opt.get());
				} else {
					cancelEdit();
				}
			}

			@Override
			public void commitEdit(String newValue) {
				System.out.println(newValue);
				super.commitEdit(newValue);
				for (int i = 0; i < list.size(); i++) {
					if (newValue.chars().allMatch(Character::isDigit)) {
						if (list.get(i).getOrderDetailID() == tableOrderDetail.getSelectionModel().getSelectedItem()
								.getOrderDetailID()) {
							list.get(i).setUnitPrice(Double.valueOf(newValue));
							reloadTable(list);
							super.commitEdit(newValue);
						}
					}
				}

			}

			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? null : item);
			}

		};
		///
		TableColumn<OrderDetail, OrderDetail> colNumbered = new TableColumn<>("STT");
		TableColumn<OrderDetail, String> colName = new TableColumn<>("Tên Xe");
		TableColumn<OrderDetail, String> colSup = new TableColumn<>("Hãng");
		TableColumn<OrderDetail, String> colColor = new TableColumn<>("Màu");
		TableColumn<OrderDetail, String> colQuantity = new TableColumn<>("Số Lượng");
		TableColumn<OrderDetail, String> colUP = new TableColumn<>("Đơn giá");
		TableColumn<OrderDetail, String> colST = new TableColumn<>("Thành tiền");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<OrderDetail, OrderDetail>, ObservableValue<OrderDetail>>() {

					@SuppressWarnings({ "rawtypes" })
					@Override
					public ObservableValue<OrderDetail> call(CellDataFeatures<OrderDetail, OrderDetail> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(
				new Callback<TableColumn<OrderDetail, OrderDetail>, TableCell<OrderDetail, OrderDetail>>() {

					@Override
					public TableCell<OrderDetail, OrderDetail> call(TableColumn<OrderDetail, OrderDetail> param) {

						return new TableCell<OrderDetail, OrderDetail>() {
							@Override
							protected void updateItem(OrderDetail arg0, boolean arg1) {

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
		colName.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getMotorbike().getProductName()));
		colSup.setCellValueFactory(celldata -> new SimpleStringProperty(
				celldata.getValue().getMotorbike().getSupplier().getSupplierName()));
		colColor.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getColor()));
		colQuantity.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getQuantity())));
		colUP.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.format("%,12.0f", celldata.getValue().getUnitPrice())));
		colUP.setCellFactory(cellFactory);
		colST.setCellValueFactory(celldata -> new SimpleStringProperty(
				String.format("%,12.0f", celldata.getValue().getUnitPrice() * celldata.getValue().getQuantity())));
		ObservableList<OrderDetail> items = FXCollections.observableArrayList(list);
		tableOrderDetail.setItems(items);
		tableOrderDetail.getColumns().addAll(colNumbered, colName, colSup, colColor, colQuantity, colUP, colST);

	}

	void reloadTable(List<OrderDetail> list) {
		tableOrderDetail.getColumns().clear();
		LoadTable(list);
	}
}
