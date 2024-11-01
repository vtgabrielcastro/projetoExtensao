package gui;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Cliente;
import application.BaseController;
import application.ConnectionBD;
import dao.ClienteDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController extends BaseController implements Initializable{
	
	@FXML
	private TextField nomeTF;
	@FXML
	private TextField cpfTF;
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
        String cpf = cpfTF.getText();
        String telefone = telefoneTF.getText();
        String email = emailTF.getText();
        String endereco = enderecoTF.getText();
        String limiteCreditoStr = limite_creditoTF.getText();

        // Usado para acumular mensagens de erro
        StringBuilder mensagemErro = new StringBuilder();

        // Verifica cada campo e adiciona mensagem se estiver vazio
        if (nome.isEmpty()) {
            mensagemErro.append("Nome não preenchido.");
        }
        if (cpf.isEmpty()) {
            mensagemErro.append(" CPF não preenchido.");
        }
        if (telefone.isEmpty()) {
            mensagemErro.append(" Telefone não preenchido.\n");
        }
        if (email.isEmpty()) {
            mensagemErro.append("Email não preenchido. ");
        }
        if (endereco.isEmpty()) {
            mensagemErro.append(" Endereço não preenchido.\n");
        }
        if (limiteCreditoStr.isEmpty()) {
            mensagemErro.append(" Limite de crédito não preenchido.");
        }

        // Verifica se há erros de entrada e exibe mensagem, caso contrário salva
        if (mensagemErro.length() > 0) {
            msgCadastro.setText(mensagemErro.toString()); // Mostra mensagem de erro
        } else {
            try {
                // Converte limite de crédito para double e continua o cadastro
                double limiteCredito = Double.parseDouble(limiteCreditoStr);

                // Cria um objeto Cliente com os dados capturados
                Cliente cliente = new Cliente(nome, cpf, telefone, email, endereco, limiteCredito);

                // Salva o cliente no banco de dados
                clienteDAO.salvar(cliente);

                // Limpa os campos após o salvamento
                nomeTF.clear();
                cpfTF.clear();
                telefoneTF.clear();
                emailTF.clear();
                enderecoTF.clear();
                limite_creditoTF.clear();

                msgCadastro.setText("Cadastrado com Sucesso!");
            } catch (NumberFormatException e) {
                msgCadastro.setText("Erro: Limite de crédito deve ser um número.");
            }
        }
    }
    @FXML
	private Button logoutButton;
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
				ClientList.add(new Cliente(resultSet.getInt("id_cliente"), resultSet.getString("nome"), resultSet.getString("telefone"), resultSet.getString("endereco"),resultSet.getDouble("saldo_devedor")));
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
	public void initialize(URL url, ResourceBundle rb) {
		if(tableCliente !=null) {
			loadDateCliente();
		}
	}
}
