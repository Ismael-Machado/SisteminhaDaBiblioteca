package br.ufac.si.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NamedQueries({
	@NamedQuery(name="Emprestimo.todos", 
		query="SELECT a FROM Emprestimo a ORDER BY a.id DESC"),
	@NamedQuery(name="Emprestimo.todosPorNomeContendo", 
		query="SELECT e FROM Emprestimo e, Usuario u WHERE e.usuario = u.id AND u.nome LIKE :nome ORDER BY e.id")
})
@Table(name="emprestimos")
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "O campo data de retirada é obrigatório")
	private String dataRetirada;
	@NotEmpty(message = "O campo prazo de devolucão é obrigatório")
	private String prazoDevolucao;
//	private String status;
	
	//Relacionamento de muitos emprestimos para um cliente
	@ManyToOne()
	@JoinColumn(name="usuario_fk")
	private Usuario usuario;
	
	//Mapeando um empréstimo com muitos itens
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimo")
	private List<ItensEmprestimo> itensEmprestimos;

	//Contrutores
	public Emprestimo(Usuario usuario, String dataRetirada, String prazo) {
		this.dataRetirada = dataRetirada;
		this.prazoDevolucao = prazo;
		this.usuario = usuario;
		itensEmprestimos = new ArrayList<ItensEmprestimo>();
	
	}
	public Emprestimo() {
		itensEmprestimos = new ArrayList<ItensEmprestimo>();
	}
	
	
	//Metodo get e set
	public long getId() {
		return this.id;
	}
	public String getDataRetirada() {
		return this.dataRetirada;
	}
	public void setDataRetirada(String retirada) {
		this.dataRetirada = retirada;
	}
	
	public String getPrazoDevolucao() {
		return prazoDevolucao;
	}
	
	public void setPrazoDevolucao(String prazoDevolucao) {
		this.prazoDevolucao = prazoDevolucao;
	}
	//	public String getDataDevolucao() {
//		return this.dataDevolucao;
//	}
//	public void setDataDevolucao(String dataDevolucao) {
//		this.dataDevolucao = dataDevolucao;
//	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public List<ItensEmprestimo> getItensEmprestimo() {
		return itensEmprestimos;
	}
	
	public void setItensEmprestimo(List<ItensEmprestimo> itensEmprestimo) {
		this.itensEmprestimos = itensEmprestimo;
	}
	
	public void addItemEmprestimo(ItensEmprestimo item) {
		this.itensEmprestimos.add(item);
	}
}
