package br.ufac.si.testes;

import java.util.List;

import br.ufac.si.entidades.Exemplar;
import br.ufac.si.entidades.Livro;
import br.ufac.si.gerentes.*;

public class LivroTeste {

	public static void main(String [] args) {
		//Adicionar Livro
		
		LivroGerente livroG = new LivroGerente();	
		Livro livro = livroG.buscarLivro(1);
		List<Exemplar> exemplares = livroG.buscarExemplaresDisponivel(livro);
		

//		
//		Livro l2 = new Livro("Introdução a shell script", "Kevin", "Editora Saraiva", "1972-09-12", "TC6680", 2);
//		
//		livroG.incluirLivro(l2);
		livroG.encerrar();
		
		
//				
		//Fazendo consultas em Livro
		
//		List<Livro> livros = livroG.buscarTodosPorNomeContendo("");
//		for(Livro l : livros) {
//			System.out.println("Titulo :"+l.getTitulo()+" - Quantidade: "+l.getQuantidade());
//		}
		
		
		//Fazendo consulta de um exemplar
//		ExemplarGerente eg = new ExemplarGerente();
//		System.out.println(eg.buscarExemplar(2, 2).getLivro().getTitulo());
//		eg.encerrar();
		
	}
	
}
