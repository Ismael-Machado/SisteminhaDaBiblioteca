package biblioteca.empty;

public class Livro {
	
	private int livroCodigo;
	private String autor;
	private String editora;
	private String titulo;
	private int ano;
	private boolean status;
	//Construtor
	public Livro(int livroCodigo, String autor, String editora, String titulo, int ano) {
		this.livroCodigo = livroCodigo;
		this.autor = autor;
		this.editora = editora;
		this.titulo = titulo;
		this.ano = ano;
		this.status = true;
	}
	//Métodos Gets and Sets
	public int getLivroCodigo() {
		return livroCodigo;
	}
	public void setLivroCodigo(int livroCodigo) {
		this.livroCodigo = livroCodigo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	//Outros Métodos
}
