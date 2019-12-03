package br.ufac.si.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="emprestimoControlador")
@SessionScoped
public class EmprestimoControlador {
	private EmprestimoGerente emG;
	private ExemplarGerente exG;
	private DevolucaoGerente devG;
	private Emprestimo emprestimo;
	private Devolucao devolucao;
	private Usuario usuario;
	public static Livro livro;
	private Exemplar exemplar;
	private List<ItensEmprestimo> lista;
	private ItensEmprestimo item;
	private String chave = "";
	
	//Construtor
	public EmprestimoControlador() {
		exG = new ExemplarGerente();
		emG = new EmprestimoGerente();
		devG = new DevolucaoGerente();
		emprestimo = new Emprestimo();
	}

	
	//Metodos Set e Get
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	
	public Emprestimo getEmprestimo() {
		return emprestimo;
	}


	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Exemplar getExemplar() {
		return exemplar;
	}


	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}


	public List<ItensEmprestimo> getLista() {
		return lista;
	}


	public void setLista(List<ItensEmprestimo> lista) {
		this.lista = lista;
	}


	public ItensEmprestimo getItem() {
		return item;
	}


	public void setItem(ItensEmprestimo item) {
		this.item = item;
	}


	public Livro getLivro() {
		return livro;
	}


	public void setLivro(Livro livro) {
		this.livro = livro;
	}


		//Metodo que envia para tela de inserção de um emprestimo
		public void incluir() {
			this.usuario = new Usuario();
			this.exemplar = new Exemplar();
			livro = new Livro();
			this.emprestimo = new Emprestimo();
		
//			return "/paginas/emprestimoInclusaoPart1";
		}
		
		//Metodo que de fato insere um usuario no banco
		public void adiciona() {
			lista = new ArrayList<>();
			exemplar.setDisponivel(1);
			exG.alterarExemplar(exemplar); // altera para 1 = indisponivel
			
			item = new ItensEmprestimo(emprestimo, exemplar);
			lista.add(item);
			
			emprestimo.setUsuario(usuario);
			emprestimo.setItensEmprestimo(lista);
			
			try {
				emG.fazerEmprestimo(emprestimo);
				ExibirMensagem.sucesso("Empréstimo realizado com sucesso");
			} catch (Exception e) {
				ExibirMensagem.error(e.getMessage());
			}
					
//			return "emprestimoGerenciamento";
		}
		
		//Metodo para retornar uma lista de emprestimos do banco
		public List<Emprestimo> getEmprestimos(){
			return emG.buscarTodosPorNomeContendo(chave);
		}
		
		//Metodo que retorna da tabela emprestimo o atual que foi clicado
		public void passa(Emprestimo e) {
			this.emprestimo = e;
//			return "/paginas/emprestimoDevolver.xhtml";
		}
		
		//Metodo que grava no banco a devolução
		public void gravaDevolucao() {
			
			System.out.println("Titulo: "+emprestimo.getItensEmprestimo().get(0).getExemplar().getLivro().getTitulo());
			
			exemplar = emprestimo.getItensEmprestimo().get(0).getExemplar(); //Pega a instancia do exemplar que foi realizado
			exemplar.setDisponivel(0);										//realizado o emprestimo e retorna pra disponivel
			exG.alterarExemplar(exemplar);
			
			devolucao = new Devolucao();
			devolucao.setDataDevolucao(emprestimo.getDataDevolucao());
			devolucao.setDataSaida(emprestimo.getDataHorario());
			devolucao.setExemplar((int)exemplar.getExemplar());
			devolucao.setLivro(exemplar.getLivro().getTitulo());
			devolucao.setUsuario(emprestimo.getUsuario());
			
						
			try {
				emG.removerEmprestimo(emprestimo);
				devG.gravar(devolucao);
				ExibirMensagem.sucesso("Devolução realizada com sucesso");
			} catch (Exception e1) {
				ExibirMensagem.error(e1.getMessage());
			}
			
			//					return "/paginas/emprestimoDevolver.xhtml";
		}
	
	
}
