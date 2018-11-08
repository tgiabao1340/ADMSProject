package application.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main2 extends Application {
private double xOffset;
private double yOffset;
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		Parent fxml = FXMLLoader.load(getClass().getResource("/application/fxml/SupplierInfoInput.fxml"));
//		Parent fxml = FXMLLoader.load(getClass().getResource("/application/uis/DepartmentHeader.fxml"));
		Scene scene = new Scene(fxml);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
		scene.setOnMousePressed(e->{
			xOffset = primaryStage.getX() - e.getScreenX();
            yOffset = primaryStage.getY() - e.getScreenY();
		});
		scene.setOnMouseDragged(e->{
			primaryStage.setX(e.getScreenX() + xOffset);
            primaryStage.setY(e.getScreenY() + yOffset);
		});
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
