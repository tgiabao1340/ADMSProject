package application.controller;

import java.io.InputStream;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutController {

	@FXML
	private Button btnClose;

	@FXML
	private ImageView bgimage;

	@FXML
	void AAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnClose) {
			Main.closeWindow(btnClose);
		}
	}

	@FXML
	void initialize() {
		InputStream url1 = getClass().getResourceAsStream("/application/style/bg.png");
		bgimage.setImage(new Image(url1, 480, 240, false, true));
	}

}
