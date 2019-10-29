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
	private String dataHorario;
	private String dataDevolucao;
	private String status;
	
	//Relacionamento de muitos emprestimos para um cliente
	@ManyToOne()
	@JoinColumn(name="usuario_fk")
	private Usuario usuario;
	
	//Mapeando um empréstimo com muitos itens
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimo")
	private List<ItensEmprestimo> itensEmprestimo;

	//Contrutores
	public Emprestimo(String dataHorario, String dataDevol, String status, Usuario usuario) {
		this.dataHorario = dataHorario;
		this.dataDevolucao = dataDevol;
		this.usuario = usuario;
	}
	public Emprestimo() {
		
	}
	
	
	//Metodo get e set
	public long getId() {
		return this.id;
	}
	public String getDataHorario() {
		return this.dataHorario;
	}
	public void setDataHorario(String dataHorario) {
		this.dataHorario = dataHorario;
	}
	public String getDataDevolucao() {
		return this.dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ItensEmprestimo> getItensEmprestimo() {
		return itensEmprestimo;
	}
	public void setItensEmprestimo(List<ItensEmprestimo> itensEmprestimo) {
		this.itensEmprestimo = itensEmprestimo;
	}
	
	
}
