package application;

/**
 * @author Tran Gia Bao
 * 
 * 
 * 
 *
 */
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private static Stage primaryStage;
	private static HashMap<String, String> listUI = new HashMap<>();
	public static final float WIDTH = 1280, HEIGTH = 680; // Khởi tạo mặc định tỷ lệ màn hình ( Dài - cao) .
	private static Handler handler;

	@Override
	public void start(final Stage primaryStage) {
		Main.primaryStage = primaryStage;
		handler = new Handler();
		initLayout();
		primaryStage.getIcons().add(new Image("/application/style/icon-title.png"));
		primaryStage.setTitle("QLCH");
		primaryStage.setResizable(true);
		primaryStage.setMaximized(true);
		primaryStage.setOnCloseRequest(e -> {
			System.exit(1);
			Platform.exit();
		});
	}

	/**
	 * Khởi tạo giao diện ban đầu - Thêm các đường dẫn layout vào danh sách ListUI
	 * 
	 */
	public void initLayout() {
		listUI.put("Login", "/application/fxml/Login.fxml");
		listUI.put("EmployeeUI", "/application/fxml/EmployeeUI.fxml");
		listUI.put("CreateOrder", "/application/fxml/CreateOrder.fxml");
		listUI.put("MaintenaceReport", "/application/fxml/MaintenaceReport.fxml");
		listUI.put("RoleUI", "/application/fxml/RoleUI.fxml");
		listUI.put("ManagementTask", "/application/fxml/ManagementTask.fxml");
		listUI.put("AdminPanel", "/application/fxml/AdminPanel.fxml");
		listUI.put("ConfirmPassword", "/application/fxml/ConfirmPassword.fxml");
		listUI.put("CustomerInfoInput", "/application/fxml/CustomerInfoInput.fxml");
		listUI.put("EmployeeInfoInput", "/application/fxml/EmployeeInfoInput.fxml");
		listUI.put("About", "/application/fxml/About.fxml");
		listUI.put("LoadingScreen", "/application/fxml/LoadingScreen.fxml");
		listUI.put("AlertMessage", "/application/fxml/AlertMessage.fxml");
		listUI.put("OrderView", "/application/fxml/OrderView.fxml");
		primaryStage.setWidth(WIDTH);
		primaryStage.setHeight(HEIGTH);
		newWindow("LoadingScreen", "Loading");
	}

	/**
	 * Load FXML from URL
	 */
	public static FXMLLoader loadFXML(final String url) {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(url));
		return loader;
	}

	/**
	 * Chuyển đổi layout
	 * 
	 * @param value là giá trị key trong listUI (List chứa danh sách đường dẫn UI)
	 */
	public static void changeLayout(final String value) {
		try {
			Parent root = loadFXML(listUI.get(value)).load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Đóng cửa sổ
	 * 
	 * @param btn - Object trên Parent đang có
	 */
	public static void closeWindow(final Object btn) {
		final Stage stage = (Stage) ((Node) btn).getScene().getWindow();
		stage.close();
	}

	/**
	 *
	 * Tạo cửa sổ mới
	 * 
	 * @param mapName   tên key trong danh sách Layout
	 * @param titlename tên tile cửa sổ
	 */
	public static void newWindow(String mapName, String titlename) {
		try {

			Stage stage = new Stage();
			if (mapName.equalsIgnoreCase("LoadingScreen")) {
				stage.initStyle(StageStyle.UNDECORATED);
			}
			Parent root = loadFXML(listUI.get(mapName)).load();
			stage.getIcons().add(new Image("/application/style/icon-title.png"));
			stage.setResizable(false);
			stage.setTitle(titlename);
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL); // Lock window
			stage.show();

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cửa sổ lỗi!");
		}

	}

	public static Handler getHandler() {
		return handler;
	}

	public static void setHandler(Handler handler) {
		Main.handler = handler;
	}

	public static void enableMainwindow() {
		primaryStage.show();
	}

	public static void disableMainwindow() {
		primaryStage.hide();
	}

	public static void main(final String[] args) {
		launch(args);
	}

}
