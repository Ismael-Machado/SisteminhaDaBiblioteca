package br.ufac.si.testes;


import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class LivroTeste {

	public static void main(String [] args) {
		//Adicionar Livro
		
		LivroGerente livroG = new LivroGerente();		
		
		Livro l2 = new Livro("Introdução a shell", "Kevin", "Editora Saraiva", "1972-09-12", "TC6680", 5);
		
		livroG.incluirLivro(l2);
		livroG.encerrar();
		
//				
		//Fazendo consultas em Livro
		
//		List<Livro> livros = livroG.buscarTodosPorNomeContendo("JAVA");
//		for(Livro l : livros) {
//			System.out.println("Titulo :"+l.getTitulo()+" - Quantidade: "+l.getQuantidade());
//		}
		
		
	}
	
}
