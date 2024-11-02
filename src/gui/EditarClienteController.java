package gui;

import Model.Cliente;
import application.BaseController;
import dao.ClienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarClienteController extends BaseController {
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField telefoneTF;
	@FXML
	private TextField emailTF;
	@FXML
	private TextField enderecoTF;
	@FXML
	private TextField limite_creditoTF;

	private Cliente cliente; // Objeto que será editado
	private ClienteDAO clienteDAO = new ClienteDAO(); // Referência ao DAO

	public void setCliente(@SuppressWarnings("exports") Cliente cliente) {
		this.cliente = cliente;
		preencherCampos();
	}

	private void preencherCampos() {
		if (cliente != null) {
			nomeTF.setText(cliente.getNome());
			telefoneTF.setText(cliente.getTelefone());
			emailTF.setText(cliente.getEmail());
			enderecoTF.setText(cliente.getEndereco());
			limite_creditoTF.setText(String.valueOf(cliente.getLimite_credito()));
			// Preencha outros campos conforme necessário
		}
	}

	@FXML
	private void onSalvarButtonClicked() {
		// Atualize o objeto cliente com os novos valores do formulário
		if (cliente != null) {
			cliente.setNome(nomeTF.getText());
			cliente.setTelefone(telefoneTF.getText());
			cliente.setEmail(emailTF.getText());
			cliente.setEndereco(enderecoTF.getText());
			cliente.setLimite_credito(Double.parseDouble(limite_creditoTF.getText()));
			// Atualize outros campos conforme necessário

			// Atualize o banco de dados
			clienteDAO.atualizarCliente(cliente);

			// Fecha a janela de edição
			Stage stage = (Stage) nomeTF.getScene().getWindow();
			stage.close();
		}
	}

}
