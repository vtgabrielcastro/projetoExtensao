package Control;

import application.BaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InterfaceController extends BaseController{
	@FXML
	private void exit() {
		Platform.exit();
	}

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Label errorLabel;

	@FXML
	private Button loginButton;
	@FXML
	private Button btnCadastroCliente;
	@FXML
	private Button btnListaCliente;
	
	private final String USERNAME = "admin";
	private final String PASSWORD = "123456";

	@FXML
	private void handleLogin(ActionEvent event) {
		String username = usernameField.getText();
		String password = passwordField.getText();

		if (username.equals(USERNAME) && password.equals(PASSWORD)) {
			irParaDashboard(event);
		} else {
			errorLabel.setText("Usuário ou Senha inválido!");
		}
	}
	@FXML
	private Button logoutButton;

	@FXML
	private void handleLogout() {
		try {
			Stage stage = (Stage) logoutButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
