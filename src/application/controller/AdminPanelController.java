package application.controller;

import java.util.List;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.AccountDAO;
import application.daos.EmployeeDAO;
import application.entities.Account;
import application.entities.Employee;
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
import javafx.util.Callback;

public class AdminPanelController {

	private Handler handler;
	private List<Account> listA;
	@FXML
	private TextField textAccountID;

	@FXML
	private ChoiceBox<?> choicePosition;

	@FXML
	private TextField textEmployeeID;

	@FXML
	private Button btnAddEmployee;

	@FXML
	private DatePicker dpCreateDate;

	@FXML
	private Label textTotalAccount;

	@FXML
	private TableView<Account> tableAccount;

	@FXML
	private Button btnSave_A;

	@FXML
	private Button btnNew_A;

	@FXML
	private Button btnDelete_A;

	@FXML
	private Button btnBack;

	@FXML
	void AdAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnBack) {
			Main.changeLayout("RoleUI");
		}
	}

	void loadAccount(List<Account> list) {

		///
		TableColumn<Account, Account> colNumbered = new TableColumn<>("STT");
		TableColumn<Account, String> colID = new TableColumn<>("Mã Account");
		TableColumn<Account, String> colDate = new TableColumn<>("Ngày tạo");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Account, Account>, ObservableValue<Account>>() {

					@Override
					public ObservableValue<Account> call(CellDataFeatures<Account, Account> param) {
						return new ReadOnlyObjectWrapper(param.getValue());
					}
				});
		colNumbered.setCellFactory(new Callback<TableColumn<Account, Account>, TableCell<Account, Account>>() {

			@Override
			public TableCell<Account, Account> call(TableColumn<Account, Account> param) {
				return new TableCell<Account, Account>() {
					@Override
					protected void updateItem(Account arg0, boolean arg1) {

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
		colID.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getAccountID()));
		ObservableList<Account> items = FXCollections.observableArrayList(list);
		tableAccount.setItems(items);
		tableAccount.getColumns().addAll(colNumbered, colID);
		tableAccount.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
			@Override
			public void changed(ObservableValue<? extends Account> observable, Account oldValue, Account newValue) {
				if (newValue != null) {
					clearField();
					loadtoField(newValue);
				}

			}

			void loadtoField(Account account) {
				textAccountID.setText(account.getAccountID());
				Employee emp = new EmployeeDAO().findByAc(account);
				if (emp == null) {
					textEmployeeID.setText(" ");
				} else
					textEmployeeID.setText(emp.getBussinessID());
			}

			void clearField() {
				textAccountID.clear();
				textEmployeeID.clear();
			}
		});
	}

	void reloadTable_A() {
		tableAccount.getColumns().clear();
		listA = new AccountDAO().getAll(Account.class);
		loadAccount(listA);
	}

	void Action_A() {
		btnAddEmployee.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.newWindow("EmployeeInfoInput", "Tạo nhân viên mới");
			}
		});
		btnNew_A.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textAccountID.clear();
				textEmployeeID.clear();
				textAccountID.requestFocus();

			}
		});
		btnSave_A.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (check()) {
					Account account;
					String id = textAccountID.getText();
					if ((new AccountDAO().getById(Account.class, id)) != null) {
						account = new AccountDAO().getById(Account.class, id);
						new AccountDAO().update(setA(account));
					} else {
						account = new Account();
						new AccountDAO().save(setA(account));
					}
					reloadTable_A();
				}
			}

			Account setA(Account account) {
				String id = textAccountID.getText();
				String employeeid = textEmployeeID.getText();
				return account;
			}

			boolean check() {
				if (textAccountID.getText().isEmpty()) {
					textAccountID.getStyleClass().add("error");
					textAccountID.requestFocus();
					return false;
				}
				if (textEmployeeID.getText().isEmpty()) {
					textEmployeeID.getStyleClass().add("error");
					textEmployeeID.requestFocus();
					return false;
				}
				textEmployeeID.getStyleClass().remove("error");
				textAccountID.getStyleClass().remove("error");
				return true;
			}
		});
		btnDelete_A.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Account account_selected = tableAccount.getSelectionModel().getSelectedItem();
				boolean check = new AccountDAO().delete(account_selected);
				if (check) {
					ErrorAlert error = new ErrorAlert("Xóa thành công",
							"Tài khoản :" + account_selected.getAccountID() + " đã được xóa.");
					handler.setError(error);
					reloadTable_A();
					Main.newWindow("AlertMessage", "Thông báo");
				}
			}

		});

	}

	public void setEmployee() {
		Employee employee = handler.getNew_Employee();
		textEmployeeID.setText(employee.getBussinessID());
	}

	void initAccountTable() {
		listA = new AccountDAO().getAll(Account.class);
		loadAccount(listA);
		Action_A();
	}

	@FXML
	void initialize() {
		handler = Main.getHandler();
		initAccountTable();
	}
}
