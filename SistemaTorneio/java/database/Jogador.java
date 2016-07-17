package database;

public class Jogador {

	private int id;
	private int vitorias;
	private String nome;

	public Jogador(int id, int vitorias, String nome) {
		this.id = id;
		this.nome = nome;
		this.vitorias = vitorias;
	}

	public int getID() {
		return id;
	}

	public void setID(int matricula) {
		this.id = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getVitorias(){
		return vitorias;
	}
	
	public void setVitorias(int vitorias){
		this.vitorias = vitorias;
	}
}
