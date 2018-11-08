package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.daos.OrderDAO;
import application.entities.Order;
import application.entities.OrderDetail;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class OrderViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderDetail> tableOrderDetail;

    @FXML
    private Text textTax;

    @FXML
    private Text textTotal;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCancel1;

    @FXML
    private Label lbCusName;

    @FXML
    private Label lbAdd;

    @FXML
    private Label lbPhone;

    @FXML
    private Label lbEmpName;
    
    @FXML
    private Label lbOrderDate;
    @FXML
    void createOrderAction(ActionEvent event) {

    }
   private Order order;
   private OrderDAO orderDAO = new OrderDAO();
   private String id = "OR0001";
    @FXML
    void initialize() {
    	order = orderDAO.getById(Order.class,id);
    	List<OrderDetail> orderDetails = order.getListItems();
    	LoadTable(orderDetails);
    	loadTotal(orderDetails);
      	lbOrderDate.setText(order.getDate().toString());
      	lbCusName.setText(order.getCustomer().getLastName().toString() + " " + order.getCustomer().getFirstName().toString());
        lbAdd.setText(order.getCustomer().getAddress());
        lbPhone.setText(order.getCustomer().getPhoneNumber());
        lbEmpName.setText(order.getEmployee().getLastName() + " " + order.getEmployee().getFirstName());
      	assert tableOrderDetail != null : "fx:id=\"tableOrderDetail\" was not injected: check your FXML file 'OrderView.fxml'.";
        assert textTax != null : "fx:id=\"textTax\" was not injected: check your FXML file 'OrderView.fxml'.";
        assert textTotal != null : "fx:id=\"textTotal\" was not injected: check your FXML file 'OrderView.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'OrderView.fxml'.";
        assert btnCancel1 != null : "fx:id=\"btnCancel1\" was not injected: check your FXML file 'OrderView.fxml'.";

    }
    @SuppressWarnings("unchecked")
	void LoadTable(List<OrderDetail> list) {
		TableColumn<OrderDetail, OrderDetail> colNumbered = new TableColumn<>("STT");
		colNumbered.setPrefWidth(100);
		TableColumn<OrderDetail, String> colName = new TableColumn<>("Tên Xe");
		TableColumn<OrderDetail, String> colSup = new TableColumn<>("Hãng");
		TableColumn<OrderDetail, String> colColor = new TableColumn<>("Màu");
		TableColumn<OrderDetail, String> colQuantity = new TableColumn<>("Số Lượng");
		TableColumn<OrderDetail, String> colUP = new TableColumn<>("Đơn giá");
		TableColumn<OrderDetail, String> colST = new TableColumn<>("Thành tiền");
		colNumbered.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<OrderDetail, OrderDetail>, ObservableValue<OrderDetail>>() {

					@SuppressWarnings("rawtypes")
					@Override
					public ObservableValue<OrderDetail> call(CellDataFeatures<OrderDetail, OrderDetail> param) {
						// TODO Auto-generated method stub
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
		colUP.setCellValueFactory(celldata -> new SimpleStringProperty(String.format("%,12.2f",
				celldata.getValue().getMotorbike().getUnitPrice())));
		colST.setCellValueFactory(celldata -> new SimpleStringProperty(String.format("%,12.2f",
				celldata.getValue().getMotorbike().getUnitPrice() * celldata.getValue().getQuantity())));
		ObservableList<OrderDetail> items = FXCollections.observableArrayList(list);
		tableOrderDetail.setItems(items);
		tableOrderDetail.getColumns().addAll(colNumbered, colName, colSup, colColor, colQuantity, colUP, colST);
	}
	void loadTotal(List<OrderDetail> list) {
		textTax.setText("");
		double total = 0;
		double tax = 0;
		total += order.getSubTotal();
		tax +=order.getTotalVAT();
		textTax.setText(String.format("%,12.0f VND", tax));
		textTotal.setText(String.format("%,12.0f VND", total));
	}
}
