package Model;

public class Produto {
	private int id_produto;
	private String nome_produto;
	private double preco_unitario;
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public double getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(double valor_unitario) {
		this.preco_unitario = valor_unitario;
	}
	public Produto( String nome_produto, double valor_unitario) {
		
		this.nome_produto = nome_produto;
		this.preco_unitario = valor_unitario;
	}
	public Produto(int int1, String string, double double1) {
		// TODO Auto-generated constructor stub
		this.id_produto = int1;
		this.nome_produto = string;
		this.preco_unitario = double1;
	}
	
	
	
}
