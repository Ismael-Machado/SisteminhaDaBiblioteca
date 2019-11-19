package br.ufac.si.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

@ManagedBean(name="emprestimoControlador")
@SessionScoped
public class EmprestimoControlador {
	private EmprestimoGerente emG;
	private LivroGerente liG;
	private UsuarioGerente usG;
	private ExemplarGerente exG;
	private Emprestimo emprestimo;
	private Usuario usuario;
	public static Livro livro;
	private Exemplar exemplar;
	private List<ItensEmprestimo> lista;
	private ItensEmprestimo item;
	private String chave = "";
	
	//Construtor
	public EmprestimoControlador() {
		emG = new EmprestimoGerente();
		liG = new LivroGerente();
		usG = new UsuarioGerente();
		exG = new ExemplarGerente();
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
		public String incluir() {
			this.usuario = new Usuario();
			this.exemplar = new Exemplar();
			livro = new Livro();
//			this.item = new ItensEmprestimo();
//			this.lista = new ArrayList<>();
			this.emprestimo = new Emprestimo();
			
			return "emprestimoInclusaoPart1";
		}
		
		//Metodo que de fato insere um usuario no banco
		public String adiciona() {
			lista = new ArrayList<>();
			item = new ItensEmprestimo(emprestimo, exemplar);
			lista.add(item);
			
			emprestimo.setUsuario(usuario);
			emprestimo.setItensEmprestimo(lista);
			emG.fazerEmprestimo(emprestimo);
			
			return "emprestimoGerenciamento";
		}
		
		//Metodo para retornar uma lista de emprestimos do banco
		public List<Emprestimo> getEmprestimos(){
			return emG.buscarTodos();
		}
		
		//Metodo que enviar para tela de edição de usuario
//		public String editar(Usuario u) {
//			this.usuario = u;
//			return "usuarioEdicao";
//		}
		
		//Metodo que de fato atualiza um usuario no banco
//		public String atualiza() {
//			ug.alterarUsuario(this.usuario);
//			return "usuarioGerenciamento";
//		}
		
		//Metodo que enviar para tela de exclusão de usuario
//		public String excluir(Usuario u) {
//			this.usuario = u;
//			return "usuarioExclusao";
//		}
		//Metodo que de fato remove um usuario do banco
//		public String remover() {
//			ug.removerUsuario(this.usuario.getId());
//			return "usuarioGerenciamento";
//		}
		
		
		//Metodo para retornar um Usuario
//		public Usuario getUsuario() {
//			return this.usuario;
//		}
	
	
}
