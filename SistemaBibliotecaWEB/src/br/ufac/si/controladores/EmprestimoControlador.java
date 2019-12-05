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
	private ItensEmprestimo item;
	private String chave = "";
	
	//Construtor
	public EmprestimoControlador() {
		exG = new ExemplarGerente();
		emG = new EmprestimoGerente();
		devG = new DevolucaoGerente();

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


	public ItensEmprestimo getItem() {
		return item;
	}


	public void setItem(ItensEmprestimo item) {
		this.item = item;
	}


	//Cria instâncias necessárias para realizar um empréstimo
	public void limpar() {
		emprestimo = new Emprestimo();
		UsuarioControlador.usuario = new Usuario();
		LivroControlador.exemplar = new Exemplar();
		LivroControlador.livro = new Livro();

	}

	//Metodo para carregar emprestimo selecionado da table
	public void carregaEmprestimo(Emprestimo e) {
		this.emprestimo = e;
	}
	
	//Metodo para retornar uma lista de emprestimos do banco
	public List<Emprestimo> getEmprestimos(){
		return emG.buscarTodosPorNomeContendo(chave);
	}

	//Metodo para inserir um emprestimo no banco
	public void adiciona() {
		
//		System.out.println("Usuario----------------> "+UsuarioControlador.usuario.getNome());
//		System.out.println("Livro------------------> " +LivroControlador.livro.getTitulo());
//		System.out.println("Exemplar----------------> "+LivroControlador.exemplar.getExemplar());
		emprestimo.setUsuario(UsuarioControlador.usuario);

		Exemplar ex = LivroControlador.exemplar;
		ex.setDisponivel(0);                   // altera para 0 = emprestado

		item = new ItensEmprestimo(emprestimo, ex);
		emprestimo.addItemEmprestimo(item);

		try {
			emG.fazerEmprestimo(emprestimo);
			exG.alterarExemplar(ex); 
			ExibirMensagem.sucesso("Empréstimo realizado com sucesso");
		} catch (Exception e) {
			ExibirMensagem.error(e.getMessage());
		}
		
		limpar();

	}


	//Metodo que grava no banco a devolução
	public void gravaDevolucao() {

		// Retorna os livros emprestados para disponível
		List<ItensEmprestimo> itens = emprestimo.getItensEmprestimo(); //recebe os itens do emprestimo realizado
		for(ItensEmprestimo item : itens) {
			Exemplar e = item.getExemplar();
			e.setDisponivel(1);
//			System.out.println("Logo depois Diponivel: "+ e.getDisponivel());
			exG.alterarExemplar(e);
		}


		devolucao = new Devolucao();
		devolucao.setDataDevolucao(emprestimo.getPrazoDevolucao());
		devolucao.setDataSaida(emprestimo.getDataRetirada());
		devolucao.setUsuario(emprestimo.getUsuario());


		try {
			emG.removerEmprestimo(emprestimo);
			devG.gravar(devolucao);
			ExibirMensagem.sucesso("Devolução realizada com sucesso");
		} catch (Exception e1) {
			ExibirMensagem.error(e1.getMessage());
		}

	}
	
	

}
