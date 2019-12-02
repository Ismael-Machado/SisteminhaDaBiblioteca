package br.ufac.si.controladores;

import java.util.List;

import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

import org.apache.tomcat.util.http.fileupload.RequestContext;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="usuarioControlador")
@SessionScoped
public class UsuarioControlador {
	private UsuarioGerente ug;
	private Usuario usuario = new Usuario();
	private String chave = "";
	private String[] generos = {"Masculino", "Feminino"};


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
	
	public String[] getGeneros() {
		return generos;
	}

	//Metodo que envia para tela de inserção de um usuario
	public void incluir() {
		this.usuario = new Usuario();
		//return "/paginas/usuarioInclusao.xhtml?faces-redirect=true";
	}

	//Metodo que de fato insere um usuario no banco
	public void adicionar() {
		try {
			
			ug.incluirUsuario(this.usuario);
			ExibirMensagem.sucesso("Usuario cadastrado com sucesso");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar cadastrar usuário :"+e.getMessage());
		}
//		return "/paginas/usuarioGerenciamento.xhtml";


	}

	//Metodo que enviar para tela de edição de usuario
	public void editar(Usuario u) {
		this.usuario = u;
//		return "/paginas/usuarioEdicao.xhtml?faces-redirect=true"
		
	}

	//Metodo que de fato atualiza um usuario no banco
	public void atualizar() {
		try {
			ug.alterarUsuario(this.usuario);
			
			ExibirMensagem.sucesso("Usuário atualizado com sucesso!");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar atualizar usuário :"+e.getMessage());
		}
		
//		return "/paginas/usuarioGerenciamento.xhtml";
	}

	//Metodo que enviar para tela de exclusão de usuario
	public void excluir(Usuario u) {
		this.usuario = u;
		
//		return "/paginas/usuarioExclusao.xhtml?faces-redirect=true";
	}
	
//	//Metodo que de fato remove um usuario do banco
	public String remover() {
		try {
			
			ug.removerUsuario(this.usuario);
			ExibirMensagem.sucesso("Usuário removido com sucesso!");

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
