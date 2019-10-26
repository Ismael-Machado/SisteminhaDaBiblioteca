package br.ufac.si.gerentes;

import javax.persistence.*;

import br.ufac.si.entidades.*;

public class EmprestimoGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public EmprestimoGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Emprestimo

		public void addEmprestimo(Emprestimo emprestimo) {
			em.getTransaction().begin();
			em.persist(emprestimo);
			em.getTransaction().commit();
		}

		public void encerrar() {
			em.clear();
			emf.close();
		}
}

