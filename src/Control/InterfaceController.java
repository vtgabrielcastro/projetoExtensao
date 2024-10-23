package Control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InterfaceController {
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
	private void handleLogin() {
		String username = usernameField.getText();
		String password = passwordField.getText();

		if (username.equals(USERNAME) && password.equals(PASSWORD)) {
			loadDashboard();
		} else {
			errorLabel.setText("Usuário ou Senha inválido!");
		}
	}
	@FXML
	private void handleClienteCadastro() {
		try {
			Stage stage = (Stage) btnCadastroCliente.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastroCliente.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@FXML
	private void handleListaCliente() {
		try {
			Stage stage = (Stage) btnListaCliente.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ListaCliente.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	private void loadDashboard() {
		try {
			Stage stage = (Stage) loginButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button logoutButton;

	@FXML
	private void handleLogout(ActionEvent event) {
		try {
			// Carregar a tela de login
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginView.fxml"));
			Parent loginView = loader.load();

			// Obter o estágio atual
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(loginView);

			// Trocar a cena para a tela de login
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
