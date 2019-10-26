package br.ufac.si.entidades;

import javax.persistence.*;

@Entity
@Table(name = "devolucoes")
public class Devolucao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dataDevolucao;
	@ManyToOne()
	@JoinColumn(name="usuario_fk")
	private Usuario usuario;
	
	//Metodos set e get
	public long getId() {
		return id;
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
