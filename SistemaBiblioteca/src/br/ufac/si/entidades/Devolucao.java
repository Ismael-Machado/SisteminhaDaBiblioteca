package br.ufac.si.entidades;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Devolucao.todos", 
		query="SELECT a FROM Devolucao a ORDER BY a.id DESC"),
	@NamedQuery(name="Devolucao.todosPorNomeContendo", 
		query="SELECT d FROM Devolucao d, Usuario u WHERE d.usuario = u.id AND u.nome LIKE :nome ORDER BY d.id DESC")
})
@Table(name = "devolucoes")
public class Devolucao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dataDevolucao;
	private String dataSaida;
	private int exemplar;
	private String livroTitulo;
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
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public int getExemplar() {
		return exemplar;
	}
	public void setExemplar(int l) {
		this.exemplar = l;
	}
	public String getLivro() {
		return livroTitulo;
	}
	public void setLivro(String titulo) {
		this.livroTitulo = titulo;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
