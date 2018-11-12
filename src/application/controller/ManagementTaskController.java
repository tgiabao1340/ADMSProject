package application.controller;

import java.awt.Dialog.ModalExclusionType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXAutoCompletePopup;

import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.daos.ModelDAO;
import application.daos.MotorbikeDAO;
import application.daos.SupplierDAO;
import application.entities.Customer;
import application.entities.Model;
import application.entities.Motorbike;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class ManagementTaskController {
	Handler handler;
	List<String> years = new ArrayList<>();
	List<Supplier> suppliers = new SupplierDAO().getAll(Supplier.class);
	List<String> suppliers_name = new ArrayList<>();
	List<String> types = new ArrayList<>();
	List<Motorbike> listM = new MotorbikeDAO().getAll(Motorbike.class);
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
	private TextField textMotorbike_COLOR;

	@FXML
	private TableView<Motorbike> tableMotorbike;

	@FXML
	private TableView<Customer> tableCustomer;

	@FXML
	private Button btnSave_M;

	@FXML
	private Button btnNew_M;

	@FXML
	private Button btnDelete_M;

	@FXML
	void MAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnBack) {
			Main.changeLayout("RoleUI");
		}
	}

	void loadMotorbike(List<Motorbike> listmotorbike) {

		TableColumn<Motorbike, Motorbike> colNumbered_M = new TableColumn<>("STT");
		TableColumn<Motorbike, String> colID_M = new TableColumn<>("Mã xe");
		TableColumn<Motorbike, String> colName_M = new TableColumn<>("Tên xe");
		TableColumn<Motorbike, String> colCapacity_M = new TableColumn<>("Dung tích");
		TableColumn<Motorbike, String> colYear_M = new TableColumn<>("Năm sản xuất");
		TableColumn<Motorbike, String> colSupplierName_M = new TableColumn<>("Mã NSX");
		TableColumn<Motorbike, String> colModel_M = new TableColumn<>("Mã Model");
		colNumbered_M.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Motorbike,Motorbike>, ObservableValue<Motorbike>>() {

			@Override
			public ObservableValue<Motorbike> call(CellDataFeatures<Motorbike, Motorbike> param) {
				return new ReadOnlyObjectWrapper(param.getValue());
			}
		});
		colNumbered_M.setCellFactory(new Callback<TableColumn<Motorbike,Motorbike>, TableCell<Motorbike,Motorbike>>() {

			@Override
			public TableCell<Motorbike, Motorbike> call(TableColumn<Motorbike, Motorbike> param) {
				// TODO Auto-generated method stub
				return new TableCell<Motorbike,Motorbike>(){
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
		colID_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getProductID()));
		colName_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getProductName()));
		colCapacity_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getCapacity())));
		colYear_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(String.valueOf(celldata.getValue().getManufactureYear())));
		colSupplierName_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getSupplier().getSupplierName()));	
		colModel_M.setCellValueFactory(
				celldata -> new SimpleStringProperty(celldata.getValue().getModel().getModelID()));
		ObservableList<Motorbike> items_M = FXCollections.observableArrayList(listmotorbike);
		tableMotorbike.setItems(items_M);
		tableMotorbike.getColumns().addAll(colNumbered_M,colID_M,colName_M,colCapacity_M,colYear_M,colSupplierName_M,colModel_M);
		tableMotorbike.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Motorbike>() {
			@Override
			public void changed(ObservableValue<? extends Motorbike> observable, Motorbike oldValue,
					Motorbike newValue) {
				if(newValue!=null) {
					clearField();
					loadtoField_M(newValue);
				}

			}

			void loadtoField_M(Motorbike motorbike) {
				
				textMotorbike_ID.setText(motorbike.getProductID());
				textMotorbike_NAME.setText(motorbike.getProductName());
				choiceMotorbike_YEAR.getItems().addAll(years);
				choiceMotorbike_TYPE.getItems().addAll(types);
				choiceMotorbike_SUP.getItems().addAll(suppliers_name);
				choiceMotorbike_YEAR.getSelectionModel().select(years.indexOf(String.valueOf(motorbike.getManufactureYear())));
				choiceMotorbike_TYPE.getSelectionModel().select(types.indexOf(motorbike.getType()));
				choiceMotorbike_SUP.getSelectionModel().select(suppliers_name.indexOf(motorbike.getSupplier().getSupplierName()+" ["+motorbike.getSupplier().getSupplierID()+"]"));
				textMotorbike_COLOR.setText(motorbike.getColor());
				textCapacity.setText(String.valueOf(motorbike.getCapacity()));
				textDes.setText(motorbike.getStatus());
				textModelID.setText(motorbike.getModel().getModelID());
			}			
			void clearField() {
				textMotorbike_ID.clear();
				textMotorbike_NAME.clear();
				textCapacity.clear();
				choiceMotorbike_YEAR.getItems().clear();
				choiceMotorbike_SUP.getItems().clear();
				choiceMotorbike_TYPE.getItems().clear();
				textMotorbike_COLOR.clear();
				textDes.clear();
				textModelID.clear();
			}
		});
	}
	void reloadTable_M() {
		tableMotorbike.getColumns().clear();
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
		textModelID.textProperty().addListener(observable->{
			autoCompletePopup.filter(string -> string.toLowerCase().contains(textModelID.getText().toLowerCase()));
			if (autoCompletePopup.getFilteredSuggestions().isEmpty() || textModelID.getText().isEmpty()) {
				autoCompletePopup.hide();
			} else {
				autoCompletePopup.show(textModelID);
			}
		});
		btnNew_M.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textMotorbike_ID.clear();
				textMotorbike_NAME.clear();
				textCapacity.clear();
				textMotorbike_COLOR.clear();
				textDes.clear();
				textModelID.clear();
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
					textMotorbike_ID.setText("OR0000");
				}

			}
		});
		btnSave_M.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(check()) {
					String name = textMotorbike_NAME.getText();
					String color = textMotorbike_COLOR.getText();
					String status = textDes.getText();
					String type = choiceMotorbike_TYPE.getSelectionModel().getSelectedItem();
					String[] sup = choiceMotorbike_SUP.getSelectionModel().getSelectedItem().split(" ");
					String year =choiceMotorbike_YEAR.getSelectionModel().getSelectedItem();
					String modelID = textModelID.getText();
					Motorbike motorbike = new Motorbike();
					motorbike.setProductName(name);
					motorbike.setColor(color);
					motorbike.setStatus(status);
					motorbike.setType(type);
					Supplier s = new SupplierDAO().findById(sup[0]);
					motorbike.setSupplier(s);
					motorbike.setManufactureYear(Integer.valueOf(year));
					Model model = new ModelDAO().findById(modelID);
					motorbike.setModel(model);
					listM.add(motorbike);
					reloadTable_M();
				}
			}
			boolean check() {
				if(textMotorbike_ID.getText().isEmpty()) {
					textMotorbike_ID.getStyleClass().add("error");
					textMotorbike_ID.requestFocus();
					return false;
				}
				if(textMotorbike_NAME.getText().isEmpty()) {
					textMotorbike_NAME.getStyleClass().add("error");
					textMotorbike_NAME.requestFocus();
					return false;
				}
				if(choiceMotorbike_YEAR.getSelectionModel().isEmpty()) {
					choiceMotorbike_YEAR.getStyleClass().add("error");
					return false;
				}
				if(choiceMotorbike_SUP.getSelectionModel().isEmpty()) {
					choiceMotorbike_SUP.getStyleClass().add("error");
					return false;
				}
				else {
					String[] SupplierID = choiceMotorbike_SUP.getSelectionModel().getSelectedItem().split(" ");
					Supplier supplier = new SupplierDAO().findById(SupplierID[0]);
					if (supplier==null) {
						choiceMotorbike_SUP.getStyleClass().add("error");
						choiceMotorbike_SUP.requestFocus();
						return false;
					}
				}
				if(choiceMotorbike_TYPE.getSelectionModel().isEmpty()) {
					choiceMotorbike_TYPE.getStyleClass().add("error");
					return false;
				}

				if(textMotorbike_COLOR.getText().isEmpty()) {
					textMotorbike_COLOR.getStyleClass().add("error");
					textMotorbike_COLOR.requestFocus();
					return false;
				}
				if(textDes.getText().isEmpty()) {
					textDes.getStyleClass().add("error");
					textDes.requestFocus();
					return false;
				}
				if(textModelID.getText().isEmpty()) {

					textModelID.getStyleClass().add("error");
					textModelID.requestFocus();
					return false;
				}
				else {
					String modelID = textModelID.getText();
					Model model = new ModelDAO().findById(modelID);
					if (modelID==null) {
						textModelID.getStyleClass().add("error");
						textModelID.requestFocus();
						return false;
					}
				}
				textMotorbike_ID.getStyleClass().remove("error");
				textMotorbike_NAME.getStyleClass().remove("error");
				textMotorbike_COLOR.getStyleClass().remove("error");
				textDes.getStyleClass().remove("error");
				textModelID.getStyleClass().remove("error");
				choiceMotorbike_TYPE.getStyleClass().remove("error");
				choiceMotorbike_SUP.getStyleClass().remove("error");
				choiceMotorbike_YEAR.getStyleClass().remove("error");

				return true;
			}
		});
	}
	@FXML
	void initialize() {
		handler = Main.getHandler();
		for (int i = 1990; i < LocalDate.now().getYear()+1; i++) {
			years.add(String.valueOf(i));
		}
		for (int i = 0; i < suppliers.size(); i++) {
			suppliers_name.add(suppliers.get(i).getSupplierName()+" ["+suppliers.get(i).getSupplierID()+"]");
		}
		types.add("Tay ga");
		types.add("Tay côn");
		types.add("Xe số");
		for (int i = 0; i < listModel.size(); i++) {
			listModelID.add(listModel.get(i).getModelID());
		}
		loadMotorbike(listM);
		Action_M();


	}

}
