package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BaseController {
	protected void trocarPagina(ActionEvent event, String caminhoFXML) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
			Parent root = loader.load();
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Outros métodos para cada página que você desejar
	public void irParaRegistrarCliente(ActionEvent event) {
		trocarPagina(event, "/gui/CadastroCliente.fxml");
	}

	public void irParaListaDeClientes(ActionEvent event) {
		trocarPagina(event, "/gui/ListaCliente.fxml");
	}

	public void irParaProdutos(ActionEvent event) {
		trocarPagina(event, "/gui/CadastroProduto.fxml");
	}

	public void irParaLogin(ActionEvent event) {
		trocarPagina(event, "/gui/LoginView.fxml");
	}

	public void irParaDashboard(ActionEvent event) {
		trocarPagina(event, "/gui/Dashboard.fxml");
	}

	public void irParaVenda(ActionEvent event) {
		trocarPagina(event, "/gui/RegistroVenda.fxml");
	}

	public void onSairButtonClicked(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação de Saída");
		alert.setHeaderText("Deseja sair da aplicação?");
		alert.setContentText("Escolha uma opção:");

		// Personalizar os botões do alerta para "Sim" e "Cancelar"
		ButtonType buttonSim = new ButtonType("Sim");
		ButtonType buttonCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

		alert.getButtonTypes().setAll(buttonSim, buttonCancelar);

		// Exibe o alerta e espera pela resposta do usuário
		alert.showAndWait().ifPresent(response -> {
			if (response == buttonSim) {
				// Ação para sair e ir para o menu principal
				irParaLogin(event);
			}
			// Se "Cancelar" for selecionado, o alerta é simplesmente fechado, mantendo o
			// usuário na tela atual
		});
	}
	
	public void exit() {
		Platform.exit();
	}
}
