package br.ufac.si.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Livro.todos", 
		query="SELECT a FROM Livro a"),
	@NamedQuery(name="Livro.todosPorNome", 
		query="SELECT a FROM Livro a ORDER BY a.titulo"),
	@NamedQuery(name="Livro.todosPorNomeContendo", 
		query="SELECT a FROM Livro a WHERE a.titulo LIKE :termo ORDER BY a.titulo")		
})
@Table(name="livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String autor;
	private String editora;
	private String ano;
	private String localizacao;
	private int quantidade;
	
	//Relacionamento de um livro para com muitos exemplares
	@OneToMany(mappedBy = "livro")
	List <Exemplar> exemplares;
	
	//Metodos set e get
	public long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getLocalizacao() {
		return this.localizacao;
	}
	public void setLocalizacao(String local) {
		this.localizacao = local;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
