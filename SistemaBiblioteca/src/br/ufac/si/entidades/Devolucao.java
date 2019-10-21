package br.ufac.si.entidades;

import java.sql.Date;

import javax.persistence.*;

public class Devolucao {
	
	private long id;
	private Date dataDevolucao;
	@ManyToOne()
	@JoinColumn(name="usuario_fk")
	private Usuario usuario;
	
	//Metodos set e get
	public long getId() {
		return id;
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
	
	
}
