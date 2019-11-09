package br.ufac.si.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

@ManagedBean(name="usuarioControlador")
@SessionScoped
public class UsuarioControlador {
	private UsuarioGerente ug;
	private Usuario usuario;
	private String chave = "";
	
	
	//Contrutor
	public UsuarioControlador() {
		ug = new UsuarioGerente();
	}
	
	//Metodos get e set
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public void setUsuario(Usuario u) {
		this.usuario = u;
	}
	
	//Metodo que envia para tela de inserção de um usuario
	public String incluir() {
		this.usuario = new Usuario();
		return "usuarioInclusao";
	}
	
	//Metodo que de fato insere um usuario no banco
	public String adiciona() {
		ug.incluirUsuario(this.usuario);
		return "usuarioGerenciamento";
	}
	
	//Metodo que enviar para tela de edição de usuario
	public String editar(Usuario u) {
		this.usuario = u;
		return "usuarioEdicao";
	}
	
	//Metodo que de fato atualiza um usuario no banco
	public String atualiza() {
		ug.alterarUsuario(this.usuario);
		return "usuarioGerenciamento";
	}
	
	//Metodo que enviar para tela de exclusão de usuario
	public String excluir(Usuario u) {
		this.usuario = u;
		return "usuarioExclusao";
	}
	//Metodo que de fato remove um usuario do banco
	public String remover() {
		ug.removerUsuario(this.usuario.getId());
		return "usuarioGerenciamento";
	}
	
	//Metodo para retornar uma lista de usuarios
	public List<Usuario> getUsuarios(){
		return ug.buscarTodosPorNomeContendo(chave);
	}
	
	
	//Metodo para retornar um Usuario
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	
}
