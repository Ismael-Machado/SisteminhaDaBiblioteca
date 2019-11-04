package br.ufac.si.testes;

import java.util.*;
import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

public class EmprestimoTeste {

	public static void main(String [] args) {
		//Acicionar Emprestimo
		EmprestimoGerente emprestimoG = new EmprestimoGerente();
		ExemplarGerente exemplarG = new ExemplarGerente();
		UsuarioGerente usuG = new UsuarioGerente();

		Emprestimo emprestimo = new Emprestimo();

		Usuario usuario = usuG.buscarUsuario(1);

		Exemplar e1 = exemplarG.buscarExemplar(1);
		Exemplar e2 = exemplarG.buscarExemplar(23);

		List<ItensEmprestimo> itens = new ArrayList<>();
		ItensEmprestimo item1 = new ItensEmprestimo(emprestimo, e1);
		ItensEmprestimo item2 = new ItensEmprestimo(emprestimo, e2);
		itens.add(item1);
		itens.add(item2);

		emprestimo.setDataHorario("2019-11-02");
		emprestimo.setDataDevolucao("2019-11-15");
		emprestimo.setStatus("pendente");
		emprestimo.setUsuario(usuario);
		emprestimo.setItensEmprestimo(itens);		

		emprestimoG.fazerEmprestimo(emprestimo);


		emprestimoG.encerrar();
		usuG.encerrar();
		exemplarG.encerrar();
		
		

		//Fazendo consulta Emprestimo
//		List<Emprestimo> emprestimos = emprestimoG.buscarTodosPorHorario();
//		for(Emprestimo e : emprestimos) {
//			System.out.println("Id: "+e.getId()+" - Data Horario: "+e.getDataHorario()+
//					" - Data Devolução: "+e.getDataDevolucao());
//		}


	}
}
