package br.ufac.si.controladores;
import java.util.*;
import javax.faces.bean.*;
import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="livroControlador")
@SessionScoped
public class LivroControlador {

	private LivroGerente lg;
	private Livro livro;
	private String chave = "";
	
	public LivroControlador() {
		lg = new LivroGerente();
	}

	//Metodo que enviar para tela de edição de livro
	public String editar(Livro livro) {
		this.livro = livro;
		return "/paginas/livroEdicao.xhtml?faces-redirect=true";
	}
	
	//Metodo que de fato atualiza um livro no banco
	public String atualizar() {
		try {
			lg.alterarLivro(livro);
			ExibirMensagem.sucesso("Livro atualizado com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar atualizar Livro :"+e.getMessage());
		}
		
		return "/paginas/livroGerenciamento.xhtml";
	}
	
	//Metodo que envia para tela de inserção de um livro
	public String incluir() {
		this.livro = new Livro();
		return "/paginas/livroInclusao.xhtml?faces-redirect=true";
	}
	
	//Metodo que de fato insere um livro no banco
	public String adicionar() {
		try {
			ExibirMensagem.sucesso("Livro cadastrado com sucesso");
			this.livro.setQuantExemplares(livro.getQuantidade());
			lg.incluirLivro(livro);
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar cadastrar livro :"+e.getMessage());
		}
		
		return "/paginas/livroGerenciamento.xhtml";
	}
	
	//Metodo que enviar para tela de exclusão de livro
	public String excluir(Livro livro) {
		this.livro = livro;
		return "/paginas/livroExclusao.xhtml?faces-redirect=true";
	}
	
	//Metodo que de fato remove um livro do banco
	public String remover() {
		try {
			lg.removerLivro(livro);
			ExibirMensagem.sucesso("Livro removido com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar remover livro :"+e.getMessage());
		}
		
		return "/paginas/livroGerenciamento.xhtml";
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public List<Livro> getLivros(){
		return lg.buscarTodosPorNomeContendo(chave);
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
}
