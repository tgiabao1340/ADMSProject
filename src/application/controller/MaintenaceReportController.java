package application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.EmployeeDAO;
import application.daos.MaintenanceReportDAO;
import application.daos.ReplacementDAO;
import application.daos.SupplierDAO;
import application.entities.Employee;
import application.entities.MaintenanceReport;
import application.entities.MaintenanceReportDetail;
import application.entities.Model;
import application.entities.OrderDetail;
import application.entities.Replacement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class MaintenaceReportController {
	Handler handler;
	MaintenanceReport rp;
	List<Replacement> listrp;
	@FXML
	private TextField textMaintenaceReportID;

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
	private ChoiceBox<Supplier> choiceSupplier;

	@FXML
	private ChoiceBox<Model> choiceModel;

	@FXML
	private ChoiceBox<Replacement> choiceReplacement;

	@FXML
	private Button btnRemoveReplacement;

	@FXML
	private Button btnAddReplacement;

	@FXML
	private TableView<MaintenanceReportDetail> tableMaintenaceReportDetail;

	@FXML
	private Text textTax;

	@FXML
	private Text textTotal;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnCheckout;

	@FXML
	void createOrderAction(final ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnCancel) {
			Main.changeLayout("EmployeeUI");
		}
		if (btn == btnAddCustomer) {
			Main.newWindow("CustomerInfoInput", "Tạo khách hàng mới");
		}
		if (btn == btnAddReplacement) {

		}
	}

	void loadData() {
		List<Supplier> listsup = new SupplierDAO().getAll(Supplier.class);
		List<Model> listModel = new ArrayList<>();
		listrp = new ArrayList<>();
		choiceReplacement.setConverter(new StringConverter<Replacement>() {

			@Override
			public Replacement fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Replacement object) {
				// TODO Auto-generated method stub
				return object.getProductName();
			}
		});
		choiceSupplier.getItems().setAll(listsup);
		choiceSupplier.setConverter(new StringConverter<Supplier>() {

			@Override
			public String toString(Supplier object) {
				// TODO Auto-generated method stub
				return object.getSupplierName() + " | " + object.getSupplierID();
			}

			@Override
			public Supplier fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		choiceModel.setConverter(new StringConverter<Model>() {

			@Override
			public Model fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Model object) {
				// TODO Auto-generated method stub
				return object.getName();
			}
		});
		AtomicReference<Supplier> supplier_selected = new AtomicReference<Supplier>();
		choiceSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {

			@Override
			public void changed(ObservableValue<? extends Supplier> observable, Supplier oldValue, Supplier newValue) {
				if (newValue != null) {
					supplier_selected.set(newValue);
					listrp = new ReplacementDAO().findbySup(supplier_selected.get());
					for (int i = 0; i < listrp.size(); i++) {
						if (!listModel.contains(listrp.get(i).getModel()))
							listModel.add(listrp.get(i).getModel());
					}
					choiceModel.getItems().setAll(listModel);
					choiceReplacement.getItems().clear();
				}
			}

		});
		AtomicReference<Model> model_selected = new AtomicReference<Model>();
		choiceModel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Model>() {

			@Override
			public void changed(ObservableValue<? extends Model> observable, Model oldValue, Model newValue) {
				// TODO Auto-generated method stub
				if (newValue != null) {
					model_selected.set(newValue);
					listrp = new ReplacementDAO().findbySupWModel(supplier_selected.get(), model_selected.get());
					choiceReplacement.getItems().setAll(listrp);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	void LoadTable(List<MaintenanceReportDetail> list) {
		tableMaintenaceReportDetail.setEditable(true);
		Callback<TableColumn<MaintenanceReportDetail, String>, TableCell<MaintenanceReportDetail, String>> cellFactory = col -> new TableCell<MaintenanceReportDetail, String>() {
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
				super.commitEdit(newValue);
				for (int i = 0; i < list.size(); i++) {
					if (newValue.chars().allMatch(Character::isDigit)) {
						if (list.get(i).equals(tableMaintenaceReportDetail.getSelectionModel().getSelectedItem())) {
							list.get(i).setUnitPrice(Double.valueOf(newValue));
							reloadTable(list);
							loadTotal(list);
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
		TableColumn<MaintenanceReportDetail, MaintenanceReportDetail> colNumbered = new TableColumn<>("STT");
		TableColumn<MaintenanceReportDetail, String> colName = new TableColumn<>("Tên Linh kiện");
		TableColumn<MaintenanceReportDetail, String> colSup = new TableColumn<>("Hãng");
		TableColumn<MaintenanceReportDetail, String> colQuantity = new TableColumn<>("Số Lượng");
		TableColumn<MaintenanceReportDetail, String> colUP = new TableColumn<>("Đơn giá");
		TableColumn<MaintenanceReportDetail, String> colST = new TableColumn<>("Thành tiền");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<MaintenanceReportDetail, MaintenanceReportDetail>, ObservableValue<MaintenanceReportDetail>>() {

					@SuppressWarnings({ "rawtypes" })
					@Override
					public ObservableValue<MaintenanceReportDetail> call(
							CellDataFeatures<MaintenanceReportDetail, MaintenanceReportDetail> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(
				new Callback<TableColumn<MaintenanceReportDetail, MaintenanceReportDetail>, TableCell<MaintenanceReportDetail, MaintenanceReportDetail>>() {

					@Override
					public TableCell<MaintenanceReportDetail, MaintenanceReportDetail> call(
							TableColumn<MaintenanceReportDetail, MaintenanceReportDetail> param) {

						return new TableCell<MaintenanceReportDetail, MaintenanceReportDetail>() {
							@Override
							protected void updateItem(MaintenanceReportDetail arg0, boolean arg1) {

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
				celldata -> new SimpleStringProperty(celldata.getValue().getReplacement().getProductName()));
		colSup.setCellValueFactory(celldata -> new SimpleStringProperty(
				celldata.getValue().getReplacement().getSupplier().getSupplierName()));
		colQuantity.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getQuantity())));
		colUP.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.format("%,12.0f", celldata.getValue().getUnitPrice())));
		colUP.setCellFactory(cellFactory);
		colST.setCellValueFactory(celldata -> new SimpleStringProperty(
				String.format("%,12.0f", celldata.getValue().getUnitPrice() * celldata.getValue().getQuantity())));
		ObservableList<MaintenanceReportDetail> items = FXCollections.observableArrayList(list);
		tableMaintenaceReportDetail.setItems(items);
		tableMaintenaceReportDetail.getColumns().addAll(colNumbered, colName, colSup, colQuantity, colUP, colST);

	}

	void reloadTable(List<MaintenanceReportDetail> list) {
		tableMaintenaceReportDetail.getColumns().clear();
		LoadTable(list);
	}

	void Action() {
		List<MaintenanceReportDetail> list_detail = new ArrayList<>();
		btnAddReplacement.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (choiceReplacement.getSelectionModel().getSelectedItem() != null) {
					System.out.println(choiceReplacement.getSelectionModel().getSelectedItem());
					MaintenanceReportDetail maintenanceReportDetail = new MaintenanceReportDetail();
					maintenanceReportDetail.setMaintenanceReport(rp);
					System.out.println(maintenanceReportDetail);
					maintenanceReportDetail.setReplacement(choiceReplacement.getSelectionModel().getSelectedItem());
					maintenanceReportDetail
							.setUnitPrice(choiceReplacement.getSelectionModel().getSelectedItem().getUnitPrice());
					maintenanceReportDetail.setQuantity(1);
					list_detail.add(maintenanceReportDetail);
					rp.setDetails(list_detail);
					loadTotal(list_detail);

					clearSection();
					reloadTable(list_detail);
					for (int i = 0; i < list_detail.size(); i++) {
						System.out.println(list_detail.get(i).toString());
					}

				} else {
					// Messomething
				}
			}

			private void clearSection() {
				choiceSupplier.getSelectionModel().clearSelection();
				choiceReplacement.getSelectionModel().clearSelection();
				choiceModel.getSelectionModel().clearSelection();
			}

		});
		btnRemoveReplacement.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				MaintenanceReportDetail selected = tableMaintenaceReportDetail.getSelectionModel().getSelectedItem();
				list_detail.remove(selected);
				loadTotal(list_detail);
				reloadTable(list_detail);
			}
		});
		btnCheckout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (checkOrder()) {
					MaintenanceReportDAO mdao = new MaintenanceReportDAO();
					mdao.save(rp);
					System.out.println(rp);
					// reload_Stock(od.getListItems());
					Main.changeLayout("EmployeeUI");
				}
			}

			boolean checkOrder() {
				if (rp.getDetails() == null || rp.getDetails().size() == 0) {
					ErrorAlert error = new ErrorAlert("Thiếu thông tin", "Chưa có linh kiện trong danh sách");
					handler.setError(error);
					Main.newWindow("AlertMessage", "Thông báo");
					return false;
				}
				return true;
			}
		});
	}

	void loadTotal(List<MaintenanceReportDetail> list) {
		textTax.setText("");
		double total = 0;
		total += rp.getTotal();
		textTax.setText(String.format("0 VND"));
		textTotal.setText(String.format("%,12.0f VND", total));
	}

	@FXML
	void initialize() {
		rp = new MaintenanceReport();
		handler = Main.getHandler();
		MaintenanceReportDAO mdao = new MaintenanceReportDAO();
		List<MaintenanceReport> list = mdao.getAll(MaintenanceReport.class);
		///
		if (!list.isEmpty()) {
			String odlast = list.get(list.size() - 1).getMaintenanceReportID();
			String prefix = odlast.substring(0, 2);
			int numberOd = Integer.valueOf((odlast.substring(2, odlast.length())));
			String odID = prefix + String.format("%04d", numberOd + 1);
			textMaintenaceReportID.setText(odID);
		} else {
			textMaintenaceReportID.setText("RM000");
		}

		///
		List<OrderDetail> listod = new ArrayList<>();
		///
		LocalDate date = LocalDate.now();
		textDate.setValue(date);
		rp.setDate(date);
		textDate.setDisable(true);
		//
		String storename = "YAMAHA HOANGCAU";
		textStoreName.setText(storename);
		// Employee
		EmployeeDAO emdao = new EmployeeDAO();
		Employee emp = emdao.findByAc(handler.getAccount_using());
		rp.setEmployee(emp);
		//
		textEmployeeID.setText(emp.getLastName() + " " + emp.getFirstName());
		loadData();
		Action();
	}
}
