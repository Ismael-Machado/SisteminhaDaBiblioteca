package br.ufac.si.testes;

import java.util.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class teste {

	public static void main(String [] args) {
		//Adicionar Livro
		
//		LivroGerente livroG = new LivroGerente();
//		Livro l1 = new Livro();
//		l1.setTitulo("Como programar em Java em 1 semana");
//		l1.setAno("1986-09-23");
//		l1.setAutor("Limeira");
//		l1.setEditora("Editora Saraiva");
//		l1.setClassificacao("JAVA");
//		
//		
//		livroG.incluirLivro(l1);
//		
//		livroG.encerrar();
		
		//Adicionando exemplares
		ExemplarGerente exemplarG = new ExemplarGerente();
//		Exemplar e1;
//		e1 = new Exemplar(52, l1);
//		exemplarG.incluirExempar(e1);
//		exemplarG.encerrar();
		
		
		//Adicionar Usuario
		UsuarioGerente usuG = new UsuarioGerente();
//		Usuario usuario = new Usuario();
//		usuario.setNome("Roniel Martins");
//		usuario.setEmail("roniefx@gmail.com");
//		usuario.setFone("68984052003");
//		usuario.setLogin("ronielmartins54");
//		usuario.setSenha("1234");
//		usuG.incluirUsuario(usuario);
//		usuG.encerrar();
		
		
		//Acicionar Emprestimo
		EmprestimoGerente emprestimoG = new EmprestimoGerente();
		ItensEmprestimoGerente ipg = new ItensEmprestimoGerente();
		Emprestimo emprestimo;
		ItensEmprestimo itens;
		Usuario usuario;
		
		usuario = usuG.buscarUsuario(1);
		Exemplar e1 = exemplarG.buscarExemplar(27);
		
		emprestimo = new Emprestimo("2019-10-22", "2019-11-02", "pendente", usuario);
		
		itens = new ItensEmprestimo(emprestimo, e1);
		
		ipg.addItensEmprestimo(itens);
		//emprestimoG.addEmprestimo(emprestimo);
		

		emprestimoG.encerrar();
		ipg.encerrar();;
		usuG.encerrar();
		exemplarG.encerrar();
	}
}
