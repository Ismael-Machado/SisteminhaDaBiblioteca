package br.ufac.si.controladores;
import java.util.*;
import javax.faces.bean.*;
import javax.xml.stream.util.EventReaderDelegate;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="livroControlador")
@SessionScoped
public class LivroControlador {

	private LivroGerente lg;
	public static Livro livro = new Livro();
	public List<Livro> livros = new ArrayList<>();
//	public List<Exemplar> exemplares = new ArrayList<>();
	public static Exemplar exemplar;
	private int quant; // quantidade de exemplares que serão adicionados
	private String chave = "";
	
	public LivroControlador() {
		lg = new LivroGerente();
	}

	//Metodo set e get
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro l) {
		livro = l;
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar e) {
		exemplar = e;
	}
	
	
	public void setQuant(int quant) {
		this.quant = quant;
	}
	
	public int getQuant() {
		return quant;
	}

	public List<Livro> getLivros(){
		this.livros = lg.buscarTodosPorTituloContendo2(chave);
		return this.livros;
	}
	
	
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	//Cria uma instancia nova sempre que inserir livros
	public void incluir() {
		this.livro = new Livro();
//		return "/paginas/livroInclusao.xhtml?faces-redirect=true";
	}

	////Metodo para carregar livro selecionado da table
	public void carregaLivro(Livro livro) {
		this.livro = livro;
	}
	
	//Metodo que retorna quantidade disponivel de livros
	public int disponivel(Livro l) {
		List<Exemplar> exemplares = lg.buscarExemplaresDisponivel(l);
		return exemplares.size();
	}
	
	//Adiciona exemplares a livro já existente
	public void adicionarExemplar() {
		ExemplarGerente eg = new ExemplarGerente();
		try {
			eg.incluirExempares(livro, quant);
			ExibirMensagem.sucesso("Adicionado com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar adicionar: "+e.getMessage());
		}
		
	}
	
	//Remove exemplar de um Livro
	public void deleteExemplar() {
		ExemplarGerente eg = new ExemplarGerente();
		
		Livro l = lg.buscarLivro(livro.getId());
		Exemplar ex = eg.buscarExemplar(exemplar.getId());
		
		try {
//			System.out.println("Livro ID----------------> " + livro.getId());
//			System.out.println("Exemplar ID----------------> " + exemplar.getExemplar());
			eg.removerExemplar(l, ex);
			ExibirMensagem.sucesso("Removido com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar remover: "+e.getMessage());
		}
		
	}

	//Metodo que retorna todos os exemplares de um livro
	public List<Exemplar> getExemplares(){
		return lg.buscarTodosExemplares(livro);
	}
		


	//Metodo para atualizar um livro no banco
	public void atualizar() {
		try {
			lg.alterarLivro(livro);
			ExibirMensagem.sucesso("Livro atualizado com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar atualizar Livro :"+e.getMessage());
		}
	
	}
	
	
	//Metodo para adicionar um livro no banco
	public void adicionar() {
		try {
			this.livro.setQuantExemplares(livro.getQuantidade());
			lg.incluirLivro(livro);
			ExibirMensagem.sucesso("Livro cadastrado com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar cadastrar livro :"+e.getMessage());
		}
	
	}
	
	
	//Metodo para remover um livro do banco
	public void remover() {
		try {
			lg.removerLivro(livro);
			ExibirMensagem.sucesso("Livro removido com sucesso!");
		} catch (Exception e) {
			ExibirMensagem.error("Erro ao tentar remover livro :"+e.getMessage());
		}
	
	}
	
	//AutoComplete : Lista de Livros
	public List<Livro> completaTitulo(String query) {
		this.livros = lg.buscarTodos();

		List<Livro> sugestoes = new ArrayList<Livro>();
		for (Livro l : this.livros) {
			if (l.getTitulo().startsWith(query)) {
				sugestoes.add(l);
			}
		}
		return sugestoes;
	}
	
	
	
}
