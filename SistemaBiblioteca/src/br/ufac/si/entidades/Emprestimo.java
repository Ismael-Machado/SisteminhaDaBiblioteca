package br.ufac.si.entidades;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="emprestimos")
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date dataHorario;
	private Date dataDevolucao;
	@ManyToOne()
	@JoinColumn(name="usuario_fk")
	private Usuario usuario;
	
//	@OneToMany(mappedBy = "emprestimo")------->Quando era relacionado diretamente com Livro
//	private List<Livro> livros;
	
	@OneToMany(mappedBy = "emprestimo")
	private List<Exemplar> exemplares;
	
	
	//Metodo get e set
	public long getId() {
		return id;
	}
	public Date getDataHorario() {
		return dataHorario;
	}
	public void setDataHorario(Date dataHorario) {
		this.dataHorario = dataHorario;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Exemplar> getExemplares() {
		return this.exemplares;
	}
	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	
	
}
