package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Cliente;
import Model.Produto;
import application.ConnectionBD;

public class ClienteDAO {
	 public void salvar(Cliente cliente) {
	        String sql = "INSERT INTO Cliente (nome, cpf, telefone, email, endereco, limite_credito) VALUES (?,?,?,?,?,?)";

	        try (Connection conn = ConnectionBD.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, cliente.getNome());  // Define o valor para o nome
	            stmt.setString(2, cliente.getCpf());
	            stmt.setString(3, cliente.getTelefone());
	            stmt.setString(4, cliente.getEmail());
	            stmt.setString(5, cliente.getEndereco());
	            stmt.setDouble(6, cliente.getLimite_credito());
	            
	            stmt.executeUpdate();     // Executa a inserção

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void salvarProduto(Produto produto) {
	        String sql = "INSERT INTO Produto (nome_produto, preco_unitario) VALUES (?,?)";

	        try (Connection conn = ConnectionBD.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, produto.getNome_produto());  // Define o valor para o nome
	            stmt.setDouble(2, produto.getPreco_unitario());
	            stmt.executeUpdate();     // Executa a inserção

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
