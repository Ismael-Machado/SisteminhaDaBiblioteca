package br.ufac.si.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "exemplares")
public class Exemplar {
	@Id
	private long exemplar;
	@Transient	//-------------------> não irá persistir no banco
	private boolean disponivel;
	
	//Relacionamento de muitos exemplares para com um livro
	@ManyToOne()
	@JoinColumn(name="livro_fk")
	private Livro livro;
	
	//Relacionamento de um exemplar para muitos itens de emprestimo
	@OneToMany(mappedBy = "exemplar")
	private List<ItensEmprestimo> itensEmp;
	
	//Contrutor
	public Exemplar(long exemplar, Livro livro) {
		this.livro = livro;
		this.exemplar = exemplar;
	}
	public Exemplar() {
		
	}
	
	//Metodos get e set
	public long getExemplar() {
		return exemplar;
	}

	public void setExemplar(long exemplar) {
		this.exemplar = exemplar;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
}
