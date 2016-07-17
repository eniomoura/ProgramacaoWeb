package mercadoLivro;

public class ItemCarrinho {
	private String nome;
	private String imagePath;
	private String tipo;
	private double preco;
	private int estoque;
	private int id;
	
	public ItemCarrinho(String nome, String imagePath, String tipo, double preco, int estoque, int id){
		this.nome=nome;
		this.tipo=tipo;
		this.preco=preco;
		this.estoque=estoque;
		this.id=id;
		this.imagePath=imagePath;
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
	//end getters and setters
}
