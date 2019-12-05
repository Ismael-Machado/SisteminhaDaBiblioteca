package br.ufac.si.testes;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class ExemplarTeste {
	
	public static void main(String[] args) {
		
		ExemplarGerente eg = new ExemplarGerente();
		LivroGerente lg = new LivroGerente();
		
		//Adicionar mais exemplares a um livro existente ============================================
//		Livro livro = lg.buscarLivro(3);
//		eg.incluirExempares(livro, 10);
//		eg.encerrar();
//		lg.encerrar();
		
		//Remover Exemplar
//		Exemplar e = eg.buscarExemplar(livro.getId(), 6);
//		eg.removerExemplar(livro, e);

		
		//Realizando consulta em um exemplar =========================================================
//		System.out.println(eg.buscarExemplar(2, 15).getLivro().getTitulo());
//		eg.encerrar();
	}
	
}
