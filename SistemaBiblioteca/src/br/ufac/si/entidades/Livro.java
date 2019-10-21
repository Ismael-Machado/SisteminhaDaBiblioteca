package br.ufac.si.entidades;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int exemplar;//utilizar como primary key?
	private String titulo;
	private String autor;
	private String editora;
	private Date ano;
	private String classificacao;
	@Transient
	private boolean status;
	
//	@ManyToOne()  
//  @JoinColumn(name="emprestimo_fk") ------------> NÃO TEM NAVEGABILIDADE DE LIVRO PARA EMPRESTIMO
//	private Emprestimo emprestimo;
	
	//Metodos set e get
	public long getId() {
		return id;
	}
	public int getExemplar() {
		return exemplar;
	}
	public void setExemplar(int exemplar) {
		this.exemplar = exemplar;
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
	public Date getAno() {
		return ano;
	}
	public void setAno(Date ano) {
		this.ano = ano;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
//	public Emprestimo getEmprestimo() {
//		return emprestimo;
//	}
	
	
}
