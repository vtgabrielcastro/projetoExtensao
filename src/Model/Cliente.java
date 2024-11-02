package Model;

import java.sql.Timestamp;

public class Cliente {
	private int id_cliente;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private double limite_credito;
	private double saldo_devedor;
	private boolean status;
	private Timestamp data_cadastro;
	
	public Cliente(String nome, String telefone, String email, String endereco, double limite_credito) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.limite_credito = limite_credito;
	}
	public Cliente(int id_clientes, String nome, String tel, String endereco, double saldo_devedor) {
		this.id_cliente = id_clientes;
		this.nome = nome;
		this.telefone = tel;
		this.endereco = endereco;
		this.saldo_devedor = saldo_devedor;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public double getLimite_credito() {
		return limite_credito;
	}
	public void setLimite_credito(double limite_credito) {
		this.limite_credito = limite_credito;
	}
	public double getSaldo_devedor() {
		return saldo_devedor;
	}
	public void setSaldo_devedor(double saldo_devedor) {
		this.saldo_devedor = saldo_devedor;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Timestamp getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
}
