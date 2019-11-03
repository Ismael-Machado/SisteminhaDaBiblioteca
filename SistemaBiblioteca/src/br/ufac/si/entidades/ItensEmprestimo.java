package br.ufac.si.entidades;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name = "itens_emprestimo")
public class ItensEmprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//Relacionamento de muitos itens para com um emprestimo
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "emprestimo_fk")
	private Emprestimo emprestimo;
	
	//Relacionamento de muitos itens para com um exemplar
	@ManyToOne()
	@JoinColumn(name = "exemplar_fk")
	private Exemplar exemplar;
	
	//construtor
	public ItensEmprestimo(Emprestimo emprestimo, Exemplar exemplar) {
		this.emprestimo = emprestimo;
		this.exemplar = exemplar;
	}
	public ItensEmprestimo() {
		
	}

	//Metodos get e set
	public long getId() {
		return id;
	}


	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}
	
	
}
