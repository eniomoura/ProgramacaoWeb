package mercadoLivro;

import java.sql.SQLException;

public class ItemCarrinho {
	private String nome;
	private String imagePath;
	private String tipo;
	private double preco;
	private int estoque;
	private int id;
	private int quantidade;
	
	public ItemCarrinho(String nome, String imagePath, String tipo, double preco, int estoque, int id){
		this.nome=nome;
		this.tipo=tipo;
		this.preco=preco;
		this.estoque=estoque;
		this.id=id;
		this.imagePath=imagePath;
		this.quantidade=0;
	}
	
	public void adicionarItem() throws SQLException{
		this.quantidade+=1;
	}
	
	//getters and setters
	public String getNome(){
		return nome;
	}

	public String getImagePath(){
		return imagePath;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public double getPreco(){
		return preco;
	}
	
	public int getEstoque(){
		return estoque;
	}
	
	public int getID(){
		return id;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public void setQuantidade(int quantidade){
		this.quantidade=quantidade;
	}
	//end getters and setters
}
