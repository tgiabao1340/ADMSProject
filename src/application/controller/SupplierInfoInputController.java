package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.SupplierDAO;
import application.entities.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SupplierInfoInputController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtTax;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextArea txtAddress;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

	private Handler handler;

	private SupplierDAO supplierDAO;

    @FXML
    void initialize() {
    	handler = Main.getHandler();
    	btnSave.setOnAction(e->{
    		if(txtName.getText().trim().equals("")||txtCountry.getText().trim().equals("")||txtAddress.getText().equals("")||txtPhone.getText().equals("")||txtTax.getText().equals("")){
    			ErrorAlert alert = new ErrorAlert("Lỗi!", "Tất cả các trường không được để trống!");
    			handler.setError(alert);
    			Main.newWindow("AlertMessage", "Thông báo");
    		}
    		else
    		{
    			
        		Supplier supplier = new Supplier(txtCountry.getText().trim(), txtName.getText().trim(), txtAddress.getText().trim(), txtPhone.getText().trim(), txtTax.getText().trim());
      
            	supplierDAO = new SupplierDAO();
            	supplierDAO.save(supplier);
            	
    		}
    	
    	});
    	
    	btnCancel.setOnAction(e->{
    		Main.closeWindow(btnCancel);
    		
    	});
		List<Supplier> suppliers = supplierDAO.getAll(Supplier.class);
		///
		if (!suppliers.isEmpty()) {
			String odlast = suppliers.get(suppliers.size() - 1).getSupplierID();
			String prefix = odlast.substring(0, 3);
			int numberOd = Integer.valueOf((odlast.substring(2, odlast.length())));
			String cusID = prefix + String.format("%04d", numberOd + 1);
			txtID.setText(cusID);
		} else {
			txtID.setText("KH0000");
		}
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert txtCountry != null : "fx:id=\"txtCountry\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert txtTax != null : "fx:id=\"txtTax\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert txtPhone != null : "fx:id=\"txtPhone\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'SupplierInfoInput.fxml'.";

    }
}
