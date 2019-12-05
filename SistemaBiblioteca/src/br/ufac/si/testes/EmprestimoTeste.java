package br.ufac.si.testes;

import java.util.*;
import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class EmprestimoTeste {

	public static void main(String [] args) {
		//Acicionar Emprestimo
		
		EmprestimoGerente emprestimoG = new EmprestimoGerente();
		Emprestimo emprestimo;
		ExemplarGerente exemplarG = new ExemplarGerente();
		UsuarioGerente usuG = new UsuarioGerente();
		
		Usuario usuario = usuG.buscarUsuario(1);

		emprestimo = new Emprestimo(usuario, "03/12/2019", "14/12/2019");

		Exemplar e1 = exemplarG.buscarExemplar(10);
		Exemplar e2 = exemplarG.buscarExemplar(25);

		ItensEmprestimo item1 = new ItensEmprestimo(emprestimo, e1); // é necessario colocar emprestimo?
		ItensEmprestimo item2 = new ItensEmprestimo(emprestimo, e2);
		
		emprestimo.addItemEmprestimo(item1);
		emprestimo.addItemEmprestimo(item2);		

		emprestimoG.fazerEmprestimo(emprestimo);


		emprestimoG.encerrar();
		usuG.encerrar();
		exemplarG.encerrar();
		
		
		//Remover Empréstimo ========================================
//		emprestimo = emprestimoG.buscarEmprestimo(1);
//		emprestimoG.removerEmprestimo(emprestimo);
//		emprestimoG.encerrar();
		
		

		//Fazendo consulta Emprestimo=====================================================
//		List<Emprestimo> emprestimos = emprestimoG.buscarTodosPorNomeContendo("");
//		for(Emprestimo e : emprestimos) {
//			System.out.println("Id: "+e.getId());
//		}


	}
}
