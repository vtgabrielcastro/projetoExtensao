package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Produto;
import application.Alerts;
import application.BaseController;
import application.ConnectionBD;
import dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutoController extends BaseController implements Initializable {
	private ClienteDAO clienteDAO = new ClienteDAO();

	@FXML
	private TextField textFieldProduto;
	@FXML
	private TextField textFieldPrecoProduto;
	@FXML
	private Label msgLabel;

	@FXML
	public void salvarProduto() {
		String nomeP = textFieldProduto.getText();
		String precoStr = textFieldPrecoProduto.getText();
		StringBuilder mensagemErro = new StringBuilder();

		// Verificação de campos vazios
	    if (nomeP == null || nomeP.trim().isEmpty()) {
	        mensagemErro.append("O campo Nome do Produto deve ser preenchido.\n");
	    }
	    if (precoStr == null || precoStr.trim().isEmpty()) {
	        mensagemErro.append("O campo Preço do Produto deve ser preenchido.\n");
	    }

	    // Exibe o alerta se houver erros de preenchimento
	    if (mensagemErro.length() > 0) {
	        Alerts.showAlert("ERROR", null, mensagemErro.toString(), AlertType.ERROR);
	        return; // Sai do método se houver erros
	    }

	    // Verificação do formato numérico do campo Preço
	    try {
	        double preco = Double.parseDouble(precoStr);

	        // Cria e salva o objeto Produto
	        Produto produto = new Produto(nomeP, preco);
	        clienteDAO.salvarProduto(produto);

	        // Limpa os campos após o salvamento
	        textFieldProduto.clear();
	        textFieldPrecoProduto.clear();

	        loadDateProduto();

	        // Mensagem de sucesso
	        Alerts.showAlert("Sucesso", null, "Produto cadastrado com sucesso!", AlertType.INFORMATION);
	    } catch (NumberFormatException e) {
	        Alerts.showAlert("ERROR", null, "Valor do produto deve ser um número", AlertType.ERROR);
	    }
	}

	@FXML
	private TableView<Produto> tableProdutos;
	@FXML
	private TableColumn<Produto, String> idCol;
	@FXML
	private TableColumn<Produto, String> produtoCol;
	@FXML
	private TableColumn<Produto, String> precoProdutoCol;

	String query = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Produto produto = null;

	ObservableList<Produto> ProdutoList = FXCollections.observableArrayList();

	@FXML
	private void refreshTable() {

		try {
			ProdutoList.clear();
			query = "Select * from Produto";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ProdutoList.add(new Produto(resultSet.getInt("id_produto"), resultSet.getString("nome_produto"),
						resultSet.getDouble("preco_unitario")));
				tableProdutos.setItems(ProdutoList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadDateProduto() {
		connection = ConnectionBD.getConnection();
		refreshTable();
		idCol.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
		produtoCol.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
		precoProdutoCol.setCellValueFactory(new PropertyValueFactory<>("preco_unitario"));

	}

	public void initialize(URL url, ResourceBundle rb) {
		loadDateProduto();
	}
}
