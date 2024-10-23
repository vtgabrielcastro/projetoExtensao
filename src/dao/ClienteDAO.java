package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Cliente;
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
	 
	 
	 public List<Cliente> listarTodos() {
	        List<Cliente> clientes = new ArrayList<>();
	        String query = "SELECT id_cliente, nome, saldo_devedor FROM cliente";

	        try {
	        	Connection conn = ConnectionBD.getConnection();
	            PreparedStatement ps = conn.prepareStatement(query);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                clientes.add(new Cliente(
	                    rs.getInt("id_cliente"),
	                    rs.getString("nome"),
	                    rs.getDouble("saldo_devedor")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return clientes;
	    }
}
