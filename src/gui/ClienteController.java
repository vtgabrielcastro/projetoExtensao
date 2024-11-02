package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Cliente;
import application.Alerts;
import application.BaseController;
import application.ConnectionBD;
import dao.ClienteDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController extends BaseController implements Initializable {

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
	@FXML
	private Button btSalvarCliente;

	private ClienteDAO clienteDAO = new ClienteDAO();

	@FXML
	private Label msgCadastro;

	@FXML
	public void salvarCliente() {
		// Obtém o texto de cada campo de entrada
		String nome = nomeTF.getText();
		String telefone = telefoneTF.getText();
		String email = emailTF.getText();
		String endereco = enderecoTF.getText();
		String limiteCreditoStr = limite_creditoTF.getText().replaceAll("[^\\d]", "");

		// Usado para acumular mensagens de erro
		StringBuilder mensagemErro = new StringBuilder();

		// Verifica cada campo e adiciona mensagem se estiver vazio

		if (nome == null || nome.trim().isEmpty()) {
			mensagemErro.append("O campo Nome deve ser preenchido.\n");
		}
		if (telefone == null || telefone.trim().isEmpty()) {
			mensagemErro.append("O campo Telefone deve ser preenchido.\n");
		}
		if (email == null || email.trim().isEmpty()) {
			mensagemErro.append("O campo E-mail deve ser preenchido.\n");
		}
		if (endereco == null || endereco.trim().isEmpty()) {
			mensagemErro.append("O campo Endereço deve ser preenchido.\n");
		}
		if (limiteCreditoStr == null || limiteCreditoStr.trim().isEmpty()) {
			mensagemErro.append("O campo Limite de Crédito deve ser preenchido.\n");
		}

		// Verifica se há erros de entrada e exibe mensagem, caso contrário salva
		if (mensagemErro.length() > 0) {
			Alerts.showAlert("ERROR", null, mensagemErro.toString(), AlertType.ERROR);
			return;
		}
		try {
			// Converte limite de crédito para double e continua o cadastro
			double limiteCredito = Double.parseDouble(limiteCreditoStr) / 100.0;

			// Cria um objeto Cliente com os dados capturados
			Cliente cliente = new Cliente(nome, telefone, email, endereco, limiteCredito);

			// Salva o cliente no banco de dados
			clienteDAO.salvar(cliente);

			// Limpa os campos após o salvamento
			nomeTF.clear();
			telefoneTF.clear();
			emailTF.clear();
			enderecoTF.clear();
			limite_creditoTF.clear();

			// Mensagem de sucesso
			Alerts.showAlert("Sucesso", null, "Cliente cadastrado com sucesso!", AlertType.INFORMATION);
		} catch (NumberFormatException e) {
			Alerts.showAlert("ERROR", null, "Limite de crédito deve ser um número", AlertType.ERROR);
		}

	}

	@FXML
	private Button btnListaCliente;

	@FXML
	private void exit() {
		Platform.exit();
	}

	@FXML
	private TableView<Cliente> tableCliente;
	@FXML
	private TableColumn<Cliente, String> idCol;
	@FXML
	private TableColumn<Cliente, String> nomeCol;
	@FXML
	private TableColumn<Cliente, String> telefoneCol;
	@FXML
	private TableColumn<Cliente, String> enderecoCol;
	@FXML
	private TableColumn<Cliente, String> saldoCol;

	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Cliente cliente = null;

	ObservableList<Cliente> ClientList = FXCollections.observableArrayList();

	@FXML
	private void refreshTable() {

		try {
			ClientList.clear();
			query = "select id_cliente, nome, telefone, endereco, saldo_devedor from Cliente";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ClientList.add(new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nome"),
						resultSet.getString("telefone"), resultSet.getString("endereco"),
						resultSet.getDouble("saldo_devedor")));
				tableCliente.setItems(ClientList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadDateCliente() {
		connection = ConnectionBD.getConnection();
		refreshTable();
		idCol.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		telefoneCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		enderecoCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		saldoCol.setCellValueFactory(new PropertyValueFactory<>("saldo_devedor"));

	}

	@FXML
	public void atualizarLista() {
		loadDateCliente();
	}

	@FXML
	public void onExcluirButtonClicked() {
		// Verifica se há um item selecionado
		Cliente clienteSelecionado = tableCliente.getSelectionModel().getSelectedItem();

		if (clienteSelecionado != null) {
			// Confirmação para excluir o item
			Alert confirmacao = new Alert(AlertType.CONFIRMATION);
			confirmacao.setTitle("Confirmação de Exclusão");
			confirmacao.setHeaderText("Tem certeza que deseja excluir o item selecionado?");
			confirmacao.setContentText("Esta ação não poderá ser desfeita.");

			confirmacao.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					// Remove o item da lista que alimenta a tabela
					ClientList = tableCliente.getItems();
					ClientList.remove(clienteSelecionado);

					// Caso queira excluir também do banco de dados, adicione o código abaixo
					clienteDAO.excluirCliente(clienteSelecionado);

					Alert sucesso = new Alert(AlertType.INFORMATION);
					sucesso.setTitle("Sucesso");
					sucesso.setHeaderText(null);
					sucesso.setContentText("Cliente excluído com sucesso!");
					sucesso.show();
				}
			});
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Nenhuma Seleção");
			alerta.setHeaderText(null);
			alerta.setContentText("Selecione um cliente para excluir.");
			alerta.show();
		}
	}

	public void initialize(URL url, ResourceBundle rb) {
		if (tableCliente != null) {
			loadDateCliente();
		}
		if (telefoneTF != null) {
			telefoneTF.setTextFormatter(new TextFormatter<>(change -> {
				String newText = change.getControlNewText();

				// Remove caracteres não numéricos
				newText = newText.replaceAll("[^\\d]", "");

				// Limita o texto a no máximo 11 dígitos (2 para DDD e até 9 para o número)
				if (newText.length() > 11) {
					return null;
				}

				// Aplica a formatação gradualmente
				if (newText.length() >= 1 && newText.length() <= 2) {
					newText = "(" + newText;
				} else if (newText.length() > 2) {
					newText = "(" + newText.substring(0, 2) + ") " + newText.substring(2);
				}

				// Atualiza o campo com o texto formatado
				change.setText(newText);
				change.setRange(0, change.getControlText().length());
				change.setCaretPosition(newText.length());
				change.setAnchor(newText.length());

				return change;
			}));
		}
		if (limite_creditoTF != null) {
			limite_creditoTF.setTextFormatter(new TextFormatter<>(change -> {
				String newText = change.getControlNewText().replaceAll("[^\\d]", "");

				if (newText.isEmpty()) {
					change.setText("0,00");
					change.setCaretPosition(4); // Define o cursor no final de "0,00"
					change.setAnchor(4);
					return change;
				}

				long value = Long.parseLong(newText);
				String formattedText = String.format("%,d", value / 100) + "," + String.format("%02d", value % 100);

				change.setText(formattedText);
				change.setRange(0, change.getControlText().length());

				// Verifica se o tamanho da posição do cursor está dentro do limite
				int newCaretPosition = Math.min(formattedText.length(), change.getText().length());
				change.setCaretPosition(newCaretPosition);
				change.setAnchor(newCaretPosition);

				return change;
			}));
		}
	}

}
