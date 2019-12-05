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
	private int disponivel; // 1=sim; 0=não
	
	//Relacionamento de muitos exemplares para com um livro
	@ManyToOne()
	@JoinColumn(name="livro_fk")
	private Livro livro;
	
	//Relacionamento de um exemplar para muitos itens de emprestimo
	@OneToMany(mappedBy = "exemplar")
	private List<ItensEmprestimo> itensEmp;
	
	//Contrutores
	public Exemplar() {
		
	}
	
	public Exemplar(Livro livro, long exemplar, int disponivel) {
		this.exemplar = exemplar;
		this.livro = livro;
		this.disponivel = disponivel;
	}
	
	public Exemplar(long exemplar, int disponivel) {
		this.exemplar = exemplar;
		this.disponivel = disponivel;
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

	public String getDisponivel() {
		return this.disponivel == 0 ? "Emprestado" : "Disponível";
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
