package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Produto;
import application.BaseController;
import application.ConnectionBD;
import dao.ClienteDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutoController extends BaseController implements Initializable {
	private ClienteDAO clienteDAO = new ClienteDAO();

	@FXML
	private TextField textFieldProduto;
	@FXML
	private TextField textFieldPrecoProduto;

	@FXML
	public void salvarProduto() {
		String nomeP = textFieldProduto.getText();
		String precoStr = textFieldPrecoProduto.getText();
		try {
			// Converte limite de crédito para double e continua o cadastro
			double preco = Double.parseDouble(precoStr);

			// Cria um objeto Cliente com os dados capturados
			Produto produto = new Produto(nomeP, preco);

			clienteDAO.salvarProduto(produto);

			// Limpa os campos após o salvamento
			textFieldProduto.clear();
			textFieldPrecoProduto.clear();

			loadDateProduto();
		} catch (Exception e) {
			e.printStackTrace();
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

	@FXML
	private void exit() {
		Platform.exit();
	}

	public void initialize(URL url, ResourceBundle rb) {
		loadDateProduto();
	}
}
