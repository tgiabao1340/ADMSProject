package application.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.AccountDAO;
import application.daos.CustomerDAO;
import application.daos.EmployeeDAO;
import application.daos.ModelDAO;
import application.daos.MotorbikeDAO;
import application.daos.OrderDAO;
import application.daos.ReplacementDAO;
import application.daos.SupplierDAO;
import application.entities.Account;
import application.entities.Customer;
import application.entities.Employee;
import application.entities.Model;
import application.entities.Motorbike;
import application.entities.Order;
import application.entities.Replacement;
import application.entities.Supplier;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class ManagementTaskController {
	Handler handler;

	// Motorbike
	List<String> years = new ArrayList<>();
	List<Supplier> suppliers = new SupplierDAO().getAll(Supplier.class);
	List<String> types = new ArrayList<>();
	List<Motorbike> listM;
	List<Model> listModel = new ArrayList<>();
	@FXML
	private Button btnBack;
	@FXML
	private RadioButton radToday;

	@FXML
	private RadioButton radioThisMonth;

	@FXML
	private RadioButton radThisYear;

	@FXML
	private RadioButton radCustom;

	@FXML
	private DatePicker datePickFrom;

	@FXML
	private DatePicker datePickTo;

	@FXML
	private Label lbSaleNoTax;

	@FXML
	private Label lbSaleTax;

	@FXML
	private Label lbSaleWithTax;

	@FXML
	private Label lbSumOfOrder;

	@FXML
	private TextField textMotorbike_ID;

	@FXML
	private TextField textMotorbike_NAME;

	@FXML
	private ChoiceBox<String> choiceMotorbike_YEAR;

	@FXML
	private ChoiceBox<Supplier> choiceMotorbike_SUP;

	@FXML
	private ChoiceBox<String> choiceMotorbike_TYPE;

	@FXML
	private TextField textCapacity;

	@FXML
	private TextField textDes;

	@FXML
	private ChoiceBox<Model> choiceMotorbike_MODEL;

	@FXML
	private CheckComboBox<String> comboxMotorbike_COLOR;

	@FXML
	private TableView<Motorbike> tableMotorbike;

	@FXML
	private Button btnSave_M;

	@FXML
	private Button btnNew_M;

	@FXML
	private Button btnDelete_M;
	// Replacement

	List<Replacement> listR;
	@FXML
	private TableView<Replacement> tableRepacement;
	@FXML
	private TextField textReplacement_ID;

	@FXML
	private TextField textReplacement_NAME;

	@FXML
	private TextField textReplacement_DES;

	@FXML
	private TextField textReplacement_STATUS;

	@FXML
	private ChoiceBox<Supplier> choiceReplacement_SUP;

	@FXML
	private ChoiceBox<Model> choiceReplacement_MODEL;

	@FXML
	private Button btnSave_R;

	@FXML
	private Button btnNew_R;

	@FXML
	private Button btnDelete_R;
	// Customer
	List<Customer> listC;
	@FXML
	private TableView<Customer> tableCustomer;

	@FXML
	private TextField textCustomer_ID;

	@FXML
	private TextField textCustomer_FNAME;

	@FXML
	private TextField textCustomer_LNAME;

	@FXML
	private CheckBox checkboxGender;

	@FXML
	private TextField textCustomer_PHONE;

	@FXML
	private DatePicker dpcustomerDoB;

	@FXML
	private TextArea textCustomer_AD;

	@FXML
	private TextField textCustomer_IDCARD;

	@FXML
	private DatePicker dpCustomer_IDDATE;

	@FXML
	private ComboBox<String> cbCustomer_city;

	@FXML
	private Button btnSave_C;

	@FXML
	private Button btnNew_C;

	@FXML
	private Button btnDelete_C;

	// Employee
	private List<Employee> listE;

	@FXML
	private TableView<Employee> tableEmployee;

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
	private TextField textEmployee_ACCOUNTID;

	@FXML
	private Button btnSave_E;

	@FXML
	private Button btnNew_E;

	@FXML
	private Button btnDelete_E;
	//
	@FXML
	private TextField textMotorbikeID_QNP;

	@FXML
	private TextField textMotorbikeNAME_QNP;

	@FXML
	private TextField textMotorbikeQUANTITY_QNP;

	@FXML
	private TextField textMotorbikePRICE_QNP;

	@FXML
	private Button btnSaveM_QNP;

	@FXML
	private TableView<Motorbike> tableMotorbike_QNP;
	//
	@FXML
	private TextField textSupplierID;

	@FXML
	private TextField textSupplierName;

	@FXML
	private TextField textSupplierCountry;

	@FXML
	private TextField textSupplierAddress;

	@FXML
	private TextField textSupplierPhone;

	@FXML
	private TextField textSupplierTaxCode;

	@FXML
	private Button btnSaveSUP;

	@FXML
	private Button btnNewSup;

	@FXML
	private Button btnDeleteSup;

	@FXML
	private TableView<Supplier> tableSupplier;
	private List<Supplier> listSupplier;

	//

	@FXML
	private TextField textModelIDD;

	@FXML
	private TextField textModelNameD;

	@FXML
	private Button btnSaveModel;

	@FXML
	private Button btnNewModel;

	@FXML
	private Button btnDeleteModel;

	@FXML
	private TableView<Model> tableModel;

	private List<Model> listModelT;

	//
	@FXML
	void MAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnBack) {
			Main.changeLayout("RoleUI");
		}
	}

	void loadMotorbike(List<Motorbike> listmotorbike) {

		///
		TableColumn<Motorbike, Motorbike> colNumbered_M = new TableColumn<>("STT");
		TableColumn<Motorbike, String> colID_M = new TableColumn<>("Mã xe");
		TableColumn<Motorbike, String> colName_M = new TableColumn<>("Tên xe");
		TableColumn<Motorbike, String> colCapacity_M = new TableColumn<>("Dung tích");
		TableColumn<Motorbike, String> colYear_M = new TableColumn<>("Năm sản xuất");
		TableColumn<Motorbike, String> colSupplierName_M = new TableColumn<>("Tên NSX");
		TableColumn<Motorbike, String> colModel_M = new TableColumn<>("Tên Model");
		colNumbered_M.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Motorbike, Motorbike>, ObservableValue<Motorbike>>() {

					@Override
					public ObservableValue<Motorbike> call(CellDataFeatures<Motorbike, Motorbike> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered_M
				.setCellFactory(new Callback<TableColumn<Motorbike, Motorbike>, TableCell<Motorbike, Motorbike>>() {

					@Override
					public TableCell<Motorbike, Motorbike> call(TableColumn<Motorbike, Motorbike> param) {
						return new TableCell<Motorbike, Motorbike>() {
							@Override
							protected void updateItem(Motorbike arg0, boolean arg1) {

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
		colNumbered_M.setSortable(false);
		colID_M.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductID()));
		colName_M.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductName()));
		colCapacity_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getCapacity())));
		colYear_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getManufactureYear())));
		colSupplierName_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getSupplier().getSupplierName()));
		colModel_M.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getModel().getName()));
		ObservableList<Motorbike> items_M = FXCollections.observableArrayList(listmotorbike);
		tableMotorbike.setItems(items_M);
		tableMotorbike.getColumns().addAll(colNumbered_M, colID_M, colName_M, colCapacity_M, colYear_M,
				colSupplierName_M, colModel_M);
		tableMotorbike.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Motorbike>() {
			@Override
			public void changed(ObservableValue<? extends Motorbike> observable, Motorbike oldValue,
					Motorbike newValue) {
				if (newValue != null) {
					clearField();
					loadtoField_M(newValue);
				}

			}

			void loadtoField_M(Motorbike motorbike) {
				choiceMotorbike_YEAR.getItems().addAll(years);
				choiceMotorbike_TYPE.getItems().addAll(types);
				choiceMotorbike_SUP.getItems().addAll(listSupplier);
				choiceMotorbike_MODEL.getItems().addAll(listModel);
				textMotorbike_ID.setText(motorbike.getProductID());
				textMotorbike_NAME.setText(motorbike.getProductName());
				choiceMotorbike_YEAR.getSelectionModel()
						.select(years.indexOf(String.valueOf(motorbike.getManufactureYear())));
				choiceMotorbike_TYPE.getSelectionModel().select(types.indexOf(motorbike.getType()));
				choiceMotorbike_SUP.getSelectionModel().select(motorbike.getSupplier());
				String color[] = motorbike.getColor().split(", ");
				for (int i = 0; i < color.length; i++) {
					comboxMotorbike_COLOR.getCheckModel().check(color[i]);
				}
				textCapacity.setText(String.format("%.0f", motorbike.getCapacity()));
				textDes.setText(motorbike.getStatus());
				choiceMotorbike_MODEL.getSelectionModel().select(motorbike.getModel());
			}

			void clearField() {
				textMotorbike_ID.clear();
				textMotorbike_NAME.clear();
				choiceMotorbike_YEAR.getItems().clear();
				choiceMotorbike_SUP.getItems().clear();
				choiceMotorbike_TYPE.getItems().clear();
				comboxMotorbike_COLOR.getCheckModel().clearChecks();
				choiceMotorbike_MODEL.getItems().clear();
				textCapacity.clear();
				textDes.clear();
			}
		});
	}

	void reloadTable_M() {
		tableMotorbike.getColumns().clear();
		listM = new MotorbikeDAO().getAll(Motorbike.class);
		listModel = new ModelDAO().getAll(Model.class);
		loadMotorbike(listM);
	}

	void Action_M() {
		textCapacity.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty()) {
					textCapacity.clear();
				} else {
					if (newValue.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
						int value = Integer.parseInt(newValue);
					} else {
						textCapacity.setText(oldValue);
					}
				}
			}
		});
		btnNew_M.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textMotorbike_ID.clear();
				textMotorbike_NAME.clear();
				textCapacity.clear();
				comboxMotorbike_COLOR.getCheckModel().clearChecks();
				textDes.clear();
				choiceMotorbike_MODEL.getSelectionModel().clearSelection();
				choiceMotorbike_YEAR.getSelectionModel().clearSelection();
				choiceMotorbike_SUP.getSelectionModel().clearSelection();
				choiceMotorbike_TYPE.getSelectionModel().clearSelection();
				choiceMotorbike_MODEL.getSelectionModel().clearSelection();
				textMotorbike_NAME.requestFocus();
				if (!listM.isEmpty()) {
					String id_last = listM.get(listM.size() - 1).getProductID();
					String prefix = id_last.substring(0, 2);
					int id = Integer.valueOf((id_last.substring(2, id_last.length())));
					String new_id = prefix + String.format("%04d", id + 1);
					textMotorbike_ID.setText(new_id);
				} else {
					textMotorbike_ID.setText("XM0001");
				}

			}
		});
		btnSave_M.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Motorbike motorbike;
					String id = textMotorbike_ID.getText();
					if ((new MotorbikeDAO().getById(Motorbike.class, id)) != null) {
						motorbike = new MotorbikeDAO().getById(Motorbike.class, id);
						new MotorbikeDAO().update(setM(motorbike));
					} else {
						motorbike = new Motorbike();
						new MotorbikeDAO().save(setM(motorbike));
					}
					reloadTable_M();
				}
			}

			Motorbike setM(Motorbike motorbike) {
				String name = textMotorbike_NAME.getText();
				String status = textDes.getText();
				String type = choiceMotorbike_TYPE.getSelectionModel().getSelectedItem();
				Supplier sup = choiceMotorbike_SUP.getSelectionModel().getSelectedItem();
				String year = choiceMotorbike_YEAR.getSelectionModel().getSelectedItem();
				Model model = choiceMotorbike_MODEL.getSelectionModel().getSelectedItem();

				motorbike.setProductName(name);
				String color = "";
				for (int i = 0; i < comboxMotorbike_COLOR.getCheckModel().getCheckedItems().size(); i++) {
					color += ", " + comboxMotorbike_COLOR.getCheckModel().getCheckedItems().get(i);
				}
				motorbike.setColor(color);
				motorbike.setCapacity(Double.valueOf(textCapacity.getText()));
				motorbike.setStatus(status);
				motorbike.setType(type);
				motorbike.setSupplier(sup);
				motorbike.setManufactureYear(Integer.valueOf(year));
				motorbike.setModel(model);
				return motorbike;
			}

			boolean check() {
				if (textMotorbike_ID.getText().isEmpty()) {
					textMotorbike_ID.getStyleClass().add("error");
					textMotorbike_ID.requestFocus();
					return false;
				}
				if (textMotorbike_NAME.getText().isEmpty()) {
					textMotorbike_NAME.getStyleClass().add("error");
					textMotorbike_NAME.requestFocus();
					return false;
				}
				if (textCapacity.getText().isEmpty()
						&& !(textCapacity.getText().chars().allMatch(Character::isDigit))) {
					textCapacity.getStyleClass().add("error");
					textCapacity.requestFocus();
					return false;
				}
				if (choiceMotorbike_YEAR.getSelectionModel().isEmpty()) {
					choiceMotorbike_YEAR.getStyleClass().add("error");
					return false;
				}
				if (choiceMotorbike_SUP.getSelectionModel().isEmpty()) {
					return false;
				}
				if (choiceMotorbike_TYPE.getSelectionModel().isEmpty()) {
					choiceMotorbike_TYPE.getStyleClass().add("error");
					return false;
				}

				if (comboxMotorbike_COLOR.getCheckModel().isEmpty()) {
					return false;
				}
				if (textDes.getText().isEmpty()) {
					textDes.getStyleClass().add("error");
					textDes.requestFocus();
					return false;
				}
				if (choiceMotorbike_MODEL.getSelectionModel().isEmpty()) {
					return false;
				}
				textMotorbike_ID.getStyleClass().remove("error");
				textMotorbike_NAME.getStyleClass().remove("error");
				textDes.getStyleClass().remove("error");
				return true;
			}
		});
		btnDelete_M.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Motorbike motorbike_selected = tableMotorbike.getSelectionModel().getSelectedItem();
				boolean check = new MotorbikeDAO().delete(motorbike_selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Xe máy :" + motorbike_selected.getProductID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_M();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
		comboxMotorbike_COLOR.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
			public void onChanged(ListChangeListener.Change<? extends String> c) {
				// System.out.println(comboxMotorbike_COLOR.getCheckModel().getCheckedItems());
			}
		});
	}

	void initTableMotorbike() {
		listM = new MotorbikeDAO().getAll(Motorbike.class);
		listModel = new ModelDAO().getAll(Model.class);
		listSupplier = new SupplierDAO().getAll(Supplier.class);
		handler = Main.getHandler();
		for (int i = 1990; i < LocalDate.now().getYear() + 1; i++) {
			years.add(String.valueOf(i));
		}
		types.add("Tay ga");
		types.add("Tay côn");
		types.add("Xe số");

		choiceMotorbike_YEAR.getItems().addAll(years);
		choiceMotorbike_TYPE.getItems().addAll(types);
		choiceMotorbike_SUP.getItems().addAll(listSupplier);
		choiceMotorbike_SUP.setConverter(new StringConverter<Supplier>() {

			@Override
			public Supplier fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Supplier object) {
				// TODO Auto-generated method stub
				return object.getSupplierName() + " | " + object.getSupplierID();
			}
		});
		choiceMotorbike_MODEL.setConverter(new StringConverter<Model>() {

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
		choiceMotorbike_MODEL.getItems().addAll(listModel);
		ObservableList<String> colors = FXCollections.observableArrayList();
		colors.add("Đen");
		colors.add("Trắng");
		colors.add("Đỏ");
		colors.add("Cam");
		colors.add("Vàng");
		colors.add("Xanh");
		colors.add("Lục");
		colors.add("Lam");
		colors.add("Chàm");
		colors.add("Tím");
		colors.add("Hồng");
		colors.add("Xám");
		comboxMotorbike_COLOR.getItems().addAll(colors);
		loadMotorbike(listM);
		Action_M();
	}

	void loadReplacement(List<Replacement> listreplacement) {

		///
		TableColumn<Replacement, Replacement> colNumbered = new TableColumn<>("STT");
		TableColumn<Replacement, String> colID = new TableColumn<>("Mã Linh kiện");
		TableColumn<Replacement, String> colName = new TableColumn<>("Tên Linh kiện");
		TableColumn<Replacement, String> colSupplierName = new TableColumn<>("Tên NSX");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Replacement, Replacement>, ObservableValue<Replacement>>() {

					@Override
					public ObservableValue<Replacement> call(CellDataFeatures<Replacement, Replacement> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(
				new Callback<TableColumn<Replacement, Replacement>, TableCell<Replacement, Replacement>>() {

					@Override
					public TableCell<Replacement, Replacement> call(TableColumn<Replacement, Replacement> param) {
						return new TableCell<Replacement, Replacement>() {
							@Override
							protected void updateItem(Replacement arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductID()));
		colName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductName()));
		colSupplierName.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getSupplier().getSupplierName()));
		ObservableList<Replacement> items = FXCollections.observableArrayList(listreplacement);
		tableRepacement.setItems(items);
		tableRepacement.getColumns().addAll(colNumbered, colID, colName, colSupplierName);
		tableRepacement.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Replacement>() {
			@Override
			public void changed(ObservableValue<? extends Replacement> observable, Replacement oldValue,
					Replacement newValue) {
				if (newValue != null) {
					clearField();
					loadtoField(newValue);
				}

			}

			void loadtoField(Replacement replacement) {
				choiceReplacement_SUP.getItems().addAll(listSupplier);
				choiceReplacement_MODEL.getItems().addAll(listModel);
				textReplacement_ID.setText(replacement.getProductID());
				textReplacement_NAME.setText(replacement.getProductName());
				choiceReplacement_SUP.getSelectionModel().select(replacement.getSupplier());
				textReplacement_DES.setText(replacement.getDescription());
				textReplacement_STATUS.setText(replacement.getStatus());
				choiceReplacement_MODEL.getSelectionModel().select(replacement.getModel());
			}

			void clearField() {
				textReplacement_ID.clear();
				textReplacement_NAME.clear();
				choiceReplacement_SUP.getItems().clear();
				textReplacement_STATUS.clear();
				textReplacement_DES.clear();
				choiceReplacement_MODEL.getItems().clear();
			}
		});
	}

	void reloadTable_R() {
		tableRepacement.getColumns().clear();
		listR = new ReplacementDAO().getAll(Replacement.class);
		listSupplier = new SupplierDAO().getAll(Supplier.class);
		listModel = new ModelDAO().getAll(Model.class);
		loadReplacement(listR);
	}

	void Action_R() {
		btnNew_R.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textReplacement_ID.clear();
				textReplacement_NAME.clear();
				choiceReplacement_SUP.getSelectionModel().clearSelection();
				choiceMotorbike_MODEL.getSelectionModel().clearSelection();
				textReplacement_DES.clear();
				textReplacement_STATUS.clear();
				textMotorbike_NAME.requestFocus();
				if (!listR.isEmpty()) {
					String id_last = listR.get(listR.size() - 1).getProductID();
					String prefix = id_last.substring(0, 2);
					int id = Integer.valueOf((id_last.substring(2, id_last.length())));
					String new_id = prefix + String.format("%04d", id + 1);
					textReplacement_ID.setText(new_id);
				} else {
					textReplacement_ID.setText("RM0001");
				}

			}
		});
		btnSave_R.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Replacement replacement;
					String id = textReplacement_ID.getText();
					if ((new ReplacementDAO().getById(Replacement.class, id)) != null) {
						replacement = new ReplacementDAO().getById(Replacement.class, id);
						new ReplacementDAO().update(setR(replacement));
					} else {
						replacement = new Replacement();
						new ReplacementDAO().save(setR(replacement));
					}
					reloadTable_R();
				}
			}

			Replacement setR(Replacement replacement) {
				String name = textReplacement_NAME.getText();
				String status = textReplacement_STATUS.getText();
				String des = textReplacement_DES.getText();
				replacement.setProductName(name);
				Supplier sup = choiceReplacement_SUP.getSelectionModel().getSelectedItem();
				Model model = choiceReplacement_MODEL.getSelectionModel().getSelectedItem();
				replacement.setSupplier(sup);
				replacement.setStatus(status);
				replacement.setDescription(des);
				replacement.setModel(model);
				return replacement;
			}

			boolean check() {
				if (textReplacement_ID.getText().isEmpty()) {
					textReplacement_ID.getStyleClass().add("error");
					textReplacement_ID.requestFocus();
					return false;
				}
				if (textReplacement_NAME.getText().isEmpty()) {
					textReplacement_NAME.getStyleClass().add("error");
					textReplacement_NAME.requestFocus();
					return false;
				}
				if (choiceReplacement_SUP.getSelectionModel().isEmpty()) {
					return false;
				}
				if (textReplacement_DES.getText().isEmpty()) {
					textReplacement_DES.getStyleClass().add("error");
					textReplacement_DES.requestFocus();
					return false;
				}
				if (textReplacement_STATUS.getText().isEmpty()) {
					textReplacement_STATUS.getStyleClass().add("error");
					textReplacement_STATUS.requestFocus();
					return false;
				}
				if (choiceReplacement_MODEL.getSelectionModel().isEmpty()) {
					return false;
				}
				textReplacement_ID.getStyleClass().remove("error");
				textReplacement_NAME.getStyleClass().remove("error");
				textReplacement_STATUS.getStyleClass().remove("error");
				textReplacement_DES.getStyleClass().remove("error");
				return true;
			}
		});
		btnDelete_R.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Replacement replacement_selected = tableRepacement.getSelectionModel().getSelectedItem();
				boolean check = new ReplacementDAO().delete(replacement_selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Linh kiện :" + replacement_selected.getProductID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_R();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
	}

	void initTableReplacement() {
		listR = new ReplacementDAO().getAll(Replacement.class);
		listModel = new ModelDAO().getAll(Model.class);
		listSupplier = new SupplierDAO().getAll(Supplier.class);
		handler = Main.getHandler();
		choiceReplacement_SUP.setConverter(new StringConverter<Supplier>() {

			@Override
			public Supplier fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Supplier object) {
				// TODO Auto-generated method stub
				return object.getSupplierName() + " | " + object.getSupplierID();
			}
		});
		choiceReplacement_MODEL.setConverter(new StringConverter<Model>() {

			@Override
			public String toString(Model object) {
				// TODO Auto-generated method stub
				return object.getName();
			}

			@Override
			public Model fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		choiceReplacement_SUP.getItems().addAll(listSupplier);
		choiceReplacement_MODEL.getItems().addAll(listModel);
		loadReplacement(listR);
		Action_R();
	}

	void loadCustomer(List<Customer> list) {

		///
		TableColumn<Customer, Customer> colNumbered = new TableColumn<>("STT");
		TableColumn<Customer, String> colID = new TableColumn<>("CustomerID");
		TableColumn<Customer, String> colLName = new TableColumn<>("Họ & Tên Đệm");
		TableColumn<Customer, String> colFName = new TableColumn<>("Tên");
		TableColumn<Customer, String> colGender = new TableColumn<>("Giới tính");
		TableColumn<Customer, String> colDoB = new TableColumn<>("Ngày sinh");
		TableColumn<Customer, String> colAddress = new TableColumn<>("Địa chỉ");
		TableColumn<Customer, String> colPhone = new TableColumn<>("Số điện thoại");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Customer, Customer>, ObservableValue<Customer>>() {

					@Override
					public ObservableValue<Customer> call(CellDataFeatures<Customer, Customer> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Customer, Customer>, TableCell<Customer, Customer>>() {

			@Override
			public TableCell<Customer, Customer> call(TableColumn<Customer, Customer> param) {
				return new TableCell<Customer, Customer>() {
					@Override
					protected void updateItem(Customer arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getBussinessID()));
		colLName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getLastName()));
		colFName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFirstName()));
		colGender.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Customer, String> param) {
						if (param.getValue().getGender())
							return new ReadOnlyObjectWrapper("Nam");
						return new ReadOnlyObjectWrapper("Nữ");
					}
				});
		colDoB.setCellValueFactory(celldata -> new SimpleStringProperty(
				(celldata.getValue().getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
		colAddress.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getAddress()));
		colPhone.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getPhoneNumber()));

		ObservableList<Customer> items = FXCollections.observableArrayList(list);
		tableCustomer.setItems(items);
		tableCustomer.getColumns().addAll(colNumbered, colID, colLName, colFName, colGender, colDoB, colAddress,
				colPhone);
		tableCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				if (newValue != null) {
					clearField();
					loadtoField(newValue);
				}

			}

			void loadtoField(Customer customer) {
				textCustomer_ID.setText(customer.getBussinessID());
				textCustomer_LNAME.setText(customer.getLastName());
				textCustomer_FNAME.setText(customer.getFirstName());
				textCustomer_AD.setText(customer.getAddress());
				textCustomer_PHONE.setText(customer.getPhoneNumber());
				textCustomer_IDCARD.setText(customer.getIdCard());
				dpcustomerDoB.setValue(customer.getDateOfBirth());
				dpCustomer_IDDATE.setValue(customer.getIdCardDate());
				checkboxGender.setSelected(customer.getGender());
				cbCustomer_city.getSelectionModel().select(customer.getPermanentAddress());
			}

			void clearField() {
				textCustomer_ID.clear();
				textCustomer_LNAME.clear();
				textCustomer_FNAME.clear();
				textCustomer_AD.clear();
				textCustomer_PHONE.clear();
				textCustomer_IDCARD.clear();
				dpcustomerDoB.setValue(null);
				dpCustomer_IDDATE.setValue(null);
				checkboxGender.setSelected(false);
				cbCustomer_city.getSelectionModel().clearSelection();
			}
		});
	}

	void reloadTable_C() {
		tableCustomer.getColumns().clear();
		listC = new CustomerDAO().getAll(Customer.class);
		loadCustomer(listC);
	}

	void Action_C() {
		btnNew_C.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textCustomer_ID.clear();
				textCustomer_LNAME.clear();
				textCustomer_FNAME.clear();
				textCustomer_AD.clear();
				textCustomer_PHONE.clear();
				textCustomer_IDCARD.clear();
				dpcustomerDoB.setValue(null);
				dpCustomer_IDDATE.setValue(null);
				textCustomer_LNAME.requestFocus();
				cbCustomer_city.getSelectionModel().clearSelection();
				if (!listR.isEmpty()) {
					String id_last = listC.get(listC.size() - 1).getBussinessID();
					String prefix = id_last.substring(0, 2);
					int id = Integer.valueOf((id_last.substring(2, id_last.length())));
					String new_id = prefix + String.format("%04d", id + 1);
					textCustomer_ID.setText(new_id);
				} else {
					textCustomer_ID.setText("KH0001");
				}

			}
		});
		btnSave_C.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Customer customer;
					String id = textCustomer_ID.getText();
					if ((new CustomerDAO().getById(Customer.class, id)) != null) {
						customer = new CustomerDAO().getById(Customer.class, id);
						new CustomerDAO().update(setC(customer));
					} else {
						customer = new Customer();
						new CustomerDAO().save(setC(customer));
					}
					reloadTable_C();
				}
			}

			Customer setC(Customer customer) {
				String lname = textCustomer_LNAME.getText();
				String fname = textCustomer_FNAME.getText();
				String address = textCustomer_AD.getText();
				String phone = textCustomer_PHONE.getText();
				String idcard = textCustomer_IDCARD.getText();
				LocalDate dob = dpcustomerDoB.getValue();
				LocalDate iddate = dpCustomer_IDDATE.getValue();
				Boolean gender = checkboxGender.isSelected();
				String city = cbCustomer_city.getSelectionModel().getSelectedItem();
				customer.setLastName(lname);
				customer.setFirstName(fname);
				customer.setAddress(address);
				customer.setPhoneNumber(phone);
				customer.setIdCard(idcard);
				customer.setDateOfBirth(dob);
				customer.setCreatedDate(iddate);
				customer.setIdCardDate(iddate);
				customer.setPermanentAddress(city);
				customer.setGender(gender);
				return customer;
			}

			boolean check() {
				if (textCustomer_ID.getText().isEmpty()) {
					textCustomer_ID.getStyleClass().add("error");
					textCustomer_ID.requestFocus();
					return false;
				}
				if (textCustomer_FNAME.getText().isEmpty()) {
					textCustomer_FNAME.getStyleClass().add("error");
					textCustomer_FNAME.requestFocus();
					return false;
				}
				if (textCustomer_LNAME.getText().isEmpty()) {
					textCustomer_LNAME.getStyleClass().add("error");
					textCustomer_LNAME.requestFocus();
					return false;
				}
				if (textCustomer_AD.getText().isEmpty()) {
					textCustomer_AD.getStyleClass().add("error");
					textCustomer_AD.requestFocus();
					return false;
				}
				if (textCustomer_IDCARD.getText().isEmpty()) {
					textCustomer_IDCARD.getStyleClass().add("error");
					textCustomer_IDCARD.requestFocus();
					return false;
				}
				if (textCustomer_PHONE.getText().isEmpty()) {
					textCustomer_PHONE.getStyleClass().add("error");
					textCustomer_PHONE.requestFocus();
					return false;
				}
				if (cbCustomer_city.getSelectionModel().isEmpty()) {
					return false;
				}
				textCustomer_ID.getStyleClass().remove("error");
				textCustomer_FNAME.getStyleClass().remove("error");
				textCustomer_LNAME.getStyleClass().remove("error");
				textCustomer_AD.getStyleClass().remove("error");
				textCustomer_IDCARD.getStyleClass().remove("error");
				textCustomer_PHONE.getStyleClass().remove("error");
				return true;
			}
		});
		btnDelete_C.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Customer customer_selected = tableCustomer.getSelectionModel().getSelectedItem();
				boolean check = new CustomerDAO().delete(customer_selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Khách hàng mã :" + customer_selected.getBussinessID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_R();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
	}

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
		cbCustomer_city.getItems().addAll(list);
	}

	void initCustomerTable() {
		listC = new CustomerDAO().getAll(Customer.class);
		loadCustomer(listC);
		Action_C();
		load64city();
	}

	void loadEmployee(List<Employee> list) {

		///
		TableColumn<Employee, Employee> colNumbered = new TableColumn<>("STT");
		TableColumn<Employee, String> colID = new TableColumn<>("Mã NV");
		TableColumn<Employee, String> colLName = new TableColumn<>("Họ & Đệm");
		TableColumn<Employee, String> colFName = new TableColumn<>("Tên");
		TableColumn<Employee, String> colGender = new TableColumn<>("Giới tính");
		TableColumn<Employee, String> colDoB = new TableColumn<>("Ngày sinh");
		TableColumn<Employee, String> colAddress = new TableColumn<>("Địa chỉ");
		TableColumn<Employee, String> colPhone = new TableColumn<>("SDT");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Employee, Employee>, ObservableValue<Employee>>() {

					@Override
					public ObservableValue<Employee> call(CellDataFeatures<Employee, Employee> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Employee, Employee>, TableCell<Employee, Employee>>() {

			@Override
			public TableCell<Employee, Employee> call(TableColumn<Employee, Employee> param) {
				return new TableCell<Employee, Employee>() {
					@Override
					protected void updateItem(Employee arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getBussinessID()));
		colLName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getLastName()));
		colFName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getFirstName()));
		colGender.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Employee, String> param) {
						if (param.getValue().getGender())
							return new ReadOnlyObjectWrapper("Nam");
						return new ReadOnlyObjectWrapper("Nữ");
					}
				});
		colDoB.setCellValueFactory(celldata -> new SimpleStringProperty(
				(celldata.getValue().getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
		colAddress.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getAddress()));
		colPhone.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getPhoneNumber()));
		ObservableList<Employee> items = FXCollections.observableArrayList(list);
		tableEmployee.setItems(items);
		tableEmployee.getColumns().addAll(colNumbered, colID, colLName, colFName, colGender, colDoB, colAddress,
				colPhone);
		tableEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
			@Override
			public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
				if (newValue != null) {
					clearField();
					loadtoField(newValue);
				}

			}

			void loadtoField(Employee employee) {
				textEmployee_ID.setText(employee.getBussinessID());
				textEmployee_LNAME.setText(employee.getLastName());
				textEmployee_FNAME.setText(employee.getFirstName());
				textEmployee_AD.setText(employee.getAddress());
				textEmployee_PHONE.setText(employee.getPhoneNumber());
				dpEmployee_DATE.setValue(employee.getDateOfBirth());
				checkEmployee_GENDER.setSelected(employee.getGender());
				if (employee.getAccount().getAccountID() == null) {
					textEmployee_ACCOUNTID.setText("NULL");
				} else
					textEmployee_ACCOUNTID.setText(employee.getAccount().getAccountID());
			}

			void clearField() {
				textEmployee_ID.clear();
				textEmployee_LNAME.clear();
				textEmployee_FNAME.clear();
				textEmployee_AD.clear();
				textEmployee_PHONE.clear();
				dpEmployee_DATE.setValue(null);
				checkEmployee_GENDER.setSelected(false);
				textEmployee_ACCOUNTID.clear();
			}
		});
	}

	void reloadTable_E() {
		tableEmployee.getColumns().clear();
		listE = new EmployeeDAO().getAll(Employee.class);
		loadEmployee(listE);
	}

	void Action_E() {
		btnNew_E.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textEmployee_ID.clear();
				textEmployee_LNAME.clear();
				textEmployee_FNAME.clear();
				textEmployee_AD.clear();
				textEmployee_PHONE.clear();
				dpEmployee_DATE.setValue(null);
				checkEmployee_GENDER.setSelected(false);
				textEmployee_ACCOUNTID.clear();
				textEmployee_LNAME.requestFocus();
				if (!listE.isEmpty()) {
					String id_last = listE.get(listE.size() - 1).getBussinessID();
					String prefix = id_last.substring(0, 2);
					int id = Integer.valueOf((id_last.substring(2, id_last.length())));
					String new_id = prefix + String.format("%04d", id + 1);
					textEmployee_ID.setText(new_id);
				} else {
					textEmployee_ID.setText("NV0001");
				}

			}
		});
		btnSave_E.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Employee employee;
					String id = textEmployee_ID.getText();
					if ((new EmployeeDAO().getById(Employee.class, id)) != null) {

						employee = new EmployeeDAO().getById(Employee.class, id);
						new EmployeeDAO().update(setE(employee));
					} else {
						employee = new Employee();
						Account account = new Account();
						account.setCreatedDate(LocalDate.now());
						account.setPassword("123");
						account.setAccountID(textEmployee_ACCOUNTID.getText().trim());
						employee.setAccount(account);
						new AccountDAO().save(account);
						new EmployeeDAO().save(setE(employee));
					}
					reloadTable_E();
				}
			}

			Employee setE(Employee employee) {
				String lname = textEmployee_LNAME.getText();
				String fname = textEmployee_FNAME.getText();
				String address = textEmployee_AD.getText();
				String phone = textEmployee_PHONE.getText();
				LocalDate dob = dpEmployee_DATE.getValue();
				Boolean gender = checkEmployee_GENDER.isSelected();
				Account account = new AccountDAO().findById(textEmployee_ACCOUNTID.getText());
				employee.setLastName(lname);
				employee.setFirstName(fname);
				employee.setAddress(address);
				employee.setPhoneNumber(phone);
				employee.setDateOfBirth(dob);
				employee.setGender(gender);
				employee.setAccount(account);
				employee.setPosition("NV Bán hàng");
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
				if (textEmployee_ACCOUNTID.getText().isEmpty()) {
					textEmployee_ACCOUNTID.getStyleClass().add("error");
					textEmployee_ACCOUNTID.requestFocus();

					return false;
				}
				String id = textEmployee_ACCOUNTID.getText();
				if ((new AccountDAO().getById(Account.class, id)) != null) {
					textEmployee_ACCOUNTID.getStyleClass().add("error");
					textEmployee_ACCOUNTID.requestFocus();
					return false;
				}
				textEmployee_ID.getStyleClass().remove("error");
				textEmployee_FNAME.getStyleClass().remove("error");
				textEmployee_LNAME.getStyleClass().remove("error");
				textEmployee_AD.getStyleClass().remove("error");
				textEmployee_PHONE.getStyleClass().remove("error");
				textEmployee_ACCOUNTID.getStyleClass().remove("error");
				return true;
			}
		});
		btnDelete_E.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Employee employee_selected = tableEmployee.getSelectionModel().getSelectedItem();
				boolean check = new EmployeeDAO().delete(employee_selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Nhân viên mã :" + employee_selected.getBussinessID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_E();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
	}

	void initEmployeeTable() {
		listE = new EmployeeDAO().getAll(Employee.class);
		loadEmployee(listE);
		Action_E();
	}

	void loadMotorbike_QNP(List<Motorbike> listmotorbike) {
		///
		TableColumn<Motorbike, Motorbike> colNumbered_M = new TableColumn<>("STT");
		TableColumn<Motorbike, String> colID_M = new TableColumn<>("Mã xe");
		TableColumn<Motorbike, String> colName_M = new TableColumn<>("Tên xe");
		TableColumn<Motorbike, String> colQuantity = new TableColumn<>("Số Lượng");
		TableColumn<Motorbike, String> colUnitPrice = new TableColumn<>("Đơn giá");
		colNumbered_M.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Motorbike, Motorbike>, ObservableValue<Motorbike>>() {

					@Override
					public ObservableValue<Motorbike> call(CellDataFeatures<Motorbike, Motorbike> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered_M
				.setCellFactory(new Callback<TableColumn<Motorbike, Motorbike>, TableCell<Motorbike, Motorbike>>() {

					@Override
					public TableCell<Motorbike, Motorbike> call(TableColumn<Motorbike, Motorbike> param) {
						return new TableCell<Motorbike, Motorbike>() {
							@Override
							protected void updateItem(Motorbike arg0, boolean arg1) {

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
		colNumbered_M.setSortable(false);
		colID_M.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductID()));
		colName_M.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getProductName()));
		colQuantity.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getQuantity())));
		colUnitPrice.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.format("%12.0f", celldata.getValue().getUnitPrice())));
		ObservableList<Motorbike> items_M = FXCollections.observableArrayList(listmotorbike);
		tableMotorbike_QNP.setItems(items_M);
		tableMotorbike_QNP.getColumns().addAll(colNumbered_M, colID_M, colName_M, colQuantity, colUnitPrice);
		tableMotorbike_QNP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Motorbike>() {
			@Override
			public void changed(ObservableValue<? extends Motorbike> observable, Motorbike oldValue,
					Motorbike newValue) {
				if (newValue != null) {
					clearField();
					loadtoField_M_QNP(newValue);
				}

			}

			void loadtoField_M_QNP(Motorbike motorbike) {
				textMotorbikeID_QNP.setText(motorbike.getProductID());
				textMotorbikeNAME_QNP.setText(motorbike.getProductName());
				textMotorbikeQUANTITY_QNP.setText(String.valueOf(motorbike.getQuantity()));
				textMotorbikePRICE_QNP.setText(String.format("%.0f", motorbike.getUnitPrice()));
			}

			void clearField() {
				textMotorbikeID_QNP.clear();
				textMotorbikeNAME_QNP.clear();
				textMotorbikeQUANTITY_QNP.clear();
				textMotorbikePRICE_QNP.clear();
			}
		});
	}

	void reloadTable_M_QNP() {
		tableMotorbike_QNP.getColumns().clear();
		listM = new MotorbikeDAO().getAll(Motorbike.class);
		loadMotorbike_QNP(listM);
	}

	void Action_M_QNP() {
		textMotorbikeQUANTITY_QNP.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty()) {
					textMotorbikeQUANTITY_QNP.clear();
				} else {
					if (newValue.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
						int value = Integer.parseInt(newValue);
					} else {
						textMotorbikeQUANTITY_QNP.setText(oldValue);
					}
				}
			}
		});
		textMotorbikePRICE_QNP.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty()) {
					textMotorbikePRICE_QNP.clear();
				} else {
					if (newValue.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
						int value = Integer.parseInt(newValue);
					} else {
						textMotorbikePRICE_QNP.setText(oldValue);
					}
				}
			}
		});

		btnSaveM_QNP.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Motorbike motorbike = tableMotorbike_QNP.getSelectionModel().getSelectedItem();
					String id = textMotorbikeID_QNP.getText();
					if ((new MotorbikeDAO().getById(Motorbike.class, id)) != null) {
						new MotorbikeDAO().update(setM(motorbike));
					}
					reloadTable_M_QNP();
				}
			}

			Motorbike setM(Motorbike motorbike) {
				String quantity = textMotorbikeQUANTITY_QNP.getText();
				String unitprice = textMotorbikePRICE_QNP.getText();
				motorbike.setQuantity(Integer.valueOf(quantity));
				motorbike.setUnitPrice(Double.valueOf(unitprice));
				System.out.println(motorbike);
				return motorbike;
			}

			boolean check() {
				if (textMotorbikeID_QNP.getText().isEmpty()) {
					textMotorbikeID_QNP.getStyleClass().add("error");
					textMotorbikeID_QNP.requestFocus();
					return false;
				}
				if (textMotorbikeNAME_QNP.getText().isEmpty()) {
					textMotorbikeNAME_QNP.getStyleClass().add("error");
					textMotorbikeNAME_QNP.requestFocus();
					return false;
				}
				if (textMotorbikeQUANTITY_QNP.getText().isEmpty()) {
					textMotorbikeQUANTITY_QNP.getStyleClass().add("error");
					textMotorbikeQUANTITY_QNP.requestFocus();
					return false;
				}
				if (textMotorbikePRICE_QNP.getText().isEmpty()) {
					textMotorbikePRICE_QNP.getStyleClass().add("error");
					textMotorbikePRICE_QNP.requestFocus();
					return false;
				}
				return true;
			}
		});

	}

	void initTableMotorbike_QNP() {
		listM = new MotorbikeDAO().getAll(Motorbike.class);
		loadMotorbike_QNP(listM);
		Action_M_QNP();
	}

	void loadSupplier(List<Supplier> listSupplier) {

		///
		TableColumn<Supplier, Supplier> colNumbered = new TableColumn<>("STT");
		TableColumn<Supplier, String> colID = new TableColumn<>("Mã NCC");
		TableColumn<Supplier, String> colName = new TableColumn<>("Tên NCC");
		TableColumn<Supplier, String> colCountry = new TableColumn<>("Quốc gia");
		TableColumn<Supplier, String> colAddress = new TableColumn<>("Địa chỉ");
		TableColumn<Supplier, String> colPhone = new TableColumn<>("SDT");
		TableColumn<Supplier, String> colTaxCode = new TableColumn<>("Mã Thuế");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Supplier, Supplier>, ObservableValue<Supplier>>() {

					@Override
					public ObservableValue<Supplier> call(CellDataFeatures<Supplier, Supplier> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Supplier, Supplier>, TableCell<Supplier, Supplier>>() {

			@Override
			public TableCell<Supplier, Supplier> call(TableColumn<Supplier, Supplier> param) {
				return new TableCell<Supplier, Supplier>() {
					@Override
					protected void updateItem(Supplier arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getSupplierID()));
		colName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getSupplierName()));
		colCountry.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getCountry()));
		colAddress.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getAddress()));
		colPhone.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getPhoneNumber()));
		colTaxCode.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getTaxCode()));
		ObservableList<Supplier> items = FXCollections.observableArrayList(listSupplier);
		tableSupplier.setItems(items);
		tableSupplier.getColumns().addAll(colNumbered, colID, colName, colCountry, colAddress, colTaxCode);
		tableSupplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
			@Override
			public void changed(ObservableValue<? extends Supplier> observable, Supplier oldValue, Supplier newValue) {
				if (newValue != null) {
					clearField();
					loadtoField_M(newValue);
				}

			}

			void loadtoField_M(Supplier suppliers) {
				textSupplierID.setText(suppliers.getSupplierID());
				textSupplierName.setText(suppliers.getSupplierName());
				textSupplierPhone.setText(suppliers.getPhoneNumber());
				textSupplierCountry.setText(suppliers.getCountry());
				textSupplierAddress.setText(suppliers.getAddress());
				textSupplierTaxCode.setText(suppliers.getTaxCode());
			}

			void clearField() {
				textSupplierID.clear();
				textSupplierName.clear();
				textSupplierCountry.clear();
				textSupplierPhone.clear();
				textSupplierAddress.clear();
				textSupplierTaxCode.clear();
			}
		});
	}

	void reloadTable_S() {
		tableSupplier.getColumns().clear();
		listSupplier = new SupplierDAO().getAll(Supplier.class);
		loadSupplier(listSupplier);
	}

	void Action_S() {
		textSupplierPhone.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty()) {
					textSupplierPhone.clear();
				} else {
					if (newValue.matches("\\d+(\\.\\d*)?|\\.\\d+")) {
						int value = Integer.parseInt(newValue);
					} else {
						textSupplierPhone.setText(oldValue);
					}
				}
			}
		});
		btnNewSup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textSupplierID.clear();
				textSupplierName.clear();
				textSupplierCountry.clear();
				textSupplierPhone.clear();
				textSupplierAddress.clear();
				textSupplierTaxCode.clear();
				textSupplierID.requestFocus();
			}
		});
		btnSaveSUP.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Supplier supplier;
					String id = textSupplierID.getText();
					if ((new SupplierDAO().getById(Supplier.class, id)) != null) {
						supplier = new SupplierDAO().getById(Supplier.class, id);
						new SupplierDAO().update(setS(supplier));
					} else {
						supplier = new Supplier();
						new SupplierDAO().save(setS(supplier));
					}
					reloadTable_S();
				}
			}

			Supplier setS(Supplier supplier) {
				String id = textSupplierID.getText();
				String name = textSupplierName.getText();
				String country = textSupplierCountry.getText();
				String address = textSupplierAddress.getText();
				String phone = textSupplierPhone.getText();
				String taxcode = textSupplierTaxCode.getText();
				supplier.setSupplierID(id);
				supplier.setSupplierName(name);
				supplier.setCountry(country);
				supplier.setAddress(address);
				supplier.setPhoneNumber(phone);
				supplier.setTaxCode(taxcode);
				return supplier;
			}

			boolean check() {
				if (textSupplierID.getText().isEmpty()) {
					textSupplierID.getStyleClass().add("error");
					textSupplierID.requestFocus();
					return false;
				}
				if (textSupplierName.getText().isEmpty()) {
					textSupplierName.getStyleClass().add("error");
					textSupplierName.requestFocus();
					return false;
				}
				if (textSupplierCountry.getText().isEmpty()) {
					textSupplierCountry.getStyleClass().add("error");
					textSupplierCountry.requestFocus();
					return false;
				}
				if (textSupplierAddress.getText().isEmpty()) {
					textSupplierAddress.getStyleClass().add("error");
					textSupplierAddress.requestFocus();
					return false;
				}
				if (textSupplierPhone.getText().isEmpty()) {
					textSupplierPhone.getStyleClass().add("error");
					textSupplierPhone.requestFocus();
					return false;
				}
				if (textSupplierTaxCode.getText().isEmpty()) {
					textSupplierTaxCode.getStyleClass().add("error");
					textSupplierTaxCode.requestFocus();
					return false;
				}
				textSupplierID.getStyleClass().remove("error");
				textSupplierName.getStyleClass().remove("error");
				textSupplierCountry.getStyleClass().remove("error");
				textSupplierPhone.getStyleClass().remove("error");
				textSupplierAddress.getStyleClass().remove("error");
				textSupplierTaxCode.getStyleClass().remove("error");
				return true;
			}
		});
		btnDeleteSup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Supplier selected = tableSupplier.getSelectionModel().getSelectedItem();
				boolean check = new SupplierDAO().delete(selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Mã :" + selected.getSupplierID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_S();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
	}

	void initTableSupplier() {
		listSupplier = new SupplierDAO().getAll(Supplier.class);
		loadSupplier(listSupplier);
		Action_S();
	}

	void loadModel(List<Model> listModel) {

		///
		TableColumn<Model, Model> colNumbered = new TableColumn<>("STT");
		TableColumn<Model, String> colID = new TableColumn<>("Mã Model");
		TableColumn<Model, String> colName = new TableColumn<>("Tên Model");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Model, Model>, ObservableValue<Model>>() {

					@Override
					public ObservableValue<Model> call(CellDataFeatures<Model, Model> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Model, Model>, TableCell<Model, Model>>() {

			@Override
			public TableCell<Model, Model> call(TableColumn<Model, Model> param) {
				return new TableCell<Model, Model>() {
					@Override
					protected void updateItem(Model arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getModelID()));
		colName.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getName()));
		ObservableList<Model> items = FXCollections.observableArrayList(listModel);
		tableModel.setItems(items);
		tableModel.getColumns().addAll(colNumbered, colID, colName);
		tableModel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Model>() {
			@Override
			public void changed(ObservableValue<? extends Model> observable, Model oldValue, Model newValue) {
				if (newValue != null) {
					clearField();
					loadtoField_Model(newValue);
				}

			}

			void loadtoField_Model(Model model) {
				textModelIDD.setText(model.getModelID());
				textModelNameD.setText(model.getName());
			}

			void clearField() {
				textModelIDD.clear();
				textModelNameD.clear();
			}
		});
	}

	void reloadTable_Model() {
		tableModel.getColumns().clear();
		listModel = new ModelDAO().getAll(Model.class);
		loadModel(listModel);
	}

	void Action_Model() {
		btnNewModel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textModelIDD.clear();
				textModelNameD.clear();
				textModelIDD.requestFocus();
			}
		});
		btnSaveModel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Model model;
					String id = textModelIDD.getText();
					if ((new ModelDAO().getById(Model.class, id)) != null) {
						model = new ModelDAO().getById(Model.class, id);
						new ModelDAO().update(setModel(model));
					} else {
						model = new Model();
						new ModelDAO().save(setModel(model));
					}
					reloadTable_Model();
				}
			}

			Model setModel(Model model) {
				String id = textModelIDD.getText();
				String name = textModelNameD.getText();
				model.setModelID(id);
				model.setName(name);
				return model;
			}

			boolean check() {
				if (textModelIDD.getText().isEmpty()) {
					textModelIDD.getStyleClass().add("error");
					textModelIDD.requestFocus();
					return false;
				}
				if (textModelNameD.getText().isEmpty()) {
					textModelNameD.getStyleClass().add("error");
					textModelNameD.requestFocus();
					return false;
				}
				textModelNameD.getStyleClass().remove("error");
				textModelIDD.getStyleClass().remove("error");
				return true;
			}
		});
		btnDeleteModel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Model selected = tableModel.getSelectionModel().getSelectedItem();
				boolean check = new ModelDAO().delete(selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Mã :" + selected.getModelID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_S();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});
	}

	void initTableModel() {
		listModel = new ModelDAO().getAll(Model.class);
		loadModel(listModel);
		Action_Model();
	}

	void ReloadData() {
		reloadTable_R();
		reloadTable_M();
	}

	void SaleOrder() {
		ToggleGroup gr = new ToggleGroup();
		radToday.setToggleGroup(gr);
		radioThisMonth.setToggleGroup(gr);
		radThisYear.setToggleGroup(gr);
		radCustom.setToggleGroup(gr);
		datePickFrom.setDisable(true);
		datePickTo.setDisable(true);
		radToday.setOnAction(e -> {
			List<Order> orders = new OrderDAO().getByDate(LocalDate.now());
			long saleNoTax = 0l;
			long saleTax = 0l;
			long saleWithTax = 0l;
			int sumOfOrder = 0;
			for (int i = 0; i < orders.size(); i++) {
				saleNoTax += orders.get(i).getTotalRaw();
				saleTax += orders.get(i).getTotalVAT();
				saleWithTax += orders.get(i).getSubTotal();
			}
			sumOfOrder += new OrderDAO().sumOfOrderByDate(LocalDate.now());
			lbSaleNoTax.setText(String.format("%,d", saleNoTax));
			lbSaleTax.setText(String.format("%,d", saleTax));
			lbSaleWithTax.setText(String.format("%,d", saleWithTax));
			lbSumOfOrder.setText(String.valueOf(sumOfOrder));
			datePickFrom.setDisable(true);
			datePickTo.setDisable(true);
		});

		radioThisMonth.setOnAction(e -> {
			List<Order> orders = new OrderDAO().getByMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
			long saleNoTax = 0l;
			long saleTax = 0l;
			long saleWithTax = 0l;
			int sumOfOrder = 0;
			for (int i = 0; i < orders.size(); i++) {
				saleNoTax += orders.get(i).getTotalRaw();
				saleTax += orders.get(i).getTotalVAT();
				saleWithTax += orders.get(i).getSubTotal();
			}
			sumOfOrder += new OrderDAO().sumOfOrderByMonth(LocalDate.now());
			lbSaleNoTax.setText(String.format("%,d", saleNoTax));
			lbSaleTax.setText(String.format("%,d", saleTax));
			lbSaleWithTax.setText(String.format("%,d", saleWithTax));
			lbSumOfOrder.setText(String.valueOf(sumOfOrder));
			datePickFrom.setDisable(true);
			datePickTo.setDisable(true);
		});
		radThisYear.setOnAction(e -> {
			List<Order> orders = new OrderDAO().getByYear(LocalDate.now().getYear());
			long saleNoTax = 0l;
			long saleTax = 0l;
			long saleWithTax = 0l;
			int sumOfOrder = 0;
			for (int i = 0; i < orders.size(); i++) {
				saleNoTax += orders.get(i).getTotalRaw();
				saleTax += orders.get(i).getTotalVAT();
				saleWithTax += orders.get(i).getSubTotal();
			}
			sumOfOrder += new OrderDAO().sumOfOrderByMonth(LocalDate.now());
			lbSaleNoTax.setText(String.format("%,d", saleNoTax));
			lbSaleTax.setText(String.format("%,d", saleTax));
			lbSaleWithTax.setText(String.format("%,d", saleWithTax));
			lbSumOfOrder.setText(String.valueOf(sumOfOrder));
			datePickFrom.setDisable(true);
			datePickTo.setDisable(true);

		});
		radCustom.setOnAction(e -> {
			datePickFrom.setDisable(false);
			datePickTo.setDisable(false);

		});
		datePickTo.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (check()) {
					List<Order> orders = new OrderDAO().getFromDatetoDate(datePickFrom.getValue(),
							datePickTo.getValue());
					long saleNoTax = 0l;
					long saleTax = 0l;
					long saleWithTax = 0l;
					int sumOfOrder = 0;
					for (int i = 0; i < orders.size(); i++) {
						saleNoTax += orders.get(i).getTotalRaw();
						saleTax += orders.get(i).getTotalVAT();
						saleWithTax += orders.get(i).getSubTotal();
					}
					sumOfOrder += new OrderDAO().sumOfOrderFromDateToDate(datePickFrom.getValue(),
							datePickTo.getValue());
					lbSaleNoTax.setText(String.format("%,d", saleNoTax));
					lbSaleTax.setText(String.format("%,d", saleTax));
					lbSaleWithTax.setText(String.format("%,d", saleWithTax));
					lbSumOfOrder.setText(String.valueOf(sumOfOrder));

				}

			}
		});

	}

	boolean check() {
		if (datePickFrom.getValue() == null) {
			datePickFrom.getStyleClass().add("error");
			datePickFrom.requestFocus();
			return false;
		} else {
			if (datePickFrom.getValue().isAfter(datePickTo.getValue())) {
				datePickFrom.getStyleClass().add("error");
				datePickTo.getStyleClass().add("error");
				datePickFrom.requestFocus();
				return false;
			}
		}

		datePickFrom.getStyleClass().remove("error");
		datePickTo.getStyleClass().remove("error");
		return true;
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		initTableMotorbike();
		initTableReplacement();
		initCustomerTable();
		initEmployeeTable();
		initTableMotorbike_QNP();
		initTableSupplier();
		initTableModel();
		SaleOrder();

	}

}
