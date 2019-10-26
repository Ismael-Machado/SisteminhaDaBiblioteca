package br.ufac.si.gerentes;

import javax.persistence.*;

import br.ufac.si.entidades.Exemplar;

public class ExemplarGerente {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ExemplarGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Exemplares

		public void incluirExempar(Exemplar exemplar) {
			em.getTransaction().begin();
			em.persist(exemplar);
			em.getTransaction().commit();
		}

		public long removerExemplar(long exemplar) {
			Exemplar e = em.find(Exemplar.class, exemplar);
			long exem = e.getExemplar();

			em.getTransaction().begin();
			em.remove(e);
			em.getTransaction().commit();

			return exem;
		}

		public void alterarExemplar(Exemplar exemplar) {
			em.getTransaction().begin();
			em.merge(exemplar);
			em.getTransaction().commit();
		}

		public Exemplar buscarExemplar(long exemplar) {
			return em.find(Exemplar.class, exemplar);
		}

		public void encerrar() {
			em.clear();
			emf.close();
		}
}
