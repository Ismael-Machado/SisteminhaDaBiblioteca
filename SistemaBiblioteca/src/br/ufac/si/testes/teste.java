package br.ufac.si.testes;

import java.util.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class teste {

	public static void main(String [] args) {
		//Adicionar Livro
		
		LivroGerente livroG = new LivroGerente();
//		ExemplarGerente exemplarG = new ExemplarGerente();
//		Livro l1 = new Livro();
//		l1.setTitulo("Programando em JAVA");
//		l1.setAno("1992-11-25");
//		l1.setAutor("Mitnick");
//		l1.setEditora("Editora Saraiva");
//		l1.setLocalizacao("TC6677");
//		l1.setQuantidade(10);
//		l1.setQuantExemplares(l1.getQuantidade());
		
		
		Livro l2 = new Livro("Programando em JAVA", "Mitnick", 
								"Editora Saraiva", "1992-11-25", "TC6677", 10);
		
		
		
		
		livroG.incluirLivro(l2);
		livroG.encerrar();
		
		//Adicionando os 10 exemplares
//		exemplarG.incluirExempares(l1, 10);		
		
//		exemplarG.encerrar();
//		livroG.encerrar();
		
		
		//Adicionar Usuario
//		UsuarioGerente usuG = new UsuarioGerente();
//		Usuario usuario = new Usuario();
//		usuario.setNome("Roniel Martins");
//		usuario.setEmail("roniefx@gmail.com");
//		usuario.setFone("68984052003");
//		usuario.setLogin("ronielmartins54");
//		usuario.setSenha("1234");
//		usuG.incluirUsuario(usuario);
//		
//		usuG.encerrar();

		
//		Acicionar Emprestimo
//		EmprestimoGerente emprestimoG = new EmprestimoGerente();
//
//		Emprestimo emprestimo = new Emprestimo();
//		
//		Usuario usuario = usuG.buscarUsuario(1);
//		
//		Exemplar e1 = exemplarG.buscarExemplar(1);
//		Exemplar e2 = exemplarG.buscarExemplar(23);
//		
//		List<ItensEmprestimo> itens = new ArrayList<>();
//		ItensEmprestimo item1 = new ItensEmprestimo(emprestimo, e1);
//		ItensEmprestimo item2 = new ItensEmprestimo(emprestimo, e2);
//		itens.add(item1);
//		itens.add(item2);
//		
//		emprestimo.setDataHorario("2019-11-02");
//		emprestimo.setDataDevolucao("2019-11-15");
//		emprestimo.setStatus("pendente");
//		emprestimo.setUsuario(usuario);
//		emprestimo.setItensEmprestimo(itens);		
//		
//		emprestimoG.fazerEmprestimo(emprestimo);
//		
//		
//		emprestimoG.encerrar();
//		usuG.encerrar();
//		exemplarG.encerrar();
		
		//Fazendo consultas em Livro
		
//		List<Livro> livros = livroG.buscarTodosPorNomeContendo("JAVA");
//		for(Livro l : livros) {
//			System.out.println("Titulo :"+l.getTitulo()+" - Quantidade: "+l.getQuantidade());
//		}
//		livroG.encerrar();
		
		//Fazendo consulta Emprestimo
//		List<Emprestimo> emprestimos = emprestimoG.buscarTodosPorHorario();
//		for(Emprestimo e : emprestimos) {
//			System.out.println("Id: "+e.getId()+" - Data Horario: "+e.getDataHorario()+" - Data Devolução: "+e.getDataDevolucao());
//		}
		
		
	}
	
}
