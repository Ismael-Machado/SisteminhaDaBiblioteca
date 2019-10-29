package br.ufac.si.gerentes;

import javax.persistence.*;

import br.ufac.si.entidades.*;

public class ItensEmprestimoGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public ItensEmprestimoGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Emprestimo

		public void addItensEmprestimo(ItensEmprestimo item) {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		}

		public void encerrar() {
			em.clear();
			emf.close();
		}
}

