package br.ufac.si.entidades;

import javax.persistence.*;

public class Exemplar {
	
	private int exemplar;
	
	@ManyToOne()
	@JoinColumn(name="emprestimo_fk")
	private Emprestimo emprestimo;
	
	@OneToOne()
	@JoinColumn(name="livro_pk")
	private Livro livro;
	
}
