package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import application.ErrorAlert;
import application.Handler;
import application.Main;
import application.daos.CustomerDAO;
import application.entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerInputController {
	Handler handler;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtFName;

    @FXML
    private CheckBox checkGender;

    @FXML
    private DatePicker datePickBirth;

    @FXML
    private TextField txtIDcard;

    @FXML
    private DatePicker datePickIdCard;

    @FXML
    private ComboBox<?> comboPlace;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextArea txtAddress;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    @FXML
    void initialize() {
    	handler = Main.getHandler();
    	btnSave.setOnAction(e->{
    		boolean gender;
    		if(checkGender.isSelected()) {
    			gender = false;
    		}
    		else
    			gender = true;
    		if(txtFName.getText().equals("")||txtLName.getText().equals("")||txtAddress.getText().equals("")||datePickBirth.equals(null)||txtPhone.getText().equals("")||txtIDcard.getText().equals("")||datePickIdCard.equals(null)){
    			ErrorAlert alert = new ErrorAlert("Lỗi!", "Tất cả các trường không được để trống!");
    			handler.setError(alert);
    			Main.newWindow("AlertMessage", "Thông báo");
    		}
    		else
    		{
    			
        		Customer customer = new Customer(txtFName.getText().trim(), txtLName.getText().trim(), txtAddress.getText().trim(), gender,datePickBirth.getValue(),txtPhone.getText().trim(), LocalDate.now(), txtIDcard.getText().trim(), datePickIdCard.getValue());
            	System.out.println(customer);
            	CustomerDAO customerDAO = new CustomerDAO();
            	customerDAO.save(customer);
            	
    		}
    	
    	});
    	
    	btnCancel.setOnAction(e->{
    		Main.closeWindow(btnCancel);
    		
    	});
    	
    	//Customer customer = new Customer();
		// System.out.println(od.toString());
		handler = Main.getHandler();
		CustomerDAO cusDAO = new CustomerDAO();
		List<Customer> listcustomer = cusDAO.getAll(Customer.class);
		///
		if (!listcustomer.isEmpty()) {
			String odlast = listcustomer.get(listcustomer.size() - 1).getBussinessID();
			String prefix = odlast.substring(0, 2);
			int numberOd = Integer.valueOf((odlast.substring(2, odlast.length())));
			String cusID = prefix + String.format("%04d", numberOd + 1);
			txtCustomerID.setText(cusID);
		} else {
			txtCustomerID.setText("KH0000");
		}
        assert txtCustomerID != null : "fx:id=\"txtCustomerID\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert txtLName != null : "fx:id=\"txtLName\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert txtFName != null : "fx:id=\"txtFName\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert checkGender != null : "fx:id=\"checkGender\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert datePickBirth != null : "fx:id=\"datePickBirth\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert txtIDcard != null : "fx:id=\"txtIDcard\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert datePickIdCard != null : "fx:id=\"datePickIdCard\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert comboPlace != null : "fx:id=\"comboPlace\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert txtPhone != null : "fx:id=\"txtPhone\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'CustomerInfoInput.fxml'.";

    }
}
