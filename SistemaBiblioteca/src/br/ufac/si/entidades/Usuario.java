package br.ufac.si.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.todos", 
		query="SELECT a FROM Usuario a"),
	@NamedQuery(name="Usuario.todosPorNome", 
		query="SELECT a FROM Usuario a ORDER BY a.nome"),
	@NamedQuery(name="Usuario.todosPorNomeContendo", 
		query="SELECT a FROM Usuario a WHERE a.nome LIKE :termo ORDER BY a.nome")		
})
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String fone;
	private String login;
	private String senha;
	@Transient	//--------------------->Significa que o atributo não vai aparecer no banco
	public boolean regular = true; //apto a fazer emprestimo
	
	//Mapeando um Usuario para muito emprestimos
	@OneToMany(mappedBy = "usuario")
	private List<Emprestimo> emprestimos;
	
	//Mapeando um Usuario para muita devoluções
	@OneToMany(mappedBy = "usuario")
	private List<Devolucao> devolucoes;
	
	//Metodo get e set
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isRegular() {
		return regular;
	}
	public void setRegular(boolean regular) {
		this.regular = regular;
	}
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
}
