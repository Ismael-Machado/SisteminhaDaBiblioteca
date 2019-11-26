package br.ufac.si.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="autenticacaoControlador")
@SessionScoped
public class AutenticacaoControlador {	
	private UsuarioGerente ug;
	private Usuario usuarioLogado;
	
	//Contrutor
	public AutenticacaoControlador() {
		ug = new UsuarioGerente();
	}
	
	
	//Metodo set e get
	public Usuario getUsuarioLogado() {
		if(usuarioLogado == null)
			usuarioLogado = new Usuario();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	//Metodo que faz a autenticação
	public String autenticar() {
		try {
			UsuarioGerente ug = new UsuarioGerente();
			usuarioLogado = ug.autenticar(usuarioLogado.getCPF(), usuarioLogado.getSenha());
			if(usuarioLogado == null) {
				ExibirMensagem.error("CPF e/ou senha inválidos");
				return null;
			}else {
				ExibirMensagem.sucesso("Usuário autenticado com sucesso");
				return "/paginas/index.xhtml?faces-redirect=true";
			}
			
			
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar autenticar no sistema: "+e.getMessage());
			return null;
		}
		
	}
	
	//Metodo que enviar para tela de edição de usuario
		public String editar() {
			return "/paginas/usuarioEdicao.xhtml?faces-redirect=true";
		}
	
	//Metodo que de fato atualiza um usuario no banco
		public String atualizar() {
			try {
				ug.alterarUsuario(this.usuarioLogado);
				ExibirMensagem.sucesso("Usuário atualizado com sucesso!");

			} catch (Exception e) {
				ExibirMensagem.error("Erro ao tentar atualizar usuário :"+e.getMessage());
			}

			return "/paginas/meusDados.xhtml";
		}
	
	//Metodo para fazer logout
	public String sair() {
		usuarioLogado = null;
		return "/paginas/login.xhtml";
	}
	
	
}
