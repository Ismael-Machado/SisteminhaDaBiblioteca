package br.ufac.si.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "exemplares")
public class Exemplar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long exemplar;
	private int disponivel; // 0=sim; 1=não
	
	//Relacionamento de muitos exemplares para com um livro
	@ManyToOne()
	@JoinColumn(name="livro_fk")
	private Livro livro;
	
	//Relacionamento de um exemplar para muitos itens de emprestimo
	@OneToMany(mappedBy = "exemplar")
	private List<ItensEmprestimo> itensEmp;
	
	//Contrutor
	public Exemplar(Livro livro, long exemplar) {
		this.livro = livro;
		this.exemplar = exemplar;
	}
	public Exemplar(long i) {
		this.exemplar = i;
	}
	
	//Metodos get e set
	
	public long getId() {
		return id;
	}
	
	public long getExemplar() {
		return exemplar;
	}
	
	public void setExemplar(long exemplar) {
		this.exemplar = exemplar;
	}

	public boolean isDisponivel() {
		return this.disponivel == 0 ? false : true;
	}

	public void setDisponivel(int valor) {
		this.disponivel = valor;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
		
	
}
