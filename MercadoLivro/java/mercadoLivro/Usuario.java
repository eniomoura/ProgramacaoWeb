package mercadoLivro;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private	String senha;
	private String userType;
	
	public Usuario(int id, String email, String nome, String senha, String userType){
		this.id=id;
		this.nome=nome;
		this.email=email;
		this.senha=senha;
		this.userType=userType;
	}
		
	//getters and setters
	public int getId() {
		return id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getSenha(){
		return senha;
	}
	
	public String getUserType(){
		return userType;
	}
	//end getters and setters
}
