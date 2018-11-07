package application.controller;

import java.util.ResourceBundle;

import application.Main;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class LoadingScreenController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private ProgressBar progressBar = new ProgressBar(0);

	@FXML
	private ProgressIndicator progressIndicatior = new ProgressIndicator(0);

	@FXML
	void initialize() {
		Load();

	}

	void Load() {
		progressBar.progressProperty().unbind();
		progressIndicatior.progressProperty().unbind();
		Task<Void> task = Main.getHandler().loaddata();
		progressBar.progressProperty().bind(task.progressProperty());
		progressIndicatior.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
		task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent event) {
				Main.closeWindow(progressBar);
				Main.newWindow("Login", "Đăng Nhập");
			}
		});
		new Thread(task).start();

	}
}
