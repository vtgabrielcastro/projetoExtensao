package gui;

import Model.Cliente;
import dao.ClienteDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClienteController {
	
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
        String nome = nomeTF.getText();
        String cpf = cpfTF.getText();
        String telefone = telefoneTF.getText();
        String email = emailTF.getText();
        String endereco = enderecoTF.getText();
        double limiteCredito = Double.parseDouble(limite_creditoTF.getText());

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
	
	@FXML
	private Button btnCadastroCliente;
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
	
}
