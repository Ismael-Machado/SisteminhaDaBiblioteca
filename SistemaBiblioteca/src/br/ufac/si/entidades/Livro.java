package br.ufac.si.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
	@NamedQuery(name="Livro.todos", 
		query="SELECT a FROM Livro a"),
	@NamedQuery(name="Livro.todosPorNome", 
		query="SELECT a FROM Livro a ORDER BY a.titulo"),
	@NamedQuery(name="Livro.todosPorNomeContendo", 
		query="SELECT a FROM Livro a WHERE a.titulo LIKE :termo ORDER BY a.titulo"),
	@NamedQuery(name="Livro.exemplaresDisponivel", 
		query="SELECT e FROM Livro l, Exemplar e WHERE l.id = :id AND l.id = e.livro AND e.disponivel = 0"),
	@NamedQuery(name="Livro.todosExemplares", 
		query="SELECT e FROM Livro l, Exemplar e WHERE l.id = :id AND l.id = e.livro")
})
@Table(name="livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "O campo titulo é obrigatório")
	private String titulo;
	@NotEmpty(message = "O campo autor é obrigatório")
	private String autor;
	@NotEmpty(message = "O campo editora é obrigatório")
	private String editora;
	@NotEmpty(message = "O campo ano é obrigatório")
	@Size(min = 4, max = 4, message = "Forneça um ano válido. ex: 2019")
	private String ano;
	@NotEmpty(message = "O campo localização é obrigatório")
	private String localizacao;
	@NotNull(message = "O campo quantidade é obrigatório")
	@Min(value = 1, message = "O campo quantidade deve ser no mínimo 1")
	private int quantidade;
	
	//Relacionamento de um livro para com muitos exemplares
//	@OneToMany(mappedBy = "livro")
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="livro_fk")
	private List <Exemplar> exemplares = new ArrayList<>();
	
	
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
		for(long i=1;i<=quant;i++)
			this.exemplares.add(new Exemplar(i));
	}
}
