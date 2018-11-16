package application.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import com.jfoenix.controls.JFXAutoCompletePopup;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.daos.ModelDAO;
import application.daos.MotorbikeDAO;
import application.daos.ReplacementDAO;
import application.daos.SupplierDAO;
import application.entities.Customer;
import application.entities.Model;
import application.entities.Motorbike;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ManagementTaskController {
	Handler handler;

	// Motorbike
	List<String> years = new ArrayList<>();
	List<Supplier> suppliers = new SupplierDAO().getAll(Supplier.class);
	List<String> suppliers_name = new ArrayList<>();
	List<String> types = new ArrayList<>();
	List<Motorbike> listM;
	List<Model> listModel = new ModelDAO().getAll(Model.class);
	List<String> listModelID = new ArrayList<>();
	@FXML
	private Button btnBack;

	@FXML
	private TextField textMotorbike_ID;

	@FXML
	private TextField textMotorbike_NAME;

	@FXML
	private ChoiceBox<String> choiceMotorbike_YEAR;

	@FXML
	private ChoiceBox<String> choiceMotorbike_SUP;

	@FXML
	private ChoiceBox<String> choiceMotorbike_TYPE;

	@FXML
	private TextField textCapacity;

	@FXML
	private TextField textDes;

	@FXML
	private TextField textModelID;

	@FXML
	private Label textModelname;

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
	private ChoiceBox<String> choiceReplacement_SUP;

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
	private TextField textCustomer_AD;

	@FXML
	private TextField textCustomer_IDCARD;

	@FXML
	private DatePicker dpCustomer_IDDATE;

	@FXML
	private Button btnSave_C;

	@FXML
	private Button btnNew_C;

	@FXML
	private Button btnDelete_C;

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
				choiceMotorbike_SUP.getItems().addAll(suppliers_name);
				textMotorbike_ID.setText(motorbike.getProductID());
				textMotorbike_NAME.setText(motorbike.getProductName());
				choiceMotorbike_YEAR.getSelectionModel()
				.select(years.indexOf(String.valueOf(motorbike.getManufactureYear())));
				choiceMotorbike_TYPE.getSelectionModel().select(types.indexOf(motorbike.getType()));
				choiceMotorbike_SUP.getSelectionModel().select(suppliers_name.indexOf(
						motorbike.getSupplier().getSupplierName() + "-" + motorbike.getSupplier().getSupplierID()));
				String color[] = motorbike.getColor().split(", ");
				for (int i = 0; i < color.length; i++) {
					comboxMotorbike_COLOR.getCheckModel().check(color[i]);
				}
				textCapacity.setText(String.format("%.0f", motorbike.getCapacity()));
				textDes.setText(motorbike.getStatus());
				textModelID.setText(motorbike.getModel().getModelID());
				textModelname.setText(motorbike.getModel().getName());
			}

			void clearField() {
				textMotorbike_ID.clear();
				textMotorbike_NAME.clear();

				choiceMotorbike_YEAR.getItems().clear();
				choiceMotorbike_SUP.getItems().clear();
				choiceMotorbike_TYPE.getItems().clear();
				comboxMotorbike_COLOR.getCheckModel().clearChecks();
				textCapacity.clear();
				textDes.clear();
				textModelID.clear();
				textModelname.setText("");
			}
		});
	}

	void reloadTable_M() {
		tableMotorbike.getColumns().clear();
		listM = new MotorbikeDAO().getAll(Motorbike.class);
		loadMotorbike(listM);
	}

	void Action_M() {
		JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
		autoCompletePopup.getSuggestions().addAll(listModelID);
		autoCompletePopup.setCellLimit(2);
		autoCompletePopup.setAutoFix(false);
		autoCompletePopup.setSelectionHandler(event -> {
			textModelID.setText(event.getObject());
		});

		textModelID.textProperty().addListener(observable -> {
			autoCompletePopup.filter(string -> string.toLowerCase().contains(textModelID.getText().toLowerCase()));
			if (autoCompletePopup.getFilteredSuggestions().isEmpty() || textModelID.getText().isEmpty()) {
				autoCompletePopup.hide();
			} else {
				if (textModelID.isFocused()) {
					autoCompletePopup.show(textModelID);
				}
			}
		});
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
				textModelID.clear();
				textModelname.setText("");
				choiceMotorbike_YEAR.getSelectionModel().clearSelection();
				choiceMotorbike_SUP.getSelectionModel().clearSelection();
				choiceMotorbike_TYPE.getSelectionModel().clearSelection();
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
				String[] sup = choiceMotorbike_SUP.getSelectionModel().getSelectedItem().split("-");
				String year = choiceMotorbike_YEAR.getSelectionModel().getSelectedItem();
				String modelID = textModelID.getText();

				motorbike.setProductName(name);
				String color = "";
				for (int i = 0; i < comboxMotorbike_COLOR.getCheckModel().getCheckedItems().size(); i++) {
					color += ", " + comboxMotorbike_COLOR.getCheckModel().getCheckedItems().get(i);
				}
				motorbike.setColor(color);
				motorbike.setCapacity(Double.valueOf(textCapacity.getText()));
				motorbike.setStatus(status);
				motorbike.setType(type);
				Supplier s = new SupplierDAO().findById(sup[1]);
				motorbike.setSupplier(s);
				motorbike.setManufactureYear(Integer.valueOf(year));
				Model model = new ModelDAO().findById(modelID);
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
					choiceMotorbike_SUP.getStyleClass().add("error");
					return false;
				} else {
					String[] sup = choiceMotorbike_SUP.getSelectionModel().getSelectedItem().split("-");
					Supplier s = new SupplierDAO().findById(sup[1]);
					if (s == null) {
						ErrorAlert error = new ErrorAlert("Nhà cung cấp không hợp lệ",
								"Nhà cung cấp không tồn tại hoặc chưa chọn nhà cung cấp");
						handler.setError(error);
						Main.newWindow("AlertMessage", "Thông báo");
						return false;
					}

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
				if (textModelID.getText().isEmpty()) {
					textModelID.getStyleClass().add("error");
					textModelID.requestFocus();
					return false;
				} else {
					Model m = new ModelDAO().findById(textModelID.getText());
					if (m == null) {
						ErrorAlert error = new ErrorAlert("Model không hợp lệ",
								"Model không tồn tại hoặc chưa chọn model");
						handler.setError(error);
						Main.newWindow("AlertMessage", "Thông báo");
						return false;
					}

				}
				textMotorbike_ID.getStyleClass().remove("error");
				textMotorbike_NAME.getStyleClass().remove("error");
				textDes.getStyleClass().remove("error");
				textModelID.getStyleClass().remove("error");
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
		handler = Main.getHandler();
		for (int i = 1990; i < LocalDate.now().getYear() + 1; i++) {
			years.add(String.valueOf(i));
		}
		for (int i = 0; i < suppliers.size(); i++) {
			suppliers_name.add(suppliers.get(i).getSupplierName() + "-" + suppliers.get(i).getSupplierID());
		}
		types.add("Tay ga");
		types.add("Tay côn");
		types.add("Xe số");
		for (int i = 0; i < listModel.size(); i++) {
			listModelID.add(listModel.get(i).getModelID());
		}
		choiceMotorbike_YEAR.getItems().addAll(years);
		choiceMotorbike_TYPE.getItems().addAll(types);
		choiceMotorbike_SUP.getItems().addAll(suppliers_name);
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
				choiceReplacement_SUP.getItems().addAll(suppliers_name);
				textReplacement_ID.setText(replacement.getProductID());
				textMotorbike_NAME.setText(replacement.getProductName());
				choiceMotorbike_SUP.getSelectionModel().select(suppliers_name.indexOf(
						replacement.getSupplier().getSupplierName() + "-" + replacement.getSupplier().getSupplierID()));
				textReplacement_DES.setText(replacement.getDescription());
				textReplacement_STATUS.setText(replacement.getStatus());
			}

			void clearField() {
				textReplacement_ID.clear();
				textReplacement_NAME.clear();
				choiceReplacement_SUP.getItems().clear();
				textReplacement_DES.clear();
			}
		});
	}

	void reloadTable_R() {
		tableRepacement.getColumns().clear();
		listR = new ReplacementDAO().getAll(Replacement.class);
		loadReplacement(listR);
	}

	void Action_R() {
		btnNew_R.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textReplacement_ID.clear();
				textReplacement_NAME.clear();
				choiceReplacement_SUP.getSelectionModel().clearSelection();
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
				String[] sup = choiceReplacement_SUP.getSelectionModel().getSelectedItem().split("-");
				replacement.setProductName(name);
				Supplier s = new SupplierDAO().findById(sup[1]);
				replacement.setSupplier(s);
				replacement.setStatus(status);
				replacement.setDescription(des);
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
					choiceReplacement_SUP.getStyleClass().add("error");
					return false;
				} else {
					String[] sup = choiceReplacement_SUP.getSelectionModel().getSelectedItem().split("-");
					Supplier s = new SupplierDAO().findById(sup[1]);
					if (s == null) {
						ErrorAlert error = new ErrorAlert("Nhà cung cấp không hợp lệ",
								"Nhà cung cấp không tồn tại hoặc chưa chọn nhà cung cấp");
						handler.setError(error);
						Main.newWindow("AlertMessage", "Thông báo");
						return false;
					}

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
		handler = Main.getHandler();
		for (int i = 0; i < listModel.size(); i++) {
			listModelID.add(listModel.get(i).getModelID());
		}
		choiceReplacement_SUP.getItems().addAll(suppliers_name);
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
				String id = textCustomer_ID.getText();
				String lname = textCustomer_LNAME.getText();
				String fname = textCustomer_FNAME.getText();
				String address = textCustomer_AD.getText();
				String phone = textCustomer_PHONE.getText();
				String idcard = textCustomer_IDCARD.getText();
				LocalDate dob = dpcustomerDoB.getValue();
				LocalDate iddate = dpCustomer_IDDATE.getValue();
				customer.setLastName(lname);
				customer.setFirstName(fname);
				customer.setAddress(address);
				customer.setPhoneNumber(phone);
				customer.setIdCard(idcard);
				customer.setDateOfBirth(dob);
				customer.setCreatedDate(iddate);
				customer.setIdCardDate(iddate);
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

	void initCustomerTable() {
		listC = new CustomerDAO().getAll(Customer.class);
		loadCustomer(listC);
		Action_C();
	}

	@FXML
	void initialize() {
		initTableMotorbike();
		initTableReplacement();
		initCustomerTable();
	}

}
