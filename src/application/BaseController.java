package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
}
