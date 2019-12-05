package br.ufac.si.controladores;

import java.util.ArrayList;
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
	public static Usuario usuario = new Usuario();
	private String chave = "";
	private String auxSexo;
	List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<String> generos = new ArrayList<String>() {	
					{
						add("Masculino");
						add("Feminino");
					}
		};


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
		usuario = u;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public String getAuxSexo() {
		return auxSexo;
	}

	public void setAuxSexo(String auxSexo) {
		this.auxSexo = auxSexo;
	}

	public List<String> getGeneros() {
		
		return this.generos;
	}
	

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	public List<Usuario> getUsuarios(){
		this.usuarios = ug.buscarTodosPorNomeContendo(chave);
		return this.usuarios;
	}
	
	//Cria uma Instancia nova sempre que incluir um usuário
	public void preparar() {
		usuario = new Usuario();
		//return "/paginas/usuarioInclusao.xhtml?faces-redirect=true";
	}


	//Metodo para carregar usuario selecionado da table
	public void carregaUsuario(Usuario u) {
		usuario = u;
		
	}
	
	//Metodo para inserir um usuario no banco
	public void adicionar() {
		try { 
			if(this.auxSexo.equals("Masculino")) {
				usuario.setSexo("M");
			}else {
				usuario.setSexo("F");
			}
				
			ug.incluirUsuario(usuario);
			ExibirMensagem.sucesso("Usuario cadastrado com sucesso");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar cadastrar usuário :"+e.getMessage());
		}
		//Limpa os dados do usuario após a inclusão
		preparar();
		
	}

	//Metodo para atualizar um usuario no banco
	public void atualizar() {
		try {
			ug.alterarUsuario(usuario);
			
			ExibirMensagem.sucesso("Usuário atualizado com sucesso!");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar atualizar usuário :"+e.getMessage());
		}
		
	}

	
//	//Metodo para remover um usuario do banco
	public void remover() {
		try {
			
			ug.removerUsuario(usuario);
			ExibirMensagem.sucesso("Usuário removido com sucesso!");

		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar remover usuário :"+e.getMessage());
		}
	
	}

	
	//AutoComplete : Lista de Usuario
	public List<Usuario> completaCPF(String query) {
		this.usuarios = ug.buscarTodos();
		
		List<Usuario> sugestoes = new ArrayList<Usuario>();
		for (Usuario u : this.usuarios) {
			if (u.getCPF().startsWith(query)) {
				sugestoes.add(u);
			}
		}
		return sugestoes;
	}

	

}
