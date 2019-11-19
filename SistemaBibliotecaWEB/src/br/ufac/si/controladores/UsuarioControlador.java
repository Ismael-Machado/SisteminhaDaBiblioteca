package br.ufac.si.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="usuarioControlador")
@SessionScoped
public class UsuarioControlador {
	private UsuarioGerente ug;
	private Usuario usuario = new Usuario();
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
		return "/paginas/usuarioInclusao.xhtml?faces-redirect=true";
	}

	//Metodo que de fato insere um usuario no banco
	public String adicionar() {
		try {
			ExibirMensagem.sucesso("Usuario cadastrado com sucesso");
			ug.incluirUsuario(this.usuario);

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar cadastrar usuário :"+e.getMessage());
		}
		return "/paginas/usuarioGerenciamento.xhtml";

	}

	//Metodo que enviar para tela de edição de usuario
	public String editar(Usuario u) {
		this.usuario = u;
		return "/paginas/usuarioEdicao.xhtml?faces-redirect=true";
	}

	//Metodo que de fato atualiza um usuario no banco
	public String atualizar() {
		try {
			ug.alterarUsuario(this.usuario);
			ExibirMensagem.sucesso("Usuário atualizado com sucesso!");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar atualizar usuário :"+e.getMessage());
		}
		
		return "/paginas/usuarioGerenciamento.xhtml";
	}

	//Metodo que enviar para tela de exclusão de usuario
	public String excluir(Usuario u) {
		this.usuario = u;
		return "/paginas/usuarioExclusao.xhtml?faces-redirect=true";
	}
	//Metodo que de fato remove um usuario do banco
	public String remover() {
		try {
			ExibirMensagem.sucesso("Usuário removido com sucesso!");
			ug.removerUsuario(this.usuario);

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar remover usuário :"+e.getMessage());
		}
		return "/paginas/usuarioGerenciamento.xhtml";
	}

	//Metodo para retornar uma lista de usuarios
	public List<Usuario> getUsuarios(){
		return ug.buscarTodosPorNomeContendo(chave);
	}


	//Metodo para retornar um Usuario
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	//Metodo que retorna o ID do usuario corrente do Table
//	public String carregarUsuario() {
//	
//		String s = ExibirMensagem.getParam("usuID");
//		System.out.println(s);
//		if(s != null) {
//			this.usuario = ug.buscarUsuario(Long.valueOf(s));
//		}
//		return "/paginas/usuarioEdicao.xhtml?faces-redirect=true";
//	}


}
