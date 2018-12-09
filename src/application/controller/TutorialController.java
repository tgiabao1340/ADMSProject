package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class TutorialController {
	@FXML
	private WebView webView;

	private WebEngine engine;
	@FXML
	private Button btnClose;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void TutorialAction(ActionEvent event) {
		Object btn = event.getSource();
		if (btn == btnClose) {
			Main.closeWindow(btn);
		}
	}

	@FXML
	void initialize() {
		engine = webView.getEngine();
		engine.load("https://www.thegioididong.com/");
	}
}