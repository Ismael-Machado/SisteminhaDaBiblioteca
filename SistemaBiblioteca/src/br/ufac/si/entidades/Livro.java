package br.ufac.si.entidades;

import java.util.ArrayList;
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
//	@OneToMany(mappedBy = "livro")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="livro_fk")
	List <Exemplar> exemplares;
	
	
	//Construtor
	public Livro () {
	}
	public Livro (String titulo, String autor, String editora, 
					String ano, String localizacao, int quantidade) {
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.ano = ano;
		this.localizacao = localizacao;
		this.quantidade = quantidade;
		this.exemplares = new ArrayList<Exemplar>();
		setQuantExemplares(this.quantidade);
	}
	
	
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
	public List<Exemplar> getExemplares() {
		return exemplares;
	}
	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	public void setQuantExemplares(int quant) {
		for(int i=1;i<=quant;i++)
			this.exemplares.add(new Exemplar(i));
	}
}
